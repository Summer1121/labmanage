package com.ncepu.feilong505.LabManage.service;

import org.springframework.web.multipart.MultipartFile;

import com.ncepu.feilong505.LabManage.common.ResponseBody;


/**
 * TODO 图片上传
 *
 * @author xtysummer1121@foxmail.com
 * @className com.ncepu.feilong505.LabManage.service.FileService
 * @methodName
 * @date 2019-06-29
 * @return
 */
public interface FileService {
	/**
	 * TODO 上传图片，获得图片url
	 *
	 * @param imageFile 上传的流图片
	 * @return com.ncepu.feilong505.LabManage.common.ResponseBody
	 * @className FileService
	 * @author xtysummer1121@foxmail.com
	 * @methodName uploadImage
	 * @date 2019-06-29
	 */
	public ResponseBody uploadImage(MultipartFile imageFile);


	/**
	 * TODO 上传文件，获取文件url
	 *
	 * @param file 上传的流文件
	 * @return com.ncepu.feilong505.LabManage.common.ResponseBody
	 * @className FileService
	 * @author xtysummer1121@foxmail.com
	 * @methodName uploadFile
	 * @date 2019-06-29
	 */
	public ResponseBody uploadFile(MultipartFile file);

}
