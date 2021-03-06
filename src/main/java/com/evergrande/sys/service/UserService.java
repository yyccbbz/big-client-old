/**
 * 
 */
package com.evergrande.sys.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.evergrande.sys.entity.User;

/**
 * 
 * <pre>
 * Description : 
 * </pre>
 * @author : xiangwei
 * @date : 2016年1月15日
 * @version : 1.0
 */
public interface UserService {
	
	/**
	 * Description 判断用户是否存在
	 * @param  username
	 * @param  password
	 * @return user
	 * @throws
	 */
	User checkUserExist(String username,String password);
	/**
	 * Description 登录
	 * @param  username
	 * @param  password
	 * @return user
	 * @throws
	 */
	Boolean login(String username,String password);
	/**
	 * Description 登出
	 * @param  
	 * @return void
	 * @throws
	 */
	void logout(HttpServletRequest request);
	/**
	 * 
	 * Description 按用户ID查找用户信息
	 * @param  
	 * @return User
	 * @throws
	 */
	User findUserByUserId(int user_id);
	
	/**
	 * Description 分页取用户列表
	 * @param paramMap
	 * @return String
	 * throws
	 */
	String findUserListByPage(Map<String, Object> paramMap);
	
	/**
	 * Description 
	 * @param paramMap
	 * @return String
	 * throws
	 */
	String findUserListByPageNoAdmin(Map<String, Object> paramMap);
	
	/**
	 * Description 修改status
	 * @param paramMap
	 * @return int
	 * throws
	 */
	void modifyUserActiveInd(int user_id, String active_ind);
	
	/**
	 * Description 插入一个用户
	 * @param paramMap void
	 * throws
	 */
	void saveOneUser(Map<String, Object> paramMap);
	
	/**
	 * Description 判断用户名是否存在
	 * @param paramMap
	 * @return int
	 * throws
	 */
	int checkUserNameExist(Map<String, Object> paramMap);
	
	/**
	 * Description 根据用户ID删除用户
	 * @param paramMap void
	 * throws
	 */
	void deleteUserById(Map<String, Object> paramMap);
	
	/**
	 * Description 根据用户ID删除用户
	 * @param paramMap void
	 * throws
	 */
	void modifyUserInfo(Map<String, Object> paramMap);
	
	/**
	 * Description 获取所有用户
	 * @param paramMap
	 * @return List<Group>
	 * throws
	 */
	List<User> findAllUser(Map<String, Object> paramMap);
	
	/**
	 * Description 根据组ID查询用户列表
	 * @param paramMap
	 * @return List<User>
	 * throws
	 */
	List<User> findUserListByGroupId(Map<String, Object> paramMap);
	
	/**
	 * Description 获取组内用户数
	 * @param paramMap
	 * @return int
	 * throws
	 */
	int findTotalNumByUserGroupId(Map<String, Object> paramMap);
	
	/**
	 * 修改用户密码
	 * Description 
	 * @param paramMap void
	 * throws
	 */
	void modifyUserPwd(int user_id, String user_pwd);
	
	/**
	 * Description 修改用户role
	 * @param paramMap void
	 * throws
	 */
	void modifyUserRole(int user_id, int role_id);
	
	User modifyUserProperties(HttpServletRequest request, User user);
	
	void modifyUsersGroupAndRole(int user_group_id, int role_id, List<Integer> user_id_list);
	
}
