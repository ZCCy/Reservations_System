package com.system.demo.service;

import com.system.demo.Utils.RestResult;
import com.system.demo.dao.LearnVisitDao;
import com.system.demo.dao.LoginDao;
import com.system.demo.dao.TeamVisitDao;
import com.system.demo.enity.LearnVisitEntity;
import com.system.demo.enity.TeamVisitEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;

@Service
public class FormSubService {
    static Logger logger = LoggerFactory.getLogger(FormSubService.class);

    @Autowired
    LoginDao loginDao;
    @Autowired
    TeamVisitDao teamVisitDao;
    public RestResult<Object> uploadTeamVisit(String postJson){
        //获取用户rawDate,user_id,signature进行用户权限验证
        RestResult<Object> result = null;
        JSONObject jsonObject = new JSONObject(postJson);
        JSONObject information = new JSONObject(jsonObject.getString("information"));
        String rawDate=jsonObject.getString("rawDate");
        Long userId = jsonObject.getLong("user_id");
        String sessionKey=null;
        if(rawDate==null){
            return result = new RestResult<>(RestResult.STATUS_OTHERS , "用户信息异常", null);
        }
        try {
            sessionKey=loginDao.foudSession(userId);
        }catch (Exception e){
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            return result = new RestResult<>(RestResult.STATUS_OTHERS , "用户信息异常", null);
        }

        String pass=rawDate+sessionKey;
        String signatuere1=null;
        try {
            signatuere1=DigestUtils.sha1Hex(pass);//加密
        }catch (Exception e){
            return result = new RestResult<>(RestResult.STATUS_OTHERS , "用户信息异常", null);        }

        String signature=jsonObject.getString("signature");//获取前台signature

        String openId=null;
        try{
            openId=loginDao.foundOpenId(userId);
        }catch (Exception e){
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
        }

        if(signatuere1.equals(signature)/*判断密钥是否正常*/){

            //为实体注入数据

            TeamVisitEntity teamVisitEntity=new TeamVisitEntity();
            teamVisitEntity.setOpenId(openId);
            teamVisitEntity.setAccompanyLeader(information.getString("accompanyLeader"));
            String str=information.getString("accompanyLeader");
            teamVisitEntity.setPlace(information.getString("place"));
            teamVisitEntity.setGust(information.getString("gust"));
            teamVisitEntity.setPosition(information.getString("UnitPosition"));
            teamVisitEntity.setPeopleNumber((information.getLong("peopleNumber")));
            teamVisitEntity.setIfAlumni(information.getBoolean("ifAlumni"));
            teamVisitEntity.setWelcomeMesag(information.getString("welcomeMessage"));
            teamVisitEntity.setContactUnit(information.getString("contactUnit"));
            teamVisitEntity.setPricpleSign(information.getString("pricpleSign"));
            teamVisitEntity.setContactMan(information.getString("contactMan"));
            teamVisitEntity.setContactPhone(information.getString("contactPhone"));
            Date date=Date.valueOf(information.getString("date"));
            teamVisitEntity.setDate(date);
            Time time=Time.valueOf(information.getString("time")+":00");
            teamVisitEntity.setTime(time);
            try {
                teamVisitDao.insertTV(teamVisitEntity);
            }catch (Exception e){
                logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
                result=new RestResult<>(RestResult.STATUS_WRONG_FORMAT, "表单提交失败",null);
            }
            result=new RestResult<>(RestResult.STATUS_SUCCESS, "表单提交成",null);
        }else {
            result=new RestResult<>(RestResult.STATUS_OTHERS, "用户信息异常",null);
        }

        return result;
    }


    @Autowired
    LearnVisitDao learnVisitDao;
    public RestResult<Object> uploadlearnVisit(String postJson){
        //获取用户rawDate,user_id,signature进行用户权限验证
        RestResult<Object> result = null;
        JSONObject jsonObject = new JSONObject(postJson);
        JSONObject information = new JSONObject(jsonObject.getString("information"));
        String rawDate=jsonObject.getString("rawDate");
        Long userId = jsonObject.getLong("user_id");
        String sessionKey=null;
        if(rawDate==null){
            return result = new RestResult<>(RestResult.STATUS_OTHERS , "用户信息异常", null);
        }
        try {
            sessionKey=loginDao.foudSession(userId);
        }catch (Exception e){
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            return result = new RestResult<>(RestResult.STATUS_OTHERS , "用户信息异常", null);
        }

        String pass=rawDate+sessionKey;
        String signatuere1=null;
        try {
            signatuere1=DigestUtils.sha1Hex(pass);//加密
        }catch (Exception e){
            return result = new RestResult<>(RestResult.STATUS_OTHERS , "用户信息异常", null);
        }

        String signature=jsonObject.getString("signature");//获取前台signature

        String openId=null;
        try{
            openId=loginDao.foundOpenId(userId);
        }catch (Exception e){
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
        }

        if(signatuere1.equals(signature)/*判断密钥是否正常*/) {
            //为实体注入数据
            LearnVisitEntity learnVisitEntity = new LearnVisitEntity();
            learnVisitEntity.setOpenId(openId);
            learnVisitEntity.setClasses(information.getString("class"));
            learnVisitEntity.setContactMan(information.getString("contactMan"));
            learnVisitEntity.setContactUnit(information.getString("contactUnit"));
            learnVisitEntity.setContactPhone(information.getString("contactPhone"));
            learnVisitEntity.setMajor(information.getString("major"));
            learnVisitEntity.setPricplesign(information.getString("pricpleSign"));
            learnVisitEntity.setPlace(information.getString("place"));
            Date date=Date.valueOf(information.getString("date"));
            learnVisitEntity.setDate(date);
            Time time=Time.valueOf(information.getString("time")+(":00"));
            learnVisitEntity.setTime(time);
            try {
                learnVisitDao.insertLv(learnVisitEntity);
            }catch (Exception e){
                logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
                return result = new RestResult<>(RestResult.STATUS_OTHERS , "提交失败", null);
            } result=new RestResult<>(RestResult.STATUS_SUCCESS, "表单提交成",null);
        }else {
            return result = new RestResult<>(RestResult.STATUS_OTHERS , "用户信息异常", null);
        }

        return result;
    }


}
