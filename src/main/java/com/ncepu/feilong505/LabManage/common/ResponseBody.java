package com.ncepu.feilong505.LabManage.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * TODO 返回信息的通用承载消息体
 *
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月13日
 */
@Data
@Accessors(chain = true)
public class ResponseBody implements Serializable {

	/**
	 * 响应业务状态
	 */
	private int status;

	/**
	 * 返回的数据
	 */
	private Object data;

	/**
	 * 错误信息
	 */
	private String error;

	/**
	 * 页大小
	 */
	private Integer pageSize;

	/**
	 * 当前页数
	 */
	private Integer curPage;

	/**
	 * 重定向URL
	 */
	private String reURL;

	/**
	 * 总数据量
	 */
	private Integer total;

	/**
	 * 小程序专用字段，对于小程序的请求，每次都需要返回本字段作为session凭证
	 */
	private String sessionId;

	public ResponseBody success() {
		this.status = Status.SUCCESS.getState();
		return this;
	}

	public ResponseBody success(Object object) {
		this.status = Status.SUCCESS.getState();
		this.data = object;
		return this;
	}

	/**
	 * TODO
	 *
	 * @param msg 错误信息
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月13日
	 */
	public ResponseBody error(String msg) {
		this.status = Status.ERROR.getState();
		this.error = msg;
		return this;
	}

	public ResponseBody error() {
		this.status = Status.ERROR.getState();
		this.error = "发生了错误";
		return this;
	}
}
