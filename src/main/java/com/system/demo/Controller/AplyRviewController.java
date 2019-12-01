package com.system.demo.Controller;

import com.system.demo.Utils.RestResult;
import com.system.demo.service.FormRviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public class AplyRviewController {
    @Autowired
    FormRviewService formRviewService;
    @RequestMapping("/manage/updateLearn")
    public RestResult<Object> learn(@RequestBody String postJson) {
        RestResult<Object> result = null;
        formRviewService.updateLearn(postJson);
        return result;
    }
    @RequestMapping("/manage/updateTeam")
    public RestResult<Object> team(@RequestBody String postJson) {
        RestResult<Object> result = null;
        formRviewService.updateTeam(postJson);
        return result;
    }
}
