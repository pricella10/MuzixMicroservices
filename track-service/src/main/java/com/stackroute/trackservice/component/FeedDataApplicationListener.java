package com.stackroute.trackservice.component;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exception.TrackAlreadyExistsException;
import com.stackroute.trackservice.service.TrackService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class FeedDataApplicationListener implements ApplicationListener<ContextRefreshedEvent>
{
    @Autowired
    Environment env;
    @Autowired
    TrackService trackService;
    Track feedTrackData = new Track();
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        int totalTrack = Integer.parseInt(env.getProperty("song.total"));

        for(int i=1; i<=3; i++)
        {
            feedTrackData.setTrackId(Integer.parseInt(env.getProperty("song."+i+".id")));
            feedTrackData.setTrackName(env.getProperty("song."+i+".name"));
            feedTrackData.setTrackSinger(env.getProperty("song."+i+".singer"));
            try
            {
                trackService.saveTrack(feedTrackData);
            }
            catch (TrackAlreadyExistsException e)
            {
                e.printStackTrace();
            }
        }
    }
}
