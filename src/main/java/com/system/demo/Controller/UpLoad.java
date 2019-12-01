package com.system.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.system.demo.Utils.RestResult;
import com.system.demo.Utils.ToolUpLoad;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UpLoad {
    /**
     * @描述:文件上传
     * 请求页面file组件的name必须为"file"才可以获取到信息,否则为null
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public RestResult<Object> upload(MultipartFile file){
        RestResult<Object> result = null;
        //
        System.out.println(file);
       String resultMap = ToolUpLoad.fileUpload(file,"/java_project/docx");
        return  result = new RestResult<>(RestResult.STATUS_OTHERS, "文件上传成功",  JSONObject.toJSONString(resultMap));
    }
}
