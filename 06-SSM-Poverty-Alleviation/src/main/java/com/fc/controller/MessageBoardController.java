package com.fc.controller;

import com.fc.entity.Alleviation;
import com.fc.entity.MessageBoardWithBLOBs;
import com.fc.service.MessageBoardService;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin("*")
@RestController
@RequestMapping("msgboard")
public class MessageBoardController {
    @Autowired
    private MessageBoardService messageBoardService;

    @GetMapping("getlist")
    public ResultVo getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize,
                            Long id) {
        return messageBoardService.getList(pageNum, pageSize, id);
    }

    @PostMapping("add")
    public ResultVo add(@RequestBody MessageBoardWithBLOBs messageBoard) {
        return messageBoardService.add(messageBoard);
    }

    @PostMapping("update")
    public ResultVo update(@RequestBody MessageBoardWithBLOBs messageBoard) {
        return messageBoardService.update(messageBoard);
    }

    @GetMapping("delete")
    public ResultVo delete(Long id) {
        return messageBoardService.delete(id);
    }

}
