package com.lxr.carsystem.service.impl;

import com.lxr.carsystem.entity.CarPic;
import com.lxr.carsystem.entity.User;
import com.lxr.carsystem.repo.CarPicRepo;
import com.lxr.carsystem.repo.UserRepo;
import com.lxr.carsystem.service.CarPicService;
import com.lxr.carsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarPicServiceImpl implements CarPicService{
    @Autowired
    CarPicRepo carPicRepo;

    @Override
    public void saveAndFlush(CarPic carPic) {
        carPicRepo.saveAndFlush(carPic);
    }
}
