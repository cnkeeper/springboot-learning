package com.github.cnkeep.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述: 获取真实请求来源地址
 * @author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date ${DATE}
 */
public final class RequestUtil {

    /**
     * 尝试读取的HTTP请求头获取真实IP
	 */
	private static final String[] HTTP_PROXY_HEADERS = { 
			"X-Real-IP",
		    "X-Forwarded-For",
		    "Proxy-Client-IP",
		    "WL-Proxy-Client-IP",
		    "HTTP_X_FORWARDED_FOR",
		    "HTTP_X_FORWARDED",
		    "HTTP_X_CLUSTER_CLIENT_IP",
		    "HTTP_CLIENT_IP",
		    "HTTP_FORWARDED_FOR",
		    "HTTP_FORWARDED",
		    "HTTP_VIA",
		    "REMOTE_ADDR"};
	
	/**
	 * 尝试获取客户端真实IP
	 * @param request
	 * @return
	 */
	public final static String getIp(HttpServletRequest request) {
	    for (String header : HTTP_PROXY_HEADERS) {
	        String ip = request.getHeader(header);
	        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
	            return ip;
	        }
	    }
	    return request.getRemoteAddr();
	}

}