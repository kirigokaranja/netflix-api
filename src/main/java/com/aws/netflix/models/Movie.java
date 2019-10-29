package com.aws.netflix.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Year;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false)
    private Long id;

    @Column(name = "movie_name")
    @NotNull(message = "Movie name is required")
    private String movieName;

    @Column(name = "movie_type")
    private MovieType movieType;

    @Column(name = "movie_verified")
    private MovieVerified verified;

//    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "movie_category",
    joinColumns = @JoinColumn(name = "movie_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> category;

    @Column(name="release_year")
    @NotNull(message = "Release year cannot be blank")
    private int releaseYear;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
        private User user;

    public Movie() {
    }

    public Movie(@NotNull(message = "Movie name is required") String movieName, @NotNull(message = "Movie Type is required") MovieType movieType, MovieVerified verified, List<Category> category, @NotNull(message = "Release year cannot be blank") int releaseYear, User user) {
        this.movieName = movieName;
        this.movieType = movieType;
        this.verified = verified;
        this.category = category;
        this.releaseYear = releaseYear;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public MovieVerified getVerified() {
        return verified;
    }

    public void setVerified(MovieVerified verified) {
        this.verified = verified;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", movieType=" + movieType +
                ", verified=" + verified +
                ", category=" + category +
                ", releaseYear=" + releaseYear +
                ", user=" + user +
                '}';
    }
}
