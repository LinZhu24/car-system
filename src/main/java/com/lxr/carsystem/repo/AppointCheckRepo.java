package com.lxr.carsystem.repo;

import com.lxr.carsystem.entity.AppointCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AppointCheckRepo extends JpaSpecificationExecutor<AppointCheck>,JpaRepository<AppointCheck,Integer> {
    List<AppointCheck> findAllByAdmin_adminid(Integer adminid);
    AppointCheck findByAppointid(Integer id);
}
