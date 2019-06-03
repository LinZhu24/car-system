package com.lxr.carsystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carpic")
public class CarPic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer picid;

    @Column(length = 255,nullable = true)
    private String path;//图片路径

    @Column(length = 255,nullable = true)
    private Integer headpic;//领头羊

    public Integer getHeadpic() {
        return headpic;
    }

    public void setHeadpic(Integer headpic) {
        this.headpic = headpic;
    }

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    private WaitSellCar wsc;
    public WaitSellCar getWsc() {
        return wsc;
    }
    public void setWsc(WaitSellCar wsc) {
        this.wsc = wsc;
    }

    public Integer getPicid() {
        return picid;
    }
    public void setPicid(Integer picid) {
        this.picid = picid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
