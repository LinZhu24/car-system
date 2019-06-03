package com.lxr.carsystem.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inputorder")//进货订单(入库订单)
public class InputOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inputorderid;//订单id(入库id)

    @Column(length = 255,nullable = false)
    private String inputordernumber;//订单号

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private AppointCheck appointCheck;//预约检车id(关联预约检车表)
    public AppointCheck getAppointCheck() {
        return appointCheck;
    }
    public void setAppointCheck(AppointCheck appointCheck) {
        this.appointCheck = appointCheck;
    }

    @Column(length = 255,nullable = false)
    private Double inputprice;//进货价（收车价）

    @Column(length = 255,nullable = false)
    private String dealdate;//交易时间

    @Column(length = 255,nullable = false)
    private Integer condiction;//该产品的状态

    public Integer getInputorderid() {
        return inputorderid;
    }

    public void setInputorderid(Integer inputorderid) {
        this.inputorderid = inputorderid;
    }

    public Double getInputprice() {
        return inputprice;
    }

    public void setInputprice(Double inputprice) {
        this.inputprice = inputprice;
    }

    public String getDealdate() {
        return dealdate;
    }

    public void setDealdate(String dealdate) {
        this.dealdate = dealdate;
    }

    public Integer getCondiction() {
        return condiction;
    }

    public void setCondiction(Integer condiction) {
        this.condiction = condiction;
    }

    public String getInputordernumber() {
        return inputordernumber;
    }

    public void setInputordernumber(String inputordernumber) {
        this.inputordernumber = inputordernumber;
    }
}
