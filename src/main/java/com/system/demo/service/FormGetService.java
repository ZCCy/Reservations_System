package com.system.demo.service;

import com.system.demo.Utils.RestResult;
import com.system.demo.dao.LearnVisitDao;
import com.system.demo.dao.LoginDao;
import com.system.demo.dao.TeamVisitDao;
import com.system.demo.enity.LearnVisitEntity;
import com.system.demo.enity.TeamVisitEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FormGetService {
    static Logger logger = LoggerFactory.getLogger(FormSubService.class);

    @Autowired
    TeamVisitDao teamVisitDao;

    @Autowired
    LoginDao loginDao;

    public RestResult<Object> getTeamVisit(String postJson) {
        //获取用户rawDate,user_id,signature进行用户权限验证
        RestResult<Object> result = null;
        JSONObject jsonObject = new JSONObject(postJson);
        String rawDate = jsonObject.getString("rawDate");
        Long userId = jsonObject.getLong("user_id");
        String sessionKey = null;
        if (rawDate == null) {
            return result = new RestResult<>(RestResult.STATUS_OTHERS , "用户信息异常", null);
        }
        try {
            sessionKey = loginDao.foudSession(userId);
        } catch (Exception e) {
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            return result = new RestResult<>(RestResult.STATUS_OTHERS , "用户信息异常", null);        }

        String pass = rawDate + sessionKey;
        String signatuere1 = null;
        try {
            signatuere1 = DigestUtils.sha1Hex(pass);//加密
        } catch (Exception e) {
            return result = new RestResult<>(RestResult.STATUS_OTHERS , "用户信息异常", null);        }

        String signature = jsonObject.getString("signature");//获取前台signature

        String openId = null;
        try {
            openId = loginDao.foundOpenId(userId);
        } catch (Exception e) {
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
        }

        if (signatuere1.equals(signature)/*判断密钥是否正常*/) {
            /**
             * 密钥正常：
             */
            ArrayList<TeamVisitEntity> teamVisit = new ArrayList<>();
            try {
                teamVisit = teamVisitDao.getForm();
            } catch (Exception e) {
                logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
                return result = new RestResult<>(RestResult.STATUS_OTHERS , "表单提取失败", null);
            }
            result=new RestResult<>(RestResult.STATUS_SUCCESS, "表单提取成功",teamVisit);
        }
        return result;
    }


    @Autowired
    LearnVisitDao learnVisitDao;
    public RestResult<Object> getLearnVisit(String postJson) {
        //获取用户rawDate,user_id,signature进行用户权限验证
        RestResult<Object> result = null;
        JSONObject jsonObject = new JSONObject(postJson);
        String rawDate = jsonObject.getString("rawDate");
        Long userId = jsonObject.getLong("user_id");
        String sessionKey = null;
        if (rawDate == null) {
            return result = new RestResult<>(RestResult.STATUS_OTHERS , "用户信息异常", null);
        }
        try {
            sessionKey = loginDao.foudSession(userId);
        } catch (Exception e) {
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            return result = new RestResult<>(RestResult.STATUS_OTHERS , "用户信息异常", null);
        }

        String pass = rawDate + sessionKey;
        String signatuere1 = null;
        try {
            signatuere1 = DigestUtils.sha1Hex(pass);//加密
        } catch (Exception e) {
            return result = new RestResult<>(RestResult.STATUS_OTHERS , "用户信息异常", null);        }

        String signature = jsonObject.getString("signature");//获取前台signature

        String openId = null;
        try {
            openId = loginDao.foundOpenId(userId);
        } catch (Exception e) {
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
        }

        if (signatuere1.equals(signature)/*判断密钥是否正常*/) {
            /**
             * 密钥正常：
             */
            ArrayList<LearnVisitEntity> learnVisit = new ArrayList<>();
            try {
                learnVisit=learnVisitDao.getForm();
            } catch (Exception e) {
                logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
                return result = new RestResult<>(RestResult.STATUS_OTHERS , "表单提取失败", null);
            }
            result=new RestResult<>(RestResult.STATUS_SUCCESS, "表单提取成功",learnVisit);
        }
        return result;
    }
}

