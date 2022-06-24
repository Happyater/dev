package com.fc.controller;

import com.fc.entity.Carousel;
import com.fc.entity.User;
import com.fc.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;

    @RequestMapping("getList")
    public Map<String, Object> getList(int pageNum, int pageSize) {
        return carouselService.getList(pageNum, pageSize);
    }

    @RequestMapping("add")
    public Map<String, Object> add(Carousel carousel){
        return carouselService.add(carousel);
    }

    @RequestMapping("update")
    public Map<String, Object> update(Carousel carousel) {
        return carouselService.update(carousel);
    }

    @RequestMapping("delete")
    public Map<String, Object> delete(int id) {
        return carouselService.delete(id);
    }
}
