package com.system.demo.Controller;

import com.system.demo.Utils.RestResult;
import com.system.demo.dao.LoginDao;
import com.system.demo.service.FormSubService;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;

@RestController
public class DownLoadController {
    static Logger logger = LoggerFactory.getLogger(FormSubService.class);

    @Autowired
    LoginDao loginDao;

    @RequestMapping("/getWord")
    public RestResult<Object> getAllFileName(@RequestBody String postJson) {
       //获取用户rawDate,user_id,signature进行用户权限验证
        RestResult<Object> result = null;
        JSONObject jsonObject = new JSONObject(postJson);
        String rawDate = jsonObject.getString("rawDate");
        String user_id = jsonObject.getString("user_id");
        Long userId = Long.valueOf(user_id);
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
            return result = new RestResult<>(RestResult.STATUS_OTHERS , "加密异常", null);
        }

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
            boolean flag = false;
            File file = new File("D:\\ScrVideo\\doc");
            File[] tempList = file.listFiles();
            ArrayList<String> fileNameList = new ArrayList<>();
            for (int i = 0; i < tempList.length; i++) {
                if (tempList[i].isFile()) {
                    fileNameList.add(tempList[i].getName());
                }
            }
            System.out.println(fileNameList);
            result = new RestResult<>(RestResult.STATUS_SUCCESS, "表单提交成", fileNameList);
        }
        return result;
    }

    //文件下载相关代码
    @RequestMapping("/download")
    public String downloadFile( HttpServletResponse response) {
        String fileName = "2.doc";// 设置文件名，根据业务需要替换成要下载的文件名
        if (fileName != null) {
            //设置文件路径
            String realPath = "D:\\ScrVideo\\doc";
            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    @RequestMapping("/getDownloadUrl")
    public String downloadFile( ) {
      return "下载地址";
    }


}
