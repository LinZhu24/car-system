package com.lxr.carsystem.repo;

import com.lxr.carsystem.entity.CarPic;
import com.lxr.carsystem.entity.WaitSellCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface WaitSellCarRepo extends JpaSpecificationExecutor<WaitSellCar>,JpaRepository<WaitSellCar,Integer>{
    WaitSellCar findByWscid(Integer wscid);
    List<WaitSellCar> findAllBySellid(Integer sellid);
}