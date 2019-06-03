package com.lxr.carsystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "appointlook")
public class AppointLook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointlookid;

    @Column(length = 255,nullable = false)
    private Integer userid;

    @Column(length = 255,nullable = false)
    private Integer wscid;

    @Column(length = 255,nullable = false)
    private String apttime;

    @Column(length = 255,nullable = true)
    private Integer outputflag;

    public Integer getAppointlookid() {
        return appointlookid;
    }

    public void setAppointlookid(Integer appointlookid) {
        this.appointlookid = appointlookid;
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

    public String getApttime() {
        return apttime;
    }

    public void setApttime(String apttime) {
        this.apttime = apttime;
    }

    public Integer getOutputflag() {
        return outputflag;
    }

    public void setOutputflag(Integer outputflag) {
        this.outputflag = outputflag;
    }
}
