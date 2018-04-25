package com.ceiec.webguide.formal.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * CreateDate: 2018/4/23 <br/>
 * Author: WangHao <br/>
 * Description:
 **/
public interface RoleDao {

    @Select("select system_mode from g_di_user_role where role_id = #{roleId}")
    int findSysModeById(@Param("roleId") int roleId);
}
