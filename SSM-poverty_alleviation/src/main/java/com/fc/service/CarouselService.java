package com.fc.service;

import com.fc.entity.Carousel;

import java.util.Map;

public interface CarouselService {
    Map<String, Object> getList(int pageNum, int pageSize);

    Map<String, Object> add(Carousel carousel);

    Map<String, Object> update(Carousel carousel);

    Map<String, Object> delete(int id);
}
