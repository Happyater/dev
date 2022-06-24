package com.fc.service.impl;

import com.fc.dao.PoorMapper;
import com.fc.entity.MessageBoardWithBLOBs;
import com.fc.entity.PoorWithBLOBs;
import com.fc.service.PoorService;
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
public class PoorServiceImpl implements PoorService {
    @Autowired
    private PoorMapper poorMapper;

    @Override
    public ResultVo getList(Integer pageNum, Integer pageSize, Long id) {
        ResultVo vo;

        DateVo<PoorWithBLOBs> poorDateVo;

        List<PoorWithBLOBs> poorWithBLOBs;

        if(id != null) {
            poorWithBLOBs = new ArrayList<>();

            PoorWithBLOBs poor = poorMapper.selectByPrimaryKey(id);

            if(poor == null) {
                poorDateVo = new DateVo<>(0L, poorWithBLOBs, pageNum, pageSize);

                vo = new ResultVo(4000, "没有这个贫困户", false, poorDateVo);
            } else {
                click(poor.getId(), null);

                poor.setClickNum(poor.getClickNum() + 1);

                poorWithBLOBs.add(poor);

                poorDateVo = new DateVo<>(1L, poorWithBLOBs, pageNum, pageSize);

                vo = new ResultVo(200,  "查到这个贫困户", true, poorDateVo);
            }
        } else {
            PageHelper.startPage(pageNum, pageSize);

            poorWithBLOBs = poorMapper.selectByExampleWithBLOBs(null);

            if(poorWithBLOBs.size() == 0) {
                poorDateVo = new DateVo<>(0L, poorWithBLOBs, pageNum, pageSize);

                vo = new ResultVo(4100, "没有贫困户", false, poorDateVo);

            } else {
                PageInfo<PoorWithBLOBs> pageInfo = new PageInfo<>(poorWithBLOBs);

                poorDateVo = new DateVo<>(pageInfo.getTotal(), poorWithBLOBs, pageNum, pageSize);

                vo = new ResultVo(200, "贫困户查询成功", true, poorDateVo);
            }
        }
        return vo;
    }

    @Override
    public ResultVo add(PoorWithBLOBs poor) {
        ResultVo vo;

        if(poor.getCreateTime() == null) {
            poor.setCreateTime(new Date());
        }

        int i = poorMapper.insertSelective(poor);

        if (i > 0) {
            vo = new ResultVo(200, "添加贫困户成功", true, poor);
        } else {
            vo = new ResultVo(4200, "添加贫困户失败", false, null);
        }
        return vo;
    }

    @Override
    public ResultVo update(PoorWithBLOBs poor) {
        ResultVo vo;

        int i = poorMapper.updateByPrimaryKeySelective(poor);

        if(i > 0) {
            poor = poorMapper.selectByPrimaryKey(poor.getId());

            vo = new ResultVo(200, "修改贫困户成功", true, poor);
        } else {
            vo = new ResultVo(4300, "修改贫困户失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo delete(Long id) {
        ResultVo vo;

        int i = poorMapper.deleteByPrimaryKey(id);

        if(id > 0) {
            vo = new ResultVo(200, "删除贫困户成功", true, null);
        } else {
            vo = new ResultVo(4400, "删除贫困户失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo click(Long id, Date lastClickTime) {
        if (lastClickTime == null) {
            lastClickTime = new Date();
        }

        Integer affectedRows = poorMapper.click(id, lastClickTime);

        ResultVo vo;

        if (affectedRows > 0) {
            vo = new ResultVo(200, "点击量加1成功！！", true, null);
        } else {
            vo = new ResultVo(5000, "点击量加1失败！！", false, null);
        }

        return vo;
    }
}
