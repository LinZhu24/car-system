package com.lxr.carsystem.service.impl;


import com.lxr.carsystem.entity.Collect;
import com.lxr.carsystem.repo.CollectRepo;

import com.lxr.carsystem.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CollectServiceImpl implements CollectService{
    @Autowired
    CollectRepo collectRepo;

    @Override
    public Collect findByUserid(Integer userid) {
        return collectRepo.findByUserid(userid);
    }

    @Override
    public void saveAndFlush(Collect collect) {
        collectRepo.saveAndFlush(collect);
    }

    @Override
    public List<Collect> findAllByUserid(Integer userid) {
        return collectRepo.findAllByUserid(userid);
    }

    @Override
    public Collect findByWscid(Integer wscid) {
        return collectRepo.findByWscid(wscid);
    }
}
