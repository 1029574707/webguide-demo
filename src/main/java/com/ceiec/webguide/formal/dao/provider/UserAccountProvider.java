package com.ceiec.webguide.formal.dao.provider;

import com.ceiec.webguide.formal.entity.UserAccountEntity;
import com.ceiec.webguide.formal.vo.UserListConditionVO;
import org.apache.ibatis.jdbc.SQL;

import static com.ceiec.webguide.formal.constant.MySqlConstant.USERACCOUNTTABLE;
import static com.ceiec.webguide.formal.constant.RoleConstant.*;
import static com.ceiec.webguide.formal.constant.TestConstant.SYSTEMMODE;

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
                if (userAccount.getUsername() != null) {
                    SET(" user_name = #{username} ");
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
                if (userAccountEntity.getUsername() != null) {
                    VALUES("user_name", "#{username}");
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

    public String getUsersWithCondition(UserListConditionVO condition) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(USERACCOUNTTABLE);
                String keywords = condition.getKeywords();
                if (keywords != null && !"".equals(keywords)) {
                    WHERE(" (user_name LIKE CONCAT ('%', #{keywords}, '%') OR job_number LIKE CONCAT ('%', #{keywords}, '%')) ");
                }

                //超级管理员在混合模式下，可以查看所有引导员信息；在分工模式下，可以查看所有管理员信息；管理员可以查看所有管理员和引导员的信息
                int role = condition.getRole();
                int sysMode = SYSTEMMODE;
                if (role == SUPERADMINROLE) {
                    //混合模式
                    if (sysMode == 1) {
                        WHERE(" role =  " + GUIDERROLE);
                    } else {
                        WHERE(" role = " + ADMINROLE);
                    }
                } else if (role == ADMINROLE) {
                    WHERE(" role in ( " + ADMINROLE + ", " + GUIDERROLE + " ) ");
                }
                // only select the not deleted accounts
                WHERE(" deleted = 0 ");
            }
        }.toString();

        Integer start = condition.getStart();
        Integer size = condition.getSize();
        int startIndex = (start - 1) * size;

        sql += " LIMIT " + startIndex + ", " + size;
        return sql;
    }
}
