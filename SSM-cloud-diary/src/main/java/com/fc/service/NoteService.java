package com.fc.service;

import org.springframework.web.servlet.ModelAndView;

public interface NoteService {
    ModelAndView view(Integer noteId);
}
