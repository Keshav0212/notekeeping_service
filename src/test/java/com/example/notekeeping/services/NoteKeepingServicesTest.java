package com.example.notekeeping.services;

import com.example.notekeeping.data.dao.NoteKeepingDao;
import com.example.notekeeping.data.repositories.NoteKeepingRepository;
import com.example.notekeeping.data.request.NoteReq;
import com.example.notekeeping.data.resp.NoteResp;
import com.example.notekeeping.services.impl.NoteKeepingServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NoteKeepingServicesTest {

    @Mock
    private NoteKeepingDao noteKeepingDao;

    @Mock
    private NoteKeepingRepository noteBookRepository;

    @InjectMocks
    private NoteKeepingServicesImpl noteKeepingServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createNoteTest(){
        NoteReq requests = new NoteReq();
        requests.setUsername("Test");
        requests.setNote("Testing note");

        when(noteKeepingDao.createnote(requests)).thenReturn("Successfully added note");
        String response = noteKeepingServices.createnote(requests);
        assertEquals("Successfully added note", response);
    }

    @Test
    void delNoteTest() {
        int id = 1;
        when(noteBookRepository.existsById(id)).thenReturn(true);
        doNothing().when(noteKeepingDao).deletenote(id);
        noteKeepingServices.deletenote(id);
    }

    @Test
    void delNoteTestException() {
        int id = 1;
        when(noteBookRepository.existsById(id)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> noteKeepingServices.deletenote(id));
    }

    @Test
    void updateNoteTest() {
        int id = 1;
        NoteReq requests = new NoteReq();
        requests.setUsername("Test");
        requests.setNote("Testing note");

        when(noteBookRepository.existsById(id)).thenReturn(true);
        when(noteKeepingDao.putnote(requests, id)).thenReturn("Updated added note");
        String response = noteKeepingServices.putnote(requests, id);

        assertEquals("Updated added note", response);
    }


    @Test
    void updateNoteTestException() {
        int id = 1;
        NoteReq requests = new NoteReq();
        requests.setUsername("Test");
        requests.setNote("Testing note");

        when(noteBookRepository.existsById(id)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> noteKeepingServices.putnote(requests, id));
    }

    @Test
    void testGetNote() {
        int id = 1;
        NoteResp mockResponse = new NoteResp();
        mockResponse.setId(id);
        mockResponse.setUsername("testUser");
        mockResponse.setNote("Test note content");

        when(noteBookRepository.existsById(id)).thenReturn(true);
        when(noteKeepingDao.getnote(id)).thenReturn(mockResponse);

        NoteResp response = noteKeepingServices.getnote(id);

        assertEquals(id, response.getId());
        assertEquals("testUser", response.getUsername());
        assertEquals("Test note content", response.getNote());
    }

    @Test
    void testGetNoteException() {
        int id = 1;
        when(noteBookRepository.existsById(id)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> noteKeepingServices.getnote(id));
    }

}

