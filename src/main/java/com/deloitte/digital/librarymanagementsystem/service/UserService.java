package com.deloitte.digital.librarymanagementsystem.service;

import com.deloitte.digital.librarymanagementsystem.model.entity.User;
import com.deloitte.digital.librarymanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User add(User user) {
        return userRepository.save(user);
    }
}
