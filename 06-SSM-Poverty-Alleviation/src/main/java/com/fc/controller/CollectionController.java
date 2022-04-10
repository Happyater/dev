package com.fc.controller;

import com.fc.entity.Alleviation;
import com.fc.entity.Collection;
import com.fc.service.CollectionService;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @GetMapping("getlist")
    public ResultVo getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize,
                            Long id) {
        return collectionService.getList(pageNum, pageSize, id);
    }
    @PostMapping("add")
    public ResultVo add(Collection collection) {
        return collectionService.add(collection);
    }
    @PostMapping("update")
    public ResultVo update(Collection collection) {
        return collectionService.update(collection);
    }

    @GetMapping("delete")
    public ResultVo delete(Long id) {
        return collectionService.delete(id);
    }
}
