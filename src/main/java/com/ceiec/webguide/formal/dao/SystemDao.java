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
 * Description: 系统Dao
 **/
public interface SystemDao {

    /**
     * 根据查询条件，获取用户列表
     *
     * @param condition 查询条件
     * @return 用户列表集合
     */
    @SelectProvider(type = SystemProvider.class, method = "getUsersWithCondition")
    List<UserAccountEntity> getUsersWithCondition(JSONObject condition);

    @Select("select * from " + SYSTEMLOGTABLE)
    @Results({@Result(column = "parameter_id", property = "parameterId"),
    @Result(column = "parameter_name", property = "parameterName"),
    @Result(column = "operate_role", property = "operateRoles"),
    @Result(column = "system_mode", property = "systemMode")})
    List<SysParamEntity> getAllSysParam();

    @UpdateProvider(type = SystemProvider.class, method = "updateSysParam")
    void updateSysParam(JSONObject paramInfoJson);

    @SelectProvider(type = SystemProvider.class, method = "getSysLogsWithCondition")
    List<SysLogEntity> getSysLogsWithCondition(JSONObject condition);

    @SelectProvider(type = SystemProvider.class, method = "getTodaySysLogsWithCondition")
    List<SysLogEntity> getTodaySysLogsWithCondition(JSONObject condition);

    @Select("select website_name from " + DIWEBSITETABLE)
    List<String> getAllWebsites();

    @Select("select guide_type_name from " + DIGUIDETYPETABLE)
    List<String> getAllGuideTypes();

    @SelectProvider(type = SystemProvider.class, method = "countUsersWithCondition")
    Integer countUsersWithCondition(JSONObject condition);
}
