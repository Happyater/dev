package com.fc.service;

import com.fc.entity.Collection;

import java.util.Map;

public interface CollectionService {
    Map<String, Object> getList(int pageNum, int pageSize);

    Map<String, Object> add(Collection collection);

    Map<String, Object> update(Collection collection);

    Map<String, Object> delete(int id);
}
