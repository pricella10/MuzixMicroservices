package com.stackroute.userservice.component;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class FeedDataAppStartRunner implements ApplicationRunner {

    private User feedUserData = new User();

    private UserService userService;

    @Value("${user.total}")
    private int total;

    @Value("${user.2.id}")
    private int id2;
    @Value("${user.2.name}")
    private String name2;
    @Value("${user.2.pass}")
    private String pass2;

    @Autowired
    public FeedDataAppStartRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        feedUserData.setUserId(id2);
        feedUserData.setUserName(name2);
        feedUserData.setUserPassword(pass2);
        userService.saveUser(feedUserData);
    }
}
