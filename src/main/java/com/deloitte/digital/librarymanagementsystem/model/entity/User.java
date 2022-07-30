package com.deloitte.digital.librarymanagementsystem.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name", length = 30)
    private String firstName;

    @NotNull
    @Column(name = "last_name", length = 30)
    private String lastName;

    @NotNull
    private String title;

    @NotNull
    @Email
    @Size(min = 5, max = 254)
    @Column(unique = true)
    private String email;

    @Column(name = "mobile_phone", length = 15)
    private String mobilePhone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Borrow> borrows = new HashSet<>();
}
