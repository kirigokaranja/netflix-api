package com.aws.netflix.repositories;

import com.aws.netflix.models.Category;
import com.aws.netflix.models.Movie;
import com.aws.netflix.models.MovieType;
import com.aws.netflix.models.MovieVerified;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByCategoryAndMovieType(Category categoryId, MovieType type);
    List<Movie> findByCategoryAndMovieTypeAndVerified(Category categoryId, MovieType type, MovieVerified verified);
    Optional<Movie> findByMovieNameAndReleaseYear(String moviename, int year);
    List<Movie> findByMovieTypeAndVerified(MovieType type, MovieVerified verified);
    Optional<Movie> findByUser_Id(Long id);

}
