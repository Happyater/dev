package com.fc.controller;

import com.fc.entity.Alleviation;
import com.fc.service.AlleviationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("alleviation")
public class AlleviationController {
    @Autowired
    private AlleviationService alleviationService;

    @RequestMapping("getList")
    public Map<String, Object> getList(int pageNum, int pageSize) {
        return alleviationService.getList(pageNum, pageSize);
    }

    @RequestMapping("add")
    public Map<String, Object> add(@RequestBody Alleviation alleviation) {
        return alleviationService.add(alleviation);
    }

    @RequestMapping("update")
    public Map<String, Object> update(@RequestBody Alleviation alleviation) {
        return alleviationService.update(alleviation);
    }

    @RequestMapping("delete")
    public Map<String, Object> delete(int id) {
        return alleviationService.delete(id);
    }
}
