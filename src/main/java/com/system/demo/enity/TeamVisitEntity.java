package com.system.demo.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
import java.sql.Time;

public class TeamVisitEntity {
    public Long formId;
    public int status;//false:没通过，ture:通过

    @JsonIgnore
    public String openId;//用户信息

    public String contactUnit;//联系单位
    public String accompanyLeader;//陪同领导
    public String place;//参观场所
    /**
     * “通讯展览馆”
     * “校史展览馆”
     */
    public Date date;
    public Time time;
    public String gust;//来宾
    public String position;//单位职务
    public Long peopleNumber;//人数
    public boolean ifAlumni;//是否校友

    /**
     * 1是校友
     * 0不是校友
     */
    public String welcomeMesag;//欢迎语
    public String contactPhone;
    public String contactMan;
    public String pricpleSign;

    @Override
    public String toString() {
        return "TeamVisitEntity{" +
                "formId=" + formId +
                ", status=" + status +
                ", openId='" + openId + '\'' +
                ", contactUnit='" + contactUnit + '\'' +
                ", accompanyLeader='" + accompanyLeader + '\'' +
                ", place='" + place + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", gust='" + gust + '\'' +
                ", position='" + position + '\'' +
                ", peopleNumber=" + peopleNumber +
                ", ifAlumni=" + ifAlumni +
                ", welcomeMesag='" + welcomeMesag + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", contactMan='" + contactMan + '\'' +
                ", pricpleSign='" + pricpleSign + '\'' +
                '}';
    }

    public Long getFormId() {
        return formId;
    }


    public String getPricpleSign() {
        return pricpleSign;
    }

    public TeamVisitEntity setPricpleSign(String pricpleSign) {
        this.pricpleSign = pricpleSign;
        return this;
    }

    public TeamVisitEntity setFormId(Long formId) {
        this.formId = formId;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public TeamVisitEntity setStatus(int status) {
        this.status = status;
        return this;
    }


    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactMan() {
        return contactMan;
    }

    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getContactUnit() {
        return contactUnit;
    }

    public void setContactUnit(String contactUnit) {
        this.contactUnit = contactUnit;
    }

    public String getAccompanyLeader() {
        return accompanyLeader;
    }

    public void setAccompanyLeader(String accompanyLeader) {
        this.accompanyLeader = accompanyLeader;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getGust() {
        return gust;
    }

    public void setGust(String gust) {
        this.gust = gust;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Long peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public boolean isIfAlumni() {
        return ifAlumni;
    }

    public void setIfAlumni(boolean ifAlumni) {
        this.ifAlumni = ifAlumni;
    }

    public String getWelcomeMesag() {
        return welcomeMesag;
    }

    public void setWelcomeMesag(String welcomeMesag) {
        this.welcomeMesag = welcomeMesag;
    }
}
