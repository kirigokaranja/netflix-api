package com.aws.netflix.controllers;

import com.aws.netflix.models.Movie;
import com.aws.netflix.models.MovieType;
import com.aws.netflix.services.CategoryService;
import com.aws.netflix.services.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "movies")
public class MovieController {
    private final MovieService movieService;
    private final CategoryService categoryService;

    public MovieController(MovieService movieService, CategoryService categoryService) {
        this.movieService = movieService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping(value = "{id}")
    Movie findById(@PathVariable Long id) {
        return movieService.findByID(id);
    }

    @GetMapping(value = "category/{id}")
    List<Movie> findByCategoryId(@PathVariable Long id, @RequestParam(value = "type")MovieType type){
        return movieService.findByCategoryAndType(id, type);
    }




}