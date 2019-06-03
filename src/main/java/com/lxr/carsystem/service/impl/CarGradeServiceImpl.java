package com.lxr.carsystem.service.impl;


import com.lxr.carsystem.entity.CarGrade;
import com.lxr.carsystem.repo.CarGradeRepo;
import com.lxr.carsystem.service.CarGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarGradeServiceImpl implements CarGradeService {
    @Autowired
    CarGradeRepo carGradeRepo;

    @Override
    public CarGrade findByGradeid(Integer gradeid) {
        return carGradeRepo.findOne(gradeid);
    }

    @Override
    public Page<CarGrade> findCarGradePageByGradeid(Pageable pageable, Integer gradeid) {
        return carGradeRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (gradeid != null && !"".equals(gradeid)) {
                predicates.add(cb.equal(root.get("gradeid"),gradeid));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }
}
