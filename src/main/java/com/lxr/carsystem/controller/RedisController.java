package com.lxr.carsystem.controller;

import com.lxr.carsystem.config.RedisDao;
import com.lxr.carsystem.entity.RedisEntity;
import com.lxr.carsystem.service.RedisEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: LinXueRui
 * @Date: 2019/2/27 20:26
 * @Desc:
 */
@Controller
@RequestMapping("ttt")
public class RedisController {

    @Autowired
    RedisEntityService redisEntityService;

    @Autowired
    RedisDao<RedisEntity> redisEntityRedisDao;

    @GetMapping("/add")
    @ResponseBody
    public String add(){
        RedisEntity redisEntity = new RedisEntity();
        redisEntity.setId(1);
        redisEntity.setName("kobe");
        redisEntityService.save(redisEntity);

        redisEntityRedisDao.set("redis","kobe",redisEntity);
        return "success";
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(){
//        RedisEntity redisEntity = redisEntityRedisDao.get("redis", "kobe");
//        if(redisEntity == null){
//            System.out.println("redis中没有值");
//        }
//        redisEntityService.deletetById(1);
        redisEntityRedisDao.del("redis","kobe");
        return "success";
    }

    @GetMapping("get")
    @ResponseBody
    public String get(){
        RedisEntity redisEntity = redisEntityRedisDao.get("redis","kobe");
        if(redisEntity == null){
            redisEntity = redisEntityService.findById(2);
            redisEntityRedisDao.set("redis","kobe",redisEntity);
        }
        return redisEntity.toString();

    }

}
