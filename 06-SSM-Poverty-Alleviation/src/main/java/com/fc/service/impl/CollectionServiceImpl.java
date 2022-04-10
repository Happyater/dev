package com.fc.service.impl;

import com.fc.dao.CollectionMapper;
import com.fc.entity.Alleviation;
import com.fc.entity.Collection;
import com.fc.service.CollectionService;
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
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public ResultVo getList(Integer pageNum, Integer pageSize, Long id) {
        ResultVo vo;

        DateVo<Collection> collectionDateVo;

        List<Collection> collections;

        if(id != null) {
            collections = new ArrayList<>();

            Collection collection = collectionMapper.selectByPrimaryKey(id);

            if(collection == null) {
                collectionDateVo = new DateVo<>(0L, collections, pageNum, pageSize);

                vo = new ResultVo(4000, "没有这个收藏表", false, collectionDateVo);
            } else {
                collections.add(collection);

                collectionDateVo = new DateVo<>(1L, collections, pageNum, pageSize);

                vo = new ResultVo(1000,  "查到这个收藏表", true, collectionDateVo);
            }
        } else {
            PageHelper.startPage(pageNum, pageSize);

            collections = collectionMapper.selectByExample(null);

            if(collections.size() == 0) {
                collectionDateVo = new DateVo<>(0L, collections, pageNum, pageSize);

                vo = new ResultVo(4100, "没有收藏表", false, collectionDateVo);

            } else {
                PageInfo<Collection> pageInfo = new PageInfo<>(collections);

                collectionDateVo = new DateVo<>(pageInfo.getTotal(), collections, pageNum, pageSize);

                vo = new ResultVo(1100, "收藏表查询成功", true, collectionDateVo);
            }
        }
        return vo;
    }

    @Override
    public ResultVo add(Collection collection) {
        ResultVo vo;

        if(collection.getCreateTime() == null) {
            collection.setCreateTime(new Date());
        }

        int i =collectionMapper.insertSelective(collection);

        if (i > 0) {
            vo = new ResultVo(1200, "添加收藏表成功", true, collection);
        } else {
            vo = new ResultVo(4200, "添加收藏表失败", false, null);
        }
        return vo;
    }

    @Override
    public ResultVo update(Collection collection) {
        ResultVo vo;

        int i = collectionMapper.updateByPrimaryKeySelective(collection);

        if(i > 0) {
            collection = collectionMapper.selectByPrimaryKey(collection.getId());

            vo = new ResultVo(1300, "修改收藏表成功", true, collection);
        } else {
            vo = new ResultVo(4300, "修改收藏表失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo delete(Long id) {
        ResultVo vo;

        int i = collectionMapper.deleteByPrimaryKey(id);

        if(id > 0) {
            vo = new ResultVo(1400, "删除收藏表成功", true, null);
        } else {
            vo = new ResultVo(4400, "删除收藏表失败", false, null);
        }

        return vo;
    }
}
