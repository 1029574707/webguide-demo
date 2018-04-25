package com.ceiec.webguide.formal.service;

import com.ceiec.webguide.formal.entity.UserAccountEntity;
import com.ceiec.webguide.formal.vo.UserAccountVo;
import org.springframework.stereotype.Service;

/**
 * CreateDate: 2018/4/23 <br/>
 * Author: WangHao <br/>
 * Description:
 **/

@Service
public interface UserAccountService {

    UserAccountVo findUserAccountById(String userId);

    void updateUserAccount(UserAccountEntity userAccount);

    String updateUserAvatar(String avatarCode, String userId);

    String insertUserAccount(UserAccountEntity userAccount);

    void setUserDeleted(String userId);
}
