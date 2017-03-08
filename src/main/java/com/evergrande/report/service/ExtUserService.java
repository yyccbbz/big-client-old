package com.evergrande.report.service;

import com.evergrande.report.entity.ExtUser;

import java.util.List;
import java.util.Map;

/**
 * Created by cc on 16-7-22.
 */
public interface ExtUserService {

    String findExtUserListByPage(Map<String, Object> paramMap);

    int findExtUserListTotalNum(Map<String, Object> paramMap);

    List<ExtUser> findAllExtUser(Map<String, Object> map);

    ExtUser findOneUserByMobileNO(String mobileNo);
    
    ExtUser findOneUserByMobileId(String mobileNo);

    void saveOneUser(Map<String, Object> paramMap);

    void deleteUserByMobile(Map<String, Object> paramMap);
    
    void deleteUserById(Map<String, Object> paramMap);

	void modifyUserInfo(Map<String, Object> paramMap);

}
