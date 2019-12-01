package com.system.demo.Controller;

import com.system.demo.Utils.HttpClientUtils;
import com.system.demo.Utils.RestResult;
import com.system.demo.enity.WXSessionEntiy;
import com.system.demo.service.LoginService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/wLogin")
    public RestResult<Object> wLogin(String code){
        RestResult<Object> result = null;

        System.out.println(code);

        //GET https://api.weixin.qq.com/sns/jscode2session?
        // appid=APPID&
        // secret=SECRET&
        // js_code=JSCODE&
        // grant_type=authorization_code

        String url="https://api.weixin.qq.com/sns/jscode2session?";


        HashMap<String,Long> user = new HashMap<>();
        Map<String,String> param = new HashMap<>();
        param.put("appid","wxcca05e4dc5d55b09");
        //param.put("appid","wx3112c0ad0c164e1f");//测试用
        //param.put("secret","e9324e1b2ea173a755f3f55c6fd71219");//测试
        param.put("secret","5e7afb0071c31d731638ef01f5361661");
        param.put("js_code",code);
        param.put("grant_type","authorization_code");
        String wxResult=HttpClientUtils.doGet(url,param);
        System.out.println(wxResult);
        JSONObject jsonObject = new JSONObject(wxResult);
        WXSessionEntiy wxSessionEntiy = new WXSessionEntiy();
        wxSessionEntiy.setSession_key(jsonObject.getString("session_key"));
        wxSessionEntiy.setOpenid(jsonObject.getString("openid"));
        user=loginService.login(wxSessionEntiy);
        if(user.get("roleId")==0){
            result = new RestResult<>(RestResult.STATUS_OTHERS, "普通用户登陆成功", user);
        }else if(user.get("roleId")==1){
            result = new RestResult<>(RestResult.STATUS_OTHERS, "管理员登陆成功", user);
        }else {
            result = new RestResult<>(RestResult.STATUS_WRONG_FORMAT, "登陆失败",null);
        }

        return result;


    }
}
