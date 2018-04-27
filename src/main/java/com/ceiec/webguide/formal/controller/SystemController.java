package com.ceiec.webguide.formal.controller;

import com.alibaba.fastjson.JSONObject;
import com.ceiec.webguide.formal.entity.SysParamEntity;
import com.ceiec.webguide.formal.entity.UserAccountEntity;
import com.ceiec.webguide.formal.page.PagedItemsVO;
import com.ceiec.webguide.formal.service.SystemService;
import com.ceiec.webguide.formal.utils.MessageType;
import com.ceiec.webguide.formal.utils.ResponseContent;
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

    @PostMapping(value = "/getUserList")
    public ResponseContent getAllUsers(@RequestBody JSONObject condition) {
        PagedItemsVO<UserAccountEntity> pagedUsers = systemService.getUsersWithCondition(condition);
        return new ResponseContent(MessageType.SUCCESS, pagedUsers);
    }

    @PostMapping(value = "/updateSystemInfo")
    public ResponseContent updateSystemInfo(@RequestBody List<SysParamEntity> paramInfos){
        systemService.updateSysParam(paramInfos);
        return new ResponseContent(MessageType.SUCCESS);
    }

    @PostMapping(value = "/getSystemLog")
    public ResponseContent getSysLog(@RequestBody JSONObject condition){
        PagedItemsVO<SysLogVO> pagedSysLogs = systemService.getSysLogsWithCondition(condition);
        return new ResponseContent(MessageType.SUCCESS, pagedSysLogs);
    }

    @PostMapping(value = "/getTodaySystemLog")
    public ResponseContent getTodaySysLog(@RequestBody JSONObject condition){
        PagedItemsVO<SysLogVO> pagedSysLogs = systemService.getSysLogsWithCondition(condition);
        return new ResponseContent(MessageType.SUCCESS, pagedSysLogs);
    }

    @GetMapping(value = "/getSystemInfo")
    public ResponseContent getSysParam(){
        JSONObject allSysParam = systemService.getAllSysParam();
        return new ResponseContent(MessageType.SUCCESS, allSysParam);
    }
}
