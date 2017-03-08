package com.evergrande.report.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.evergrande.base.service.BaseService;
import com.evergrande.report.entity.AssignUser;
import com.evergrande.report.service.AssignUserService;
import com.evergrande.sys.entity.PageBean;

/**
 * Created by cc on 16-7-25.
 */
@Service
public class AssignUserServiceImpl extends BaseService implements AssignUserService {


    /**
     * 分页查询所有用户
     *
     * @param paramMap
     * @return
     */
    @Override
    public String findAssignUserListByPage(Map<String, Object> paramMap) {
        PageBean<AssignUser> pageBean = relationDbTemplate.selectListForPage(
                "assignUser.findAssignUserListByPage", "assignUser.findAssignUserListTotalNum",
                paramMap);
        String json = objectToJson(pageBean);
        return json;

    }

    /**
     * 查询用户总数
     *
     * @param paramMap
     * @return
     */
    @Override
    public int findAssignUserListTotalNum(Map<String, Object> paramMap) {
        int totalNum = relationDbTemplate.selectOne("assignUser.findAssignUserListTotalNum");
        return totalNum;
    }

    /**
     * 查询所有用户
     *
     * @param map
     * @return
     */
    @Override
    public List<AssignUser> findAllAssignUser(Map<String, Object> map) {
        return relationDbTemplate.selectList("assignUser.findAllAssignUser");
    }

    /**
     * 查询资产在5到50万的用户
     *
     * @param map
     * @return
     */
    @Override
    public List<AssignUser> findAssignUserByAUM(Map<String, Object> map) {
        return relationDbTemplate.selectList("assignUser.findAssignUserByAUM");
    }


}
