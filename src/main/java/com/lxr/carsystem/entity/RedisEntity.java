package com.lxr.carsystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: LinXueRui
 * @Date: 2019/2/28 15:53
 * @Desc:
 */
@Data
@Entity
@Table(name = "redis_entity")
public class RedisEntity{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20,nullable = true)//姓名可为空
    private String name;
}
