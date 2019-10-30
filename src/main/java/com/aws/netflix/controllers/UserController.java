package com.aws.netflix.controllers;

import com.aws.netflix.models.*;
import com.aws.netflix.services.MovieService;
import com.aws.netflix.services.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "users")

public class UserController {

    private final UserService userService;
    private final MovieService movieService;

    public UserController(UserService userService, MovieService movieService) {
        this.userService = userService;
        this.movieService = movieService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "{id}")
    User findById(@PathVariable Long id) {
        return userService.findByID(id);
    }

    //create a user
    @PostMapping
    User create(@Valid @RequestBody User user)
    {
        user.setUserType(UserType.user);
        return userService.create(user);
    }

    //create a movie
    @PostMapping(value = "{id}/movie")
    Movie create(@Valid @RequestBody Movie movie, @PathVariable Long id) {
        User user = findById(id);
        movie.setUser(user);

        if (user.getUserType() == UserType.admin){
            movie.setMovieType(MovieType.original);
            movie.setVerified(MovieVerified.verified);
        }else {
            movie.setMovieType(MovieType.suggested);
            movie.setVerified(MovieVerified.notverified);
        }
        return movieService.create(movie);
    }

    //update a movie
    @PatchMapping(value = "{userid}/movie/{id}")
    Movie update(@PathVariable(value = "id") Long id, @PathVariable(value = "userid") Long userid, @RequestBody Movie movie) {
        return movieService.update(id, userid, movie);
    }

    //delete a movie
    @DeleteMapping(value = "{userid}/movie/{id}")
    String deleteMovie(@PathVariable(value = "id") Long id, @PathVariable(value = "userid") Long userid) {
        return movieService.delete(id, userid);
    }

    //admin verifies a movie
    @PostMapping(value = "{userid}/verify/{id}")
    Movie verifyMovie(@PathVariable(value = "id") Long id, @PathVariable(value = "userid") Long userid){
        return movieService.verifyMovie(id, userid);
    }

    //delete a user
    @DeleteMapping(value = "{id}")
    void delete(@PathVariable Long id, @RequestBody User user) {
         userService.delete(id, user);
    }

    //update a user
    @PatchMapping(value = "{id}")
    User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

}
