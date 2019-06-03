package com.lxr.carsystem.repo;

import com.lxr.carsystem.entity.CarPic;
import com.lxr.carsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CarPicRepo extends JpaSpecificationExecutor<CarPic>,JpaRepository<CarPic,Integer>{

}