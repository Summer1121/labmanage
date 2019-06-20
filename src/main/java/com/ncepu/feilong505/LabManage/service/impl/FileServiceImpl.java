package com.ncepu.feilong505.LabManage.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ncepu.feilong505.LabManage.common.FileUtil;
import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.service.FileService;

/**
 * TODO
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年6月9日
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${image.user}")
    public String userImagePath;

    @Value("${file.user}")
    public String userFilePath;

    @Override
    public ResponseBody uploadImage(MultipartFile imageFile) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    // 设置文件名
	    String imageName = new Date().getTime() + "-" + imageFile.getOriginalFilename();
	    // 上传文件
	    FileUtil.uploadImage(imageFile, userImagePath, imageName);
	    // 设置返回内容
	    Map<String, Object> res = new HashMap<String, Object>();
	    res.put("imageName", imageName);
	    responseBody.success(res);
	} catch (Exception e) {
	    responseBody.error();
	    e.printStackTrace();
	}
	return responseBody;
    }

    @Override
    public ResponseBody uploadFile(MultipartFile file) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    // 设置文件名
	    String fileName = new Date().getTime() + "-" + file.getOriginalFilename();
	    // 上传文件
	    FileUtil.uploadFile(file, userFilePath, fileName);
	    // 设置返回内容
	    Map<String, Object> res = new HashMap<String, Object>();
	    res.put("fileName", file);
	    responseBody.success(res);
	} catch (Exception e) {
	    responseBody.error();
	    e.printStackTrace();
	}
	return responseBody;
    }

}
