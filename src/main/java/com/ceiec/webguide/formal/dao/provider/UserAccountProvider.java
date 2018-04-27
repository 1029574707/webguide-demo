package com.ceiec.webguide.formal.dao.provider;

import com.alibaba.fastjson.JSONObject;
import com.ceiec.webguide.formal.entity.UserAccountEntity;
import org.apache.ibatis.jdbc.SQL;

import static com.ceiec.webguide.formal.constant.MySqlConstant.USERACCOUNTTABLE;
import static com.ceiec.webguide.formal.constant.RoleConstant.*;

/**
 * CreateDate: 2018/4/23 <br/>
 * Author: WangHao <br/>
 * Description: the Provider for userDao
 **/
public class UserAccountProvider {

    public String updateUserAccount(UserAccountEntity userAccount) {
        return new SQL() {
            {
                UPDATE(USERACCOUNTTABLE);
                if (userAccount.getUserName() != null) {
                    SET(" user_name = #{userName} ");
                }
                if (userAccount.getPassword() != null) {
                    SET(" password = #{password} ");
                }
                if (userAccount.getRole() != null && userAccount.getRole() != 0) {
                    SET(" role = #{role} ");
                }
                if (userAccount.getDepartment() != null && userAccount.getDepartment() != 0) {
                    SET(" department = #{department} ");
                }
                if (userAccount.getAddress() != null) {
                    SET(" address = #{address} ");
                }
                if (userAccount.getMobile() != null) {
                    SET(" contact = #{mobile} ");
                }
                if (userAccount.getSignature() != null) {
                    SET(" signature = #{signature} ");
                }
                WHERE(" user_id = #{userId} ");
            }
        }.toString();
    }


    public String insertUser(UserAccountEntity userAccountEntity) {
        return new SQL() {
            {
                INSERT_INTO(USERACCOUNTTABLE);
                if (userAccountEntity.getUserId() != null) {
                    VALUES("user_id", "#{userId}");
                }
                if (userAccountEntity.getUserName() != null) {
                    VALUES("user_name", "#{userName}");
                }
                if (userAccountEntity.getRealName() != null) {
                    VALUES("real_name", "#{realName}");
                }
                if (userAccountEntity.getPassword() != null) {
                    VALUES("password", "#{password}");
                }
                if (userAccountEntity.getRole() != null && userAccountEntity.getRole() != 0) {
                    VALUES("role", "#{role}");
                }
                if (userAccountEntity.getJobId() != null) {
                    VALUES("job_number", "#{jobId}");
                }
            }
        }.toString();
    }

    public String getUsersWithCondition(JSONObject condition) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(USERACCOUNTTABLE);
                String keywords = condition.getString("keywords");
                if (keywords != null && !"".equals(keywords)) {
                    WHERE(" (user_name LIKE CONCAT ('%', #{keywords}, '%') OR job_number LIKE CONCAT ('%', #{keywords}, '%')) ");
                }
                Integer role = condition.getInteger("role");
                if (role != null) {
                    if (role == SUPERADMINROLE) {
                        WHERE(" role in ( " + ADMINROLE + ", " + GUIDERINFIXROLE + " ) ");
                    }
                    if (role == ADMINROLE) {
                        WHERE(" role in ( " + ADMINROLE + ", " + GUIDERROLE + " ) ");
                    }
                }
                // only select the not deleted accounts
                WHERE(" deleted = 0 ");
            }
        }.toString();

        Integer start = condition.getInteger("start");
        Integer size = condition.getInteger("size");
        int startIndex = (start - 1) * size;

        sql += " LIMIT " + startIndex + ", " + size;
        return sql;
    }
}
