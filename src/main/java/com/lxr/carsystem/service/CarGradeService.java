package com.lxr.carsystem.service;

import com.lxr.carsystem.entity.CarBrand;
import com.lxr.carsystem.entity.CarGrade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface CarGradeService {
    CarGrade findByGradeid(Integer gradeid);

    @Transactional
    Page<CarGrade> findCarGradePageByGradeid(Pageable pageable, Integer seriesid);
}
