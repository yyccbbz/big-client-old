package com.evergrande.report.entity;

import com.evergrande.base.utils.DateUtils;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by cc on 16-7-20.
 *
 *
 * 平台待分配客户名单
 */
//CREATE TABLE `rpt_to_assigned_customers` (
//        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
//        `user_name` varchar(30) NOT NULL DEFAULT '' COMMENT '客户名称',
//        `mobile_no` varchar(30) NOT NULL DEFAULT '' COMMENT '注册手机号',
//        `reg_date` date NOT NULL DEFAULT '0000-00-00' COMMENT '注册日期',
//        `id_card_no` varchar(30) NOT NULL DEFAULT '' COMMENT '身份证号',
//        `is_staff` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否员工',
//        `assets` int(11) NOT NULL DEFAULT '0' COMMENT '资产金额',
//        `gender` tinyint(4) NOT NULL DEFAULT '0' COMMENT '性别：1：男；0：女',
//        `age` tinyint(4) NOT NULL DEFAULT '0' COMMENT '年龄',
//        `birthday` date NOT NULL DEFAULT '0000-00-00' COMMENT '出生日期',
//        `invite_user` varchar(30) NOT NULL DEFAULT '' COMMENT '邀请人',
//        `invite_user_mobile` varchar(30) NOT NULL DEFAULT '' COMMENT '邀请人手机',
//        `invite_user_is_staff` tinyint(4) NOT NULL,
//        `create_ts` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
//        `update_ts` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',

public class AssignUser implements Serializable {

    private static final long serialVersionUID = -2618312381008661567L;
    private int id;                     //自增id
    private String user_name;           //客户名称(系统内名称)
    private String mobile_no;           //手机号(注册手机号)
    private Date reg_date;              //注册日期
    private String format_reg_date;
    private String id_card_no;          //身份证号
    private int is_staff;               //是否员工
    private int assets;                 //资产金额
    private int gender;                 //性别：1：男；0：女
    private int age;                    //年龄
    private Date birthday;              //出生日期
    private String format_birthday;
    private String invite_user;         //邀请人
    private String invite_user_mobile;  //邀请人手机
    private int invite_user_is_staff;   //邀请人是否员工
    private Date create_ts;             //创建时间
    private String format_create_ts;
    private Date update_ts;             //修改时间
    private String format_update_ts;


    public AssignUser() {
    }

    public AssignUser(int id, String user_name, String mobile_no, Date reg_date, String id_card_no, int is_staff, int assets, int gender, int age, Date birthday, String invite_user, String invite_user_mobile, int invite_user_is_staff, Date create_ts, Date update_ts) {
        this.id = id;
        this.user_name = user_name;
        this.mobile_no = mobile_no;
        this.reg_date = reg_date;
        this.id_card_no = id_card_no;
        this.is_staff = is_staff;
        this.assets = assets;
        this.gender = gender;
        this.age = age;
        this.birthday = birthday;
        this.invite_user = invite_user;
        this.invite_user_mobile = invite_user_mobile;
        this.invite_user_is_staff = invite_user_is_staff;
        this.create_ts = create_ts;
        this.update_ts = update_ts;
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

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public String getId_card_no() {
        return id_card_no;
    }

    public void setId_card_no(String id_card_no) {
        this.id_card_no = id_card_no;
    }

    public int getIs_staff() {
        return is_staff;
    }

    public void setIs_staff(int is_staff) {
        this.is_staff = is_staff;
    }

    public int getAssets() {
        return assets;
    }

    public void setAssets(int assets) {
        this.assets = assets;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getInvite_user() {
        return invite_user;
    }

    public void setInvite_user(String invite_user) {
        this.invite_user = invite_user;
    }

    public String getInvite_user_mobile() {
        return invite_user_mobile;
    }

    public void setInvite_user_mobile(String invite_user_mobile) {
        this.invite_user_mobile = invite_user_mobile;
    }

    public int getInvite_user_is_staff() {
        return invite_user_is_staff;
    }

    public void setInvite_user_is_staff(int invite_user_is_staff) {
        this.invite_user_is_staff = invite_user_is_staff;
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
        if(reg_date == null){
            format_reg_date = "";
        }else{
            format_reg_date = DateUtils.getFormatDate(reg_date,DateUtils.SHORT_DATE_FORMAT);
        }
        return format_reg_date;
    }

    public void setFormat_reg_date(String format_reg_date) {
        this.format_reg_date = format_reg_date;
    }

    public String getFormat_birthday() {
        if(birthday == null){
            format_birthday = "";
        }else{
            format_birthday = DateUtils.getFormatDate(birthday,DateUtils.SHORT_DATE_FORMAT);
        }
        return format_birthday;
    }

    public void setFormat_birthday(String format_birthday) {
        this.format_birthday = format_birthday;
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

    @Override
    public String toString() {
        return "AssignUser{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", mobile_no='" + mobile_no + '\'' +
                ", reg_date=" + reg_date +
                ", id_card_no='" + id_card_no + '\'' +
                ", is_staff=" + is_staff +
                ", assets=" + assets +
                ", gender=" + gender +
                ", age=" + age +
                ", birthday=" + birthday +
                ", invite_user='" + invite_user + '\'' +
                ", invite_user_mobile='" + invite_user_mobile + '\'' +
                ", invite_user_is_staff=" + invite_user_is_staff +
                ", create_ts=" + create_ts +
                ", update_ts=" + update_ts +
                '}';
    }
}
