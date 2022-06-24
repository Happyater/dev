package com.fc.service.impl;

import com.fc.dao.AlleviationMapper;
import com.fc.entity.Alleviation;
import com.fc.service.AlleviationService;
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
public class AlleviationServiceImpl implements AlleviationService {
    @Autowired
    private AlleviationMapper alleviationMapper;


    @Override
    public ResultVo getList(Integer pageNum, Integer pageSize, Long id) {
        ResultVo vo;

        DateVo<Alleviation> alleviationDateVo;

        List<Alleviation> alleviations;

        if(id != null) {
            alleviations = new ArrayList<>();

            Alleviation alleviation = alleviationMapper.selectByPrimaryKey(id);

            if(alleviation == null) {
                alleviationDateVo = new DateVo<>(0L, alleviations, pageNum, pageSize);

                vo = new ResultVo(4000, "没有这个项目", false, alleviationDateVo);
            } else {
                click(alleviation.getId(), null);

                alleviation.setClickNum(alleviation.getClickNum() + 1);

                alleviations.add(alleviation);

                alleviationDateVo = new DateVo<>(1L, alleviations, pageNum, pageSize);

                vo = new ResultVo(200,  "查到这个项目", true, alleviationDateVo);
            }
        } else {
            PageHelper.startPage(pageNum, pageSize);

            alleviations = alleviationMapper.selectByExample(null);

            if(alleviations.size() == 0) {
                alleviationDateVo = new DateVo<>(0L, alleviations, pageNum, pageSize);

                vo = new ResultVo(4100, "没有项目", false, alleviationDateVo);

            } else {
                PageInfo<Alleviation> pageInfo = new PageInfo<>(alleviations);

                alleviationDateVo = new DateVo<>(pageInfo.getTotal(), alleviations, pageNum, pageSize);

                vo = new ResultVo(200, "项目查询成功", true, alleviationDateVo);
            }
        }
        return vo;
    }

    @Override
    public ResultVo add(Alleviation alleviation) {
        ResultVo vo;

        if(alleviation.getCreateTime() == null) {
            alleviation.setCreateTime(new Date());
        }

        int i =alleviationMapper.insertSelective(alleviation);

        if (i > 0) {
            vo = new ResultVo(200, "添加项目成功", true, alleviation);
        } else {
            vo = new ResultVo(4200, "添加项目失败", false, null);
        }
        return vo;
    }

    @Override
    public ResultVo update(Alleviation alleviation) {
        ResultVo vo;

        int i = alleviationMapper.updateByPrimaryKeySelective(alleviation);

        if(i > 0) {
            alleviation = alleviationMapper.selectByPrimaryKey(alleviation.getId());

            vo = new ResultVo(200, "修改项目成功", true, alleviation);
        } else {
            vo = new ResultVo(4300, "修改项目失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo delete(Long id) {
        ResultVo vo;

        int i = alleviationMapper.deleteByPrimaryKey(id);

        if(id > 0) {
            vo = new ResultVo(200, "删除项目成功", true, null);
        } else {
            vo = new ResultVo(4400, "删除项目失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo click(Long id, Date lastClickTime) {
        if (lastClickTime == null) {
            lastClickTime = new Date();
        }

        Integer affectedRows = alleviationMapper.click(id, lastClickTime);

        ResultVo vo;

        if (affectedRows > 0) {
            vo = new ResultVo(200, "播放量加1成功！！", true, null);
        } else {
            vo = new ResultVo(5000, "播放量加1失败！！", false, null);
        }

        return vo;
    }
}
