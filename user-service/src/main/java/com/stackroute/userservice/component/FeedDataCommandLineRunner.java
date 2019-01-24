package com.stackroute.userservice.component;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class FeedDataCommandLineRunner implements CommandLineRunner
{
    private User feedUserData = new User();
    @Autowired
    Environment env;
    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception
    {
//        int totalTrack = Integer.parseInt(env.getProperty("song.total"));
//
//        for(int i=7; i<=totalTrack; i++)
//        {
//
//        }

        feedUserData.setUserId(Integer.parseInt(env.getProperty("user.3.id")));
        feedUserData.setUserName(env.getProperty("user.3.name"));
        feedUserData.setUserPassword(env.getProperty("user.3.pass"));
        try
        {
            userService.saveUser(feedUserData);
        }
        catch (UserAlreadyExistsException e)
        {
            e.printStackTrace();
        }
    }
}
