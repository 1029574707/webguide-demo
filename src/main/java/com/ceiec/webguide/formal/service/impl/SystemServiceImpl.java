package com.ceiec.webguide.formal.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ceiec.webguide.formal.dao.SystemDao;
import com.ceiec.webguide.formal.entity.SysLogEntity;
import com.ceiec.webguide.formal.entity.SysParamEntity;
import com.ceiec.webguide.formal.entity.UserAccountEntity;
import com.ceiec.webguide.formal.page.PagedItemsVO;
import com.ceiec.webguide.formal.service.SystemService;
import com.ceiec.webguide.formal.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CreateDate: 2018/4/24 <br/>
 * Author: WangHao <br/>
 * Description:
 **/
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    SystemDao systemDao;

    @Override
    public PagedItemsVO<UserAccountEntity> getUsersWithCondition(JSONObject condition) {
        List<UserAccountEntity> users = systemDao.getUsersWithCondition(condition);
        int totalCount = systemDao.countUsersWithCondition(condition);
        int pageIndex =  condition.getInteger("start");
        int size = condition.getInteger("size");
        return PageUtil.createPageMsg(totalCount, pageIndex, size, users);
    }

    @Override
    public JSONObject getAllSysParam() {
        List<SysParamEntity> sysParams = systemDao.getAllSysParam();
        List<String> allWebsites = systemDao.getAllWebsites();
        List<String> allGuideTypes = systemDao.getAllGuideTypes();
        JSONObject allParams = new JSONObject();
        allParams.put("guideSites", allWebsites);
        allParams.put("guideType", allGuideTypes);
        for (SysParamEntity sysParam : sysParams) {
            allParams.put(sysParam.getParameterName(), sysParam.getValue());
        }
        return allParams;
    }

    @Override
    public void updateSysParam(JSONObject paramInfoJson) {

    }

    @Override
    public List<SysLogEntity> getSysLogsWithCondition(JSONObject condition) {
        return null;
    }

    @Override
    public List<SysLogEntity> getTodaySysLogsWithCondition(JSONObject condition) {
        return null;
    }
}
