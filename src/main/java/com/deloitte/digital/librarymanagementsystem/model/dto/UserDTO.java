package com.deloitte.digital.librarymanagementsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String title;

    @NotNull
    @Email
    @Size(min = 5, max = 254)
    private String email;

    private String mobilePhone;
}
