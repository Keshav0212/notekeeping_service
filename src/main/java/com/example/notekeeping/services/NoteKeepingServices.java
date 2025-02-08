package com.example.notekeeping.services;

import com.example.notekeeping.data.request.NoteReq;
import com.example.notekeeping.data.resp.NoteResp;
import org.springframework.stereotype.Service;

@Service
public interface NoteKeepingServices {
    public String createnote(NoteReq noteReq);

    public NoteResp getnote(int id);

    public String putnote(NoteReq noteReq, int id);

    public void deletenote(int id);
}
