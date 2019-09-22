package com.ncepu.feilong505.LabManage.common;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * TODO 用于发送短信的工具类
 *
 * @author xtysummer1121@foxmail.com
 * @className SMSUtil
 * @date 2019-09-14
 */
@Data
public class SMSUtil {
	public static int sendMessage(MessageParam param) {
		String urlStr = "https://api.sms.jpush.cn/v1/messages";
		HttpsURLConnection connection = null;
		OutputStream dataOut = null;
		BufferedReader reader = null;
		String line = null;

		try {
			URL url = new URL(urlStr);
			connection = (HttpsURLConnection) url.openConnection();// 根据URL生成HttpURLConnection
			connection.setDoOutput(true);// 设置是否向connection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true,默认情况下是false
			connection.setDoInput(true); // 设置是否从connection读入，默认情况下是true;
			connection.setRequestMethod("POST");// 设置请求方式为post,默认GET请求
			connection.setUseCaches(false);// post请求不能使用缓存设为false
			connection.setConnectTimeout(3000);// 连接主机的超时时间
			connection.setReadTimeout(3000);// 从主机读取数据的超时时间
			connection.setInstanceFollowRedirects(true);// 设置该HttpURLConnection实例是否自动执行重定向
//			connection.setRequestProperty("connection", "Keep-Alive");// 连接复用
			connection.setRequestProperty("charset", "utf-8");
			//本内容，来自极光api
			connection.setRequestProperty("Authorization", "Basic " +
					"ZmY3OGNjNTdiY2JhNDE1MWYyYjdhNzg0OjBiOGZjNGJmZGMzNTJlNDk2MmM2ZjIzNQ==");

			connection.setRequestProperty("Content-Type", "application/json");
//			connection.setRequestProperty("Authorization", "Bearer 66cb225f1c3ff0ddfdae31rae2b57488aadfb8b5e7");
			connection.connect();// 建立TCP连接,getOutputStream会隐含的进行connect,所以此处可以不要

//			dataOut = new DataOutputStream(connection.getOutputStream());// 创建输入输出流,用于往连接里面输出携带的参数
			String body = "{" +
					"\"mobile\":\"" + param.getMobile() + "\"," +
					"\"sign_id\":\"" + param.getSignId() + "\"," +
					"\"temp_id\":\"" + param.getTempId() + "\"," +
					"\"temp_para\":{" + param.getTempPara() + "}" +
					"}";
			dataOut = connection.getOutputStream();
			dataOut.write(body.getBytes());
			dataOut.flush();
			dataOut.close();

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 发送http请求
				StringBuilder result = new StringBuilder();
				// 循环读取流
				while ((line = reader.readLine()) != null) {
					result.append(line).append(System.getProperty("line.separator"));
				}
				System.out.println("短信发送结果" + result.toString());
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Data
	@Accessors(chain = true)
	public static class MessageParam {
		//手机号码
		String mobile;
		//签名ID，该字段为空则使用应用默认签名
		Integer signId;
		//模板ID
		Integer tempId;
		//模板参数,需要替换的参数名和 value 的键值对
		String tempPara;
	}
}
