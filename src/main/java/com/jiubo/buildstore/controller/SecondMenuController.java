package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.SecondMenuBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.SecondMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dx
 * @since 2020-05-20
 */
@RestController
@RequestMapping("/secondMenuBean")
public class SecondMenuController {

    @Autowired
    private SecondMenuService secondMenuService;


    @PostMapping("/getAllSM")
    public JSONObject getAllSM() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,secondMenuService.getAllSM());
        return jsonObject;
    }

    @PostMapping("/addSM")
    public JSONObject addSM(@RequestBody SecondMenuBean secondMenuBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        secondMenuService.addSM(secondMenuBean);
        return jsonObject;
    }

    @PostMapping("/patchSMById")
    public JSONObject patchSMById(@RequestBody SecondMenuBean secondMenuBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        secondMenuService.patchSMById(secondMenuBean);
        return jsonObject;
    }
}
