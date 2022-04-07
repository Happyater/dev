package com.fc.service;

import com.fc.entity.Alleviation;

import java.util.Map;

public interface AlleviationService {
    Map<String, Object> getList(int pageNum, int pageSize);

    Map<String, Object> add(Alleviation alleviation);

    Map<String, Object> update(Alleviation alleviation);

    Map<String, Object> delete(int id);
}
