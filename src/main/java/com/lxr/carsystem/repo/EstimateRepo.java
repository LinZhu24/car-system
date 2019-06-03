package com.lxr.carsystem.repo;

import com.lxr.carsystem.entity.Estimate;
import com.lxr.carsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EstimateRepo extends JpaSpecificationExecutor<Estimate>,JpaRepository<Estimate,Integer> {
    Estimate findByEstid(Integer estid);
    List<Estimate> findByContactway(String contactway);
    List<Estimate> findAllByContactway(String contactway);

}