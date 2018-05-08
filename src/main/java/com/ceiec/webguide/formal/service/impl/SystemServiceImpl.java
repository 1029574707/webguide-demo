package com.ceiec.webguide.formal.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ceiec.webguide.formal.dao.SystemDao;
import com.ceiec.webguide.formal.entity.SysParamEntity;
import com.ceiec.webguide.formal.entity.UserAccountEntity;
import com.ceiec.webguide.formal.page.PagedItemsVO;
import com.ceiec.webguide.formal.service.SystemService;
import com.ceiec.webguide.formal.service.UserAccountService;
import com.ceiec.webguide.formal.utils.PageUtil;
import com.ceiec.webguide.formal.vo.ParentVO;
import com.ceiec.webguide.formal.vo.SysLogVO;
import com.ceiec.webguide.formal.vo.UserAccountVo;
import com.ceiec.webguide.formal.vo.UserListConditionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ceiec.webguide.formal.constant.TestConstant.USERID;

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

    @Autowired
    UserAccountService userAccountService;

    @Override
    public PagedItemsVO<UserAccountEntity> getUsersWithCondition(ParentVO condition) {

        UserListConditionVO userListConditionVO = new UserListConditionVO();
        userListConditionVO.setUserId(USERID);
        UserAccountVo userAccount = userAccountService.findUserAccountById(USERID);
        userListConditionVO.setRole(userAccount.getRole());
        userListConditionVO.setKeywords(condition.getKeywords());
        userListConditionVO.setSize(condition.getSize());
        userListConditionVO.setStart(condition.getStart());

        List<UserAccountEntity> users = systemDao.getUsersWithCondition(userListConditionVO);
        int totalCount = systemDao.countUsersWithCondition(userListConditionVO);
        int pageIndex = condition.getStart();
        int size = condition.getSize();
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
        allParams.put("items", sysParams);
        return allParams;
    }

    @Override
    public void updateSysParam(List<SysParamEntity> paramInfoArray) {
        for (SysParamEntity sysParamEntity : paramInfoArray) {
            String name = sysParamEntity.getName();
            Object value = sysParamEntity.getValue();
            systemDao.updateSysParam(name, value);
        }
    }

    @Override
    public PagedItemsVO<SysLogVO> getSysLogsWithCondition(JSONObject condition) {
        List<SysLogVO> sysLogs = systemDao.getSysLogsWithCondition(condition);
        int totalCount = systemDao.countUsersWithCondition(condition);
        int pageIndex = condition.getInteger("start");
        int size = condition.getInteger("size");
        return PageUtil.createPageMsg(totalCount, pageIndex, size, sysLogs);
    }

    @Override
    public PagedItemsVO<SysLogVO> getTodaySysLogsWithCondition(JSONObject condition) {
        List<SysLogVO> sysLogs = systemDao.getTodaySysLogsWithCondition(condition);
        int totalCount = systemDao.countUsersWithCondition(condition);
        int pageIndex = condition.getInteger("start");
        int size = condition.getInteger("size");
        return PageUtil.createPageMsg(totalCount, pageIndex, size, sysLogs);
    }
}
