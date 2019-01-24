package com.stackroute.trackservice.component;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class FeedDataAppStartRunner implements ApplicationRunner {

    private Track feedTrackData = new Track();

    private TrackService trackService;

    @Value("${song.total}")
    private int total;

    @Value("${song.4.id}")
    private int id4;
    @Value("${song.4.name}")
    private String name4;
    @Value("${song.4.singer}")
    private String singer4;
    @Value("${song.5.id}")
    private int id5;
    @Value("${song.5.name}")
    private String name5;
    @Value("${song.5.singer}")
    private String singer5;
    @Value("${song.6.id}")
    private int id6;
    @Value("${song.6.name}")
    private String name6;
    @Value("${song.6.singer}")
    private String singer6;

    @Autowired
    public FeedDataAppStartRunner(TrackService trackService) {
        this.trackService = trackService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        feedTrackData.setTrackId(id4);
        feedTrackData.setTrackName(name4);
        feedTrackData.setTrackSinger(singer4);
        trackService.saveTrack(feedTrackData);

        feedTrackData.setTrackId(id5);
        feedTrackData.setTrackName(name5);
        feedTrackData.setTrackSinger(singer5);
        trackService.saveTrack(feedTrackData);

        feedTrackData.setTrackId(id6);
        feedTrackData.setTrackName(name6);
        feedTrackData.setTrackSinger(singer6);
        trackService.saveTrack(feedTrackData);
    }
}
