package com.stackroute.trackservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exception.EmptyTrackListException;
import com.stackroute.trackservice.exception.TrackAlreadyExistsException;
import com.stackroute.trackservice.exception.TrackDoesNotExistException;
import com.stackroute.trackservice.service.TrackService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TrackControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    private Track track;
    private List<Track> tracks;

    @MockBean
    private TrackService trackService;

    @InjectMocks
    private TrackController trackController;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trackController).build();

        track = new Track();
        track.setTrackId(101);
        track.setTrackName("Track1");
        track.setTrackSinger("Singer11");

        tracks = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception
    {
        tracks=null;
        track=null;
    }

    @Test
    public void saveTrackSuccess() throws Exception {
        when(trackService.saveTrack((Track)any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tracks/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void saveTrackFailure() throws Exception {
        when(trackService.saveTrack((Track)any())).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tracks/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateTrackSuccess() throws Exception {
        when(trackService.updateTrack((Track)any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/tracks/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateTrackFailure() throws Exception {
        when(trackService.updateTrack((Track)any())).thenThrow(TrackDoesNotExistException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/tracks/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getTrackByIdSuccess() throws Exception {
        when(trackService.getTrackById(anyInt())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/track/id/{trackId}",track.getTrackId())
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getTrackByIdFailure() throws Exception {
        when(trackService.getTrackById(anyInt())).thenThrow(TrackDoesNotExistException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/track/id/{trackId}",track.getTrackId())
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getTrackByNameSuccess() throws Exception {
        when(trackService.getTrackByName((String)any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/track/name/{trackName}",track.getTrackName())
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getTrackByNameFailure() throws Exception {
        when(trackService.getTrackByName((String) any())).thenThrow(TrackDoesNotExistException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/track/name/{trackName}",track.getTrackName())
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteTrackByIdSuccess() throws Exception {
        when(trackService.getTrackById(anyInt())).thenReturn(track);
        when(trackService.deleteTrack((Track)any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/tracks/track/{trackId}",track.getTrackId())
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteTrackByIdFailure() throws Exception {
        when(trackService.getTrackById(anyInt())).thenThrow(TrackDoesNotExistException.class);
        when(trackService.deleteTrack((Track)any())).thenThrow(TrackDoesNotExistException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/tracks/track/{trackId}",track.getTrackId())
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllTracksSuccess() throws Exception {
        when(trackService.getAllTracks()).thenReturn(tracks);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllTracksFailure() throws Exception {
        when(trackService.getAllTracks()).thenThrow(EmptyTrackListException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }


    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}