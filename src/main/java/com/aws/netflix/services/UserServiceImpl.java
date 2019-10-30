package com.aws.netflix.services;

import com.aws.netflix.exceptions.NotAuthorized;
import com.aws.netflix.exceptions.NotFoundException;
import com.aws.netflix.models.Movie;
import com.aws.netflix.models.User;
import com.aws.netflix.models.UserType;
import com.aws.netflix.repositories.MovieRepository;
import com.aws.netflix.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    public UserRepository userRepository;
    public MovieRepository movieRepository;

    public UserServiceImpl(UserRepository userRepository, MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        Optional<User> exist = findByNationalId(user.getUserNationalId());
        return exist.orElseGet(() -> userRepository.save(user));
    }

    @Override
    public User findByID(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User " + id + " Not Found"));
    }

    @Override
    public Optional<User> findByNationalId(int id) {
        return userRepository.findByUserNationalId(id);
    }

    @Override
    public User update(Long id, User user) {
        User userUpdate = findByID(id);
        if (userUpdate.getUserType() != UserType.admin){
        if (userUpdate.getUserNationalId() != user.getUserNationalId())
            throw new NotFoundException("User " + id + " and National Id " + user.getUserNationalId() + " Not Found");

            userUpdate.setUserName(user.getUserName());
            return userRepository.save(userUpdate);
        }
        throw new NotAuthorized("You cannot update the admin");

    }

    @Override
    public void delete(Long id) {
//        User userDelete = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User " + id + " Not Found"));
//        Optional<Movie> movie = movieRepository.findByUser_Id(id);
//        System.out.println(movie);
//        if (movie.isPresent()){
//            throw new NotAuthorized("You cannot delete a user who has a movie");
//        }
        userRepository.deleteById(id);
    }
}
