package com.evergrande.report.service;

import java.util.List;
import java.util.Map;

import com.evergrande.report.entity.AssignUser;

/**
 * Created by cc on 16-7-25.
 */
public interface AssignUserService {
	
    String findAssignUserListByPage(Map<String, Object> paramMap);

    int findAssignUserListTotalNum(Map<String, Object> paramMap);

    List<AssignUser> findAllAssignUser(Map<String, Object> map);

    List<AssignUser> findAssignUserByAUM(Map<String, Object> map);
}
