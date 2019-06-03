package com.lxr.carsystem.repo;

import com.lxr.carsystem.entity.CarDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CarDetailRepo extends JpaSpecificationExecutor<CarDetail>,JpaRepository<CarDetail,Integer>{
    CarDetail findByBrandid(Integer bid);
    CarDetail findBySeriesid(Integer sid);
    CarDetail findByModelid(Integer mid);
    CarDetail findByBrandidAndSeriesidAndModelid(Integer bid,Integer sid,Integer mid);
}
