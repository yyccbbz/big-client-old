package com.evergrande.sys.util;

public class Regex {

	public static final String USER_NAME = "[a-zA-Z0-9_]{3,30}";
	
	public static final String PASSWORD = "[a-zA-Z0-9_]{6,20}";
	
	public static final String REAL_NAME = "[\u4E00-\u9FA50-9a-zA-Z_]{2,20}";
	
	public static final String GROUP_ORG_NAME = "[\u4E00-\u9FA5a-zA-Z0-9]{1,20}";
	
	public static final String GROUP_DEP_NAME = "[\u4E00-\u9FA5a-zA-Z0-9]{1,20}";
	
}
