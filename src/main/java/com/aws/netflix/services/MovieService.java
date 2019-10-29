package com.aws.netflix.services;

import com.aws.netflix.models.Movie;
import com.aws.netflix.models.MovieType;
import com.aws.netflix.repositories.MovieRepository;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    MovieRepository movieRepository = null;

    List<Movie> findAll();

    public Movie create(Movie Movie);

    Movie findByID(Long id);

    List<Movie> findByCategoryAndType(Long id, MovieType type);

    public Movie update(Long id, Long userid, Movie Movie);

    public String delete(Long id, Long userid);

    Optional<Movie> findByMovieNameAndReleaseYear(String name, int year);

}
