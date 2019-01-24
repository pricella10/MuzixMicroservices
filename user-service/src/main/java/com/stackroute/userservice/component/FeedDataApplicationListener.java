package com.stackroute.userservice.component;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class FeedDataApplicationListener implements ApplicationListener<ContextRefreshedEvent>
{
    @Autowired
    Environment env;
    @Autowired
    UserService userService;
    User feedUserData = new User();
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        int totalUser = Integer.parseInt(env.getProperty("user.total"));

//        for(int i=1; i<=3; i++)
//        {
//            feedTrackData.setTrackId(Integer.parseInt(env.getProperty("song."+i+".id")));
//            feedTrackData.setTrackName(env.getProperty("song."+i+".name"));
//            feedTrackData.setTrackSinger(env.getProperty("song."+i+".singer"));
//            try
//            {
//                userService.saveTrack(feedTrackData);
//            }
//            catch (TrackAlreadyExistsException e)
//            {
//                e.printStackTrace();
//            }
//        }

        feedUserData.setUserId(Integer.parseInt(env.getProperty("user.1.id")));
        feedUserData.setUserName(env.getProperty("user.1.name"));
        feedUserData.setUserPassword(env.getProperty("user.1.pass"));
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
