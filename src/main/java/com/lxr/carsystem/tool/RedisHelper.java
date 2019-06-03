package com.lxr.carsystem.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: LinXueRui
 * @Date: 2019/3/2 20:59
 * @Desc:
 */
@Service
public class RedisHelper<T>{
    private final RedisTemplate<String,Object> redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisHelper(RedisTemplate<String,Object> redisTemplate,StringRedisTemplate stringRedisTemplate){
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }


}
