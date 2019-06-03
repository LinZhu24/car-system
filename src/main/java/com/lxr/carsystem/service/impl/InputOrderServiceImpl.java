package com.lxr.carsystem.service.impl;

import com.lxr.carsystem.entity.InputOrder;
import com.lxr.carsystem.repo.InputOrderRepo;
import com.lxr.carsystem.service.InputOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InputOrderServiceImpl implements InputOrderService{
    @Autowired
    InputOrderRepo inputOrderRepo;

    @Override
    public void saveAndFlush(InputOrder inputOrder) {
        inputOrderRepo.saveAndFlush(inputOrder);
    }

    @Override
    public InputOrder findByInputorderid(Integer id) {
        return inputOrderRepo.findByInputorderid(id);
    }

    @Override
    public List<InputOrder> findAll() {
        return inputOrderRepo.findAll();
    }

    @Override
    public Page<InputOrder> findInputOrderPageByInputid(Pageable pageable, Integer inputorderid) {
        return inputOrderRepo.findAll((root, query, cb) -> {
            query.orderBy(cb.asc(root.get("dealdate")));
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (inputorderid != null && !"".equals(inputorderid)) {
                predicates.add(cb.equal(root.get("inputorderid"),inputorderid));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }

    @Override
    public Page<InputOrder> findInputOrderPage(Pageable pageable, Integer inputorderid) {
        return inputOrderRepo.findAll((root, query, cb) -> {
            query.orderBy(cb.asc(root.get("dealdate")));
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (inputorderid != null && !"".equals(inputorderid)) {
                predicates.add(cb.equal(root.get("inputorderid"),inputorderid));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }
}
