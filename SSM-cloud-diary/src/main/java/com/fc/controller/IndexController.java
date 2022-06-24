package com.fc.controller;

import com.fc.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("index")
public class IndexController {
    @Autowired
    private IndexService indexService;

    @RequestMapping("page")
    public ModelAndView page(Integer id, String title, String date,
                             @RequestParam(defaultValue = "1") int pageNum,
                             @RequestParam(defaultValue = "10") int pageSize,
                             HttpSession session) {
        return indexService.page(id, title, date, pageNum, pageSize, session);
    }

    @GetMapping("searchDate")
    public ModelAndView searchDate(String date, ModelAndView modelAndView) {
        modelAndView.addObject("date", date);
        modelAndView.setViewName("forward:/index/page");
        return modelAndView;
    }

    @GetMapping("searchType")
    public ModelAndView searchType(int id, ModelAndView modelAndView) {
        modelAndView.addObject("id", id);
        modelAndView.setViewName("forward:/index/page");
        return modelAndView;
    }

    @GetMapping("searchTitle")
    public ModelAndView searchTitle(String title, ModelAndView modelAndView) {
        modelAndView.addObject("title", title);
        modelAndView.setViewName("forward:/index/page");
        return modelAndView;
    }
}
