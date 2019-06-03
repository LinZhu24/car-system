package com.lxr.carsystem.service.impl;


import com.lxr.carsystem.entity.CarSeries;
import com.lxr.carsystem.repo.CarSeriesRepo;
import com.lxr.carsystem.service.CarSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarSeriesServiceImpl implements CarSeriesService {
    @Autowired
    CarSeriesRepo carSeriesRepo;

    @Override
    public CarSeries findBySeriesid(Integer seriesid) {
        return carSeriesRepo.findBySeriesid(seriesid);
    }

    @Override
    public CarSeries findBySeriesname(String seriesname) {
        return carSeriesRepo.findBySeriesname(seriesname);
    }

    @Override
    public List<CarSeries> findAllByBrandid(Integer brandid) {
        return carSeriesRepo.findAllByBrandid(brandid);
    }

    @Override
    public Page<CarSeries> findCarSeriesPageByBrandid(Pageable pageable, String seriesname, Integer brandid) {
        return carSeriesRepo.findAll((root, query, cb) -> {
            query.orderBy(cb.asc(root.get("seriesid")));
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (seriesname != null && !"".equals(seriesname)) {
                predicates.add(cb.like(root.get("seriesname"),'%'+seriesname+'%'));
            }
            if (brandid != null && !"".equals(brandid)) {
                predicates.add(cb.equal(root.get("brandid"),brandid));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }

    @Override
    public CarSeries findByBrandid(Integer brandid) {
        return carSeriesRepo.findByBrandid(brandid);
    }

    @Override
    public CarSeries findBySeriesnameAndBrandid(String sname, Integer bid) {
        return carSeriesRepo.findBySeriesnameAndBrandid(sname,bid);
    }
}
