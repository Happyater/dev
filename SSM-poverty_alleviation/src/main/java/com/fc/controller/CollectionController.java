package com.fc.controller;

import com.fc.entity.Collection;
import com.fc.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @RequestMapping("getList")
    public Map<String, Object> getList(int pageNum, int pageSize) {
        return collectionService.getList(pageNum, pageSize);
    }

    @RequestMapping("add")
    public Map<String, Object> add(Collection collection) {
        return collectionService.add(collection);
    }

    @RequestMapping("update")
    public Map<String, Object> update(Collection collection) {
        return collectionService.update(collection);
    }

    @RequestMapping("delete")
    public Map<String, Object> delete(int id) {
        return collectionService.delete(id);
    }
}
