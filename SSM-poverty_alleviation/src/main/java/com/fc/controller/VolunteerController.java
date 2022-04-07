package com.fc.controller;

import com.fc.entity.VolunteerRecruitment;
import com.fc.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("volunteer")
public class VolunteerController {
    @Autowired
    private VolunteerService volunteerService;

    @RequestMapping("getList")
    public Map<String, Object> getList(int pageNum, int pageSize) {
        return volunteerService.getList(pageNum, pageSize);
    }

    @RequestMapping("add")
    public Map<String, Object> add(VolunteerRecruitment volunteerRecruitment) {
        return volunteerService.add(volunteerRecruitment);
    }

    @RequestMapping("update")
    public Map<String, Object> update(VolunteerRecruitment volunteerRecruitment) {
        return volunteerService.update(volunteerRecruitment);
    }

    @RequestMapping("delete")
    public Map<String, Object> delete(int id) {
        return volunteerService.delete(id);
    }
}
