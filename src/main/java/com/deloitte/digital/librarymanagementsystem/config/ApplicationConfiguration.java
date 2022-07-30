package com.deloitte.digital.librarymanagementsystem.config;

import com.deloitte.digital.librarymanagementsystem.model.dto.BookDTO;
import com.deloitte.digital.librarymanagementsystem.model.dto.BorrowDTO;
import com.deloitte.digital.librarymanagementsystem.model.dto.CategoryDTO;
import com.deloitte.digital.librarymanagementsystem.model.dto.UserDTO;
import com.deloitte.digital.librarymanagementsystem.model.entity.Book;
import com.deloitte.digital.librarymanagementsystem.model.entity.Borrow;
import com.deloitte.digital.librarymanagementsystem.model.entity.Category;
import com.deloitte.digital.librarymanagementsystem.model.entity.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;


@Configuration
public class ApplicationConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<BookDTO, Book> bookConverter = context -> {
            BookDTO s = context.getSource();
            Book d = new Book();
            d.setId(s.getId());
            d.setTitle(s.getTitle());
            d.setAuthor(s.getAuthor());
            d.setPublisher(s.getPublisher());
            d.setLanguage(s.getLanguage());
            d.setPublishedDate(s.getPublishedDate());
            d.setPages(s.getPages());
            d.setNrCopies(s.getNrCopies());
            d.setAvailableCopies(s.getAvailableCopies());
            d.setBorrowDays(s.getBorrowDays());
            d.setIsbn(s.getIsbn());
            return d;
        };


        Converter<CategoryDTO, Category> categoryConverter = context -> {
            CategoryDTO s = context.getSource();
            Category d = new Category();
            d.setId(s.getId());
            d.setName(s.getName());
            d.setDescription(s.getDescription());
            d.setCreatedAt(s.getCreatedAt());
            d.setBooks(s.getBooks().stream()
                    .map(x -> modelMapper.map(x , Book.class))
                    .collect(Collectors.toSet()));
            return d;
        };

        Converter<UserDTO, User> userConverter = context -> {
            UserDTO s = context.getSource();
            User d = new User();
            d.setId(s.getId());
            d.setFirstName(s.getFirstName());
            d.setLastName(s.getLastName());
            d.setEmail(s.getEmail());
            d.setTitle(s.getTitle());
            d.setMobilePhone(s.getMobilePhone());
            return d;
        };

        Converter<BorrowDTO, Borrow> borrowConverter = context -> {
            BorrowDTO s = context.getSource();
            Borrow d = new Borrow();
            d.setId(s.getId());
            d.setBook(modelMapper.map(s.getBook(), Book.class));
            d.setUser(modelMapper.map(s.getUser(), User.class));
            d.setBorrowDate(s.getBorrowDate());
            d.setDueDate(s.getDueDate());
            d.setReturnDate(s.getReturnDate());
            return d;
        };


        modelMapper.addConverter(bookConverter);
        modelMapper.addConverter(categoryConverter);
        modelMapper.addConverter(userConverter);
        modelMapper.addConverter(borrowConverter);
        return modelMapper;
    }
}
