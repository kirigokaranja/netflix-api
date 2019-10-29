package com.aws.netflix.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                ", userNationalId=" + userNationalId +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false)
    private Long id;

    @NotNull(message = "User name is required")
    @Column(name = "user_name")
    private String userName;


    @Column(name = "user_type")
    private UserType userType;

    @NotNull(message = "User national id is required")
    @Column(name = "user_national_id", unique = true)
    private int userNationalId;

//    @OneToMany(mappedBy = "users")
//    private Set<Movie> movies;

    public User() {
    }

    public User(@NotNull(message = "User name is required") String userName, @NotNull(message = "User type is required") UserType userType, @NotNull(message = "User national id is required") int userNationalId) {
        this.userName = userName;
        this.userType = userType;
        this.userNationalId = userNationalId;
    }

    public int getUserNationalId() {
        return userNationalId;
    }

    public void setUserNationalId(int userNationalId) {
        this.userNationalId = userNationalId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
//
//    public Set<Movie> getMovies() {
//        return movies;
//    }
//
//    public void setMovies(Set<Movie> movies) {
//        this.movies = movies;
//    }

}
