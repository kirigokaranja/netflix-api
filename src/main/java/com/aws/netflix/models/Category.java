package com.aws.netflix.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false)
    private Long id;

    @Column(name = "category_name", unique = true)
    @NotNull(message = "Category name is required")
    private String categoryName;

    @JsonIgnore
   @ManyToMany(mappedBy = "category")
    private Set<Movie> movies;

    public Category() {
    }


    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(@NotNull(message = "Category name is required") String categoryName, Set<Movie> movies) {
        this.categoryName = categoryName;
        this.movies = movies;
    }

    public Category(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", movies=" + movies +
                '}';
    }
}
