package com.ceiec.webguide.formal.utils;

import com.alibaba.fastjson.JSONObject;
import com.ceiec.webguide.formal.enums.ERedisDomain;
import io.lettuce.core.RedisException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

/**
 * CreateDate: 2018/4/10 <br/>
 * Author: WangHao <br/>
 * Description:
 **/
public class RedisUtil {

    /**
     * jedis连接池
     */
    private static JedisPool pool;

    /**
     * 初始化redis连接
     */
    public static void init() {
        if (pool == null) {
            // 创建jedis池配置实例
            JedisPoolConfig config = new JedisPoolConfig();
            //Redis主机IP
            String hostIP = PropertyReaderUtils.getProValue("spring.redis.host");
            //Redis主机端口
            Integer hostPort = Integer.valueOf(PropertyReaderUtils.getProValue("spring.redis.port"));
            Integer maxActive = Integer.valueOf(PropertyReaderUtils.getProValue("spring.redis.pool.max-active"));
            Integer maxIdle = Integer.valueOf(PropertyReaderUtils.getProValue("spring.redis.pool.max-idle"));
            Long maxWait = Long.valueOf(PropertyReaderUtils.getProValue("spring.redis.pool.max-wait"));
            Boolean testOnBorrow = Boolean.valueOf(PropertyReaderUtils.getProValue("spring.redis.pool.testOnBorrow"));
            Boolean testOnReturn = Boolean.valueOf(PropertyReaderUtils.getProValue("spring.redis.pool.testOnReturn"));
            int timeout = Integer.valueOf(PropertyReaderUtils.getProValue("spring.redis.timeout"));
            //设置池配置项值
            config.setMaxTotal(maxActive);
            config.setMaxIdle(maxIdle);
            config.setMaxWaitMillis(maxWait);
            config.setTestOnBorrow(testOnBorrow);
            config.setTestOnReturn(testOnReturn);
            //根据配置实例化jedis池
            pool = new JedisPool(config, hostIP, hostPort, timeout);
        }
    }

    /**
     * 添加单个Redis缓存
     *
     * @param domain Redis分组
     * @param key    Redis的组内key
     * @param value  Redis的组内value
     * @return boolean 是否添加成功
     */
    public static boolean save(ERedisDomain domain, String key, Object value) {
        Jedis jedis = null;
        long result;
        try {
            jedis = getJedis();
            result = jedis.hset(domain.getKey(), key, JSONObject.toJSONString(value));
        } catch (Exception e) {
            throw new RedisException("unable to save key " + key + " in domain " + domain.getKey(), e);
        } finally {
            if (null != jedis) {
                jedis.close(); // 释放资源还给连接池
            }
        }
        return result == 1;
    }

    /**
     * 添加单个Redis缓存
     *
     * @param domain    Redis分组
     * @param key       Redis的组内key
     * @param valueJson Redis的组内value(JSON格式)
     * @return boolean 是否添加成功
     */
    public static boolean save(ERedisDomain domain, String key, String valueJson) {
        Jedis jedis = null;
        long result;
        try {
            jedis = getJedis();
            result = jedis.hset(domain.getKey(), key, valueJson);
        } catch (Exception e) {
            throw new RedisException("unable to save key " + key + " in domain " + domain.getKey(), e);
        } finally {
            // 释放资源还给连接池
            close(jedis);
        }
        return result == 1;
    }

    /**
     * 批量添加Redis缓存
     *
     * @param domain Redis分组
     * @param data   Redis缓存数据
     * @return boolean 是否添加成功
     */
    public static boolean batchSave(ERedisDomain domain, Map<String, Object> data) {
        Jedis jedis = null;
        String result;
        try {
            jedis = getJedis();
            //json格式的数据
            Map<String, String> jsonData = new HashMap<>(data.size());
            for (String key : data.keySet()) {
                jsonData.put(key, JSONObject.toJSONString(data.get(key)));
            }
            result = jedis.hmset(domain.getKey(), jsonData);
        } catch (Exception e) {
            throw new RedisException("unable to batch save keys " + data.keySet() + " in domain " + domain.getKey(), e);
        } finally {
            // 释放资源还给连接池
            close(jedis);
        }
        return "OK".equalsIgnoreCase(result);
    }

    /**
     * 批量添加Redis缓存
     *
     * @param domain Redis分组
     * @param data   Redis缓存数据（值为json格式）
     * @return boolean 是否添加成功
     */
    public static boolean batchSaveJson(ERedisDomain domain, Map<String, String> data) {
        Jedis jedis = null;
        String result;
        try {
            jedis = getJedis();
            result = jedis.hmset(domain.getKey(), data);
        } catch (Exception e) {
            throw new RedisException("unable to batch save keys " + data.keySet() + " in domain " + domain.getKey(), e);
        } finally {
            // 释放资源还给连接池
            close(jedis);
        }
        return "OK".equalsIgnoreCase(result);
    }

