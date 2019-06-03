package com.lxr.carsystem.service;

import com.lxr.carsystem.entity.CarModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface CarModelService {
    List<CarModel> findAll();
    List<CarModel> findAllBySeriesid(Integer seriesid);
    CarModel findByModelid(Integer modelid);
    CarModel findBySeriesid(Integer seriesid);
    CarModel findByModelname(String modelname);

    CarModel findByModelnameAndSeriesidAndBrandid(String mname,Integer sid,Integer bid);

    @Transactional
    Page<CarModel> findCarModelPageBySeriesid(Pageable pageable,Integer seriesid);
}
