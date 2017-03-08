package com.evergrande.sys.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.DecoderException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.evergrande.sys.entity.User;
import com.evergrande.sys.service.UserService;
import com.evergrande.sys.util.BlowfishCodecUtil;
import com.evergrande.sys.util.ErrorMsg;
import com.evergrande.sys.util.Regex;

/**
 * Description : 登录相关
 * @author : xiangwei
 * @date : 2016年1月16日
 */
@Controller
@RequestMapping("sys")
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;

	/**
	 * Description 校验用户名密码 
	 * @return String 
	 * @throws
	 */
	@RequestMapping("login")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			password = BlowfishCodecUtil.decode( password );
		} catch (DecoderException e) {
			logger.error("failed to decode password string", e);

			map.put("result", "fail");
			map.put("msg", "网络错误，无法解密");
			return map;
		}
		logger.debug("user login begin:" + username);

		try {
			//验证用户名密码是否正确
			/*
			 * if("".equals(captcha)||captcha==null||!checkCaptcha(captcha,
			 * request)){ map.put("result", "errorcaptcha"); map.put("msg",
			 * "验证码错误"); return map; }
			 */
			if (username == null || password == null || "".equals(username) || "".equals(password)) {
				map.put("result", "errorpwd");
				map.put("msg", "用户名或密码不能为空");
				return map;
			}
			User user = userService.checkUserExist(username, password);
			if(user == null ){
				map.put("result", "errorpwd");
				map.put("msg", "用户名或密码错误，请重新输入");
			} else {
				map.put("result", "success");
				map.put("msg", "登录成功");
				user = userService.modifyUserProperties(request, user);
				Session session = SecurityUtils.getSubject().getSession();
				session.setAttribute("user", user);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("user login fail .." + username, e);
			map.put("result", "fail");
			map.put("msg", "登录失败");
		}
		logger.debug("user login success .." + username);
		return map;
	}

	/**
	 * Description 登出
	 * @return String 登出后跳转至登录页面
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		try {
			userService.logout(request);
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute("user", null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("user logout fail ..", e);
		}
		logger.debug("user logout success..");
		return "sys/login";
	}
	
	/**
	 * Description 跳转至登录页面
	 * @return String
	 */
	@RequestMapping("toLogin")
	public String toLogin() {
		return "sys/login";
	}
	
	@RequestMapping("toLogout")
	public String toLogout() {
		return "sys/logout";
	}

	/**
	 * Description 跳转至首页
	 *  @param 
	 *  @return String @throws
	 */
	@RequestMapping("mainframe")
	public String main(ModelMap modelMap) {
		User user = null;
		try {
			//将用户菜单列表传入页面
			user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
			if (user == null) {
				return "sys/login";
			}
			modelMap.addAttribute("menuList", user.getMenuList());
		} catch (Exception e) {
			logger.debug("not login and turn to login.jsp", e);
			return "sys/login";
		}
		logger.debug("login success and redirect to main.jsp");
		return "main";
	}

	/**
	 * Description 游客登陆
	 * @param 
	 * @return String 
	 * @throws
	 */
	@RequestMapping("guestMain")
	public String guestMain() {
		return "main";
	}
	
	/**
	 * Description 修改密码
	 * @param request
	 * @return Map<String,Object>
	 * throws
	 */
	@RequestMapping("modifyUserPwd")
	@ResponseBody
	public Map<String, Object> modifyUserPwd(HttpServletRequest request) {
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd");
		String repeatpwd = request.getParameter("repeatpwd");

		try {
			oldpwd = BlowfishCodecUtil.decode( oldpwd );
			newpwd = BlowfishCodecUtil.decode( newpwd );
			repeatpwd = BlowfishCodecUtil.decode( repeatpwd );
		} catch (DecoderException e) {
			logger.error("failed to decode passwd:", e);
			Map<String, Object> map = new HashMap<>();
			map.put("result", "fail");
			map.put("msg", "网络错误，无法解密");
			return map;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		if (user == null) {
			map.put("result", "fail");
			map.put("msg", ErrorMsg.LOGIN_OVERDUE);
			return map;
		} 
		User oldPwduser = userService.checkUserExist(user.getUser_nm(), oldpwd);
		if (oldPwduser == null) {
			map.put("result", "fail");
			map.put("msg", "旧密码输入错误");
			return map;
		} 
		Pattern pattern = Pattern.compile(Regex.PASSWORD, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(newpwd);
		if(!matcher.matches()){
			map.put("result", "fail");
			map.put("msg", ErrorMsg.PASSWORD_ERROR_MSG);
			return map;
		}
		if(!newpwd.equals(repeatpwd)){
			map.put("result", "fail");
			map.put("msg", ErrorMsg.TWO_PWD_DIF);
			return map;
		}
		userService.modifyUserPwd(user.getUser_id(), newpwd);
		map.put("result", "success");
		map.put("msg", "密码修改成功");
		return map;
	}
	
	@RequestMapping("goModifyPassword")
	public String goModifyPassword(HttpServletRequest request, HttpServletResponse response) {
		return "sys/userPwdModify";
	}
	
	@RequestMapping("resetPwd")
	public String resetPwd(HttpServletRequest request, HttpServletResponse response) {
		return "sys/resetPwd";
	}

}
