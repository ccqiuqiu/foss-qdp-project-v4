/*******************************************************************************
 * Copyright (c) Aug 28, 2017 @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package com.foreveross.common.shiro;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.iff.infra.util.Assert;
import org.iff.infra.util.BaseCryptHelper;
import org.iff.infra.util.FCS;
import org.iff.infra.util.MD5Helper;

import com.foreveross.common.application.SystemApplication;

/**
 * <pre>
 * Shiro Auth验证过滤器，通过验证Header中的auth值来验证密码。
 * auth = base64(loginId:MD5Helper.firstSalt(密码明文))
 * 数据库密码 = MD5Helper.secondSalt(MD5Helper.firstSalt(密码明文));
 * 验证密码时会把token中的密码进行MD5Helper.secondSalt()然后与数据库的密码匹配。
 * </pre>
 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
 * @since Aug 11, 2016
 */
public class ShiroAuthAccessControlFilter extends AdviceFilter implements OnceValidAdvice {

	private static final org.iff.infra.util.Logger.Log Logger = org.iff.infra.util.Logger.get("FOSS-SHIRO");

	@Inject
	@Named("systemApplication")
	SystemApplication systemApplication;

	public boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
		try {
			HttpServletRequest request = (HttpServletRequest) servletRequest;

			//是否为OnceValidAdvice。
			//boolean isOnceValidAdvice = Boolean.TRUE.equals(request.getAttribute(OnceValidAdvice.REQUEST_MARK));

			String token = request.getHeader("_auth");

			Logger.debug(FCS.get("Shiro ShiroAuthAccessControlFilter.preHandle, token: {0}", token));

			if (StringUtils.isBlank(token)) {
				token = request.getParameter("_auth");
			}
			if (StringUtils.isBlank(token)) {
				return false;
			}
			String loginId = "";
			String password = "";
			if (StringUtils.isNotBlank(token)) {
				String auth = new String(BaseCryptHelper.decodeBase64(token));
				if (!StringUtils.contains(auth, ':')) {
					return false;
				}
				loginId = StringUtils.substringBefore(auth, ":");
				password = StringUtils.substringAfter(auth, ":");
				Assert.notBlank(loginId);
				Assert.notBlank(password);
			}

			ShiroUser user = systemApplication.getShiroUserByLoginId(loginId);
			if (user == null) {
				return false;
			}
			String loginPasswd = user.getLoginPasswd();
			if (StringUtils.isBlank(token)) {
				return false;
			}

			boolean valid = loginPasswd.equalsIgnoreCase(MD5Helper.secondSalt(password));
			//			if (!valid) {
			//				//enable localhost, if you don't want to enable localhost use ShiroIpAccessControlFilter
			//				String[] ips = new String[] { "0:0:0:0:0:0:0:*", "127.0.0.*" };
			//				for (String aip : ips) {
			//					valid = StringHelper.wildCardMatch(ip, aip.trim());
			//					if (valid) {
			//						break;
			//					}
			//				}
			//			}
			if (valid) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	protected void cleanup(ServletRequest request, ServletResponse response, Exception existing)
			throws ServletException, IOException {
		super.cleanup(request, response, existing);
	}

}