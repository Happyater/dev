package com.fc.service.impl;

import com.fc.service.UploadImgService;
import com.fc.vo.ResultVo;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class UploadImgServiceImpl implements UploadImgService {
    @Override
    public ResultVo uploadImg(MultipartFile file, String type) {
        String path = "D:/apache-tomcat-8.5.37-windows-x64/apache-tomcat-8.5.37/webapps/upload/" + type;

        File pathFile = new File(path);

        if(pathFile.exists()) {
            pathFile.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        String format = simpleDateFormat.format(new Date());

        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        originalFilename = format + suffix;

        ResultVo resultVo = new ResultVo();

        try {
            file.transferTo(new File(pathFile, originalFilename));

            Map<String, Object> map = new HashMap<>();

            map.put("imgurl", "http://localhost:8081/upload/Poverty-Alleviation/" + type + "/" + originalFilename);

            resultVo.setCode(200);
            resultVo.setSuccess(true);
            resultVo.setMessage("上传成功！！！");
            resultVo.setData(map);
        } catch (IOException e) {
            resultVo.setCode(-100);
            resultVo.setSuccess(false);
            resultVo.setMessage("上传失败！！！");

        }
        return resultVo;
    }
}
