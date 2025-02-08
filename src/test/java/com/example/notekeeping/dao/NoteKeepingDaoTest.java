package com.example.notekeeping.dao;

import com.example.notekeeping.data.dao.impl.NoteKeepingDaoImpl;
import com.example.notekeeping.data.entity.NoteKeepingEntity;
import com.example.notekeeping.data.repositories.NoteKeepingRepository;
import com.example.notekeeping.data.request.NoteReq;
import com.example.notekeeping.data.resp.NoteResp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class NoteKeepingDaoTest {

    @InjectMocks
    private NoteKeepingDaoImpl noteKeepingDao; // Using concrete implementation

    @Mock
    private NoteKeepingRepository repo;

    private NoteKeepingEntity entity;
    private NoteReq addNoteRequests;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this); // Initialize mocks correctly

        entity = new NoteKeepingEntity();
        entity.setId(1);
        entity.setUsername("testUser");
        entity.setNote("Test Note");

        addNoteRequests = new NoteReq();
        addNoteRequests.setUsername("testUser");
        addNoteRequests.setNote("Updated Test Note");
    }

    @Test
    void testCreateNote() {
        when(repo.save(any(NoteKeepingEntity.class))).thenReturn(new NoteKeepingEntity());

        String response = noteKeepingDao.createnote(addNoteRequests);

        assertEquals("note of the user created successfully", response);
        verify(repo, times(1)).save(any(NoteKeepingEntity.class));
    }

    @Test
    void testGetNote() {
        OngoingStubbing<Optional<NoteKeepingEntity>> optionalOngoingStubbing = when(repo.findById(1)).thenReturn(Optional.ofNullable(entity));// Return Optional<NotebookEntity>

        NoteResp response = noteKeepingDao.getnote(1);

        assertNotNull(response);
        assertEquals(1, response.getId());
        assertEquals("testUser", response.getUsername());
        assertEquals("Test Note", response.getNote());

        verify(repo, times(1)).findById(1); // Ensure findById() is called once
    }

    @Test
    void testDeleteNote() {
        doNothing().when(repo).deleteById(1);

        noteKeepingDao.deletenote(1);

        verify(repo, times(1)).deleteById(1); // Ensure deleteById() is called once
    }

    @Test
    void testUpdateNote() {
        OngoingStubbing<Optional<NoteKeepingEntity>> optionalOngoingStubbing = when(repo.findById(1)).thenReturn(Optional.ofNullable(entity)); // Return Optional
        when(repo.save(any(NoteKeepingEntity.class))).thenReturn(entity);

        String response = noteKeepingDao.putnote(addNoteRequests, 1);

        assertEquals("User details updated successfully", response);
        verify(repo, times(1)).save(any(NoteKeepingEntity.class));
    }
}
