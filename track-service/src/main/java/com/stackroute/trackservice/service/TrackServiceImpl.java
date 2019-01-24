package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exception.EmptyTrackListException;
import com.stackroute.trackservice.exception.TrackAlreadyExistsException;
import com.stackroute.trackservice.exception.TrackDoesNotExistException;
import com.stackroute.trackservice.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService
{
    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository)
    {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException
    {
        if(trackRepository.existsByTrackName(track.getTrackName()))
            throw new TrackAlreadyExistsException("Track Name Already Exists!");
        else
            return trackRepository.save(track);
    }

    @Override
    public Track updateTrack(Track updatedTrack) throws TrackDoesNotExistException
    {
        if(trackRepository.existsById(updatedTrack.getTrackId()))
            return trackRepository.save(updatedTrack);
        else
            throw new TrackDoesNotExistException("Track Does Not Exist!");
    }

    @Override
    public Track deleteTrack(Track track) throws TrackDoesNotExistException
    {
        if(trackRepository.existsById(track.getTrackId()))
            trackRepository.delete(track);
        else
            throw new TrackDoesNotExistException("Track Does Not Exist!");
        return track;
    }

    @Override
    public Track getTrackById(int trackId) throws TrackDoesNotExistException
    {
        if(trackRepository.existsById(trackId))
            return trackRepository.findById(trackId).get();
        else
            throw new TrackDoesNotExistException("Track Does Not Exist");

    }

    @Override
    public Track getTrackByName(String trackName) throws TrackDoesNotExistException
    {
        if(trackRepository.existsByTrackName(trackName))
            return trackRepository.findByTrackName(trackName).get();
        else
            throw new TrackDoesNotExistException("Track Does Not Exist!");
    }


    @Override
    public List<Track> getAllTracks() throws EmptyTrackListException
    {
        List<Track> allTracks = trackRepository.findAll();
        if(allTracks==null)
            throw new EmptyTrackListException("Track List Is Empty!");
        else
            return allTracks;
    }
}
