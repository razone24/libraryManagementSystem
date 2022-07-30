package com.deloitte.digital.librarymanagementsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CategoryDTO {

    private Long id;

    @NotNull
    private String name;

    private String description;

    private LocalDate createdAt;

    private Set<BookDTO> books = new HashSet<>();
}
