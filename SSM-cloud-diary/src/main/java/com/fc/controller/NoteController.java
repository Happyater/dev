package com.fc.controller;

import com.fc.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("view")
    public ModelAndView view(Integer noteId) {
        return noteService.view(noteId);
    }
}
