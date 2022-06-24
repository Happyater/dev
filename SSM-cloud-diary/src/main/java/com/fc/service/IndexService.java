package com.fc.service;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

public interface IndexService {
    ModelAndView page(Integer id, String title, String date, int pageNum, int pageSize, HttpSession session);
}
