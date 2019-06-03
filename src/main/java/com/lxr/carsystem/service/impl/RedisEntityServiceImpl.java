package com.lxr.carsystem.service.impl;

import com.lxr.carsystem.entity.RedisEntity;
import com.lxr.carsystem.repo.RedisEntityRepo;
import com.lxr.carsystem.service.RedisEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: LinXueRui
 * @Date: 2019/2/28 16:11
 * @Desc:
 */
@Service
public class RedisEntityServiceImpl implements RedisEntityService {
    @Autowired
    RedisEntityRepo redisEntityRepo;

    @Override
    public RedisEntity findById(Integer id) {
        return redisEntityRepo.findById(id);
    }

    @Override
    public void deletetById(Integer id) {
        redisEntityRepo.delete(id);
    }

    @Override
    public void save(RedisEntity redisEntity) {
        redisEntityRepo.saveAndFlush(redisEntity);
    }
}
