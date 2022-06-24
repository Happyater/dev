package com.fc.controller;

import com.fc.service.UploadImgService;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@CrossOrigin("*")
@RestController
public class UploadImgController {
    @Autowired
    private UploadImgService uploadImgService;

    @PostMapping("uploadImg")
    public ResultVo uploadImg(MultipartFile file, @RequestParam String type) {
        return uploadImgService.uploadImg(file, type);
    }
}
