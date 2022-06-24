package com.fc.service.impl;

import com.fc.dao.TbNoteMapper;
import com.fc.entity.TbNote;
import com.fc.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private TbNoteMapper tbNoteMapper;

    @Autowired
    private HttpSession session;

    @Override
    public ModelAndView view(Integer noteId) {
        ModelAndView modelAndView = new ModelAndView();

        if (noteId != null) {
            int id = noteId;
            TbNote tbNote = tbNoteMapper.selectByPrimaryKey(id);
            modelAndView.addObject("noteInfo", tbNote);

        }
        return null;
    }
}
