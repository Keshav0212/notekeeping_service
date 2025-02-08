package com.example.notekeeping.controller;

import com.example.notekeeping.data.request.NoteReq;
import com.example.notekeeping.data.resp.NoteResp;
import com.example.notekeeping.services.impl.NoteKeepingServicesImpl;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("notekeeping")
@Service
@RequiredArgsConstructor
@RestController
@Slf4j
@Validated
public class NoteKeepingController {
    private final NoteKeepingServicesImpl noteKeepingServices;
    @PostMapping
    public String createnote(@RequestBody NoteReq noteReq) {
      //  log.info("[createnote] users note is added");
        return noteKeepingServices.createnote(noteReq);
    }

    @GetMapping("/{id}")
    public NoteResp getnote(@PathVariable int id) {
      //  log.info("[getnote] controller ");
        return noteKeepingServices.getnote(id);
    }

    @PutMapping("/{id}")
    public String putnote(@RequestBody NoteReq noteReq, @PathVariable int id) {
       // log.info("[putnote] controller");
        return noteKeepingServices.putnote(noteReq, id);
    }

    @DeleteMapping("/{id}")
    public void deletenote(@PathVariable int id) {
       // log.info("[deletenote] controller");
        noteKeepingServices.deletenote(id);
        return;
    }

}
