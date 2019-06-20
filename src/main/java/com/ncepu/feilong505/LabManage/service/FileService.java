package com.ncepu.feilong505.LabManage.service;

import org.springframework.web.multipart.MultipartFile;

import com.ncepu.feilong505.LabManage.common.ResponseBody;

/**
 * TODO 图片处理
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年6月9日
 */
public interface FileService {
    /**
     * 
     * TODO 上传图片，获得图片url
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月9日
     * @param imageFile
     * @return
     */
    public ResponseBody uploadImage(MultipartFile imageFile);

    /**
     * TODO  上传普通文件
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月14日
     * @param file
     * @return
     */
    public ResponseBody uploadFile(MultipartFile file);
}
