package com.aws.netflix.services;

import com.aws.netflix.models.User;
import com.aws.netflix.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserRepository userRepository = null;

    List<User> findAll();

    public User create(User User);

    User findByID(Long id);

    Optional<User> findByNationalId(int id);

    public User update(Long id, User User);

    public void delete(Long id);

}
