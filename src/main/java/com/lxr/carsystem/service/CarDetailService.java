package com.lxr.carsystem.service;

import com.lxr.carsystem.entity.CarBrand;
import com.lxr.carsystem.entity.CarDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;

public interface CarDetailService {
    CarDetail findByModelid(Integer modelid);
    List<CarDetail> findAll();
}
