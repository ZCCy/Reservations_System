package com.system.demo.service;

import com.system.demo.dao.LoginDao;
import com.system.demo.enity.WXSessionEntiy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginService {
    static Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    LoginDao loginDao;
    public HashMap<String,Long> login(WXSessionEntiy wxSessionEntiy){
        Long user_id=0l;
        int role_id=0;
        String openid=wxSessionEntiy.getOpenid();
        String session_key=wxSessionEntiy.getSession_key();
        try {
            user_id=loginDao.foudByOpenid(openid);
        }catch (Exception e){
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
        }
        if(user_id==null){//没有注册过
            try {
                loginDao.insertAuser(wxSessionEntiy);
            }catch (Exception e){
                logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            }
        }else{
            try {
                loginDao.updateAuser(wxSessionEntiy);
            }catch (Exception e){
                logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            }
        }
        try {
            role_id=loginDao.foundRoleId(openid);
        }catch (Exception e){
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
        }
        HashMap<String,Long> user=new HashMap<>();
        user.put("userId",user_id);
        user.put("roleId",Long.valueOf(role_id));
        return user;
    }
}
