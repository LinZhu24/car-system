package com.lxr.carsystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carbrand")
public class CarBrand {
    @Id
    private Integer brandid;

    @Column(length = 255,nullable = false)
    private String brandinitial;

    @Column(length = 255,nullable = false)
    private String brandname;

    public Integer getBrandid() {
        return brandid;
    }

    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    public String getBrandinitial() {
        return brandinitial;
    }

    public void setBrandinitial(String brandinitial) {
        this.brandinitial = brandinitial;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

/*    @OneToMany(mappedBy = "carbrand",cascade = CascadeType.ALL)
    private List<CarSeries> carSeriesList=new ArrayList<>();

    public List<CarSeries> getCarSeriesList() {
        return carSeriesList;
    }

    public void setCarSeriesList(List<CarSeries> carSeriesList) {
        this.carSeriesList = carSeriesList;
    }*/

}
