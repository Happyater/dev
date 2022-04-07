package com.fc.service.impl;

import com.fc.dao.MessageBoardMapper;
import com.fc.entity.Alleviation;
import com.fc.entity.Collection;
import com.fc.entity.MessageBoard;
import com.fc.entity.MessageBoardWithBLOBs;
import com.fc.service.MsgboardService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MsgboardServiceImpl implements MsgboardService {
    @Autowired
    private MessageBoardMapper messageBoardMapper;


    @Override
    public Map<String, Object> getListt(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<MessageBoardWithBLOBs> messageBoardWithBLOBs = messageBoardMapper.selectByExampleWithBLOBs(null);

        PageInfo<MessageBoardWithBLOBs> pageInfo = new PageInfo<>(messageBoardWithBLOBs);

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

    @Override
    public Map<String, Object> add(MessageBoardWithBLOBs messageBoard) {
        int i = messageBoardMapper.insert(messageBoard);

        if (i > 0) {
            Map<String, Object> map = new HashMap<>();

            MessageBoard data = messageBoardMapper.selectByPrimaryKey(messageBoard.getId());

            map.put("message", "OK");
            map.put("code", 200);
            map.put("success", true);
            map.put("data", data);

            return map;
        }
        return null;
    }

    @Override
    public Map<String, Object> update(MessageBoardWithBLOBs messageBoard) {
        int i = messageBoardMapper.updateByPrimaryKeySelective(messageBoard);

        Map<String, Object> map = new HashMap<>();

        if(i > 0) {

            MessageBoardWithBLOBs data = messageBoardMapper.selectByPrimaryKey(messageBoard.getId());

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
        int i = messageBoardMapper.deleteByPrimaryKey((long) id);

        Map<String, Object> map = new HashMap<>();

        if (i > 0) {

            map.put("code", 200);
            map.put("message", "OK");
            map.put("success", true);

            return map;
        }

        return null;
    }
}
