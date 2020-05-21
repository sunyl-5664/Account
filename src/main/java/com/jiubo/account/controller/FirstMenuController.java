package com.jiubo.account.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.account.bean.FirstMenuBean;
import com.jiubo.account.common.Constant;
import com.jiubo.account.service.FirstMenuService;
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
@RequestMapping("/firstMenuBean")
public class FirstMenuController {
    @Autowired
    private FirstMenuService firstMenuService;


    @PostMapping("/getFMByCondition")
    public JSONObject getFMByCondition() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, firstMenuService.getFMByCondition());
        return jsonObject;
    }

    @PostMapping("/addFM")
    public JSONObject addFM(@RequestBody FirstMenuBean firstMenuBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        firstMenuService.addFM(firstMenuBean);
        return jsonObject;
    }
    @PostMapping("/patchFMById")
    public JSONObject patchFMById(@RequestBody FirstMenuBean firstMenuBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        firstMenuService.patchFMById(firstMenuBean);
        return jsonObject;
    }
}
