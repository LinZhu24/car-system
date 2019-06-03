package com.lxr.carsystem.repo;

import com.lxr.carsystem.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CarModelRepo extends JpaSpecificationExecutor<CarModel>,JpaRepository<CarModel,Integer>{
    CarModel findByModelid(Integer mid);
    CarModel findByModelname(String modelname);
    CarModel findByBrandid(Integer bid);
    CarModel findBySeriesid(Integer sid);
    List<CarModel> findAllBySeriesid(Integer seriesid);
    CarModel findByModelnameAndSeriesidAndBrandid(String mname,Integer sis,Integer bid);
}
