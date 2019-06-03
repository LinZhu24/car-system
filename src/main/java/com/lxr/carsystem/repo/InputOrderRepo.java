package com.lxr.carsystem.repo;

import com.lxr.carsystem.entity.InputOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface InputOrderRepo extends JpaSpecificationExecutor<InputOrder>,JpaRepository<InputOrder,Integer> {
    InputOrder findByInputorderid(Integer id);


}
