package com.system.demo.enity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
import java.sql.Time;

public class LearnVisitEntity {
    @JsonIgnore
    public String openId;//用户信息

    public String contactMan;
    public String contactUnit;
    public String contactPhone;
    public String place;
    public Date date;
    public Time time;
    public String major;
    public String classes;
    public int status;
    public Long formid;
    public String pricpleSign;


    @Override
    public String toString() {
        return "LearnVisitEntity{" +
                "openId='" + openId + '\'' +
                ", contactMan='" + contactMan + '\'' +
                ", contactUnit='" + contactUnit + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", place='" + place + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", major='" + major + '\'' +
                ", classes='" + classes + '\'' +
                ", status=" + status +
                ", formid=" + formid +
                ", pricplesign='" + pricpleSign + '\'' +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public Long getFormid() {
        return formid;
    }

    public LearnVisitEntity setFormid(Long formid) {
        this.formid = formid;
        return this;
    }

    public String getPricplesign() {
        return pricpleSign;
    }

    public LearnVisitEntity setPricplesign(String pricplesign) {
        this.pricpleSign = pricplesign;
        return this;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Long getId() {
        return formid;
    }

    public void setId(Long id) {
        this.formid = id;
    }

    public String getContactMan() {
        return contactMan;
    }

    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
    }

    public String getContactUnit() {
        return contactUnit;
    }

    public void setContactUnit(String contactUnit) {
        this.contactUnit = contactUnit;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
