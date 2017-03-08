package com.evergrande.report.service;

import java.util.List;
import java.util.Map;

import com.evergrande.report.entity.OfficialUser;

/**
 * Created by cc on 16-7-25.
 */
public interface OfficialUserService {
	
	String findOfficialUserListByPage(Map<String, Object> paramMap);

	int findOfficialUserListTotalNum(Map<String, Object> paramMap);

	OfficialUser findOneUserByMobileNO(String key);

	void saveOneUser(Map<String, Object> paramMap);

	List<OfficialUser> findAllOfficialUser(Map<String, Object> map);

	void modifyUserInfo(Map<String, Object> paramMap);

    void deleteUserById(Map<String, Object> paramMap);

    void deleteAll();
}
