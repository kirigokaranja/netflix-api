package com.aws.netflix.repositories;

import com.aws.netflix.models.Category;
import com.aws.netflix.models.Movie;
import com.aws.netflix.models.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByCategoryAndMovieType(Category categoryId, MovieType type);
    Optional<Movie> findByMovieNameAndReleaseYear(String moviename, int year);

}
