package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserDoesNotExistException;
import com.stackroute.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException
    {
        if(userRepository.existsById(user.getUserId()))
            throw new UserAlreadyExistsException("User Already Exists!");
        else
            return userRepository.save(user);
    }

    @Override
    public User updateUser(User updatedUser) throws UserDoesNotExistException
    {
        if(userRepository.existsById(updatedUser.getUserId()))
            return userRepository.save(updatedUser);
        else
            throw new com.stackroute.userservice.exception.UserDoesNotExistException("User Does Not Exist!");
    }

    @Override
    public User deleteUser(User user) throws UserDoesNotExistException
    {
        if(userRepository.existsById(user.getUserId()))
            userRepository.delete(user);
        else
            throw new UserDoesNotExistException("User Does Not Exist!");
        return user;
    }

    @Override
    public User getUserById(int userId) throws UserDoesNotExistException
    {
        if(userRepository.existsById(userId))
            return userRepository.findById(userId).get();
        else
            throw new UserDoesNotExistException("User Does Not Exist!");
    }

}
