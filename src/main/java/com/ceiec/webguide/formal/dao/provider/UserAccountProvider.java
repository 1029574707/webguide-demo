package com.ceiec.webguide.formal.dao.provider;

import com.alibaba.fastjson.JSONObject;
import com.ceiec.webguide.formal.entity.UserAccountEntity;
import org.apache.ibatis.jdbc.SQL;

import static com.ceiec.webguide.formal.constant.MySqlConstant.USERACCOUNTTABLE;

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


    public String insertUser(UserAccountEntity userAccountEntity){
        return new SQL(){
            {
                INSERT_INTO(USERACCOUNTTABLE);
                String userId = userAccountEntity.getUserId();
                VALUES("user_id", "#{userId}");
                String userName = userAccountEntity.getUserName();
                VALUES("user_name", "#{userName}");
                String loginName = userAccountEntity.getLoginName();
                VALUES("login_name", "#{loginName}");
                String password = userAccountEntity.getPassword();
                VALUES("password", "#{password}");
                Integer role = userAccountEntity.getRole();
                VALUES("role", "#{role}");
                String jobId = userAccountEntity.getJobId();
                VALUES("job_number", "#{jobId}");
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
                    WHERE(" role = #{role} ");
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
