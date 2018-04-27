package com.ceiec.webguide.formal.service.impl;

import com.ceiec.webguide.formal.dao.UserAccountDao;
import com.ceiec.webguide.formal.entity.UserAccountEntity;
import com.ceiec.webguide.formal.service.UserAccountService;
import com.ceiec.webguide.formal.utils.Base64Util;
import com.ceiec.webguide.formal.utils.IpAddressUtil;
import com.ceiec.webguide.formal.utils.PropertyReaderUtils;
import com.ceiec.webguide.formal.vo.UserAccountVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

/**
 * CreateDate: 2018/4/23 <br/>
 * Author: WangHao <br/>
 * Description:
 **/

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private static final Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);

    @Autowired
    UserAccountDao userAccountDao;

    private static final String PORTRAIT_PATH_WINDOWS = "E:/staticfile/avatar/%s";

    private static final String PORTRAIT_PATH_LINUX = "/usr/webguide/staticfile/avatar/%s";

    @Override
    public UserAccountVo findUserAccountById(String userId) {
        return userAccountDao.selectAccountById(userId);
    }

    @Override
    public void updateUserAccount(UserAccountEntity userAccount) {
        userAccountDao.updateAccount(userAccount);
    }

    @Override
    public String updateUserAvatar(String avatarCode, String userId) {
        //生成用户头像，以32位UUID命名
        String savePath = "";
        String pictureName = UUID.randomUUID() + ".jpg";
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            savePath = String.format(PORTRAIT_PATH_WINDOWS, pictureName);
        } else {
            savePath = String.format(PORTRAIT_PATH_LINUX, pictureName);
        }
        boolean savePortraitSuccess = false;
        try {
            String base64ImgCode = avatarCode.replaceAll("data:image/jpeg;base64,", "");
            savePortraitSuccess = Base64Util.generateImage(base64ImgCode, savePath);
        } catch (IOException e) {
            logger.error("generate portrait error, userId: " + userId, e);
        }

        //保存头像访问路径
        String avatarUrl = "";
        if (savePortraitSuccess) {
            avatarUrl = String.format("http://%s:%s/static/avatar/%s", IpAddressUtil.getLocalIp(),
                    PropertyReaderUtils.getProValue("server.port"), pictureName);
        }

        //头像url保存到数据库
        userAccountDao.updateAvatar(avatarUrl, userId);

        //返回头像url
        return avatarUrl;
    }

    @Override
    public String insertUserAccount(UserAccountEntity userAccount) {
        //generate userId by UUID
        String userId = UUID.randomUUID().toString().replaceAll("-", "");
        userAccount.setUserId(userId);
        userAccountDao.insertUser(userAccount);
        return userId;
    }

    @Override
    public void setUserDeleted(String userId) {
        userAccountDao.setUserDeleted(userId);
    }
}
