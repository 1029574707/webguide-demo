package com.ceiec.webguide.formal.service;

import com.alibaba.fastjson.JSONObject;
import com.ceiec.webguide.formal.entity.SysLogEntity;
import com.ceiec.webguide.formal.entity.UserAccountEntity;
import com.ceiec.webguide.formal.page.PagedItemsVO;

import java.util.List;

/**
 * CreateDate: 2018/4/24 <br/>
 * Author: WangHao <br/>
 * Description:
 **/
public interface SystemService {

    PagedItemsVO<UserAccountEntity> getUsersWithCondition(JSONObject condition);

    JSONObject getAllSysParam();

    void updateSysParam(JSONObject paramInfoJson);

    List<SysLogEntity> getSysLogsWithCondition(JSONObject condition);

    List<SysLogEntity> getTodaySysLogsWithCondition(JSONObject condition);
}
