package com.ceiec.webguide.formal.controller;

import com.alibaba.fastjson.JSONObject;
import com.ceiec.webguide.formal.entity.SysParamEntity;
import com.ceiec.webguide.formal.entity.UserAccountEntity;
import com.ceiec.webguide.formal.page.PagedItemsVO;
import com.ceiec.webguide.formal.service.SystemService;
import com.ceiec.webguide.formal.service.UserAccountService;
import com.ceiec.webguide.formal.utils.MessageType;
import com.ceiec.webguide.formal.utils.ResponseContent;
import com.ceiec.webguide.formal.vo.ParentVO;
import com.ceiec.webguide.formal.vo.SysLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    UserAccountService userAccountService;

    @GetMapping(value = "/user/list")
    public ResponseContent getAllUsers(@RequestBody ParentVO condition) {
        PagedItemsVO<UserAccountEntity> pagedUsers = systemService.getUsersWithCondition(condition);
        return new ResponseContent(MessageType.SUCCESS, pagedUsers);
    }

    @PostMapping(value = "/user/add")
    public ResponseContent insertUserAccount(@RequestBody UserAccountEntity userAccount) {
        String userId = userAccountService.insertUserAccount(userAccount);
        return new ResponseContent(MessageType.SUCCESS, userId);
    }

    @PostMapping(value = "/user/update")
    public ResponseContent updateSystemInfo(@RequestBody List<SysParamEntity> paramInfos){
        systemService.updateSysParam(paramInfos);
        return new ResponseContent(MessageType.SUCCESS);
    }

    @DeleteMapping(value = "/user/delete/{userId}")
    public ResponseContent deleteUserAccount(@PathVariable("userId") String userId){
        userAccountService.setUserDeleted(userId);
        return new ResponseContent(MessageType.SUCCESS);
    }

    @GetMapping(value = "/parameter/list")
    public ResponseContent getSysParam(){
        JSONObject allSysParam = systemService.getAllSysParam();
        return new ResponseContent(MessageType.SUCCESS, allSysParam);
    }

    @PostMapping(value = "/parameter/update")
    public ResponseContent updateSysParams(List<SysParamEntity> params){
        systemService.updateSysParam(params);
        return new ResponseContent(MessageType.SUCCESS);
    }

    @PostMapping(value = "/log/list/all")
    public ResponseContent getSysLog(@RequestBody JSONObject condition){
        PagedItemsVO<SysLogVO> pagedSysLogs = systemService.getSysLogsWithCondition(condition);
        return new ResponseContent(MessageType.SUCCESS, pagedSysLogs);
    }

    @PostMapping(value = "/log/list/today")
    public ResponseContent getTodaySysLog(@RequestBody JSONObject condition){
        PagedItemsVO<SysLogVO> pagedSysLogs = systemService.getSysLogsWithCondition(condition);
        return new ResponseContent(MessageType.SUCCESS, pagedSysLogs);
    }





}
