package com.evergrande.report.entity;


import com.evergrande.base.utils.DateUtils;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by cc on 16-7-20.
 *
 * 外部拓展客户名单
 */
public class ExtUser implements Serializable {

    private static final long serialVersionUID = -436402805740922934L;

    private Integer id;             //自增id
    private String mobile_no;       //手机号
    private String user_name;       //系统内姓名
    private Integer is_reg;         //是否注册
    private Date reg_date;          //注册日期
    private String format_reg_date;
    private Integer is_real_name;   //是否实名
    private Integer is_tie_card;    //是否绑卡
    private Integer is_trans;       //是否有过交易
    private String scan_code_rec;   //扫码推荐人
    private Date rebate_expire_date;//返利失效日期
    private String format_rebate_expire_date;
    private Date create_ts;         //创建时间
    private String format_create_ts;
    private Date update_ts;         //修改时间
    private String format_update_ts;


    public ExtUser() {
    }

    public ExtUser(String mobile_no, String user_name, Integer is_reg, Date reg_date, Integer is_real_name, Integer is_tie_card, Integer is_trans, String scan_code_rec, Date rebate_expire_date, Date create_ts, Date update_ts) {
        this.mobile_no = mobile_no;
        this.user_name = user_name;
        this.is_reg = is_reg;
        this.reg_date = reg_date;
        this.is_real_name = is_real_name;
        this.is_tie_card = is_tie_card;
        this.is_trans = is_trans;
        this.scan_code_rec = scan_code_rec;
        this.rebate_expire_date = rebate_expire_date;
        this.create_ts = create_ts;
        this.update_ts = update_ts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getIs_reg() {
        return is_reg;
    }

    public void setIs_reg(Integer is_reg) {
        this.is_reg = is_reg;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Integer getIs_real_name() {
        return is_real_name;
    }

    public void setIs_real_name(Integer is_real_name) {
        this.is_real_name = is_real_name;
    }

    public Integer getIs_tie_card() {
        return is_tie_card;
    }

    public void setIs_tie_card(Integer is_tie_card) {
        this.is_tie_card = is_tie_card;
    }

    public Integer getIs_trans() {
        return is_trans;
    }

    public void setIs_trans(Integer is_trans) {
        this.is_trans = is_trans;
    }

    public String getScan_code_rec() {
        return scan_code_rec;
    }

    public void setScan_code_rec(String scan_code_rec) {
        this.scan_code_rec = scan_code_rec;
    }

    public Date getRebate_expire_date() {
        return rebate_expire_date;
    }

    public void setRebate_expire_date(Date rebate_expire_date) {
        this.rebate_expire_date = rebate_expire_date;
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


}
