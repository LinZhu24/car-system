package com.lxr.carsystem.service;

import com.lxr.carsystem.entity.CarSeries;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface CarSeriesService {
    CarSeries findBySeriesid(Integer seriesid);
    CarSeries findBySeriesname(String seriesname);
    CarSeries findBySeriesnameAndBrandid(String sname,Integer bid);
    List<CarSeries> findAllByBrandid(Integer brandid);
    CarSeries findByBrandid(Integer brandid);
    @Transactional
    Page<CarSeries> findCarSeriesPageByBrandid(Pageable pageable, String seriesname,Integer brandid);
}
