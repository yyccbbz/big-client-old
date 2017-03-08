package com.evergrande.report.service.impl;

import com.evergrande.base.service.BaseService;
import com.evergrande.report.entity.OfficialUser;
import com.evergrande.report.service.OfficialUserService;
import com.evergrande.sys.entity.PageBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by cc on 16-7-25.
 */
@Service
public class OfficialUserServiceImpl extends BaseService implements OfficialUserService {

    /**
     * 分页查询外部用户列表
     *
     * @param paramMap
     * @return
     */
    @Override
    public String findOfficialUserListByPage(Map<String, Object> paramMap) {
        PageBean<OfficialUser> pageBean = relationDbTemplate.selectListForPage(
                "officialUser.findOfficialUserListByPage", "officialUser.findOfficialUserListTotalNum",
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
    public int findOfficialUserListTotalNum(Map<String, Object> paramMap) {
        int totalNum = relationDbTemplate.selectOne("officialUser.findOfficialUserListTotalNum");
        return totalNum;
    }

    /**
     * 根据手机号查询用户
     *
     * @param mobileNo
     * @return
     */
    @Override
    public OfficialUser findOneUserByMobileNO(String mobileNo) {
        return relationDbTemplate.selectOne("officialUser.findOneUserByMobileNO",mobileNo);
    }

    /**
     * 添加单个用户
     *
     * @param paramMap
     */
    @Override
    public void saveOneUser(Map<String, Object> paramMap) {
        relationDbTemplate.insert("officialUser.saveOneUser",paramMap);
    }

    /**
     * 查询所有用户
     *
     * @param map
     * @return
     */
    @Override
    public List<OfficialUser> findAllOfficialUser(Map<String, Object> map) {
        return relationDbTemplate.selectList("officialUser.findAllOfficialUser");
    }

	@Override
	public void modifyUserInfo(Map<String, Object> paramMap) {
		relationDbTemplate.update("officialUser.modifyUserInfo",paramMap);
		
	}

    @Override
    public void deleteUserById(Map<String, Object> paramMap) {
        relationDbTemplate.delete("officialUser.deleteUserById",paramMap);
    }

    @Override
    public void deleteAll() {
        relationDbTemplate.delete("officialUser.deleteAll");
    }


}
