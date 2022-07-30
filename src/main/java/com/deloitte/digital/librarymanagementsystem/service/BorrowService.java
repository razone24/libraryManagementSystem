package com.deloitte.digital.librarymanagementsystem.service;

import com.deloitte.digital.librarymanagementsystem.exception.BookAlreadyReturnedException;
import com.deloitte.digital.librarymanagementsystem.exception.BookOutOfStockException;
import com.deloitte.digital.librarymanagementsystem.model.entity.Book;
import com.deloitte.digital.librarymanagementsystem.model.entity.Borrow;
import com.deloitte.digital.librarymanagementsystem.model.entity.User;
import com.deloitte.digital.librarymanagementsystem.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.deloitte.digital.librarymanagementsystem.exception.Constants.BOOK_NOT_FOUND;
import static com.deloitte.digital.librarymanagementsystem.exception.Constants.USER_NOT_FOUND;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    public Borrow add(Long userId, Long bookId) {
        Borrow borrow = new Borrow();
        Book book = bookService.get(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, BOOK_NOT_FOUND));
        if(book.getAvailableCopies() == 0) {
            throw new BookOutOfStockException();
        }
        book.borrowBook();
        bookService.update(book, book.getCategory());
        borrow.setBook(book);

        User user = userService.get(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, USER_NOT_FOUND));
        borrow.setUser(user);

        LocalDate borrowDate = LocalDate.now();
        borrow.setBorrowDate(borrowDate);

        LocalDate dueDate = borrowDate.plusDays(book.getBorrowDays());
        borrow.setDueDate(dueDate);
        return borrowRepository.save(borrow);
    }

    public Optional<Borrow> get(Long borrowId) {
        return borrowRepository.findById(borrowId);
    }

    public void returnBook(Borrow borrow) {
        if(borrow.getReturnDate() != null) {
            throw new BookAlreadyReturnedException();
        }
        LocalDate returnDate = LocalDate.now();
        borrow.setReturnDate(returnDate);

        Book book = borrow.getBook();
        book.returnBook();
        bookService.update(book, book.getCategory());

        borrowRepository.save(borrow);
    }

    public List<Borrow> getAll() {
        return borrowRepository.findAll();
    }
}
