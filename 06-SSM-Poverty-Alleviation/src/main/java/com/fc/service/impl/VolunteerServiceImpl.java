package com.fc.service.impl;

import com.fc.dao.VolunteerRecruitmentMapper;
import com.fc.entity.VolunteerRecruitment;
import com.fc.service.VolunteerService;
import com.fc.vo.DateVo;
import com.fc.vo.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Autowired
    private VolunteerRecruitmentMapper volunteerRecruitmentMapper;
    @Override
    public ResultVo getList(Integer pageNum, Integer pageSize, Long id) {
        ResultVo vo;

        DateVo<VolunteerRecruitment> volunteerRecruitmentDateVo;

        List<VolunteerRecruitment> volunteerRecruitments;

        if(id != null) {
            volunteerRecruitments = new ArrayList<>();

            VolunteerRecruitment volunteerRecruitment = volunteerRecruitmentMapper.selectByPrimaryKey(id);

            if(volunteerRecruitment == null) {
                volunteerRecruitmentDateVo = new DateVo<>(0L, volunteerRecruitments, pageNum, pageSize);

                vo = new ResultVo(4000, "没有这个志愿者", false, volunteerRecruitmentDateVo);
            } else {
//                click(volunteerRecruitment.getId(), null);
//
//                volunteerRecruitment.setClickNum(volunteerRecruitment.getClickNum() + 1);

                volunteerRecruitments.add(volunteerRecruitment);

                volunteerRecruitmentDateVo = new DateVo<>(1L, volunteerRecruitments, pageNum, pageSize);

                vo = new ResultVo(1000,  "查到了志愿者", true, volunteerRecruitmentDateVo);
            }
        } else {
            PageHelper.startPage(pageNum, pageSize);

            volunteerRecruitments = volunteerRecruitmentMapper.selectByExample(null);

            if(volunteerRecruitments.size() == 0) {
                volunteerRecruitmentDateVo = new DateVo<>(0L, volunteerRecruitments, pageNum, pageSize);

                vo = new ResultVo(4100, "没有志愿者", false, volunteerRecruitmentDateVo);

            } else {
                PageInfo<VolunteerRecruitment> pageInfo = new PageInfo<>(volunteerRecruitments);

                volunteerRecruitmentDateVo = new DateVo<>(pageInfo.getTotal(), volunteerRecruitments, pageNum, pageSize);

                vo = new ResultVo(1100, "志愿者查询成功", true, volunteerRecruitmentDateVo);
            }
        }
        return vo;
    }

    @Override
    public ResultVo add(VolunteerRecruitment volunteerRecruitment) {
        ResultVo vo;

        if(volunteerRecruitment.getCreateTime() == null) {
            volunteerRecruitment.setCreateTime(new Date());
        }

        int i =volunteerRecruitmentMapper.insertSelective(volunteerRecruitment);

        if (i > 0) {
            vo = new ResultVo(1200, "添加志愿者成功", true, volunteerRecruitment);
        } else {
            vo = new ResultVo(4200, "添加志愿者失败", false, null);
        }
        return vo;
    }

    @Override
    public ResultVo update(VolunteerRecruitment volunteerRecruitment) {
        ResultVo vo;

        int i = volunteerRecruitmentMapper.updateByPrimaryKeySelective(volunteerRecruitment);

        if(i > 0) {
            volunteerRecruitment = volunteerRecruitmentMapper.selectByPrimaryKey(volunteerRecruitment.getId());

            vo = new ResultVo(1300, "修改志愿者成功", true, volunteerRecruitment);
        } else {
            vo = new ResultVo(4300, "修改志愿者失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo delete(Long id) {
        ResultVo vo;

        int i = volunteerRecruitmentMapper.deleteByPrimaryKey(id);

        if(id > 0) {
            vo = new ResultVo(1400, "删除志愿者成功", true, null);
        } else {
            vo = new ResultVo(4400, "删除志愿者失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo click(Long id, Date lastClickTime) {
        if (lastClickTime == null) {
            lastClickTime = new Date();
        }

        Integer affectedRows = volunteerRecruitmentMapper.click(id, lastClickTime);

        ResultVo vo;

        if (affectedRows > 0) {
            vo = new ResultVo(1000, "点击加1成功！！", true, null);
        } else {
            vo = new ResultVo(5000, "点击加1失败！！", false, null);
        }

        return vo;
    }
}
