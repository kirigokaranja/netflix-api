package com.aws.netflix.controllers;

import com.aws.netflix.models.Category;
import com.aws.netflix.models.Movie;
import com.aws.netflix.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //find all
    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    //find by id
    @GetMapping(value = "{id}")
    Category findById(@PathVariable Long id){
        return categoryService.findByID(id);
    }

    //create
    @PostMapping
    Category create(@Valid @RequestBody Category category){
        return categoryService.create(category);
    }

    //delete
    @DeleteMapping(value = "{id}")
    void delete(@PathVariable Long id){
        categoryService.delete(id);
    }

    //update
    @PatchMapping(value = "{id}")
    Category update(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.update(id, category);
    }
}