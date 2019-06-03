package com.lxr.carsystem.service.impl;

import com.lxr.carsystem.entity.CarDetail;
import com.lxr.carsystem.repo.CarDetailRepo;
import com.lxr.carsystem.service.CarDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarDetailServiceImpl implements CarDetailService{
    @Autowired
    CarDetailRepo carDetailRepo;

    @Override
    public CarDetail findByModelid(Integer modelid) {
        return carDetailRepo.findByModelid(modelid);
    }

    @Override
    public List<CarDetail> findAll() {
        return carDetailRepo.findAll();
    }
}
