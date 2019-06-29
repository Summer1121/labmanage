package com.ncepu.feilong505.LabManage.common;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * TODO 文件操作
 *
 * @author xtysummer1121@foxmail.com
 * @date 2019年6月9日
 */
public class FileUtil {

	/**
	 * TODO 递归创建文件路径
	 *
	 * @param file
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年6月9日
	 */
	public static void createFolder(String file) {
		File myFile = new File(file);
		if (!myFile.exists()) {
			boolean a = myFile.mkdirs();
		}
	}

	/**
	 * TODO 上传图片并压缩
	 *
	 * @param file     流式文件
	 * @param path     存储路径
	 * @param fileName 文件名
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年6月9日
	 */
	public static void uploadImage(MultipartFile file, String path, String fileName) {
		try {
			InputStream inputStream = file.getInputStream();
			createFolder(path);
			Files.write(Paths.get(path + fileName), file.getBytes());
			// 生成压缩图片
			zipImage(path, fileName);
			// Files.copy(inputStream, Paths.get(path + imageName),
			// StandardCopyOption.REPLACE_EXISTING);// 这里指定了下载的位置
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * TODO 上传文件
	 *
	 * @param file
	 * @param path
	 * @param fileName
	 * @return void
	 * @className FileUtil
	 * @author xtysummer1121@foxmail.com
	 * @methodName uploadFile
	 * @date 2019-06-29
	 */
	public static void uploadFile(MultipartFile file, String path, String fileName) {
		try {
			InputStream inputStream = file.getInputStream();
			createFolder(path);
			Files.write(Paths.get(path + fileName), file.getBytes());
			// Files.copy(inputStream, Paths.get(path + imageName),
			// StandardCopyOption.REPLACE_EXISTING);// 这里指定了下载的位置
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * TODO 图片压缩
	 *
	 * @param path
	 * @param fileName
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年6月10日
	 */
	public static void zipImage(String path, String fileName) {
		try {
			Thumbnails.of(path + fileName).size(320, 240).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
