package com.lxr.carsystem.service.impl;

import com.lxr.carsystem.entity.CarTip;
import com.lxr.carsystem.repo.CarTipRepo;
import com.lxr.carsystem.service.CarTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarTipServiceImpl implements CarTipService{
    @Autowired
    CarTipRepo carTipRepo;

    @Override
    public void saveAndFlush(CarTip carTip) {
        carTipRepo.saveAndFlush(carTip);
    }

    @Override
    public List<CarTip> findAll() {
        return carTipRepo.findAll();
    }
}
