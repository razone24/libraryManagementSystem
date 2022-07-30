package com.deloitte.digital.librarymanagementsystem.service;

import com.deloitte.digital.librarymanagementsystem.model.entity.Category;
import com.deloitte.digital.librarymanagementsystem.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> get(Long id) {
        return categoryRepository.findById(id);
    }

    public Category add(Category category) {
        category.setCreatedAt(LocalDate.now());
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }

}
