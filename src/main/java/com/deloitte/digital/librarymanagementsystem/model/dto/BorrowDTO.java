package com.deloitte.digital.librarymanagementsystem.model.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class BorrowDTO {

    private Long id;

    private BookDTO book;

    private UserDTO user;

    @NotNull
    private LocalDate borrowDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
