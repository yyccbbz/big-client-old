package com.evergrande.report.entity;

import java.sql.Date;

public class ExtPhone {

	private String mobile_no;
	private Date create_ts;         //创建时间
    private Date update_ts;         //修改时间
    
    
    
	public ExtPhone(String mobile_no, Date create_ts, Date update_ts) {
		super();
		this.mobile_no = mobile_no;
		this.create_ts = create_ts;
		this.update_ts = update_ts;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public Date getCreate_ts() {
		return create_ts;
	}
	public void setCreate_ts(Date create_ts) {
		this.create_ts = create_ts;
	}
	public Date getUpdate_ts() {
		return update_ts;
	}
	public void setUpdate_ts(Date update_ts) {
		this.update_ts = update_ts;
	}
    
    
}
