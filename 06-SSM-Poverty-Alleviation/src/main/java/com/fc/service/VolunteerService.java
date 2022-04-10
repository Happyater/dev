package com.fc.service;

import com.fc.entity.VolunteerRecruitment;
import com.fc.vo.ResultVo;

import java.util.Date;

public interface VolunteerService {
    ResultVo getList(Integer pageNum, Integer pageSize, Long id);

    ResultVo add(VolunteerRecruitment volunteerRecruitment);

    ResultVo update(VolunteerRecruitment volunteerRecruitment);

    ResultVo delete(Long id);

    ResultVo click(Long id, Date lastClickTime);
}
