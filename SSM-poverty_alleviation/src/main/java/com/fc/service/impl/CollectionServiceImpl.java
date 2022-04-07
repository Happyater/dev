package com.fc.service.impl;

import com.fc.dao.CollectionMapper;
import com.fc.entity.Carousel;
import com.fc.entity.Collection;
import com.fc.entity.User;
import com.fc.service.CollectionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;


    @Override
    public Map<String, Object> getList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<Collection> collections = collectionMapper.selectByExample(null);

        PageInfo<Collection> pageInfo = new PageInfo<>(collections);

        Map<String, Object> data = new HashMap<>();

        data.put("total", pageInfo.getTotal());
        data.put("pageSize", pageSize);
        data.put("pageNum", pageNum);
        data.put("list", pageInfo.getList());

        Map<String, Object> map = new HashMap<>();

        map.put("code", 200);
        map.put("message", "OK");
        map.put("success", true);
        map.put("data", data);


        return map;
    }

    @Override
    public Map<String, Object> add(Collection collection) {
        int i = collectionMapper.insert(collection);

        if(i > 0) {
            Map<String, Object> map = new HashMap<>();

            Collection data = collectionMapper.selectByPrimaryKey(collection.getId());


            map.put("message", "OK");
            map.put("code", 200);
            map.put("success", true);
            map.put("data", data);

            return map;
        }
        return null;
    }

    @Override
    public Map<String, Object> update(Collection collection) {
        int i = collectionMapper.updateByPrimaryKeySelective(collection);

        Map<String, Object> map = new HashMap<>();

        if(i > 0) {

            Collection data = collectionMapper.selectByPrimaryKey(collection.getId());

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
        int i = collectionMapper.deleteByPrimaryKey((long) id);

        Map<String, Object> map = new HashMap<>();

        if (i > 0) {

            map.put("code", 200);
            map.put("success", true);
            map.put("message", "OK");


            return map;
        }

        return null;
    }
}
