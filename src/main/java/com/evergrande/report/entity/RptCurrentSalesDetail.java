package com.evergrande.report.entity;

import com.evergrande.base.utils.Constants;
import com.evergrande.base.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

public class RptCurrentSalesDetail implements Serializable {

    private static final long serialVersionUID = 2399764597753537760L;

    private int id;//自动编号

    private String user_name;//客户姓名

    private String mobile_no;//手机号码

    private int report_or_allot;//上报/分配；上报：1；分配：2

    private String str_report_allot;//上报/分配；上报：1；分配：2

    private Date report_allot_date;//上报分配时间

    private String format_report_allot_date;//格式化上报分配时间

    private String invest_adviser;//投资顾问

    private String customer_ind;//客户标识

    private String basic_product_name;//基础产品名称

    private double invest_amount;//申购金额

    private Date buy_date;//申购日期

    private String format_buy_date;//格式化申购日期

    private Date update_ts;

    private Date create_ts;


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

    public Date getUpdate_ts() {
        return update_ts;
    }

    public void setUpdate_ts(Date update_ts) {
        this.update_ts = update_ts;
    }

    public Date getCreate_ts() {
        return create_ts;
    }

    public void setCreate_ts(Date create_ts) {
        this.create_ts = create_ts;
    }

    @Override
    public String toString() {
        return "RptCurrentSalesDetail{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", mobile_no='" + mobile_no + '\'' +
                ", report_or_allot=" + report_or_allot +
                ", str_report_allot='" + str_report_allot + '\'' +
                ", report_allot_date=" + report_allot_date +
                ", format_report_allot_date='" + format_report_allot_date + '\'' +
                ", invest_adviser='" + invest_adviser + '\'' +
                ", customer_ind='" + customer_ind + '\'' +
                ", basic_product_name='" + basic_product_name + '\'' +
                ", invest_amount=" + invest_amount +
                ", buy_date=" + buy_date +
                ", format_buy_date='" + format_buy_date + '\'' +
                ", update_ts=" + update_ts +
                ", create_ts=" + create_ts +
                '}';
    }
}
