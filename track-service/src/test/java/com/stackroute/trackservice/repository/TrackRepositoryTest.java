package com.stackroute.trackservice.repository;

import com.stackroute.trackservice.domain.Track;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrackRepositoryTest
{
    @Autowired
    private TrackRepository trackRepository;

    private Track track;
    private List<Track> tracks;

    @Before
    public void setUp()
    {
        track = new Track();
        track.setTrackId(101);
        track.setTrackName("Track1");
        track.setTrackSinger("Singer11");

        tracks = new ArrayList<>();
        tracks.add(trackRepository.save(track));
    }

    @After
    public void tearDown()
    {
        trackRepository.deleteAll();
        tracks=null;
        track=null;
    }

    @Test
    public void testSaveTrackSuccess()
    {
        Track savedTrack = trackRepository.save(track);
        Track fetchedTrack = trackRepository.findById(savedTrack.getTrackId()).get();

        assertEquals(savedTrack, fetchedTrack);
    }

    @Test
    public void testSaveTrackFailure()
    {
        Track newTrack = new Track();
        newTrack.setTrackId(102);
        newTrack.setTrackName("Track2");
        newTrack.setTrackSinger("Singer22");

        Track savedTrack = trackRepository.save(track);
        Track fetchedTrack = trackRepository.findById(savedTrack.getTrackId()).get();

        assertNotEquals(newTrack, fetchedTrack);
    }

    @Test
    public void testUpdateTrackSuccess()
    {
        Track previousTrack = trackRepository.save(track);

        Track updatedTrack = new Track();
        updatedTrack.setTrackId(previousTrack.getTrackId());
        updatedTrack.setTrackName("UpdatedTrack");
        updatedTrack.setTrackSinger("UpdatedSinger");

        Track savedTrack = trackRepository.save(updatedTrack);

        Track fetchedTrack = trackRepository.findById(savedTrack.getTrackId()).get();

        assertEquals(updatedTrack, fetchedTrack);
    }

    @Test
    public void testUpdateTrackFailure()
    {
        Track previousTrack = trackRepository.save(track);

        Track updatedTrack = new Track();
        updatedTrack.setTrackId(previousTrack.getTrackId());
        updatedTrack.setTrackName("UpdatedTrack");
        updatedTrack.setTrackSinger("UpdatedSinger");

        Track fetchedTrack = trackRepository.findById(previousTrack.getTrackId()).get();

        assertNotEquals(updatedTrack, fetchedTrack);
    }

    @Test
    public void getTrackByIdSuccess()
    {
        Track savedTrack = trackRepository.save(track);

        Track fetchedTrack = trackRepository.findById(savedTrack.getTrackId()).get();

        assertEquals(savedTrack, fetchedTrack);
    }

    @Test
    public void getTrackByIdFailure()
    {
        Track newTrack = new Track();
        newTrack.setTrackId(102);
        newTrack.setTrackName("Track2");
        newTrack.setTrackSinger("Singer22");

        assertTrue(trackRepository.findById(newTrack.getTrackId()).isEmpty());
    }

    @Test
    public void getTrackByNameSuccess()
    {
        Track savedTrack = trackRepository.save(track);

        Track fetchedTrack = trackRepository.findByTrackName(savedTrack.getTrackName()).get();

        assertEquals(savedTrack, fetchedTrack);
    }

    @Test
    public void getTrackByNameFailure()
    {
        Track newTrack = new Track();
        newTrack.setTrackId(102);
        newTrack.setTrackName("Track2");
        newTrack.setTrackSinger("Singer22");

        assertTrue(trackRepository.findByTrackName(newTrack.getTrackName()).isEmpty());
    }

    @Test
    public void deleteTrackSuccess()
    {
        Track savedTrack = trackRepository.save(track);

        trackRepository.delete(savedTrack);

        assertTrue(trackRepository.findById(savedTrack.getTrackId()).isEmpty());
    }

    @Test
    public void deleteTrackFailure()
    {
        Track savedTrack = trackRepository.save(track);

        assertFalse(trackRepository.findById(savedTrack.getTrackId()).isEmpty());
    }

    @Test
    public void getAllTracksSuccess()
    {
        Track newTrack = new Track();
        newTrack.setTrackId(102);
        newTrack.setTrackName("Track2");
        newTrack.setTrackSinger("Singer22");

        tracks.add(trackRepository.save(newTrack));

        assertEquals(tracks, trackRepository.findAll());
    }

    @Test
    public void getAllTracksFailure()
    {
        Track newTrack = new Track();
        newTrack.setTrackId(102);
        newTrack.setTrackName("Track2");
        newTrack.setTrackSinger("Singer22");

        trackRepository.save(newTrack);

        assertNotEquals(tracks, trackRepository.findAll());
    }

}