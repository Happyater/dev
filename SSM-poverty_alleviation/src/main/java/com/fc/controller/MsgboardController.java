package com.fc.controller;

import com.fc.dao.MessageBoardMapper;
import com.fc.entity.MessageBoard;
import com.fc.entity.MessageBoardWithBLOBs;
import com.fc.service.MsgboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("msgboard")
public class MsgboardController {
    @Autowired
    private MsgboardService msgboardService;

    @RequestMapping("getList")
    public Map<String, Object> getList(int pageNum, int pageSize) {
        return msgboardService.getListt(pageNum, pageSize);
    }

    @RequestMapping("add")
    public Map<String, Object> add(MessageBoardWithBLOBs messageBoard) {
        return msgboardService.add(messageBoard);
    }

    @RequestMapping("update")
    public Map<String, Object> update(MessageBoardWithBLOBs messageBoard) {
        return msgboardService.update(messageBoard);
    }
    @RequestMapping("delete")
    public Map<String, Object> delete(int id) {
        return msgboardService.delete(id);
    }
}
