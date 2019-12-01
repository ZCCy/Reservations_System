package com.system.demo.Controller;

import com.system.demo.Utils.RestResult;
import com.system.demo.service.FormGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormGetController {

        @Autowired
        FormGetService formGetService;
        @RequestMapping("/get/teamVisit")
        public RestResult<Object> getteamVisit(@RequestBody String postJson) {
            RestResult<Object> result = null;
            result=formGetService.getTeamVisit(postJson);
            return result;
        }

    @RequestMapping("/get/learnVisit")
    public RestResult<Object> getlearnVisit(@RequestBody String postJson) {
        RestResult<Object> result = null;
        result=formGetService.getLearnVisit(postJson);
        return result;
    }
}

