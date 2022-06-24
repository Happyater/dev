package com.fc.controller;

import com.fc.service.TypeService;
import com.fc.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("list")
    public ModelAndView list() {
        return typeService.list();
    }

    @PostMapping("addOrUpdate")
    public ResultInfo addOrUpdate(@RequestParam(name = "id", defaultValue = "0") int typeId,
                                  @RequestParam String typeName,
                                  @RequestParam( defaultValue = "-1") int userId) {
        return typeService.addOrUpdate(typeId, typeName, userId);
    }

    @GetMapping("delete")
    public ResultInfo delete(@RequestParam int id) {
        return typeService.delete(id);
    }
}
