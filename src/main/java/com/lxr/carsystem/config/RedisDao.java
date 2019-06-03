package com.lxr.carsystem.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @Author: LinXueRui
 * @Date: 2019/2/27 19:14
 * @Desc:
 */
@Component
public class RedisDao<T> {
    @Resource
    RedisTemplate<String,T> redisTemplate;

    /**
     * 设置redis的值
     * @param scope
     * @param key
     * @param t
     */
    public void set(String scope,String key,T t){
        System.out.println("开始执行redis赋值操作...");
        redisTemplate.opsForValue().set(scope + ":" +key,t);
        System.out.println("成功执行redis赋值操作...");
    }

    /**
     * 获取redis的值
     * @param scope
     * @param key
     * @return
     */
    public T get(String scope,String key){
        System.out.println("从redis取值操作...");
        return redisTemplate.opsForValue().get(scope + ":" + key);

    }

    /**
     * 删除key
     * @param scope
     * @param key
     */
    public void del(String scope, String key) {
        if (key == null) {
            Set<String> keys = redisTemplate.keys(scope + ":" + "*");
            redisTemplate.delete(keys);
        } else {
            redisTemplate.delete(scope + ":" + key);
        }
    }

    public boolean isContains(String scope, String key, T t) {
        SetOperations<String, T> set = redisTemplate.opsForSet();
        return set.isMember(scope + ":" + key, t);
    }

    public void putSet(String scope, String key, T e) {
        SetOperations<String, T> set = redisTemplate.opsForSet();
        set.add(scope + ":" + key,e);
    }

    public Long increment(String scope, String key) {
        return redisTemplate.opsForValue().increment(scope + ":" + key, 1);
    }

    public Long increment(String scope, String key,long increment) {
        return redisTemplate.opsForValue().increment(scope + ":" + key, increment);
    }
    public long getIncrementValue(String scope, String key) {
        final String iKey = scope + ":" + key;
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer=redisTemplate.getStringSerializer();
                byte[] rowKey=serializer.serialize(iKey);
                byte[] rowVal=connection.get(rowKey);
                try {
                    String val=serializer.deserialize(rowVal);
                    return Long.parseLong(val);
                } catch (Exception e) {
                    return 0L;
                }
            }
        });
    }

    public void putHash(String scope, String key, String hashKey, String value) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(scope + ":" + key, hashKey, value);
    }

    public String getHash(String scope, String key, String hashKey) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(scope + ":" + key, hashKey);
    }

    public void deleteHash(String scope, String key, String hashKey, String value) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(scope + ":" + key, hashKey);
    }

    public String getHashAndDelete(String scope, String key, String hashKey) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        String s = hashOperations.get(scope + ":" + key, hashKey);
        if (StringUtils.isNotEmpty(s)) {
            hashOperations.delete(scope + ":" + key, hashKey);
        }
        return s;
    }

    public T rightPop(String key) {
        BoundListOperations<String, T> listOperations = redisTemplate.boundListOps(key);
        return listOperations.rightPop();
    }

    /**
     * 头部
     * @param key
     * @return
     */
    public T leftPop(String key) {
        BoundListOperations<String, T> listOperations = redisTemplate.boundListOps(key);
        return listOperations.leftPop();
    }

    /**
     * 尾部
     * @param key
     * @param t
     */
    public void rightPush(String key, T t) {
        BoundListOperations<String, T> listOperations = redisTemplate.boundListOps(key);
        listOperations.rightPush(t);
    }

}
