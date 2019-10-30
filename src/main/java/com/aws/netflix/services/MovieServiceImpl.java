package com.aws.netflix.services;

import com.aws.netflix.exceptions.NotAuthorized;
import com.aws.netflix.exceptions.NotFoundException;
import com.aws.netflix.models.*;
import com.aws.netflix.repositories.CategoryRepository;
import com.aws.netflix.repositories.MovieRepository;
import com.aws.netflix.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    public MovieRepository movieRepository;
    public UserRepository userRepository;
    public CategoryRepository categoryRepository;

    public MovieServiceImpl(MovieRepository movieRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }


    //find all movies
    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    //create movie
    @Override
    public Movie create(Movie Movie) {
        List<Category> categories = Movie.getCategory();
        for (Category m:categories) {
            Category found = categoryRepository.findById(m.getId()).orElseThrow(()-> new NotFoundException("Category id "+ m.getId() +" not found"));
        }
        Optional<Movie> exist = findByMovieNameAndReleaseYear(Movie.getMovieName(), Movie.getReleaseYear());
        return exist.orElseGet(()->movieRepository.save(Movie));
    }

    //find movies by id
    @Override
    public Movie findByID(Long id) {
        return movieRepository.findById(id).orElseThrow(()->
                new NotFoundException("Movie id" + id + "not found"));
    }

    //querymovie
    @Override
    public List<Movie> findByCategoryAndType(Long id, MovieType type) {
        System.out.println(type);
        System.out.println(MovieType.suggested);
        return movieRepository.findByCategoryAndMovieTypeAndVerified(new Category(id), type, MovieVerified.verified);
    }

    @Override
    public Movie update(Long id, Long userid, Movie Movie)
    {
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new NotFoundException("Movie" + id +"does not exist"));
        Optional<User> user = Optional.ofNullable(userRepository.findById(userid).orElseThrow(() -> new NotFoundException("This user" + userid+ "not found")));
        if (movie.getUser().getId().equals(userid)){
            movie.setMovieName(Movie.getMovieName());
            movie.setReleaseYear(Movie.getReleaseYear());
            movie.setCategory(Movie.getCategory());
            return movieRepository.save(movie);
        }
        throw new NotAuthorized("This user did not add the movie");
    }

    @Override
    public String delete(Long id, Long userid) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(userid).orElseThrow(() -> new NotFoundException("This user" + userid+ "not found")));
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new NotFoundException("Movie" + id +"does not exist"));
        if (movie.getUser().getId().equals(userid)){
            movieRepository.delete(movie);
            return id.toString();
        }
        throw new NotAuthorized("This user did not add the movie");
    }

    @Override
    public Movie verifyMovie(Long id, Long userid) {
        if (userid == 1){
            Movie movie = movieRepository.findById(id).orElseThrow(()-> new NotFoundException("Movie id" + id + "not found"));
            movie.setVerified(MovieVerified.verified);
            return movieRepository.save(movie);
        }
        throw new NotAuthorized("Only the admin can verify a movie");

    }

    @Override
    public Optional<Movie> findByMovieNameAndReleaseYear(String name, int year) {
        return movieRepository.findByMovieNameAndReleaseYear(name, year);
    }
}
