package com.lxr.carsystem.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointcheck")
public class AppointCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//执行save操作之后estimate就已经更新为已经保存的实体了
    private Integer appointid;

    @Column(length = 255,nullable = false)
    private String appointdate;

    @Column(length = 255,nullable = false)
    private String appointplace;

    @Column(length = 255,nullable = false)
    private Integer conditionflag;

    @Column(length = 255,nullable = false)
    private Integer workingflag;

    @Column(length = 255,nullable = false)
    private Integer doneflag;

    @Column(length = 255,nullable = false)
    private Integer orderflag;

    public Integer getOrderflag() {
        return orderflag;
    }

    public void setOrderflag(Integer orderflag) {
        this.orderflag = orderflag;
    }

    public Integer getWorkingflag() {
        return workingflag;
    }

    public void setWorkingflag(Integer workingflag) {
        this.workingflag = workingflag;
    }

    public Integer getDoneflag() {
        return doneflag;
    }

    public void setDoneflag(Integer doneflag) {
        this.doneflag = doneflag;
    }

    public Integer getConditionflag() {
        return conditionflag;
    }

    public void setConditionflag(Integer conditionflag) {
        this.conditionflag = conditionflag;
    }

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Estimate estimate;
    public Estimate getEstimate() {
        return estimate;
    }
    public void setEstimate(Estimate estimate) {
        this.estimate = estimate;
    }


    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "inputorderid")
    private InputOrder inputOrder;
    public InputOrder getInputOrder() {
        return inputOrder;
    }
    public void setInputOrder(InputOrder inputOrder) {
        this.inputOrder = inputOrder;
    }


    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    private Admin admin;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Integer getAppointid() {
        return appointid;
    }

    public void setAppointid(Integer appointid) {
        this.appointid = appointid;
    }

    public String getAppointdate() {
        return appointdate;
    }

    public void setAppointdate(String appointdate) {
        this.appointdate = appointdate;
    }

    public String getAppointplace() {
        return appointplace;
    }

    public void setAppointplace(String appointplace) {
        this.appointplace = appointplace;
    }

}