    /**
     * 获取指定Redis组内的所有缓存数据
     *
     * @param domain Redis分组
     * @return Map 缓存数据，值为json格式
     */
    public static Map<String, String> get(ERedisDomain domain) {
        Jedis jedis = null;
        Map<String, String> maps;
        try {
            jedis = getJedis();
            maps = jedis.hgetAll(domain.getKey());
        } catch (Exception e) {
            throw new RedisException("unable to get data from domain " + domain.getKey(), e);
        } finally {
            // 释放资源还给连接池
            close(jedis);
        }
        return maps;
    }

    /**
     * 获取指定Redis组内的所有缓存数据
     *
     * @param domain Redis分组
     * @param clazz  缓存对象的类型
     * @return Map 缓存数据
     */
    public static <T> Map<String, T> get(ERedisDomain domain, Class<T> clazz) {
        Jedis jedis = null;
        //初始化返回结果
        Map<String, T> resultMap = new HashMap<>();
        try {
            jedis = getJedis();
            //json格式的缓存结果
            Map<String, String> data = jedis.hgetAll(domain.getKey());
            for (String key : data.keySet()) {
                JSONObject jsonCacheObj = JSONObject.parseObject(data.get(key));
                T cacheData = JSONObject.toJavaObject(jsonCacheObj, clazz);
                resultMap.put(key, cacheData);
            }
        } catch (Exception e) {
            throw new RedisException("unable to get data from domain " + domain.getKey(), e);
        } finally {
            // 释放资源还给连接池
            close(jedis);
        }
        return resultMap;
    }

    /**
     * 获取单个缓存对象
     *
     * @param domain Redis分组
     * @param key    缓存对象的key
     * @return String json格式的缓存对象
     */
    public static String get(ERedisDomain domain, String key) {
        Jedis jedis = null;
        String returnValue;
        try {
            jedis = getJedis();
            returnValue = jedis.hget(domain.getKey(), key);
        } catch (Exception e) {
            throw new RedisException("unable to get key " + key + " in domain " + domain.getKey(), e);
        } finally {
            // 释放资源还给连接池
            close(jedis);
        }
        return returnValue;
    }

    /**
     * 获取单个缓存对象
     *
     * @param domain Redis分组
     * @param key    缓存对象的key
     * @param clazz  缓存对象的类型
     * @return T 缓存对象
     */
    public static <T> T get(ERedisDomain domain, String key, Class<T> clazz) {
        Jedis jedis = null;
        T returnObj;
        try {
            jedis = getJedis();
            String jsonData = jedis.hget(domain.getKey(), key);
            JSONObject jsonCacheObj = JSONObject.parseObject(jsonData);
            returnObj = JSONObject.toJavaObject(jsonCacheObj, clazz);
        } catch (Exception e) {
            throw new RedisException("unable to get key " + key + " in domain " + domain.getKey(), e);
        } finally {
            // 释放资源还给连接池
            close(jedis);
        }
        return returnObj;
    }

    /**
     * 删除给定Redis分组的所有缓存
     *
     * @param domain Redis分组
     * @return boolean 是否删除成功
     */
    public static boolean delete(ERedisDomain domain) {
        Jedis jedis = null;
        long result;
        try {
            jedis = getJedis();
            result = jedis.del(domain.getKey());
        } catch (Exception e) {
            throw new RedisException("unable to delete data in domain " + domain.getKey(), e);
        } finally {
            // 释放资源还给连接池
            close(jedis);
        }
        return result == 1;
    }

    /**
     * 删除缓存
     *
     * @param domain 缓存所属分组
     * @param keys   缓存对象的key
     * @return boolean 是否删除成功
     */
    public static boolean delete(ERedisDomain domain, String... keys) {
        Jedis jedis = null;
        long result;
        try {
            jedis = getJedis();
            result = jedis.hdel(domain.getKey(), keys);
        } catch (Exception e) {
            throw new RedisException("unable to delete keys " + Arrays.toString(keys) + " in domain " + domain.getKey(), e);
        } finally {
            // 释放资源还给连接池
            close(jedis);
        }
        return result == 1;
    }

    /**
     * 获取给定分组的所有Redis缓存对象
     *
     * @param domain Redis分组
     * @return List json格式的缓存对象列表
     */
    public static List<String> getValues(ERedisDomain domain) {
        Jedis jedis = null;
        List<String> returnList;
        try {
            jedis = getJedis();
            returnList = jedis.hvals(domain.getKey());
        } catch (Exception e) {
            throw new RedisException("unable to delete data in domain " + domain.getKey(), e);
        } finally {
            // 释放资源还给连接池
            close(jedis);
        }
        return returnList;
    }

