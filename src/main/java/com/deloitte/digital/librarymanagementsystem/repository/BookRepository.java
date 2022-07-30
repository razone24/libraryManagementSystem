package com.deloitte.digital.librarymanagementsystem.repository;

import com.deloitte.digital.librarymanagementsystem.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //TODO: Simple Derived Query (find books by author)

    //TODO: Derived Query with multiple clauses (find books by author and published date before a given date)

    //TODO: Derived Query with operators for String (find books by title starting with a given string)

    //TODO: Derived Query with comparison condition (find available books)

    //TODO: Derived Query with sorted results (find books by category ordered by the title)

    //TODO: JPQL Query (find books by language)

    //TODO: JPQL Query (find all available books by author)

    //TODO: Native Query (find all books by publisher)

    //TODO: Native query (find all books borrowed by a user)

}

