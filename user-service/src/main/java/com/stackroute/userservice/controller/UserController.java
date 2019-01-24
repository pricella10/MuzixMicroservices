package com.stackroute.userservice.controller;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserDoesNotExistException;
import com.stackroute.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
//@Api(description="User Service")
public class UserController
{
    private UserService userSrevice;
    private ResponseEntity<?> responseEntity;

    @Autowired
    public UserController(UserService userSrevice)
    {
        this.userSrevice = userSrevice;
    }

//    @ApiOperation(value = "Save User")
    @PostMapping("/users/user")
    public ResponseEntity<?> saveUser(@RequestBody User user)
    {
        try
        {
            return new ResponseEntity<User>(userSrevice.saveUser(user), HttpStatus.CREATED);
        }
        catch (com.stackroute.userservice.exception.UserAlreadyExistsException e)
        {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

//    @ApiOperation(value = "Update User")
    @PutMapping("/users/user")
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser)
    {
        try
        {
            userSrevice.updateUser(updatedUser);
            return new ResponseEntity<User>(updatedUser, HttpStatus.ACCEPTED);
        }
        catch (UserDoesNotExistException e)
        {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/user/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer userId)
    {
        try
        {
            return new ResponseEntity<User>(userSrevice.deleteUser(userSrevice.getUserById(userId)), HttpStatus.ACCEPTED);
        }
        catch (UserDoesNotExistException e)
        {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Integer userId)
    {
        try
        {
            return new ResponseEntity<User>(userSrevice.getUserById(userId), HttpStatus.FOUND);
        }
        catch (UserDoesNotExistException e)
        {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
