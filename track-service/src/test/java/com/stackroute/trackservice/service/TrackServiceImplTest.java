package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exception.EmptyTrackListException;
import com.stackroute.trackservice.exception.TrackAlreadyExistsException;
import com.stackroute.trackservice.exception.TrackDoesNotExistException;
import com.stackroute.trackservice.repository.TrackRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceImplTest
{
    private Track track;
    private List<Track> tracks;

    @Mock
    private TrackRepository trackRepository;

    @InjectMocks
    private TrackServiceImpl trackService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        track = new Track();
        track.setTrackId(101);
        track.setTrackName("Track1");
        track.setTrackSinger("Singer11");

        tracks = new ArrayList<>();
        tracks.add(track);
    }

    @After
    public void tearDown() throws Exception
    {
        tracks=null;
        track=null;
    }

    @Test
    public void saveTrackSuccess() throws TrackAlreadyExistsException
    {
        when(trackRepository.save((Track)any())).thenReturn(track);

        Track savedTrack = trackService.saveTrack(track);
        assertEquals(track, savedTrack);

        verify(trackRepository,times(1)).save(track);
    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveTrackFailure() throws TrackAlreadyExistsException
    {
        when(trackRepository.existsByTrackName((String)any())).thenReturn(true);

        Track savedTrack = trackService.saveTrack(track);

        verify(trackRepository,times(1)).existsByTrackName((String)any());
    }

    @Test
    public void updateTrackSuccess() throws TrackDoesNotExistException {
        when(trackRepository.existsById((Integer)any())).thenReturn(true);
        when(trackRepository.save((Track) any())).thenReturn(track);

        Track updatedTrack = trackService.updateTrack(track);
        assertEquals(updatedTrack, track);

        verify(trackRepository,times(1)).existsById((Integer)any());
        verify(trackRepository,times(1)).save((Track)any());
    }

    @Test(expected = TrackDoesNotExistException.class)
    public void updateTrackFailure() throws TrackDoesNotExistException {
        when(trackRepository.existsById((Integer)any())).thenReturn(false);

        Track updatedTrack = trackService.updateTrack(track);

        verify(trackRepository,times(1)).existsById((Integer)any());
    }

    @Test
    public void deleteTrackSuccess() throws TrackDoesNotExistException {
        when(trackRepository.existsById((Integer)any())).thenReturn(true);

        Track deletedTrack = trackService.deleteTrack(track);
        assertEquals(deletedTrack, track);

        verify(trackRepository,times(1)).existsById((Integer)any());
        verify(trackRepository,times(1)).delete((Track)any());
    }

    @Test(expected = TrackDoesNotExistException.class)
    public void deleteTrackFailure() throws TrackDoesNotExistException {
        when(trackRepository.existsById((Integer)any())).thenReturn(false);

        Track deletedTrack = trackService.deleteTrack(track);

        verify(trackRepository,times(1)).existsById((Integer)any());
    }

    @Test
    public void getTrackByIdSuccess() throws TrackDoesNotExistException {
        when(trackRepository.existsById((Integer)any())).thenReturn(true);
        when(trackRepository.findById((Integer) any())).thenReturn(Optional.of(track));

        Track fetchedTrack = trackService.getTrackById(track.getTrackId());
        assertEquals(fetchedTrack, track);

        verify(trackRepository,times(1)).existsById((Integer)any());
        verify(trackRepository,times(1)).findById((Integer) any());
    }

    @Test(expected = TrackDoesNotExistException.class)
    public void getTrackByIdFailure() throws TrackDoesNotExistException {
        when(trackRepository.existsById((Integer)any())).thenReturn(false);

        Track fetchedTrack = trackService.getTrackById(track.getTrackId());

        verify(trackRepository,times(1)).existsById((Integer)any());
    }

    @Test
    public void getTrackByNameSuccess() throws TrackDoesNotExistException {
        when(trackRepository.existsByTrackName((String)any())).thenReturn(true);
        when(trackRepository.findByTrackName((String) any())).thenReturn(Optional.of(track));

        Track fetchedTrack = trackService.getTrackByName(track.getTrackName());
        assertEquals(fetchedTrack, track);

        verify(trackRepository,times(1)).existsByTrackName((String)any());
        verify(trackRepository,times(1)).findByTrackName((String) any());
    }

    @Test(expected = TrackDoesNotExistException.class)
    public void getTrackByNameFailure() throws TrackDoesNotExistException {
        when(trackRepository.existsByTrackName((String)any())).thenReturn(false);

        Track fetchedTrack = trackService.getTrackByName(track.getTrackName());

        verify(trackRepository,times(1)).existsByTrackName((String)any());
    }

    @Test
    public void getAllTracksSuccess() throws EmptyTrackListException {
        when(trackRepository.findAll()).thenReturn(tracks);

        List<Track> fetchedTracks = trackService.getAllTracks();
        assertEquals(fetchedTracks, tracks);

        verify(trackRepository,times(1)).findAll();
    }

    @Test(expected = EmptyTrackListException.class)
    public void getAllTracksFailure() throws EmptyTrackListException {
        when(trackRepository.findAll()).thenReturn(null);

        List<Track> fetchedTracks = trackService.getAllTracks();

        verify(trackRepository,times(1)).findAll();
    }
}