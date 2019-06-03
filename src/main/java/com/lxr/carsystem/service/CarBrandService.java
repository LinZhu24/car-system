package com.lxr.carsystem.service;

import com.lxr.carsystem.entity.CarBrand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface CarBrandService {

    CarBrand findByBrandid(Integer brandid);

    CarBrand findByBrandname(String brandname);

    List<CarBrand> findAll();

    void deleteByBrandid(Integer brandid);

    @Transactional
    Page<CarBrand> findCarBrandPage(Pageable pageable, String brandname,String brandinitial);
}
