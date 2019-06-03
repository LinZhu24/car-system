package com.lxr.carsystem.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "collect")
public class Collect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;//收藏ID

    @Column(length = 255,nullable = true)
    private Integer userid;//用户ID

    @Column(length = 255,nullable = true)
    private Integer wscid;//售卖汽车ID

    @Column(updatable = false)
    private LocalDateTime localDateTime=LocalDateTime.now();//收藏时间

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getWscid() {
        return wscid;
    }

    public void setWscid(Integer wscid) {
        this.wscid = wscid;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
