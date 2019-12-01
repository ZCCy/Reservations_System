package com.system.demo.service;

import com.system.demo.Utils.RestResult;
import com.system.demo.dao.LearnVisitDao;
import com.system.demo.dao.LoginDao;
import com.system.demo.dao.TeamVisitDao;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormRviewService {
    static Logger logger = LoggerFactory.getLogger(FormSubService.class);

    @Autowired
    LoginDao loginDao;
    @Autowired
    LearnVisitDao learnVisitDao;
    public RestResult<Object> updateLearn(String postJson) {
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

        int role_id=loginDao.foundRoleId(openId);
        if (signatuere1.equals(signature)&&role_id==1/*判断密钥是否正常*/) {
            /**
             * 密钥正常：
             */
            Long id=jsonObject.getLong("formId");
            int status=jsonObject.getInt("status");
            learnVisitDao.UpdateLearn(id,status);
        }else {
            return result = new RestResult<>(RestResult.STATUS_OTHERS , "用户信息异常", null);
        }
         return result;
    }

    @Autowired
    TeamVisitDao teamVisitDao;
    public RestResult<Object> updateTeam(String postJson) {
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

        int role_id=loginDao.foundRoleId(openId);
        if (signatuere1.equals(signature)&&role_id==1/*判断密钥是否正常*/) {
            /**
             * 密钥正常：
             */
            Long id=jsonObject.getLong("formId");
            int status=jsonObject.getInt("status");
            teamVisitDao.updateTeam(id,status);
         }else {
            return result = new RestResult<>(RestResult.STATUS_OTHERS , "用户信息异常", null);
        }
        return result;
    }
}
