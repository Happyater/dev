package com.fc.controller;

import com.fc.service.ReportService;
import com.fc.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("info")
    public ModelAndView info(ModelAndView modelAndView) {
        modelAndView.addObject("menu_page", "report");
        modelAndView.addObject("changePage", "report/info.jsp");
        modelAndView.setViewName("forward:/index.jsp");
        return modelAndView;
    }

    @GetMapping("location")
    public ResultInfo location() {
        return reportService.location();
    }

    @GetMapping("month")
    public ResultInfo month() {
        return reportService.month();
    }
}
