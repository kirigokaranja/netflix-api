package com.aws.netflix.services;

import com.aws.netflix.models.Category;
import com.aws.netflix.models.Movie;
import com.aws.netflix.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    CategoryRepository categoryRepository = null;

    List<Category> findAll();

    public Category create(Category Category);

    Category findByID(Long id);

    Optional<Movie> findMovieinCategories(Long id);

    Optional<Movie> findMovieinCategories(Long id, String type);

    Optional<Category> findByCategoryName(String name);

    public Category update(Long id, Category Category);

    Optional<Category> findCategoryIds(List<Category> category);

    public void delete(Long id);

}
