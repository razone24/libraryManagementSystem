package com.deloitte.digital.librarymanagementsystem.repository;

import com.deloitte.digital.librarymanagementsystem.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //TODO: find the user with a specific email
}
