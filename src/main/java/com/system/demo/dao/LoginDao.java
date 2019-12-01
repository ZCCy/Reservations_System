package com.system.demo.dao;

import com.system.demo.enity.WXSessionEntiy;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao {
    Long foudByOpenid(String openid);
    int foundRoleId(String openid);
    void insertAuser(WXSessionEntiy wxSessionEntiy);
    void updateAuser(WXSessionEntiy wxSessionEntiy);
    String foudSession(Long id);
    String foundOpenId(Long id);
}