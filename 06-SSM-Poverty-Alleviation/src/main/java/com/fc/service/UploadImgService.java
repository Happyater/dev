package com.fc.service;

import com.fc.vo.ResultVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface UploadImgService {
    ResultVo uploadImg(MultipartFile file, String type);
}
