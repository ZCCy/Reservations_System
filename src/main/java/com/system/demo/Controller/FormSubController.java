package com.system.demo.Controller;

import com.system.demo.Utils.RestResult;
import com.system.demo.service.FormSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormSubController {

    @Autowired
    FormSubService formSubService;
    @RequestMapping("/sub/teamVisit")
    public RestResult<Object> teamVisit(@RequestBody String postJson) {
        RestResult<Object> result = null;
        result=formSubService.uploadTeamVisit(postJson);
        return result;
    }

    @RequestMapping("/sub/learnVisit")
    public RestResult<Object> learnVisit(@RequestBody String postJson) {
        RestResult<Object> result = null;
        result=formSubService.uploadlearnVisit(postJson);
        return result;
    }
}

