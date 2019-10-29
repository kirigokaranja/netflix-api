package com.aws.netflix.repositories;

import com.aws.netflix.models.Category;
import com.aws.netflix.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String name);

    @Query(value = "SELECT * FROM movies JOIN movie_category ON movies.id = movie_category.movie_id " +
            "JOIN categories ON categories.id = movie_category.category_id " +
            "WHERE categories.id = :categoryid and movies.movie_type = :movietype" , nativeQuery = true)
    Optional<Movie> movieByIdAndType(@Param("categoryid") Long id,
                                     @Param("movietype") String type);

    @Query(value = "SELECT * FROM movies JOIN movie_category ON movies.id = movie_category.movie_id " +
            "JOIN categories ON categories.id = movie_category.category_id " +
            "WHERE categories.id = :categoryid" , nativeQuery = true)
    Optional<Movie> movieById(@Param("categoryid") Long id);
}
