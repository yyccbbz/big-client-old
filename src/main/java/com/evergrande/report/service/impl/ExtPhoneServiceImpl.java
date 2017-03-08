package com.evergrande.report.service.impl;

import com.evergrande.base.service.BaseService;
import com.evergrande.report.service.ExtPhoneService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by cc on 16-7-22.
 */
@Service
public class ExtPhoneServiceImpl extends BaseService implements ExtPhoneService {

    /**
     * 导入手机号
     */
    @Override
    public void saveExtPhone(Map<String, Object> paramMap) {
        relationDbTemplate.insert("extPhone.saveExtPhone", paramMap);

    }

    /**
     * 根据手机号删除用户
     */
    @Override
    public void deleteByMobile(Map<String, Object> paramMap) {
        relationDbTemplate.delete("extPhone.deleteByMobile", paramMap);
    }

    /**
     * 全量导入前的删除rpt_ext_phone
     */
    @Override
    public void deleteAllExtPhone() {
        relationDbTemplate.delete("extPhone.deleteAllExtPhone");
    }

}
