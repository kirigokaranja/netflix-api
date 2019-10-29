package com.aws.netflix.services;

import com.aws.netflix.exceptions.NotAuthorized;
import com.aws.netflix.exceptions.NotFoundException;
import com.aws.netflix.models.User;
import com.aws.netflix.models.UserType;
import com.aws.netflix.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    public UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public void delete(Long id, User user) {
        User userDelete = findByID(id);

        if (userDelete.getUserNationalId() != user.getUserNationalId())
            throw new NotFoundException("User " + id + " and National Id " + user.getUserNationalId() + " Not Found");

        userRepository.deleteById(id);
    }
}
