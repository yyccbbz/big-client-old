/**
 * 
 */
package com.evergrande.sys.service;

import java.util.List;

import com.evergrande.sys.entity.PageBean;
import com.evergrande.sys.entity.Role;

/**
 * Description
 * @author shibh
 * @create date 2015年12月26日
 * @version 0.0.1
 */
public interface RoleService {
	/**
	 * 
	 * Description 查找用户所属角色
	 * @param  
	 * @return List<Role>
	 * @throws
	 */
	List<Role> findUserRoles(String username);

	/**
	 * Description 查找角色列表
	 * @param  
	 * @return List<Role>
	 * @throws 
	 */
	List<Role> findRoleList();

	/**
	 * Description
	 * @param  
	 * @return PageBean<Role>
	 * @throws 
	 */
	PageBean<Role> findRoleListByPage(Integer currPage,Integer pageSize);
}
