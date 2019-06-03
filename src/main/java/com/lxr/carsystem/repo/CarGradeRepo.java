package com.lxr.carsystem.repo;


import com.lxr.carsystem.entity.CarGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CarGradeRepo extends JpaSpecificationExecutor<CarGrade>,JpaRepository<CarGrade,Integer>{

}
