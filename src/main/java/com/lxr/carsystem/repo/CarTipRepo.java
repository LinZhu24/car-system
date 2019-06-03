package com.lxr.carsystem.repo;

import com.lxr.carsystem.entity.CarTip;
import com.lxr.carsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CarTipRepo extends JpaSpecificationExecutor<CarTip>,JpaRepository<CarTip,Integer>{

}