package com.ceiec.webguide.formal.dao;//package com.ceiec.webguide.formal.mapper;

import com.ceiec.webguide.formal.dao.provider.UserAccountProvider;
import com.ceiec.webguide.formal.entity.UserAccountEntity;
import com.ceiec.webguide.formal.vo.UserAccountVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import static com.ceiec.webguide.formal.constant.MySqlConstant.USERACCOUNTTABLE;

/**
 * CreateDate: 2018/4/23 <br/>
 * Author: WangHao <br/>
 * Description: 用户账号dao
 **/
public interface UserAccountDao {

    /**
     * 通过用户id获取账号信息
     * 第一种方法，通过SQL语句实现关联查询，这种方法只需要执行一次SQL
     *
     * @param userAccountId 用户id
     * @return 账号信息
     */
    @Select("select u.*, r.system_mode from g_bs_user_account_info u, g_di_user_role r where u.user_id = #{userAccountId} and u.role = r.role_id")
    @Results({@Result(column = "user_id", property = "userId"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "real_name", property = "realName"),
            @Result(column = "job_number", property = "jobId"),
            @Result(column = "system_mode", property = "systemMode"),
            @Result(column = "contact", property = "mobile")})
    UserAccountVo selectAccountById(@Param("userAccountId") String userAccountId);

    /**
     * 通过用户id获取账号信息
     * 第二种方法，通过注解@One关联查询，这种方法需要执行两次SQL，且column的值需要填入下一个SQL语句需要的值
     *
     * @param userAccountId 用户id
     * @return 账号信息
     */
    @Select("select * from g_bs_user_account_info where user_id = #{userAccountId}")
    @Results({@Result(column = "user_id", property = "userId"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "real_name", property = "realName"),
            @Result(column = "job_number", property = "jobId"),
            @Result(column = "contact", property = "mobile"),
            @Result(column = "role", property = "systemMode",
                    one = @One(select = "com.ceiec.webguide.formal.mapper.RoleDao.findSysModeById",
                            fetchType = FetchType.EAGER))})
    UserAccountVo selectAccountByIdSecond(@Param("userAccountId") String userAccountId);

    /**
     * 修改用户信息
     *
     * @param userAccount 需要修改的用户信息
     */
    @UpdateProvider(type = UserAccountProvider.class, method = "updateUserAccount")
    void updateAccount(UserAccountEntity userAccount);

    /**
     * 修改用户头像
     *
     * @param avatar
     * @param userId
     */
    @Update("update " + USERACCOUNTTABLE + " set avatar = #{avatar} where user_id = #{userId}")
    void updateAvatar(@Param("avatar") String avatar, @Param("userId") String userId);


    /**
     * 新增用户
     *
     * @param userAccountEntity 新增的用户
     */
    @InsertProvider(type = UserAccountProvider.class, method = "insertUser")
    void insertUser(UserAccountEntity userAccountEntity);

    /**
     * 将用户设置为已删除状态
     *
     * @param userId 要删除的用户id
     */
    @Update("update " + USERACCOUNTTABLE + " set deleted = 1 where user_id = #{userId}")
    void setUserDeleted(@Param("userId") String userId);
}
