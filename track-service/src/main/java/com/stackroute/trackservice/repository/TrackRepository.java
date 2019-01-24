package com.stackroute.trackservice.repository;

import com.stackroute.trackservice.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrackRepository extends MongoRepository<Track, Integer>
{
    Optional<Track> findByTrackName(String trackName);
    boolean existsByTrackName(String trackName);
}
