package com.example.notekeeping.services.impl;

import com.example.notekeeping.data.dao.NoteKeepingDao;
import com.example.notekeeping.data.dao.impl.NoteKeepingDaoImpl;
import com.example.notekeeping.data.repositories.NoteKeepingRepository;
import com.example.notekeeping.data.request.NoteReq;
import com.example.notekeeping.data.resp.NoteResp;
import com.example.notekeeping.services.NoteKeepingServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class NoteKeepingServicesImpl implements NoteKeepingServices {
//    private final NoteKeepingDaoImpl noteKeepingDao;
//
//    @Override
//    public String createnote(NoteReq noteReq) {
//
//         noteKeepingDao.createnote(noteReq);
//        return "Successfully added note";
//    }
//
//    @Override
//    public NoteResp getnote(int id) {
//        return noteKeepingDao.getnote(id);
//    }
//
//    @Override
//    public String putnote(NoteReq noteReq, int id) {
//        return noteKeepingDao.putnote(noteReq, id);
//    }
//
//    @Override
//    public void deletenote(int id) {
//        noteKeepingDao.deletenote(id);
//        return;
//    }
private final NoteKeepingDao noteKeepingDao;
    private final NoteKeepingRepository noteBookRepository;



    @Override
    public String createnote(NoteReq noteReq) {
        return noteKeepingDao.createnote(noteReq);

    }

    @Override
    public NoteResp getnote(int id) {
        if(!noteBookRepository.existsById(id)){
            throw new IllegalArgumentException("Id doesn't exist");
        }
        return noteKeepingDao.getnote(id);

    }

    @Override
    public String putnote(NoteReq noteReq, int id) {
        if(!noteBookRepository.existsById(id)){
            throw new IllegalArgumentException("Id doesn't exist");
        }
        return noteKeepingDao.putnote(noteReq,id);
    }



    @Override
    public void deletenote(int id) {
        if(!noteBookRepository.existsById(id)){
            throw new IllegalArgumentException("Id doesn't exist");
        }
        noteKeepingDao.deletenote(id);

    }
}

