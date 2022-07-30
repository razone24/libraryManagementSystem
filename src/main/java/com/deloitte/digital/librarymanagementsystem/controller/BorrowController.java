package com.deloitte.digital.librarymanagementsystem.controller;

import com.deloitte.digital.librarymanagementsystem.model.dto.BorrowDTO;
import com.deloitte.digital.librarymanagementsystem.model.entity.Borrow;
import com.deloitte.digital.librarymanagementsystem.service.BorrowService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static com.deloitte.digital.librarymanagementsystem.exception.Constants.BORROW_NOT_FOUND;

@RestController
@RequestMapping("/borrows")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<BorrowDTO> borrowBook(@RequestParam("userId") Long userId, @RequestParam("bookId") Long bookId) {
        Borrow savedBorrow = borrowService.add(userId, bookId);

        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(savedBorrow, BorrowDTO.class));
    }

    @PutMapping("/{borrowId}")
    public ResponseEntity returnBook(@PathVariable("borrowId") Long borrowId) {
        Borrow foundBorrow =  borrowService.get(borrowId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, BORROW_NOT_FOUND));
        borrowService.returnBook(foundBorrow);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<BorrowDTO>> getAll() {
        return ResponseEntity.ok(borrowService.getAll()
                .stream()
                .map(borrow -> modelMapper.map(borrow, BorrowDTO.class))
                .collect(Collectors.toList()));
    }

}
