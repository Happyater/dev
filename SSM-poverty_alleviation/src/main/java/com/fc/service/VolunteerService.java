package com.fc.service;

import com.fc.entity.VolunteerRecruitment;

import java.util.Map;

public interface VolunteerService {
    Map<String, Object> getList(int pageNum, int pageSize);

    Map<String, Object> add(VolunteerRecruitment volunteerRecruitment);

    Map<String, Object> update(VolunteerRecruitment volunteerRecruitment);

    Map<String, Object> delete(int id);
}
