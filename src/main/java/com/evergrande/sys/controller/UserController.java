package com.evergrande.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.DecoderException;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.evergrande.base.controller.BaseController;
import com.evergrande.sys.entity.User;
import com.evergrande.sys.service.UserService;
import com.evergrande.sys.util.BlowfishCodecUtil;
import com.evergrande.sys.util.ErrorMsg;
import com.evergrande.sys.util.Regex;
import com.evergrande.sys.util.RoleID;

/**
 * Description : 用户管理
 * @author : xiangwei
 * @date : 2016年1月16日
 */
@Controller
@RequestMapping("sys/user")
public class UserController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	/**
	 * Description 用户管理页面
	 * @param  @return 
	 */
	@RequestMapping("main")
	public String userMain(HttpServletRequest request) {
		String strCurrPage = request.getParameter("currPage");
		int currPage = 1;
		if(strCurrPage != null){
			currPage = Integer.parseInt(strCurrPage);
		}
		String order_id = request.getParameter("order_id");
		if(order_id == null){
			order_id = "user_id";
		}
		String order_type = request.getParameter("order_type");
		if(order_type == null){
			order_type = "asc";
		}
		request.setAttribute("currPage", currPage);
		request.setAttribute("order_id", order_id);
		request.setAttribute("order_type", order_type);
		return "sys/user/userMain";
	}
	
	@RequestMapping("findUserListByPageNoAdmin")
	@ResponseBody
	public String findUserListByPageNoAdmin(HttpServletRequest request, HttpServletResponse response) {
		logger.info("request.getParameter(\"currPage\"):" + request.getParameter("currPage"));
		Map<String,Object> paramMap = initRequestParam(request);
		String resultJson = userService.findUserListByPageNoAdmin(paramMap);
		return resultJson;
	}
	
	@RequestMapping("findUserListByPage")
	@ResponseBody
	public String findUserListByPage(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> paramMap = initRequestParam(request);
		String resultJson = userService.findUserListByPage(paramMap);
		return resultJson;
	}
	
	@RequestMapping("modifyUserActiveInd")
	@ResponseBody
	public String modifyUserActiveInd(HttpServletRequest request, HttpServletResponse response) {
		String strCurrPage = request.getParameter("currPage");
		int currPage = 1;
		if(strCurrPage != null ){
			currPage = Integer.parseInt(strCurrPage);
		}
		request.setAttribute("currPage", currPage);
		String active_ind = request.getParameter("active_ind");
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		userService.modifyUserActiveInd(user_id, active_ind);
		return "success";
	}
	
	@RequestMapping("userAddEdit")
	public String userAddEdit(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		request.setAttribute("action", action);
		String strCurrPage = request.getParameter("currPage");
		int currPage = 1;
		if(strCurrPage != null ){
			currPage = Integer.parseInt(strCurrPage);
		}
		request.setAttribute("currPage", currPage);
		String order_id = request.getParameter("order_id");
		if(order_id == null){
			order_id = "user_id";
		}
		request.setAttribute("order_id", order_id);
		String order_type = request.getParameter("order_type");
		if(order_type == null){
			order_type = "asc";
		}
		request.setAttribute("order_type", order_type);
		if("edit".equals(action)){
			int edit_user_id = Integer.parseInt(request.getParameter("edit_user_id"));
			User editUser = userService.findUserByUserId(edit_user_id);
			request.setAttribute("editUser", editUser);
		} 
		return "sys/user/userAddEdit";
	}
	
	@RequestMapping("saveOneUser")
	@ResponseBody
	public Map<String, Object> saveOneUser(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		if (user == null) {
			map.put("result", "fail");
			map.put("msg", ErrorMsg.LOGIN_OVERDUE);
			return map;
		}
		String action = request.getParameter("action");
		Map<String, Object> paramMap = new HashMap<String, Object>();

		String password = request.getParameter("password");
		String confpwd = request.getParameter("confpwd");
		

		String username = request.getParameter("username");
		String realname = request.getParameter("realname");
		paramMap.put("username", username);
		if("add".equals(action)){
			if(userService.checkUserNameExist(paramMap) > 0){
				map.put("result", "fail");
				map.put("msg", "用户名已存在");
				return map;
			} 
			Pattern pattern = Pattern.compile(Regex.USER_NAME, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(username);
			if(!matcher.matches()){
				map.put("result", "fail");
				map.put("msg", ErrorMsg.USERNAME_ERROR_MSG);
				return map;
			}
			try {

				password = BlowfishCodecUtil.decode( password );
				confpwd = BlowfishCodecUtil.decode( confpwd );

			} catch (DecoderException e) {
				logger.error("failed to decode password string", e);

				map.put("result", "fail");
				map.put("msg", "网络错误，无法解密");
				return map;
			}
			pattern = Pattern.compile(Regex.PASSWORD, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(password);
			if(!matcher.matches()){
				map.put("result", "fail");
				map.put("msg", ErrorMsg.PASSWORD_ERROR_MSG);
				return map;
			}
			if(!password.equals(confpwd)){
				map.put("result", "fail");
				map.put("msg", ErrorMsg.TWO_PWD_DIF);
				return map;
			}
		}
		Pattern pattern = Pattern.compile(Regex.REAL_NAME, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(realname);
		if(!matcher.matches()){
			map.put("result", "fail");
			map.put("msg", ErrorMsg.REALNAME_ERROR_MSG);
			return map;
		}
		paramMap.put("realname", realname);
		paramMap.put("updateid", user.getUser_id());
		if("add".equals(action)){
			paramMap.put("password", password);
			paramMap.put("roleid", RoleID.REPORT_ADMIN);
			paramMap.put("createid", user.getUser_id());
			userService.saveOneUser(paramMap);
		} else if("edit".equals(action)){
			int edit_user_id = 0;
			String strEditUserId = request.getParameter("edit_user_id");
			if(strEditUserId != null){
				edit_user_id = Integer.parseInt(strEditUserId);
				paramMap.put("edit_user_id", edit_user_id);
			}
			userService.modifyUserInfo(paramMap);
		}
		map.put("result", "success");
		map.put("msg", "保存成功");
		return map;
	}

	@RequestMapping("deleteUserById")
	@ResponseBody
	public String deleteUserById(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		if (user == null) {
			return "WEB-INF/jsp/sys/login";
		} 
		if(user.getRole_id() != RoleID.SYS_ADMIN){
			return "无权删除用户";
		}
		String strCurrPage = request.getParameter("currPage");
		int currPage = 1;
		if(strCurrPage != null ){
			currPage = Integer.parseInt(strCurrPage);
		}
		request.setAttribute("currPage", currPage);
		String order_id = request.getParameter("order_id");
		if(order_id == null){
			order_id = "user_id";
		}
		String order_type = request.getParameter("order_type");
		if(order_type == null){
			order_type = "asc";
		}
		int delete_user_id = Integer.parseInt(request.getParameter("delete_user_id"));
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_id", delete_user_id);
		userService.deleteUserById(paramMap);
		return "success";
	}
	
	/**
	 * Description 获取组内用户数
	 * @param request
	 * @return Map<String,Object>
	 * throws
	 */
	@RequestMapping("findTotalNumByUserGroupId")
	@ResponseBody
	public Map<String, Object> findTotalNumByUserGroupId(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String strGroupId = request.getParameter("user_group_id");
		int user_group_id = 0;
		if(strGroupId != null){
			user_group_id = Integer.parseInt(strGroupId);
		}
		paramMap.put("user_group_id", user_group_id);
		int totalNum = userService.findTotalNumByUserGroupId(paramMap);
		map.put("totalNum", totalNum);
		return map;
	}
	
	@RequestMapping("modifyUsersGroupAndRole")
	@ResponseBody
	public String modifyUsersGroupAndRole(HttpServletRequest request, HttpServletResponse response){
		int user_group_id = Integer.parseInt(request.getParameter("user_group_id"));
		int role_id = Integer.parseInt(request.getParameter("role_id"));
		List<Integer> user_id_list = new ArrayList<Integer> ();
		user_id_list.add(32);
		user_id_list.add(34);
		userService.modifyUsersGroupAndRole(user_group_id, role_id, user_id_list);
		return "success";
	}

}
