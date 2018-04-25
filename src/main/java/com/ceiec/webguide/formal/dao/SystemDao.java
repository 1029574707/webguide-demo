package com.ceiec.webguide.formal.dao;

import com.alibaba.fastjson.JSONObject;
import com.ceiec.webguide.formal.dao.provider.SystemProvider;
import com.ceiec.webguide.formal.entity.SysLogEntity;
import com.ceiec.webguide.formal.entity.SysParamEntity;
import com.ceiec.webguide.formal.entity.UserAccountEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

import static com.ceiec.webguide.formal.constant.MySqlConstant.SYSTEMLOGTABLE;
import static com.ceiec.webguide.formal.constant.MySqlConstant.DIWEBSITETABLE;
import static com.ceiec.webguide.formal.constant.MySqlConstant.DIGUIDETYPETABLE;
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
    @SelectProvider(type = SystemProvider.class, method = "getUsersWithCondition")
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
     * modify someone system parameter
     *
     * @param paramInfoJson the parameter need to modify
     */
    @UpdateProvider(type = SystemProvider.class, method = "updateSysParam")
    void updateSysParam(JSONObject paramInfoJson);

    /**
     * find the system logs with giving conditions
     *
     * @param condition conditions
     * @return list- system logs
     */
    @SelectProvider(type = SystemProvider.class, method = "getSysLogsWithCondition")
    List<SysLogEntity> getSysLogsWithCondition(JSONObject condition);

    /**
     * find the system logs with giving condition in today
     *
     * @param condition conditions
     * @return list- system logs
     */
    @SelectProvider(type = SystemProvider.class, method = "getTodaySysLogsWithCondition")
    List<SysLogEntity> getTodaySysLogsWithCondition(JSONObject condition);

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
