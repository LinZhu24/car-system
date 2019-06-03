package com.lxr.carsystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "waitsellcar")//待售汽车表
public class WaitSellCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wscid;//1.待售id

    @Column(length = 255,nullable = false)
    private String carbrand;//2.品牌名

    @Column(length = 255,nullable = false)
    private String carseries;//3.车系名

    @Column(length = 255,nullable = false)
    private String carmodel;//4.车型名

    @Column(length = 255,nullable = false)
    private Integer brandid;//5.品牌ID

    @Column(length = 255,nullable = false)
    private Integer seriesid;//6.车系ID

    @Column(length = 255,nullable = false)
    private Integer modelid;//7.车型ID

    @Column(length = 255,nullable = false)
    private Double sellprice;//8.售价

    @Column(length = 255,nullable = false)
    private String regdate;//9.上牌时间

    @Column(length = 255,nullable = false)
    private Double drivemiles;//10.行驶里程

    @Column(length = 255,nullable = false)
    private Integer inputorderid;//11.库存ID

    @Column(length = 255,nullable = false)
    private String regcity;//12.上牌城市

    @Column(length = 255,nullable = false)
    private String ownercity;//13.卖车城市

    @Column(length = 255,nullable = false)
    private String uploadtime;//14.发布时间

    @Column(length = 255,nullable = false)
    private Integer sellflag;//15.汽车销售状态，0代表售卖中，1代表已卖出

    @Column(length = 255,nullable = false)
    private Integer sellid;//16.卖家ID

    @Column(length = 255,nullable = true)
    private Integer buyerid;//17.买家ID

    @Column(length = 255,nullable = true)
    private String headpath;//17.买家ID

    @Column(length = 255,nullable = true)
    private String dealdate;//18.交易时间

    public String getDealdate() {
        return dealdate;
    }

    public void setDealdate(String dealdate) {
        this.dealdate = dealdate;
    }

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getRegcity() {
        return regcity;
    }

    public void setRegcity(String regcity) {
        this.regcity = regcity;
    }

    public String getOwnercity() {
        return ownercity;
    }

    public void setOwnercity(String ownercity) {
        this.ownercity = ownercity;
    }

    @OneToMany(mappedBy = "wsc",cascade = CascadeType.ALL)
    private List<CarPic> carPicList=new ArrayList<>();

    public Integer getInputorderid() {
        return inputorderid;
    }

    public void setInputorderid(Integer inputorderid) {
        this.inputorderid = inputorderid;
    }

    public Integer getWscid() {
        return wscid;
    }

    public void setWscid(Integer wscid) {
        this.wscid = wscid;
    }

    public String getCarbrand() {
        return carbrand;
    }

    public void setCarbrand(String carbrand) {
        this.carbrand = carbrand;
    }

    public String getCarseries() {
        return carseries;
    }

    public void setCarseries(String carseries) {
        this.carseries = carseries;
    }

    public String getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel;
    }

    public Integer getBrandid() {
        return brandid;
    }

    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    public Integer getSeriesid() {
        return seriesid;
    }

    public void setSeriesid(Integer seriesid) {
        this.seriesid = seriesid;
    }

    public Integer getModelid() {
        return modelid;
    }

    public void setModelid(Integer modelid) {
        this.modelid = modelid;
    }

    public Double getSellprice() {
        return sellprice;
    }

    public void setSellprice(Double sellprice) {
        this.sellprice = sellprice;
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

    public List<CarPic> getCarPicList() {
        return carPicList;
    }

    public void setCarPicList(List<CarPic> carPicList) {
        this.carPicList = carPicList;
    }

    public Integer getSellflag() {
        return sellflag;
    }

    public void setSellflag(Integer sellflag) {
        this.sellflag = sellflag;
    }

    public Integer getSellid() {
        return sellid;
    }

    public void setSellid(Integer sellid) {
        this.sellid = sellid;
    }

    public Integer getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(Integer buyerid) {
        this.buyerid = buyerid;
    }

    public String getHeadpath() {
        return headpath;
    }

    public void setHeadpath(String headpath) {
        this.headpath = headpath;
    }
}
