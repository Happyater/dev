package com.fc.service.impl;

import com.fc.dao.VolunteerRecruitmentMapper;
import com.fc.entity.Alleviation;
import com.fc.entity.Carousel;
import com.fc.entity.User;
import com.fc.entity.VolunteerRecruitment;
import com.fc.service.VolunteerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Autowired
    private VolunteerRecruitmentMapper volunteerRecruitmentMapper;

    @Override
    public Map<String, Object> getList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<VolunteerRecruitment> volunteerRecruitments = volunteerRecruitmentMapper.selectByExampleWithBLOBs(null);

        PageInfo<VolunteerRecruitment> pageInfo = new PageInfo<>(volunteerRecruitments);

        Map<String, Object> data = new HashMap<>();
        Map<String, Object> map = new HashMap<>();

        data.put("pageSize", pageSize);
        data.put("pageNum", pageNum);
        data.put("list", pageInfo.getList());
        data.put("total", pageInfo.getTotal());

        map.put("code", 200);
        map.put("data", data);
        map.put("message", "OK");
        map.put("success", true);


        return map;
    }

    @Override
    public Map<String, Object> add(VolunteerRecruitment volunteerRecruitment) {
        int i = volunteerRecruitmentMapper.insert(volunteerRecruitment);

        if (i > 0) {
            Map<String, Object> map = new HashMap<>();

            VolunteerRecruitment data = volunteerRecruitmentMapper.selectByPrimaryKey(volunteerRecruitment.getId());

            map.put("message", "OK");
            map.put("code", 200);
            map.put("success", true);
            map.put("data", data);

            return map;
        }
        return null;
    }

    @Override
    public Map<String, Object> update(VolunteerRecruitment volunteerRecruitment) {
        int i = volunteerRecruitmentMapper.updateByPrimaryKeySelective(volunteerRecruitment);

        Map<String, Object> map = new HashMap<>();

        if(i > 0) {

            VolunteerRecruitment data = volunteerRecruitmentMapper.selectByPrimaryKey(volunteerRecruitment.getId());

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

        Map<String, Object> map = new HashMap<>();

        int i = volunteerRecruitmentMapper.deleteByPrimaryKey((long) id);

        if (i > 0) {
            map.put("code", 200);
            map.put("message", "OK");
            map.put("success", true);

            return map;
        }

        return null;
    }
}
