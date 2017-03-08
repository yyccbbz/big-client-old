package com.evergrande.report.entity;

import java.io.Serializable;
import java.util.Date;

import com.evergrande.base.utils.DateUtils;

public class RptAssetsBalance implements Serializable {

	private static final long serialVersionUID = -1638947212374373997L;

	private int id;

	private String user_name;

	private String mobile_no;

	private Date reg_date;
	
	private String format_reg_date;

	private double assets_total;

	private Date aum_time;

	private String format_aum_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public String getFormat_reg_date() {
		if (reg_date == null) {
			format_reg_date = "";
		} else {
			format_reg_date = DateUtils.getFormatDate(reg_date, DateUtils.SHORT_DATE_FORMAT);
		}
		return format_reg_date;
	}

	public void setFormat_reg_date(String format_reg_date) {
		this.format_reg_date = format_reg_date;
	}
	
	public double getAssets_total() {
		return assets_total;
	}

	public void setAssets_total(double assets_total) {
		this.assets_total = assets_total;
	}

	public Date getAum_time() {
		return aum_time;
	}

	public void setAum_time(Date aum_time) {
		this.aum_time = aum_time;
	}

	public String getFormat_aum_time() {
		if (aum_time == null) {
			format_aum_time = "";
		} else {
			format_aum_time = DateUtils.getFormatDate(aum_time, DateUtils.LONG_DATE_FORMAT);
		}
		return format_aum_time;
	}

	public void setFormat_aum_time(String format_aum_time) {
		this.format_aum_time = format_aum_time;
	}

	@Override
	public String toString() {
		return "RptAssetsBalance [id=" + id + ", user_name=" + user_name + ", mobile_no=" + mobile_no + ", reg_date="
				+ reg_date + ", assets_total=" + assets_total + ", aum_time=" + aum_time + ", format_aum_time="
				+ format_aum_time + "]";
	}

}
