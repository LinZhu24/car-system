package com.lxr.carsystem.service;

import com.lxr.carsystem.entity.Estimate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface EstimateService {
    void save(Estimate estimate);
    void saveAndFlush(Estimate estimate);
    Estimate findByEstid(Integer estid);
    List<Estimate> findByContactWay(String contactway);
    List<Estimate> findAllByContactway(String contactway);
    List<Estimate> findAll();

 //分页以及联合查询
    @Transactional
    Page<Estimate> findEstimatePage(Pageable pageable, String estid, String car, String contactway, Integer userid);
}
