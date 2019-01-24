package com.stackroute.trackservice.component;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exception.TrackAlreadyExistsException;
import com.stackroute.trackservice.service.TrackService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class FeedDataCommandLineRunner implements CommandLineRunner
{
    private Track feedTrackData = new Track();
    @Autowired
    Environment env;
    @Autowired
    TrackService trackService;

    @Override
    public void run(String... args) throws Exception
    {
        int totalTrack = Integer.parseInt(env.getProperty("song.total"));

        for(int i=7; i<=totalTrack; i++)
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
