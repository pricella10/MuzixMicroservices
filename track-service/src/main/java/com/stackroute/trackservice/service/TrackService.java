package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exception.EmptyTrackListException;
import com.stackroute.trackservice.exception.TrackAlreadyExistsException;
import com.stackroute.trackservice.exception.TrackDoesNotExistException;

import java.util.List;

public interface TrackService
{
    Track saveTrack(Track track) throws TrackAlreadyExistsException;
    Track updateTrack(Track updatedTrack) throws TrackDoesNotExistException;
    Track deleteTrack(Track track) throws TrackDoesNotExistException;
    Track getTrackById(int trackId) throws TrackDoesNotExistException;
    Track getTrackByName(String trackName) throws TrackDoesNotExistException;

    List<Track> getAllTracks() throws EmptyTrackListException;
}
