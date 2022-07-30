package com.deloitte.digital.librarymanagementsystem.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class BookDTO {

    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String author;

    @NotNull
    private String publisher;

    private String language;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate publishedDate;

    private Integer pages;

    private Integer availableCopies;

    @NotNull
    private Integer nrCopies;

    @NotNull
    private Integer borrowDays;

    @NotNull
    private String isbn;
}