    /**
     * 获取给定分组的所有Redis缓存对象
     *
     * @param domain Redis分组
     * @param clazz  缓存对象类型
     * @return List 缓存对象列表
     */
    public static <T> List<T> getValues(ERedisDomain domain, Class<T> clazz) {
        Jedis jedis = null;
        //初始化返回结果
        List<T> result = new ArrayList<>();
        try {
            jedis = getJedis();
            List<String> cacheObs = jedis.hvals(domain.getKey());
            for (String cache : cacheObs) {
                JSONObject jsonCacheObj = JSONObject.parseObject(cache);
                result.add(JSONObject.toJavaObject(jsonCacheObj, clazz));
            }
        } catch (Exception e) {
            throw new RedisException("unable to get data in domain " + domain.getKey(), e);
        } finally {
            // 释放资源还给连接池
            close(jedis);
        }
        return result;
    }

    /**
     * 获取给定分组的所有Redis缓存key
     *
     * @param domain Redis分组
     * @return Set 缓存key集
     */
    public static Set<String> getKeys(ERedisDomain domain) {
        Jedis jedis = null;
        Set<String> returnSet;
        try {
            jedis = getJedis();
            returnSet = jedis.hkeys(domain.getKey());
        } catch (Exception e) {
            throw new RedisException("unable to get keys in domain " + domain.getKey(), e);
        } finally {
            // 释放资源还给连接池
            close(jedis);
        }
        return returnSet;
    }

    /**
     * 获取Jedis实例
     *
     * @return 返回redis客户端实例
     */
    private synchronized static Jedis getJedis() {
        if (pool != null) {
            return pool.getResource();
        } else {
            return null;
        }
    }

    /**
     * 释放jedis资源
     *
     * @param jedis redis客户端
     */
    private static void close(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


    /**
     * 添加单个Redis缓存
     *
     * @param key       Redis的组内key
     * @param valueJson Redis的组内value(JSON格式)
     * @return boolean 是否添加成功
     */
    public static boolean save(byte[] domain, byte[] key, byte[] valueJson) {
        Jedis jedis = null;
        Long result;
        try {
            jedis = getJedis();
            result = jedis.hset(domain, key, valueJson);
        } catch (Exception e) {
            throw new RedisException("unable to save key " + key, e);
        } finally {
            // 释放资源还给连接池
            close(jedis);
        }
        return result == 1;
    }

    /**
     * 获取单个缓存对象
     *
     * @param key 缓存对象的key
     * @return String json格式的缓存对象
     */
    public static byte[] get(byte[] domain, byte[] key) {
        Jedis jedis = null;
        byte[] returnValue;
        try {
            jedis = getJedis();
            returnValue = jedis.hget(domain, key);
        } catch (Exception e) {
            throw new RedisException("unable to get key " + key, e);
        } finally {
            // 释放资源还给连接池
            close(jedis);
        }
        return returnValue;
    }

    /**
     * 删除缓存
     *
     * @param domain 缓存所属分组
     * @param keys   缓存对象的key
     * @return boolean 是否删除成功
     */
    public static boolean delete(byte[] domain, byte[]... keys) {
        Jedis jedis = null;
        long result;
        try {
            jedis = getJedis();
            result = jedis.hdel(domain, keys);
        } catch (Exception e) {
            throw new RedisException("unable to delete keys " + Arrays.toString(keys), e);
        } finally {
            // 释放资源还给连接池
            close(jedis);
        }
        return result == 1;
    }

    /**
     * 清空mybatis二级缓存
     *
     * @param domain 需要清空的mybatis的key
     * @return 是否清空完成
     */
    public static boolean flushSecondCache(byte[] domain) {
        Jedis jedis = null;
        Long result;
        try {
            jedis = getJedis();
            result = jedis.del(domain);
        } catch (Exception e) {
            throw new RedisException("flush database fail!", e);
        } finally {
            //释放资源还给连接池
            close(jedis);
        }
        return result == 1;
    }

    /**
     * 获取给定分组的所有Redis缓存key
     *
     * @param domain Redis分组
     * @return Set 缓存key集
     */
    public static Set<byte[]> getKeys(byte[] domain) {
        Jedis jedis = null;
        Set<byte[]> returnSet;
        try {
            jedis = getJedis();
            returnSet = jedis.hkeys(domain);
        } catch (Exception e) {
            throw new RedisException("unable to get keys", e);
        } finally {
            // 释放资源还给连接池
            close(jedis);
        }
        return returnSet;
    }
}
