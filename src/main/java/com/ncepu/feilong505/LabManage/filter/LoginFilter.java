package com.ncepu.feilong505.LabManage.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.feilong505.LabManage.common.ResponseBody;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * TODO
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年4月3日
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "loginFilter")
public class LoginFilter implements Filter {

	ResponseBody responseBody;

	// 例外的uri
	final static String[] exceptionUrls = new String[] { 
		"/user/login",
		"/user/wxlogin",
		"/user/regis" ,
		"/getcert"
		};

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		if ((ifException((HttpServletRequest) request)) == true)
		{
			chain.doFilter(request, response);
			return;
		}
		try {
			HttpSession session = ((HttpServletRequest) request).getSession();
			if (session == null || session.getAttribute("logined") == null) {
				responseBody = new ResponseBody();
				responseBody.error("请登录");
				response.getWriter().write(JSONObject.toJSONString(responseBody));
			} else {
				session.setMaxInactiveInterval(1800);
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			responseBody.error("发生了错误");
			response.getWriter().write(JSONObject.toJSONString(responseBody));
			e.printStackTrace();
		}

	}

	private boolean ifException(HttpServletRequest request) {
		String url = request.getRequestURI().toString();
		for (String str : exceptionUrls) {
			if (str.equals(url))
				return true;
		}
		return false;
	}

}
