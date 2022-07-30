package com.deloitte.digital.librarymanagementsystem.controller;

import com.deloitte.digital.librarymanagementsystem.model.dto.CategoryDTO;
import com.deloitte.digital.librarymanagementsystem.model.entity.Category;
import com.deloitte.digital.librarymanagementsystem.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static com.deloitte.digital.librarymanagementsystem.exception.Constants.CATEGORY_NOT_FOUND;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok(categoryService.getAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> get(@PathVariable("id") Long id) {
        Category category =  categoryService.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CATEGORY_NOT_FOUND));

        return ResponseEntity.ok(modelMapper.map(category, CategoryDTO.class));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> add(@RequestBody CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);

        Category savedCategory = categoryService.add(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(savedCategory, CategoryDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable("id") Long id, @RequestBody CategoryDTO categoryDTO) {
        Category foundCategory = categoryService.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CATEGORY_NOT_FOUND));

        categoryDTO.setId(foundCategory.getId());
        Category updatedCategory = categoryService.update(modelMapper.map(categoryDTO, Category.class));
        return ResponseEntity.ok(modelMapper.map(updatedCategory, CategoryDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Category category = categoryService.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CATEGORY_NOT_FOUND));

        categoryService.delete(category);
        return ResponseEntity.noContent().build();
    }
}
