package com.evergrande.base.service;

import javax.annotation.Resource;

import com.evergrande.base.db.RelationDbTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseService {
//	@Resource
//	protected SqlSession mybatisTemplate;
	
	@Resource
	protected RelationDbTemplate relationDbTemplate;
	
	public <T> String objectToJson(T object){
		ObjectMapper mapper = new ObjectMapper();
		String jsonValue = null;
		try {
			jsonValue = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonValue;
	}
}
