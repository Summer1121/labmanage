package com.ncepu.feilong505.LabManage.service.impl;

import com.ncepu.feilong505.LabManage.common.FileUtil;
import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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

			res.put("fileName", fileName);
			responseBody.success(res);
		} catch (Exception e) {
			responseBody.error();
			e.printStackTrace();
		}
		return responseBody;
	}

}
