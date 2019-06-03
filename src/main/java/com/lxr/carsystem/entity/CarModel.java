package com.lxr.carsystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carmodel")
public class CarModel {
    @Id
    private Integer modelid;

    @Column(length = 255,nullable = false)
    private String modelname;

    @Column(length = 255,nullable = false)
    private Integer brandid;

    @Column(length = 255,nullable = false)
    private Integer seriesid;

    @Column(length = 255,nullable = false)
    private Integer gradeid;

/*    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    private CarSeries carseries;

    @OneToMany(mappedBy = "carmodel",cascade = CascadeType.ALL)
    private List<CarInfo> carInfoList=new ArrayList<>();

    public List<CarInfo> getCarInfoList() {
        return carInfoList;
    }

    public void setCarInfoList(List<CarInfo> carInfoList) {
        this.carInfoList = carInfoList;
    }*/


    /*    public CarSeries getCarseries() {
        return carseries;
    }

    public void setCarseries(CarSeries carseries) {
        this.carseries = carseries;
    }*/

    public Integer getModelid() {
        return modelid;
    }

    public void setModelid(Integer modelid) {
        this.modelid = modelid;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
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

    public Integer getGradeid() {
        return gradeid;
    }

    public void setGradeid(Integer gradeid) {
        this.gradeid = gradeid;
    }
}
