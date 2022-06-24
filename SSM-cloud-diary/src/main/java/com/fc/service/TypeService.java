package com.fc.service;

import com.fc.vo.ResultInfo;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

public interface TypeService {
    ModelAndView list();


    ResultInfo addOrUpdate(int typeId, String typeName, int userId);

    ResultInfo delete(int id);
}
