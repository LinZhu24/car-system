package com.lxr.carsystem.repo;

import com.lxr.carsystem.entity.RedisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: LinXueRui
 * @Date: 2019/2/28 16:08
 * @Desc:
 */
public interface RedisEntityRepo extends JpaSpecificationExecutor<RedisEntity>, JpaRepository<RedisEntity,Integer> {
    RedisEntity findById(Integer id);
}
