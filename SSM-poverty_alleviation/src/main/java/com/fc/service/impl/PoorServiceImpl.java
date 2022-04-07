package com.fc.service.impl;

import com.fc.dao.PoorMapper;
import com.fc.entity.Alleviation;
import com.fc.entity.Poor;
import com.fc.entity.PoorWithBLOBs;
import com.fc.service.PoorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PoorServiceImpl implements PoorService {
    @Autowired
    private PoorMapper poorMapper;

    @Override
    public Map<String, Object> getList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<PoorWithBLOBs> poorWithBLOBs = poorMapper.selectByExampleWithBLOBs(null);

        PageInfo<PoorWithBLOBs> pageInfo = new PageInfo<>(poorWithBLOBs);

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
}
