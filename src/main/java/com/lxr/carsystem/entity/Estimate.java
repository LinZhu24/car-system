package com.lxr.carsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "estimate")
public class Estimate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//执行save操作之后estimate就已经更新为已经保存的实体了
    private Integer estid;

    @Column(length = 255,nullable = false)
    private String carbrand;

    @Column(length = 255,nullable = false)
    private String carmodel;

    @Column(length = 255,nullable = false)
    private Integer modelid;//车型主键，借助该主键，可以查出该车型的详情（取出其中的市场价格）

    @Column(length = 255,nullable = false)
    private String carseries;

    @Column(length = 255,nullable = false)
    private String ownercity;

    @Column(length = 255,nullable = false)
    private String regcity;

    @Column(length = 255,nullable = false)
    private String regdate;

    @Column(length = 255,nullable = false)
    private Double drivemiles;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "appointid")
    private AppointCheck appointCheck;
    public AppointCheck getAppointCheck() {
        return appointCheck;
    }
    public void setAppointCheck(AppointCheck appointCheck) {
        this.appointCheck = appointCheck;
    }

    @Column(length = 255,nullable = false)
    private String contactway;

    public Integer getModelid() {
        return modelid;
    }

    public void setModelid(Integer modelid) {
        this.modelid = modelid;
    }

    public Integer getEstid() {
        return estid;
    }

    public void setEstid(Integer estid) {
        this.estid = estid;
    }

    public String getCarbrand() {
        return carbrand;
    }

    public void setCarbrand(String carbrand) {
        this.carbrand = carbrand;
    }

    public String getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel;
    }

    public String getCarseries() {
        return carseries;
    }

    public void setCarseries(String carseries) {
        this.carseries = carseries;
    }

    public String getOwnercity() {
        return ownercity;
    }

    public void setOwnercity(String ownercity) {
        this.ownercity = ownercity;
    }

    public String getRegcity() {
        return regcity;
    }

    public void setRegcity(String regcity) {
        this.regcity = regcity;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public Double getDrivemiles() {
        return drivemiles;
    }

    public void setDrivemiles(Double drivemiles) {
        this.drivemiles = drivemiles;
    }

    public String getContactway() {
        return contactway;
    }

    public void setContactway(String contactway) {
        this.contactway = contactway;
    }

    @Override
    public String toString() {
        return "Estimate{" +
                "estid=" + estid +
                ", carbrand='" + carbrand + '\'' +
                ", carmodel='" + carmodel + '\'' +
                ", carseries='" + carseries + '\'' +
                ", ownercity='" + ownercity + '\'' +
                ", regcity='" + regcity + '\'' +
                ", regdate='" + regdate + '\'' +
                ", drivemiles=" + drivemiles +
                ", contactway='" + contactway + '\'' +
                '}';
    }
}
