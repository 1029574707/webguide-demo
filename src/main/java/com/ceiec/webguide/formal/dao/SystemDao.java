package com.ceiec.webguide.formal.dao;

import com.alibaba.fastjson.JSONObject;
import com.ceiec.webguide.formal.dao.provider.SystemProvider;
import com.ceiec.webguide.formal.dao.provider.UserAccountProvider;
import com.ceiec.webguide.formal.entity.SysParamEntity;
import com.ceiec.webguide.formal.entity.UserAccountEntity;
import com.ceiec.webguide.formal.vo.SysLogVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

import static com.ceiec.webguide.formal.constant.MySqlConstant.*;

/**
 * CreateDate: 2018/4/24 <br/>
 * Author: WangHao <br/>
 * Description: the system dao
 **/
public interface SystemDao {

    /**
     * select accounts with giving conditions
     *
     * @param condition conditions
     * @return accounts
     */
    @SelectProvider(type = UserAccountProvider.class, method = "getUsersWithCondition")
    @Results({@Result(column = "user_id", property = "userId"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "login_name", property = "loginName"),
            @Result(column = "job_number", property = "jobId")})
    List<UserAccountEntity> getUsersWithCondition(JSONObject condition);

    /**
     * get all system parameters
     *
     * @return list- parameters
     */
    @Select("select * from " + SYSTEMLOGTABLE)
    @Results({@Result(column = "parameter_id", property = "parameterId"),
            @Result(column = "parameter_name", property = "parameterName"),
            @Result(column = "operate_role", property = "operateRoles"),
            @Result(column = "system_mode", property = "systemMode")})
    List<SysParamEntity> getAllSysParam();


    /**
     * modify a system parameter value
     *
     * @param name  the parameter need to modify
     * @param value the value need to be modified
     */
    @Update("update " + SYSTEMPARAMTABLE + " set value = #{value} where parameter_name = #{parameter_name}")
    void updateSysParam(@Param("parameter_name") String name, @Param("value") Object value);

    /**
     * find the system logs with giving conditions
     *
     * @param condition conditions
     * @return list- system logs
     */
    @SelectProvider(type = SystemProvider.class, method = "getSysLogsWithCondition")
    @Results({@Result(column = "log_id", property = "logId"),
            @Result(column = "task_id", property = "taskId"),
            @Result(column = "log_content", property = "log_content"),
            @Result(column = "operator_id", property = "operator"),
            @Result(column = "operate_time", property = "operationTime"),
            @Result(column = "role", property = "operatorRole")})
    List<SysLogVO> getSysLogsWithCondition(JSONObject condition);

    /**
     * find the system logs with giving condition in today
     *
     * @param condition conditions
     * @return list- system logs
     */
    @SelectProvider(type = SystemProvider.class, method = "getTodaySysLogsWithCondition")
    @Results({@Result(column = "log_id", property = "logId"),
            @Result(column = "task_id", property = "taskId"),
            @Result(column = "log_content", property = "log_content"),
            @Result(column = "operator_id", property = "operator"),
            @Result(column = "operate_time", property = "operationTime"),
            @Result(column = "role", property = "operatorRole")})
    List<SysLogVO> getTodaySysLogsWithCondition(JSONObject condition);

    /**
     * get all system supported websites
     *
     * @return list- websites' name
     */
    @Select("select website_name from " + DIWEBSITETABLE)
    List<String> getAllWebsites();

    /**
     * get all system supported guide types
     *
     * @return list- guide types' name
     */
    @Select("select guide_type_name from " + DIGUIDETYPETABLE)
    List<String> getAllGuideTypes();

    /**
     * count users with giving conditions
     *
     * @param condition conditions
     * @return the count of users
     */
    @SelectProvider(type = SystemProvider.class, method = "countUsersWithCondition")
    Integer countUsersWithCondition(JSONObject condition);
}
