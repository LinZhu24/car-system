package com.lxr.carsystem.service.impl;

import com.lxr.carsystem.entity.CarBrand;
import com.lxr.carsystem.repo.CarBrandRepo;
import com.lxr.carsystem.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarBrandServiceImpl implements CarBrandService{
    @Autowired
    CarBrandRepo carBrandRepo;

    @Override
    public CarBrand findByBrandid(Integer brandid) {
        return carBrandRepo.findByBrandid(brandid);
    }

    @Override
    public CarBrand findByBrandname(String brandname) {
        return carBrandRepo.findByBrandname(brandname);
    }

    @Override
    public List<CarBrand> findAll() {
        Sort sort = new Sort(Sort.Direction.ASC, "brandinitial");
        return carBrandRepo.findAll(sort);
    }

    @Override
    public Page<CarBrand> findCarBrandPage(Pageable pageable,String brandname,String brandinitial) {
        return carBrandRepo.findAll((root, query, cb) -> {
            query.orderBy(cb.asc(root.get("brandinitial")));
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (brandname != null && !"".equals(brandname)) {
                predicates.add(cb.like(root.get("brandname"),'%'+brandname+'%'));
            }
            if (brandinitial != null && !"".equals(brandinitial)) {
                predicates.add(cb.equal(root.get("brandinitial"),brandinitial));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }

    @Override
    public void deleteByBrandid(Integer brandid) {
        carBrandRepo.delete(brandid);
    }
}
