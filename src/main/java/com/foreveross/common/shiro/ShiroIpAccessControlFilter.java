/*******************************************************************************
 * Copyright (c) Aug 28, 2017 @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package com.foreveross.common.shiro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.iff.infra.util.FCS;
import org.iff.infra.util.HttpHelper;
import org.iff.infra.util.StringHelper;

import com.foreveross.common.ConstantBean;

/**
 * Shiro IP验证过滤器
 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
 * @since Aug 11, 2016
 */
public class ShiroIpAccessControlFilter extends AdviceFilter implements OnceValidAdvice {

	private static final org.iff.infra.util.Logger.Log Logger = org.iff.infra.util.Logger.get("FOSS-SHIRO");

	private String accessIp = "";
	private String[] ips = new String[0];

	public boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
		HttpServletRequest request = (HttpServletRequest) servletRequest;

		//是否为OnceValidAdvice。
		//boolean isOnceValidAdvice = Boolean.TRUE.equals(request.getAttribute(OnceValidAdvice.REQUEST_MARK));

		String ip = HttpHelper.getRemoteIpAddr(request);

		{
			Logger.debug(FCS.get("Shiro ShiroIpAccessControlFilter.preHandle, ip: {0}", ip));
		}

		{/*check the match list.*/
			if (accessIp.length() < 1) {
				accessIp = ConstantBean.getProperty("access.ip", "").trim();
			}
			if (ips.length < 1) {
				ips = StringUtils.split(accessIp, ',');
			}
			for (String aip : ips) {
				boolean match = StringHelper.wildCardMatch(ip, aip.trim());
				if (match) {
					return true;
				}
			}
		}
		/**
		 * 验证2： 如果Header[Authorization]采用了约定的加密方式（把客户端的所有IP进行md5(md5(ip).reverse())拼接），服务端拿到客户端的IP也进行相同的加密方式，最后对比是否包含加密段即可，【非严谨验证方式】。
		 */
		boolean valid = HttpHelper.validateIpMd5(ip, HttpHelper.getAuthorization(request));
		if (valid) {
			return true;
		} else {
			return false;
		}
	}

	protected void cleanup(ServletRequest request, ServletResponse response, Exception existing)
			throws ServletException, IOException {
		super.cleanup(request, response, existing);
	}

}