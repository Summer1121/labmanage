package com.ncepu.feilong505.LabManage.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * TODO
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年5月16日
 */
@Component
@WebFilter(urlPatterns = { "/*" })
@Order(1)
public class CROSFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	    throws IOException, ServletException {
	HttpServletRequest request = (HttpServletRequest) req;
	HttpServletResponse response = (HttpServletResponse) res;
	String origin = request.getHeader("Origin");
	response.setHeader("Access-Control-Allow-Origin", origin);
	response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	response.setHeader("Access-Control-Allow-Credentials", "true");
	response.setHeader("Access-Control-Allow-Headers",
		"Origin, X-Requested-With, Content-Type, Accept, Authorization,Cookie,ResponseType");

	chain.doFilter(req, res);
    }
}
