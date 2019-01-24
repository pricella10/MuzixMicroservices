package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserDoesNotExistException;


import java.util.List;

public interface UserService
{
    User saveUser(User user) throws UserAlreadyExistsException;
    User updateUser(User updatedUser) throws UserDoesNotExistException;
    User deleteUser(User user) throws UserDoesNotExistException;
    User getUserById(int userId) throws UserDoesNotExistException;

}
