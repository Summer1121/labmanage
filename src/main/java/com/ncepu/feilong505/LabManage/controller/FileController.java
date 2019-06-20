package com.ncepu.feilong505.LabManage.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.feilong505.LabManage.service.FileService;

import org.springframework.web.multipart.MultipartFile;

/**
 * TODO 文件操作控制器
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年6月9日
 */
@Controller
@RequestMapping(method = RequestMethod.POST)
public class FileController {

    @Autowired
    FileService fileService;

    /**
     * 
     * TODO 上传图片，返回文件名，可以使用/image/user/get+[thumbnail.]+文件名 获取原图或者缩略图
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月14日
     * @param image
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/upload/image")
    public String uploadUserImage(@RequestParam("file") MultipartFile image) {
	return JSONObject.toJSONString(fileService.uploadImage(image));
    }

    /**
     * 
     * TODO 上传文件，返回文件名，可以使用/file/user/get+文件名 获取文件  
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月14日
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/upload/file")
    public String uploadUserFile(@RequestParam("file") MultipartFile file) {
	return JSONObject.toJSONString(fileService.uploadFile(file));
    }
//    /**
//     * 
//     * TODO 获取用户的图片
//     * 
//     * @author xtysummer1121@foxmail.com
//     * @date 2019年6月10日
//     * @param fileName
//     * @return
//     */
//    @RequestMapping(value = "/user/get/{fileName}",method = RequestMethod.GET)
//    public String getUserImage(@PathVariable String fileName) {
//	return fileName;
//    }
}
