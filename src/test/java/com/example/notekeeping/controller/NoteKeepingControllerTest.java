package com.example.notekeeping.controller;

import com.example.notekeeping.data.request.NoteReq;
import com.example.notekeeping.data.resp.NoteResp;
import com.example.notekeeping.services.impl.NoteKeepingServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class NoteKeepingControllerTest {

    @Mock
    private NoteKeepingServicesImpl noteKeepingServices;

    @InjectMocks
    private NoteKeepingController noteKeepingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createNoteTest(){
        // Arrange
        NoteReq requests = new NoteReq();
        requests.setUsername("Test");
        requests.setNote("Testing note");

        when(noteKeepingServices.createnote(requests)).thenReturn("Successfully added note");

        // Act
        String response = noteKeepingController.createnote(requests);

        // Assert
        assertEquals("Successfully added note", response);
    }

    @Test
    void delNoteTest(){
        // Arrange
        int id = 1;
        doNothing().when(noteKeepingServices).deletenote(id);

        // Act
        noteKeepingController.deletenote(id);

        // Assert (no response from the method, so we verify deletion occurred)
        // You can verify that delete method was called as a side effect if necessary
    }

    @Test
    void updateNoteTest(){
        // Arrange
        int id = 1;
        NoteReq requests = new NoteReq();
        requests.setUsername("Test");
        requests.setNote("Testing note");

        when(noteKeepingServices.putnote(requests, id)).thenReturn("Updated added note");

        // Act
        String response = noteKeepingController.putnote(requests, id);

        // Assert
        assertEquals("Updated added note", response);
    }

    @Test
    void testGetNote() {
        // Arrange
        int id = 1;
        NoteResp mockResponse = new NoteResp();
        mockResponse.setId(id);
        mockResponse.setUsername("testUser");
        mockResponse.setNote("Test note content");

        when(noteKeepingServices.getnote(id)).thenReturn(mockResponse);

        // Act
        NoteResp response = noteKeepingController.getnote(id);

        // Assert
        assertEquals(id, response.getId());
        assertEquals("testUser", response.getUsername());
        assertEquals("Test note content", response.getNote());
    }
}
