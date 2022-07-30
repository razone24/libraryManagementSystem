package com.deloitte.digital.librarymanagementsystem.controller;

import com.deloitte.digital.librarymanagementsystem.model.dto.BookDTO;
import com.deloitte.digital.librarymanagementsystem.model.entity.Book;
import com.deloitte.digital.librarymanagementsystem.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.deloitte.digital.librarymanagementsystem.exception.Constants.BOOK_NOT_FOUND;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAll() {
        //TODO complete with query params for filtering by different fields
        return ResponseEntity.ok(bookService.getAll()
                .stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/available")
    public ResponseEntity<List<BookDTO>> getAllAvailableBooks() {
        return ResponseEntity.ok(bookService.getAvailableBooks()
                .stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<List<BookDTO>> getAllBooksByCategory(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(bookService.getAllBooksByCategory(categoryId)
                .stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> get(@PathVariable("id") Long id) {
        Book book =  bookService.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, BOOK_NOT_FOUND));

        return ResponseEntity.ok(modelMapper.map(book, BookDTO.class));
    }

    @GetMapping("/borrowedBy/{userId}")
    public ResponseEntity<List<BookDTO>> getAllBooksBorrowedByUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(bookService.getAllBooksBorrowedByUser(userId)
                .stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList()));
    }

    @PostMapping("/{categoryId}")
    public ResponseEntity<BookDTO> add(@PathVariable("categoryId") Long categoryId, @RequestBody @Valid BookDTO bookDTO){
        Book book = modelMapper.map(bookDTO, Book.class);

        Book savedBook = bookService.add(categoryId, book);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(savedBook, BookDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable("id") Long id, @RequestBody @Valid BookDTO bookDTO){
        Book foundBook = bookService.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, BOOK_NOT_FOUND));

        bookDTO.setId(foundBook.getId());
        Book updatedBook = bookService.update(modelMapper.map(bookDTO, Book.class), foundBook.getCategory());
        return ResponseEntity.ok(modelMapper.map(updatedBook, BookDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        Book foundBook = bookService.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, BOOK_NOT_FOUND));

        bookService.delete(foundBook);
        return ResponseEntity.noContent().build();
    }
}
