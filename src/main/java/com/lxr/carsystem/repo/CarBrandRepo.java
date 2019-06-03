package com.lxr.carsystem.repo;

import com.lxr.carsystem.entity.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CarBrandRepo extends JpaSpecificationExecutor<CarBrand>,JpaRepository<CarBrand,Integer>{

    CarBrand findByBrandid(Integer brandid);

    CarBrand findByBrandname(String brandname);

}
