package com.evergrande.report.service;

import java.util.Map;

/**
 * Created by cc on 16-7-22.
 */
public interface ExtPhoneService {

	void saveExtPhone(Map<String, Object> paramMap);

    void deleteByMobile(Map<String, Object> params);

    void deleteAllExtPhone();
}
