package com.lxr.carsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "cardetail")
public class CarDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detailid;//详情id

    @Column(length = 255,nullable = false)
    private Integer brandid;//品牌id

    @Column(length = 255,nullable = false)
    private Integer seriesid;//车系id

    @Column(length = 255,nullable = false)
    private Integer modelid;//车型id

    @Column(length = 255)
    private String condition;//状态

    @Column(length = 255)
    private String gearboxtype;//变速箱类型

    @Column(length = 255)
    private String gearbox;//变速箱

    @Column(length = 255)
    private String length;//长度(mm)

    @Column(length = 255)
    private String width;//宽度(mm)

    @Column(length = 255)
    private String height;//高度(mm)

    @Column(length = 255)
    private String wheelbase;//轴距(mm)

    @Column(length = 255)
    private String carstructure;//车身结构

    @Column(length = 255)
    private String weight;//整备质量(kg)

    @Column(length = 255)
    private String enginetype;//发动机型号

    @Column(length = 255)
    private String outputml;//排量(mL)

    @Column(length = 255)
    private String outputl;//排量(L)

    @Column(length = 255)
    private String intaketype;//进气形式

    @Column(length = 255)
    private String maxhorsepower;//最大马力(Ps)

    @Column(length = 255)
    private String maxpowerspeed;//最大功率转速(rpm)

    @Column(length = 255)
    private String fueltype;//燃料形式

    @Column(length = 255)
    private String fuelmarking;//燃油标号

    @Column(length = 255)
    private String drivingmode;//驱动方式

    @Column(length = 255)
    private String frontsuspensiontype;//前悬架类型

    @Column(length = 255)
    private String rearsuspensiontype;//后悬架类型

    @Column(length = 255)
    private String assistancetype;//助力类型

    @Column(length = 255)
    private String carbodystructure;//车体结构

    @Column(length = 255)
    private String environmentprotectstandard;//环保标准

    @Column(length = 255)
    private String firmguideprice;//厂商指导价(元)

    @Column(length = 255)
    private String carqualityassurance;//整车质保

    public Integer getDetailid() {
        return detailid;
    }

    public void setDetailid(Integer detailid) {
        this.detailid = detailid;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getGearboxtype() {
        return gearboxtype;
    }

    public void setGearboxtype(String gearboxtype) {
        this.gearboxtype = gearboxtype;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWheelbase() {
        return wheelbase;
    }

    public void setWheelbase(String wheelbase) {
        this.wheelbase = wheelbase;
    }

    public String getCarstructure() {
        return carstructure;
    }

    public void setCarstructure(String carstructure) {
        this.carstructure = carstructure;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getEnginetype() {
        return enginetype;
    }

    public void setEnginetype(String enginetype) {
        this.enginetype = enginetype;
    }

    public String getOutputml() {
        return outputml;
    }

    public void setOutputml(String outputml) {
        this.outputml = outputml;
    }

    public String getOutputl() {
        return outputl;
    }

    public void setOutputl(String outputl) {
        this.outputl = outputl;
    }

    public String getIntaketype() {
        return intaketype;
    }

    public void setIntaketype(String intaketype) {
        this.intaketype = intaketype;
    }

    public String getMaxhorsepower() {
        return maxhorsepower;
    }

    public void setMaxhorsepower(String maxhorsepower) {
        this.maxhorsepower = maxhorsepower;
    }

    public String getMaxpowerspeed() {
        return maxpowerspeed;
    }

    public void setMaxpowerspeed(String maxpowerspeed) {
        this.maxpowerspeed = maxpowerspeed;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

    public String getFuelmarking() {
        return fuelmarking;
    }

    public void setFuelmarking(String fuelmarking) {
        this.fuelmarking = fuelmarking;
    }

    public String getDrivingmode() {
        return drivingmode;
    }

    public void setDrivingmode(String drivingmode) {
        this.drivingmode = drivingmode;
    }

    public String getFrontsuspensiontype() {
        return frontsuspensiontype;
    }

    public void setFrontsuspensiontype(String frontsuspensiontype) {
        this.frontsuspensiontype = frontsuspensiontype;
    }

    public String getRearsuspensiontype() {
        return rearsuspensiontype;
    }

    public void setRearsuspensiontype(String rearsuspensiontype) {
        this.rearsuspensiontype = rearsuspensiontype;
    }

    public String getAssistancetype() {
        return assistancetype;
    }

    public void setAssistancetype(String assistancetype) {
        this.assistancetype = assistancetype;
    }

    public String getCarbodystructure() {
        return carbodystructure;
    }

    public void setCarbodystructure(String carbodystructure) {
        this.carbodystructure = carbodystructure;
    }

    public String getEnvironmentprotectstandard() {
        return environmentprotectstandard;
    }

    public void setEnvironmentprotectstandard(String environmentprotectstandard) {
        this.environmentprotectstandard = environmentprotectstandard;
    }

    public String getFirmguideprice() {
        return firmguideprice;
    }

    public void setFirmguideprice(String firmguideprice) {
        this.firmguideprice = firmguideprice;
    }

    public String getCarqualityassurance() {
        return carqualityassurance;
    }

    public void setCarqualityassurance(String carqualityassurance) {
        this.carqualityassurance = carqualityassurance;
    }
}
