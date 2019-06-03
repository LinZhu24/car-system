package com.lxr.carsystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminid;

    @Column(length = 255,nullable = false)
    private String adminname;

    @Column(length = 255,nullable = false)
    private String passwd;

    @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
    private List<AppointCheck> appointCheckList=new ArrayList<>();

    public List<AppointCheck> getAppointCheckList() {
        return appointCheckList;
    }

    public void setAppointCheckList(List<AppointCheck> appointCheckList) {
        this.appointCheckList = appointCheckList;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminid=" + adminid +
                ", adminname='" + adminname + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
