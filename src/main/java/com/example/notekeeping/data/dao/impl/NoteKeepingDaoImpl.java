package com.example.notekeeping.data.dao.impl;

import com.example.notekeeping.data.dao.NoteKeepingDao;
import com.example.notekeeping.data.entity.NoteKeepingEntity;
import com.example.notekeeping.data.repositories.NoteKeepingRepository;
import com.example.notekeeping.data.request.NoteReq;
import com.example.notekeeping.data.resp.NoteResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoteKeepingDaoImpl implements NoteKeepingDao {
    private final NoteKeepingRepository noteKeepingRepository;

    @Override
    public String createnote(NoteReq noteReq) {
        NoteKeepingEntity noteKeepingEntity = new NoteKeepingEntity();
        noteKeepingEntity.setNote(noteReq.getNote());
        noteKeepingEntity.setUsername(noteReq.getUsername());
        noteKeepingRepository.save(noteKeepingEntity);
        return "note of the user created successfully";
    }

    @Override
    public NoteResp getnote(int id) {
        NoteKeepingEntity noteKeepingEntity = new NoteKeepingEntity();
        noteKeepingEntity = noteKeepingRepository.findById(id) .orElseThrow(() -> new IllegalArgumentException("Note not found with id" + id));

        NoteResp noteResp = new NoteResp();
        noteResp.setId(noteKeepingEntity.getId());
        noteResp.setNote(noteKeepingEntity.getNote());
        noteResp.setUsername(noteKeepingEntity.getUsername());
        return noteResp;
    }

    @Override
    public String putnote(NoteReq noteReq, int id) {
        NoteKeepingEntity noteKeepingEntity = noteKeepingRepository.findById(id) .orElseThrow(() -> new IllegalArgumentException("Note not found with id:" + id));

        noteKeepingEntity.setUsername(noteReq.getUsername());
        noteKeepingEntity.setNote(noteReq.getNote());
        noteKeepingRepository.save(noteKeepingEntity);

        return "User details updated successfully";
    }

    @Override
    public void deletenote(int id) {
        noteKeepingRepository.deleteById(id);
    }


}
