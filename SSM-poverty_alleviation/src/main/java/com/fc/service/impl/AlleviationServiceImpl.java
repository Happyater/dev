package com.fc.service.impl;

import com.fc.dao.AlleviationMapper;
import com.fc.entity.Alleviation;
import com.fc.service.AlleviationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlleviationServiceImpl implements AlleviationService {
    @Autowired
    private AlleviationMapper alleviationMapper;

    @Override
    public Map<String, Object> getList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<Alleviation> alleviations = alleviationMapper.selectByExampleWithBLOBs(null);

        PageInfo<Alleviation> pageInfo = new PageInfo<>(alleviations);

        Map<String, Object> data = new HashMap<>();

        data.put("pageNum", pageNum);
        data.put("pageSize", pageSize);
        data.put("total", pageInfo.getTotal());
        data.put("list", pageInfo.getList());

        Map<String, Object> map = new HashMap<>();

        map.put("code", 200);
        map.put("data", data);
        map.put("message", "OK");
        map.put("success", true);


        return map;
    }

    @Override
    public Map<String, Object> add(Alleviation alleviation) {
        int i = alleviationMapper.insert(alleviation);

        if (i > 0) {
            Map<String, Object> map = new HashMap<>();

            Alleviation data = alleviationMapper.selectByPrimaryKey(alleviation.getId());

            map.put("message", "OK");
            map.put("code", 200);
            map.put("success", true);
            map.put("data", data);

            return map;
        }
        return null;
    }

    @Override
    public Map<String, Object> update(Alleviation alleviation) {
        int i = alleviationMapper.updateByPrimaryKeySelective(alleviation);

        Map<String, Object> map = new HashMap<>();

        if(i > 0) {

            Alleviation data = alleviationMapper.selectByPrimaryKey(alleviation.getId());

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
        int i = alleviationMapper.deleteByPrimaryKey((long) id);

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
