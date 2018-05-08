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


    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping(value = "/info")
    public ResponseContent getUserInfo() {
        String userId = "ABCDEFGHIGKLMNOPQRSTUVWXYZ123456";
        UserAccountVo userAccountVo = userAccountService.findUserAccountById(userId);
        return new ResponseContent(MessageType.SUCCESS, userAccountVo);
    }

    @PostMapping(value = "/info/update")
    public ResponseContent updateUserInfo(@RequestBody UserAccountEntity userAccount) {
        userAccountService.updateUserAccount(userAccount);
        return new ResponseContent(MessageType.SUCCESS);
    }

    @PostMapping(value = "/avatar/update")
    public ResponseContent updateUserAvatar(@RequestBody JSONObject avatarJson) {
        //保留获取userId的逻辑
        //String userId = "1decccc28231490f993102c30b446e0d";
        String userId = "ABCDEFGHIGKLMNOPQRSTUVWXYZ123456";

        String avatarUrl = userAccountService.updateUserAvatar(avatarJson.getString("avatarCode"), userId);
        return new ResponseContent(MessageType.SUCCESS, avatarUrl);
    }


}