package com.lxr.carsystem.repo;

import com.lxr.carsystem.entity.Collect;
import com.lxr.carsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CollectRepo extends JpaSpecificationExecutor<Collect>,JpaRepository<Collect,Integer>{
    Collect findByUserid(Integer userid);
    List<Collect> findAllByUserid(Integer userid);
    Collect findByWscid(Integer wscid);
}