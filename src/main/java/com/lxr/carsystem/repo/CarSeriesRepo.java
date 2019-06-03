package com.lxr.carsystem.repo;

import com.lxr.carsystem.entity.CarBrand;
import com.lxr.carsystem.entity.CarSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CarSeriesRepo extends JpaSpecificationExecutor<CarSeries>,JpaRepository<CarSeries,Integer>{
    CarSeries findBySeriesid(Integer seriesid);
    CarSeries findBySeriesname(String seriesname);
    List<CarSeries> findAllByBrandid(Integer brandid);
    CarSeries findByBrandid(Integer brandid);
    CarSeries findBySeriesnameAndBrandid(String sname,Integer bid);
}
