package com.lxr.carsystem.service.impl;

import com.lxr.carsystem.entity.CarPic;
import com.lxr.carsystem.entity.WaitSellCar;
import com.lxr.carsystem.repo.CarPicRepo;
import com.lxr.carsystem.repo.WaitSellCarRepo;
import com.lxr.carsystem.service.CarPicService;
import com.lxr.carsystem.service.WaitSellCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WaitSellCarServiceImpl implements WaitSellCarService{
    @Autowired
    WaitSellCarRepo waitSellCarRepo;

    @Override
    public void saveAndFlush(WaitSellCar waitSellCar) {
        waitSellCarRepo.saveAndFlush(waitSellCar);
    }

    @Override
    public WaitSellCar findByWaitsellcarid(Integer wscid) {
        return waitSellCarRepo.findByWscid(wscid);
    }

    @Override
    public List<WaitSellCar> findAll() {
        return waitSellCarRepo.findAll();
    }

    @Override
    public List<WaitSellCar> findAllBySellid(Integer sellid) {
        return waitSellCarRepo.findAllBySellid(sellid);
    }


    @Override
    public Page<WaitSellCar> findAllCarByPriceMoreThan(Pageable pageable, Double aa) {
        return waitSellCarRepo.findAll((root, query, cb) -> {
            query.orderBy(cb.asc(root.get("uploadtime")));

            List<Predicate> predicates = new ArrayList<Predicate>();
            if (aa != null && !"".equals(aa) ) {
                predicates.add(cb.greaterThan(root.get("sellprice"),aa));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }

    @Override
    public Page<WaitSellCar> findAllCar(Pageable pageable, String brandname) {
        return waitSellCarRepo.findAll((root, query, cb) -> {
            query.orderBy(cb.asc(root.get("uploadtime")));

            List<Predicate> predicates = new ArrayList<Predicate>();

            if (brandname != null && !"".equals(brandname)) {
                predicates.add(cb.like(root.get("carbrand"), '%'+brandname+'%'));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }

    @Override
    public Page<WaitSellCar> findAllCarByPrice(Pageable pageable, Double aa,Double bb) {
        return waitSellCarRepo.findAll((root, query, cb) -> {
            query.orderBy(cb.asc(root.get("uploadtime")));

            List<Predicate> predicates = new ArrayList<Predicate>();
            if (aa != null && !"".equals(aa) && bb!=null && !"".equals(bb)) {
                predicates.add(cb.between(  root.get("sellprice"),aa,bb  ));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }

    @Override
    public Page<WaitSellCar> findUserBroughtPage(Pageable pageable, Integer userid) {
        return waitSellCarRepo.findAll((root, query, cb) -> {
            query.orderBy(cb.asc(root.get("uploadtime")));

            List<Predicate> predicates = new ArrayList<Predicate>();

            if (userid != null && !"".equals(userid)) {
                predicates.add(cb.equal(root.get("buyerid"), userid));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }
}
