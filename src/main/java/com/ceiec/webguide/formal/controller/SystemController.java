package com.ceiec.webguide.formal.controller;

import com.alibaba.fastjson.JSONObject;
import com.ceiec.webguide.formal.entity.UserAccountEntity;
import com.ceiec.webguide.formal.page.PagedItemsVO;
import com.ceiec.webguide.formal.service.SystemService;
import com.ceiec.webguide.formal.utils.MessageType;
import com.ceiec.webguide.formal.utils.ResponseContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CreateDate: 2018/4/24 <br/>
 * Author: WangHao <br/>
 * Description:
 **/
@RestController
@RequestMapping(value = "/system")
public class SystemController {

    @Autowired
    SystemService systemService;

    @PostMapping(value = "/getUserList")
    public ResponseContent getAllUsers(@RequestBody JSONObject condition) {
        PagedItemsVO<UserAccountEntity> pagedUsers = systemService.getUsersWithCondition(condition);
        return new ResponseContent(MessageType.SUCCESS, pagedUsers);
    }
}
