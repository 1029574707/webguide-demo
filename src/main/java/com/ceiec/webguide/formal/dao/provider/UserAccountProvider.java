package com.ceiec.webguide.formal.dao.provider;

import com.ceiec.webguide.formal.entity.UserAccountEntity;
import org.apache.ibatis.jdbc.SQL;

import static com.ceiec.webguide.formal.constant.MySqlConstant.USERACCOUNTTABLE;

/**
 * CreateDate: 2018/4/23 <br/>
 * Author: WangHao <br/>
 * Description:
 **/
public class UserAccountProvider {

    public String updateUserAccount(UserAccountEntity userAccount) {
        return new SQL() {
            {
                UPDATE(USERACCOUNTTABLE);
                if (userAccount.getPassword() != null) {
                    SET(" password = #{password} ");
                }
                if (userAccount.getRole() != 0) {
                    SET(" role = #{role} ");
                }
                if (userAccount.getDepartment() != 0) {
                    SET(" department = #{department} ");
                }
                if (userAccount.getAddress() != null) {
                    SET(" address = #{address} ");
                }
                if (userAccount.getContact() != null) {
                    SET(" contact = #{contact} ");
                }
                if (userAccount.getSignature() != null) {
                    SET(" signature = #{signature} ");
                }
                if (userAccount.getAvatar() != null) {
                    SET(" avatar = #{avatar} ");
                }
            }
        }.toString();
    }
}
