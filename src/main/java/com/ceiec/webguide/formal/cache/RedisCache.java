package com.ceiec.webguide.formal.cache;

import com.ceiec.webguide.formal.enums.ERedisDomain;
import com.ceiec.webguide.formal.utils.RedisUtil;
import com.ceiec.webguide.formal.utils.SerializableUtil;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * CreateDate: 2018/4/10 <br/>
 * Author: WangHao <br/>
 * Description: MyBatis二级缓存
 **/

public class RedisCache implements Cache {

    private final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private String id;

    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getSize() {
        // 获取 缓存key的大小
        return RedisUtil.getKeys(SerializableUtil.serialize(ERedisDomain.MYBATIS_SECOND_CACHE)).size();
    }

    @Override
    public void putObject(Object key, Object value) {
        String keyString = key.toString();
        byte[] keySerialize = SerializableUtil.serialize(keyString);
        RedisUtil.save(SerializableUtil.serialize(ERedisDomain.MYBATIS_SECOND_CACHE),
                keySerialize,
                SerializableUtil.serialize(value));
        logger.info("put key: {} to redis success, serialize key is: {}", keyString, keySerialize);
    }

    @Override
    public Object getObject(Object key) {
        Object value = SerializableUtil.unserialize(RedisUtil.get(
                SerializableUtil.serialize(ERedisDomain.MYBATIS_SECOND_CACHE),
                SerializableUtil.serialize(key.toString())));

        return value;
    }

    @Override
    public Object removeObject(Object key) {
        System.err.println("remove key" + key);
        return RedisUtil.delete(SerializableUtil.serialize(ERedisDomain.MYBATIS_SECOND_CACHE),
                SerializableUtil.serialize(key.toString()));
    }


    @Override
    public void clear() {
        System.err.println("clear all data");
        RedisUtil.flushSecondCache(SerializableUtil.serialize(ERedisDomain.MYBATIS_SECOND_CACHE));
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}