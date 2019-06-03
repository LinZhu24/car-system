package com.lxr.carsystem.service.impl;

import com.lxr.carsystem.entity.CarModel;
import com.lxr.carsystem.repo.CarModelRepo;
import com.lxr.carsystem.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {
    @Autowired
    CarModelRepo carModelRepo;

    @Override
    public List<CarModel> findAll() {
        return carModelRepo.findAll();
    }

    @Override
    public List<CarModel> findAllBySeriesid(Integer seriesid) {
        return carModelRepo.findAllBySeriesid(seriesid);
    }

    @Override
    public CarModel findByModelid(Integer modelid) {
        return carModelRepo.findByModelid(modelid);
    }

    @Override
    public Page<CarModel> findCarModelPageBySeriesid(Pageable pageable, Integer seriesid) {
        return carModelRepo.findAll((root, query, cb) -> {
            query.orderBy(cb.asc(root.get("modelid")));
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (seriesid != null && !"".equals(seriesid)) {
                predicates.add(cb.equal(root.get("seriesid"),seriesid));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }

    @Override
    public CarModel findBySeriesid(Integer seriesid) {
        return carModelRepo.findBySeriesid(seriesid);
    }

    @Override
    public CarModel findByModelname(String modelname) {
        return carModelRepo.findByModelname(modelname);
    }

    @Override
    public CarModel findByModelnameAndSeriesidAndBrandid(String mname, Integer sid, Integer bid) {
        return carModelRepo.findByModelnameAndSeriesidAndBrandid(mname,sid,bid);
    }
}
