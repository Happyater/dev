package com.fc.service.impl;

import com.fc.dao.CarouselMapper;
import com.fc.entity.Carousel;
import com.fc.service.CarouselService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public Map<String, Object> getList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<Carousel> carousels = carouselMapper.selectByExample(null);

        PageInfo<Carousel> pageInfo = new PageInfo<>(carousels);

        Map<String, Object> data = new HashMap<>();

        data.put("total", pageInfo.getTotal());
        data.put("pageNum", pageNum);
        data.put("pageSize", pageSize);
        data.put("list", pageInfo.getList());

        Map<String, Object> map = new HashMap<>();

        map.put("data", data);
        map.put("message", "OK");
        map.put("code", 200);
        map.put("success", true);


        return map;
    }

    @Override
    public Map<String, Object> add(Carousel carousel) {
        int i = carouselMapper.insert(carousel);
        List<Carousel> carousels = carouselMapper.selectByExample(null);
        int total = carousels.size();

        if (i > 0) {
            Map<String, Object> map = new HashMap<>();

            Map<String, Object> data = new HashMap<>();

            Carousel primaryKey = carouselMapper.selectByPrimaryKey(carousel.getId());

            data.put("total", total);
            data.put("list", primaryKey);

            map.put("message", "OK");
            map.put("code", 200);
            map.put("success", true);
            map.put("data", data);

            return map;
        }
        return null;
    }

    @Override
    public Map<String, Object> update(Carousel carousel) {
        int i = carouselMapper.updateByPrimaryKeySelective(carousel);

        Map<String, Object> map = new HashMap<>();

        if(i > 0) {

            Carousel data = carouselMapper.selectByPrimaryKey(carousel.getId());

            map.put("message", "OK");
            map.put("code", 200);
            map.put("success", true);
            map.put("data", data);

            return map;
        }
        return null;
    }

    @Override
    public Map<String, Object> delete(int id) {

        int i = carouselMapper.deleteByPrimaryKey(id);

        Map<String, Object> map = new HashMap<>();

        if (i > 0) {

            map.put("code", 200);
            map.put("message", "OK");
            map.put("success", true);

            return map;
        }

        return null;
    }
}
