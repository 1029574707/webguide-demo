package com.ceiec.webguide.formal.service;

import com.alibaba.fastjson.JSONObject;
import com.ceiec.webguide.formal.entity.SysParamEntity;
import com.ceiec.webguide.formal.entity.UserAccountEntity;
import com.ceiec.webguide.formal.page.PagedItemsVO;
import com.ceiec.webguide.formal.vo.SysLogVO;

import java.util.List;

/**
 * CreateDate: 2018/4/24 <br/>
 * Author: WangHao <br/>
 * Description:
 **/
public interface SystemService {

    PagedItemsVO<UserAccountEntity> getUsersWithCondition(JSONObject condition);

    JSONObject getAllSysParam();

    void updateSysParam(List<SysParamEntity> paramInfoJson);

    PagedItemsVO<SysLogVO> getSysLogsWithCondition(JSONObject condition);

    PagedItemsVO getTodaySysLogsWithCondition(JSONObject condition);
}
