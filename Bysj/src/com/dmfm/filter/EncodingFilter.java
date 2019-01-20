package com.dmfm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


@WebFilter(
		description = "字符编码过滤器", 
		filterName = "encodingFilter", 
		urlPatterns = { "/*" },
		initParams = { 
			@WebInitParam(name = "ENCODING", value = "UTF-8")
		} 
	)
public class EncodingFilter implements Filter {

	private static Logger log = Logger.getLogger("EncodingFilter");
	private String encoding = "";
	private String filterName = "";
	
	@Override
	public void destroy() {
		log.debug("请求销毁");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("encodingFilter:doFilter");
		//分别对请求和响应进行编码
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		log.debug("请求被"+filterName+"过滤");
		if("GET".equals(request.getMethod())){
			request = new RequestEncodingWrapper(request, encoding);
		} else {
			request.setCharacterEncoding(encoding);
		}
		response.setCharacterEncoding(encoding);
		//传输给过滤器链过滤
		chain.doFilter(request, response);
		log.debug("响应被"+filterName+"过滤");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//通过filterCongig获得初始化中编码
		System.out.println("encodingFilter:init");
		encoding = filterConfig.getInitParameter("ENCODING");
		filterName = filterConfig.getFilterName();
		if(encoding==null||"".equals(encoding))
			encoding = "UTF-8";
		log.debug("获得编码值");
	}

}
