package com.lxr.carsystem.service;

import com.lxr.carsystem.entity.RedisEntity;

/**
 * @Author: LinXueRui
 * @Date: 2019/2/28 16:10
 * @Desc:
 */
public interface RedisEntityService {
    RedisEntity findById(Integer id);
    void save(RedisEntity redisEntity);
    void deletetById(Integer id);
}
