package com.ceiec.webguide.formal.controller;

import com.alibaba.fastjson.JSONObject;
import com.ceiec.webguide.formal.entity.UserAccountEntity;
import com.ceiec.webguide.formal.service.UserAccountService;
import com.ceiec.webguide.formal.utils.MessageType;
import com.ceiec.webguide.formal.utils.ResponseContent;
import com.ceiec.webguide.formal.vo.UserAccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * CreateDate: 2018/4/23 <br/>
 * Author: WangHao <br/>
 * Description:
 **/

@RestController()
@RequestMapping(value = "/user")
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;


    @GetMapping(value = "/getUserInfo/{userId}")
    public ResponseContent getUserInfo(@PathVariable("userId") String userId) {
        UserAccountVo userAccountVo = userAccountService.findUserAccountById(userId);
        return new ResponseContent(MessageType.SUCCESS, userAccountVo);
    }

    @PostMapping(value = "/updateUserInfo")
    public ResponseContent updateUserInfo(@RequestBody UserAccountEntity userAccount) {
        userAccountService.updateUserAccount(userAccount);
        return new ResponseContent(MessageType.SUCCESS);
    }

    @PostMapping(value = "/updateUserAvatar")
    public ResponseContent updateUserAvatar(@RequestBody JSONObject avatarJson) {
        //保留获取userId的逻辑
        //String userId = "1decccc28231490f993102c30b446e0d";
        String userId = "ABCDEFGHIGKLMNOPQRSTUVWXYZ123456";

        String avatarUrl = userAccountService.updateUserAvatar(avatarJson.getString("avatarCode"), userId);
        return new ResponseContent(MessageType.SUCCESS, avatarUrl);
    }

    @PostMapping(value = "/addUser")
    public ResponseContent insertUserAccount(@RequestBody UserAccountEntity userAccount) {
        String userId = userAccountService.insertUserAccount(userAccount);
        return new ResponseContent(MessageType.SUCCESS, userId);
    }

    @DeleteMapping(value = "/deleteUser/{userId}")
    public ResponseContent deleteUserAccount(@PathVariable("userId") String userId){
        userAccountService.setUserDeleted(userId);
        return new ResponseContent(MessageType.SUCCESS);
    }
}