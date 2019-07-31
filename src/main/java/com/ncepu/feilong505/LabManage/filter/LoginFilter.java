package com.ncepu.feilong505.LabManage.filter;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.feilong505.LabManage.common.ResponseBody;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

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
	final static String[] exceptionUrls = new String[]{
			"^/user/login$",
			"^/user/wxlogin$",
			"^/user/regis$",
			"^/getcert$",
			"^/image/user/get/*",
			"^/file/user/get/*"
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
		if ((ifException((HttpServletRequest) request)) == true) {
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
		Pattern pattern = null;

		for (String str : exceptionUrls) {
			pattern=Pattern.compile(str);
			if(pattern.matcher(url).find())
			{
				return true;
			}
//			if (str.equals(url))
//				return true;
		}
		return false;
	}

}
