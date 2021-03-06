/**
 * 
 */
package com.evergrande.sys.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.evergrande.sys.entity.User;

/**
 * <pre>
 * Description : 用户登录拦截器
 * </pre>
 * 
 * @author : xiangwei
 * @date : 2016年1月18日
 * @version : 1.0
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {
	
	//private static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Session session = SecurityUtils.getSubject().getSession();
		User user = (User) session.getAttribute("user");
		String uri = request.getRequestURI();
		String appContext = uri.substring(0, uri.indexOf("/", 2));
		//String resource = uri.substring(uri.indexOf("/", 2), uri.length());
		//logger.debug("user:" + user);
		if (user == null) {
			// 用户未登录则直接跳转至登录页面
			response.sendRedirect(appContext + "/sys/toLogin.do");
			return false;
		} else {
			return true;
		}
		/*
		 * for(Menu menu:user.getMenuList()){
		 * logger.debug("menu.getMenu_en_name():" + menu.getMenu_en_name());
		 * if(resource.equals(menu.getMenu_en_name())){ //用户具有菜单权限则进入正常流程 return
		 * super.preHandle(request, response, handler); } } //用户没有菜单权限则不进行处理
		 * response.sendRedirect(appContext+"/pageStatic/noPrivate.jsp");
		 */
	}
	
}
