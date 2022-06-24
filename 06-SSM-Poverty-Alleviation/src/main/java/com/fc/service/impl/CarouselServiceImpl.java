package com.fc.service.impl;

import com.fc.dao.CarouselMapper;
import com.fc.entity.Carousel;
import com.fc.service.CarouselService;
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
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public ResultVo getList(Integer pageNum, Integer pageSize, Integer id) {
        ResultVo vo;

        DateVo<Carousel> carouselDateVo;

        List<Carousel> carousels;

        if(id != null) {
            carousels = new ArrayList<>();

            Carousel carousel = carouselMapper.selectByPrimaryKey(id);

            if(carousel == null) {
                carouselDateVo = new DateVo<>(0L, carousels, pageNum, pageSize);

                vo = new ResultVo(4000, "查无此图", false, carouselDateVo);
            } else {
                carousels.add(carousel);

                carouselDateVo = new DateVo<>(1L, carousels, pageNum, pageSize);

                vo = new ResultVo(200,  "有这张图", true, carouselDateVo);
            }
        } else {
            PageHelper.startPage(pageNum, pageSize);

            carousels = carouselMapper.selectByExample(null);

            if(carousels.size() == 0) {
                carouselDateVo = new DateVo<>(0L, carousels, pageNum, pageSize);

                vo = new ResultVo(4100, "没有图片", false, carouselDateVo);

            } else {
                PageInfo<Carousel> pageInfo = new PageInfo<>(carousels);

                carouselDateVo = new DateVo<>(pageInfo.getTotal(), carousels, pageNum, pageSize);

                vo = new ResultVo(200, "图片查询成功", true, carouselDateVo);
            }
        }
        return vo;
    }

    @Override
    public ResultVo add(Carousel carousel) {
        ResultVo vo;

        if(carousel.getAvailable() == null) {
            carousel.setAvailable(false);
        }

        int i = carouselMapper.insertSelective(carousel);

        if (i > 0) {
            vo = new ResultVo(200, "添加图片成功", true, carousel);
        } else {
            vo = new ResultVo(4200, "添加图片失败", false, null);
        }
        return vo;
    }

    @Override
    public ResultVo update(Carousel carousel) {
        ResultVo vo;

        int i = carouselMapper.updateByPrimaryKeySelective(carousel);

        if(i > 0) {
            carousel = carouselMapper.selectByPrimaryKey(carousel.getId());

            vo = new ResultVo(200, "修改图片成功", true, carousel);
        } else {
            vo = new ResultVo(4300, "修改图片失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo delete(Integer id) {
        ResultVo vo;

        int i = carouselMapper.deleteByPrimaryKey(id);

        if(id > 0) {
            vo = new ResultVo(200, "删除图片成功", true, null);
        } else {
            vo = new ResultVo(4400, "删除图片失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo changeStatus(Integer id) {
        Integer i = carouselMapper.changeStatus(id);

        ResultVo vo;
        if(i > 0) {
            vo = new ResultVo(200, "修改图片状态成功", true, null);
        } else {
            vo = new ResultVo(4500, "修改图片状态失败", false, null);
        }

        return vo;
    }


}
