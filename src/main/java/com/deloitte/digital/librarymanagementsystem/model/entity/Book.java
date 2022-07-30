package com.deloitte.digital.librarymanagementsystem.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    private String title;

    @NotNull
    private String author;

    @NotNull
    private String publisher;

    @Column(length = 50)
    private String language;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    private Integer pages;

    @Column(name = "available_copies")
    private Integer availableCopies;

    @Column(name = "nr_copies")
    @NotNull
    private Integer nrCopies;

    @NotNull
    @Column(name = "borrow_days")
    private Integer borrowDays;

    @NotNull
    @Column(length = 13, unique = true)
    private String isbn;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Borrow> borrows = new HashSet<>();

    public void borrowBook() {
        this.availableCopies -= 1;
    }

    public void returnBook() {
        this.availableCopies += 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn);
    }
}
