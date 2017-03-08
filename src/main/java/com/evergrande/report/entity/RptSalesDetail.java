package com.evergrande.report.entity;

import java.io.Serializable;
import java.util.Date;

import com.evergrande.base.utils.Constants;
import com.evergrande.base.utils.DateUtils;

public class RptSalesDetail implements Serializable {

	private static final long serialVersionUID = 2399765597753537760L;

	private int id;

	private String user_name;

	private String mobile_no;

	private String invite_user;

	private Date rebate_expire_date;

	private String format_rebate_expire_date;

	private int report_or_allot;

	private String str_report_allot;

	private Date report_allot_date;

	private String format_report_allot_date;

	private String invest_adviser;

	private String customer_ind;

	private String basic_product_name;

	private double invest_amount;

	private double exist_assets;

	private Date buy_date;

	private String format_buy_date;

	private Date product_date;

	private String format_product_date;

	/**产品利率*/
	private String product_interest_rate;

	/**产品期限*/
	private String product_term;

	public String getProduct_interest_rate() {
		return product_interest_rate;
	}

	public void setProduct_interest_rate(String product_interest_rate) {
		this.product_interest_rate = product_interest_rate;
	}

	public String getProduct_term() {
		return product_term;
	}

	public void setProduct_term(String product_term) {
		this.product_term = product_term;
	}

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

	public String getInvite_user() {
		return invite_user;
	}

	public void setInvite_user(String invite_user) {
		this.invite_user = invite_user;
	}

	public Date getRebate_expire_date() {
		return rebate_expire_date;
	}

	public void setRebate_expire_date(Date rebate_expire_date) {
		this.rebate_expire_date = rebate_expire_date;
	}

	public String getFormat_rebate_expire_date() {
		if (rebate_expire_date == null) {
			format_rebate_expire_date = "";
		} else {
			format_rebate_expire_date = DateUtils.getFormatDate(rebate_expire_date, DateUtils.SHORT_DATE_FORMAT);
		}
		return format_rebate_expire_date;
	}

	public void setFormat_rebate_expire_date(String format_rebate_expire_date) {
		this.format_rebate_expire_date = format_rebate_expire_date;
	}

	public int getReport_or_allot() {
		return report_or_allot;
	}

	public void setReport_or_allot(int report_or_allot) {
		this.report_or_allot = report_or_allot;
	}

	public String getStr_report_allot() {
		if (Constants.REPORT == report_or_allot) {
			str_report_allot = Constants.STR_REPORT;
		} else if (Constants.ALLOT == report_or_allot) {
			str_report_allot = Constants.STR_ALLOT;
		} else {
			str_report_allot = "";
		}
		return str_report_allot;
	}

	public void setStr_report_allot(String str_report_allot) {
		this.str_report_allot = str_report_allot;
	}

	public Date getReport_allot_date() {
		return report_allot_date;
	}

	public void setReport_allot_date(Date report_allot_date) {
		this.report_allot_date = report_allot_date;
	}

	public String getFormat_report_allot_date() {
		if (report_allot_date == null) {
			format_report_allot_date = "";
		} else {
			format_report_allot_date = DateUtils.getFormatDate(report_allot_date, DateUtils.SHORT_DATE_FORMAT);
		}
		return format_report_allot_date;
	}

	public void setFormat_report_allot_date(String format_report_allot_date) {
		this.format_report_allot_date = format_report_allot_date;
	}

	public String getInvest_adviser() {
		return invest_adviser;
	}

	public void setInvest_adviser(String invest_adviser) {
		this.invest_adviser = invest_adviser;
	}

	public String getCustomer_ind() {
		return customer_ind;
	}

	public void setCustomer_ind(String customer_ind) {
		this.customer_ind = customer_ind;
	}

	public String getBasic_product_name() {
		return basic_product_name;
	}

	public void setBasic_product_name(String basic_product_name) {
		this.basic_product_name = basic_product_name;
	}

	public double getInvest_amount() {
		return invest_amount;
	}

	public void setInvest_amount(double invest_amount) {
		this.invest_amount = invest_amount;
	}

	public double getExist_assets() {
		return exist_assets;
	}

	public void setExist_assets(double exist_assets) {
		this.exist_assets = exist_assets;
	}

	public Date getBuy_date() {
		return buy_date;
	}

	public void setBuy_date(Date buy_date) {
		this.buy_date = buy_date;
	}

	public String getFormat_buy_date() {
		if (buy_date == null) {
			format_buy_date = "";
		} else {
			format_buy_date = DateUtils.getFormatDate(buy_date, DateUtils.LONG_DATE_FORMAT);
		}
		return format_buy_date;
	}

	public void setFormat_buy_date(String format_buy_date) {
		this.format_buy_date = format_buy_date;
	}

	public Date getProduct_date() {
		return product_date;
	}

	public void setProduct_date(Date product_date) {
		this.product_date = product_date;
	}

	public String getFormat_product_date() {
		if (product_date == null) {
			format_product_date = "";
		} else {
			format_product_date = DateUtils.getFormatDate(product_date, DateUtils.SHORT_DATE_FORMAT);
		}
		return format_product_date;
	}

	public void setFormat_product_date(String format_product_date) {
		this.format_product_date = format_product_date;
	}

	@Override
	public String toString() {
		return "RptSalesDetail{" +
				"id=" + id +
				", user_name='" + user_name + '\'' +
				", mobile_no='" + mobile_no + '\'' +
				", invite_user='" + invite_user + '\'' +
				", rebate_expire_date=" + rebate_expire_date +
				", format_rebate_expire_date='" + format_rebate_expire_date + '\'' +
				", report_or_allot=" + report_or_allot +
				", str_report_allot='" + str_report_allot + '\'' +
				", report_allot_date=" + report_allot_date +
				", format_report_allot_date='" + format_report_allot_date + '\'' +
				", invest_adviser='" + invest_adviser + '\'' +
				", customer_ind='" + customer_ind + '\'' +
				", basic_product_name='" + basic_product_name + '\'' +
				", invest_amount=" + invest_amount +
				", exist_assets=" + exist_assets +
				", buy_date=" + buy_date +
				", format_buy_date='" + format_buy_date + '\'' +
				", product_date=" + product_date +
				", format_product_date='" + format_product_date + '\'' +
				", product_interest_rate='" + product_interest_rate + '\'' +
				", product_term='" + product_term + '\'' +
				'}';
	}
}
