package com.evergrande.report.service.impl;

import com.evergrande.base.service.BaseService;
import com.evergrande.report.entity.ExtUser;
import com.evergrande.report.service.ExtUserService;
import com.evergrande.sys.entity.PageBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by cc on 16-7-22.
 */
@Service
public class ExtUserServiceImpl extends BaseService implements ExtUserService {


    /**
     * 分页查询外部用户列表
     *
     * @param paramMap
     * @return
     */
    @Override
    public String findExtUserListByPage(Map<String, Object> paramMap) {
        PageBean<ExtUser> pageBean = relationDbTemplate.selectListForPage(
                "extUser.findExtUserListByPage", "extUser.findExtUserListTotalNum",
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
    public int findExtUserListTotalNum(Map<String, Object> paramMap) {
        int totalNum = relationDbTemplate.selectOne("extUser.findExtUserListTotalNum");
        return totalNum;
    }

    /**
     * 查询所有外部用户
     *
     * @param map
     * @return
     */
    @Override
    public List<ExtUser> findAllExtUser(Map<String, Object> map) {
        return relationDbTemplate.selectList("extUser.findAllExtUser");
    }

    /**
     * 根据手机号查询用户
     *
     * @param mobileNo
     * @return
     */
    @Override
    public ExtUser findOneUserByMobileNO(String mobileNo) {
        return relationDbTemplate.selectOne("extUser.findOneUserByMobileNO",mobileNo);
    }

    /**
     * 添加单个用户
     *
     * @param paramMap
     */
    @Override
    public void saveOneUser(Map<String, Object> paramMap) {
        relationDbTemplate.insert("extUser.saveOneUser",paramMap);
    }


    /**
     * 根据手机号删除用户
     * 
     */
    @Override
    public void deleteUserByMobile(Map<String, Object> paramMap) {
        relationDbTemplate.delete("extUser.deleteUserByMobile",paramMap);
    }

	@Override
	public void deleteUserById(Map<String, Object> paramMap) {
		relationDbTemplate.delete("extUser.deleteUserById",paramMap);
	}

	@Override
	public ExtUser findOneUserByMobileId(String id) {
		return relationDbTemplate.selectOne("extUser.findOneUserByMobileId",id);
	}

	@Override
	public void modifyUserInfo(Map<String, Object> paramMap) {
		relationDbTemplate.update("extUser.modifyUserInfo", paramMap);
	}


}
