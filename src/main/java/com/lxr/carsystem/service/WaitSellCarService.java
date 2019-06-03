package com.lxr.carsystem.service;

import com.lxr.carsystem.entity.CarPic;
import com.lxr.carsystem.entity.WaitSellCar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface WaitSellCarService {
    void saveAndFlush(WaitSellCar waitSellCar);
    WaitSellCar findByWaitsellcarid(Integer wscid);
    List<WaitSellCar> findAll();
    List<WaitSellCar> findAllBySellid(Integer sellid);

    @Transactional
    Page<WaitSellCar> findAllCar(Pageable pageable, String brandname);

    @Transactional
    Page<WaitSellCar> findUserBroughtPage(Pageable pageable,Integer userid);

    @Transactional
    Page<WaitSellCar> findAllCarByPrice(Pageable pageable, Double aa,Double bb);


    @Transactional
    Page<WaitSellCar> findAllCarByPriceMoreThan(Pageable pageable, Double aa);
}
