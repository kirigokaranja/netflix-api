package com.aws.netflix.services;

import com.aws.netflix.exceptions.NotFoundException;
import com.aws.netflix.models.Category;
import com.aws.netflix.models.Movie;
import com.aws.netflix.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    public CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //get all
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    //create
    @Override
    public Category create(Category Category) {
        Optional<Category> exist = findByCategoryName(Category.getCategoryName());
        return exist.orElseGet(() -> categoryRepository.save(Category));
    }

    //find by id
    @Override
    public Category findByID(Long id) {
        return categoryRepository.findById(id).orElseThrow(()->
                new NotFoundException("Category id "+ id +" not found"));
    }

    //find movies by id
    @Override
    public Optional<Movie> findMovieinCategories(Long id) {
        return categoryRepository.movieById(id);
    }

    //find movies by id and type
    @Override
    public Optional<Movie> findMovieinCategories(Long id, String type) {
        return categoryRepository.movieByIdAndType(id, type);
    }

    // find movies by category name
    @Override
    public Optional<Category> findByCategoryName(String name) {
        return categoryRepository.findByCategoryName(name);
    }

    //update category
    @Override
    public Category update(Long id, Category Category) {
        Category catg = findByID(id);

        if (catg.getId().equals(id)){
            catg.setCategoryName(Category.getCategoryName());
            return categoryRepository.save(catg);
        }
        return null;

    }

    @Override
    public Optional<Category> findCategoryIds(List<Category> category) {
        return Optional.empty();
    }

    //delete category
    @Override
    public void delete(Long id) {

        categoryRepository.deleteById(id);

    }
}
