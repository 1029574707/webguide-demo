package com.ceiec.webguide.formal.dao;

import com.ceiec.webguide.formal.cache.RedisCache;
import com.ceiec.webguide.formal.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * CreateDate: 2018/4/9 <br/>
 * Author: WangHao <br/>
 * Description:
 **/

@CacheNamespace(implementation=RedisCache.class)
public interface UserMapper {

    @Select("select * from users")
    List<UserEntity> fildAllUser();

    @Select("select * from users where id=#{id}")
    UserEntity findUserById(int id);

    @Insert("insert into users (id,username,password) value(#{id},#{username},#{password})")
    void insertUser(UserEntity user);

    @Update("update users set username=#{username},password=#{password} where id=#{id}")
    void updateUser(UserEntity user);

    @Delete("delete from users where id=#{id}")
    void deleteUser(int id);

}
