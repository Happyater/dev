package com.fc.service.impl;

import com.fc.dao.MessageBoardMapper;
import com.fc.entity.Collection;
import com.fc.entity.MessageBoard;
import com.fc.entity.MessageBoardWithBLOBs;
import com.fc.service.MessageBoardService;
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
public class MessageBoardServiceImpl implements MessageBoardService {
    @Autowired
    private MessageBoardMapper messageBoardMapper;

    @Override
    public ResultVo getList(Integer pageNum, Integer pageSize, Long id) {
        ResultVo vo;

        DateVo<MessageBoardWithBLOBs> messageBoardDateVo;

        List<MessageBoardWithBLOBs> messageBoards;

        if(id != null) {
            messageBoards = new ArrayList<>();

            MessageBoardWithBLOBs messageBoardWithBLOBs = messageBoardMapper.selectByPrimaryKey(id);

            if(messageBoardWithBLOBs == null) {
                messageBoardDateVo = new DateVo<>(0L, messageBoards, pageNum, pageSize);

                vo = new ResultVo(4000, "没有这个留言板", false, messageBoardDateVo);
            } else {
                messageBoards.add(messageBoardWithBLOBs);

                messageBoardDateVo = new DateVo<>(1L, messageBoards, pageNum, pageSize);

                vo = new ResultVo(200,  "查到这个留言板", true, messageBoardDateVo);
            }
        } else {
            PageHelper.startPage(pageNum, pageSize);

            messageBoards = messageBoardMapper.selectByExampleWithBLOBs(null);

            if(messageBoards.size() == 0) {
                messageBoardDateVo = new DateVo<>(0L, messageBoards, pageNum, pageSize);

                vo = new ResultVo(4100, "没有留言板", false, messageBoardDateVo);

            } else {
                PageInfo<MessageBoardWithBLOBs> pageInfo = new PageInfo<>(messageBoards);

                messageBoardDateVo = new DateVo<>(pageInfo.getTotal(), messageBoards, pageNum, pageSize);

                vo = new ResultVo(200, "留言板查询成功", true, messageBoardDateVo);
            }
        }
        return vo;
    }

    @Override
    public ResultVo add(MessageBoardWithBLOBs messageBoard) {
        ResultVo vo;

        if(messageBoard.getCreateTime() == null) {
            messageBoard.setCreateTime(new Date());
        }

        int i = messageBoardMapper.insertSelective(messageBoard);

        if (i > 0) {
            vo = new ResultVo(200, "添加留言板成功", true, messageBoard);
        } else {
            vo = new ResultVo(4200, "添加留言板失败", false, null);
        }
        return vo;
    }

    @Override
    public ResultVo update(MessageBoardWithBLOBs messageBoard) {
        ResultVo vo;

        int i = messageBoardMapper.updateByPrimaryKeySelective(messageBoard);

        if(i > 0) {
            messageBoard = messageBoardMapper.selectByPrimaryKey(messageBoard.getId());

            vo = new ResultVo(200, "修改留言板成功", true, messageBoard);
        } else {
            vo = new ResultVo(4300, "修改留言板失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo delete(Long id) {
        ResultVo vo;

        int i = messageBoardMapper.deleteByPrimaryKey(id);

        if(id > 0) {
            vo = new ResultVo(200, "删除留言板成功", true, null);
        } else {
            vo = new ResultVo(4400, "删除留言板失败", false, null);
        }

        return vo;
    }
}
