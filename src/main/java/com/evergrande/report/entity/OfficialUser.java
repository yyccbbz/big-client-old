package com.evergrande.report.entity;

import com.evergrande.base.utils.DateUtils;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by cc on 16-7-20.
 *
 *
 * 正式名单
 */

public class OfficialUser implements Serializable {

    private static final long serialVersionUID = -7166416414448854094L;
    private int id;                     //自增id
    private String user_name;           //客户名称(系统内名称)
    private String mobile_no;           //手机号(注册手机号)
    private int report_or_allot;        //上报/分配；上报：1；分配：2
    private Date report_allot_date;     //上报分配时间
    private String format_report_allot_date;
    private String invest_adviser;      //投资顾问
    private String customer_ids;        //客户标识
    private Date create_ts;             //创建时间
    private String format_create_ts;
    private Date update_ts;             //修改时间
    private String format_update_ts;

    public OfficialUser() {
    }

    public OfficialUser(String mobile_no, int report_or_allot, Date report_allot_date, String invest_adviser, String customer_ids) {
        this.mobile_no = mobile_no;
        this.report_or_allot = report_or_allot;
        this.report_allot_date = report_allot_date;
        this.invest_adviser = invest_adviser;
        this.customer_ids = customer_ids;
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

    public int getReport_or_allot() {
        return report_or_allot;
    }

    public void setReport_or_allot(int report_or_allot) {
        this.report_or_allot = report_or_allot;
    }

    public Date getReport_allot_date() {
        return report_allot_date;
    }

    public void setReport_allot_date(Date report_allot_date) {
        this.report_allot_date = report_allot_date;
    }

    public String getFormat_report_allot_date() {
        if(report_allot_date == null){
            format_report_allot_date = "";
        }else{
            format_report_allot_date = DateUtils.getFormatDate(report_allot_date,DateUtils.SHORT_DATE_FORMAT);
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

    public String getCustomer_ids() {
        return customer_ids;
    }

    public void setCustomer_ids(String customer_ids) {
        this.customer_ids = customer_ids;
    }

    public Date getCreate_ts() {
        return create_ts;
    }

    public void setCreate_ts(Date create_ts) {
        this.create_ts = create_ts;
    }

    public String getFormat_create_ts() {
        if(create_ts == null){
            format_create_ts = "";
        }else{
            format_create_ts = DateUtils.getFormatDate(create_ts,DateUtils.SHORT_DATE_FORMAT);
        }
        return format_create_ts;
    }

    public void setFormat_create_ts(String format_create_ts) {
        this.format_create_ts = format_create_ts;
    }

    public Date getUpdate_ts() {
        return update_ts;
    }

    public void setUpdate_ts(Date update_ts) {
        this.update_ts = update_ts;
    }

    public String getFormat_update_ts() {
        if(update_ts == null){
            format_update_ts = "";
        }else{
            format_create_ts = DateUtils.getFormatDate(update_ts,DateUtils.SHORT_DATE_FORMAT);
        }
        return format_update_ts;
    }

    public void setFormat_update_ts(String format_update_ts) {
        this.format_update_ts = format_update_ts;
    }

    @Override
    public String toString() {
        return "OfficialUser{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", mobile_no='" + mobile_no + '\'' +
                ", report_or_allot=" + report_or_allot +
                ", report_allot_date=" + report_allot_date +
                ", invest_adviser='" + invest_adviser + '\'' +
                ", customer_ids='" + customer_ids + '\'' +
                ", create_ts=" + create_ts +
                ", update_ts=" + update_ts +
                '}';
    }
}
