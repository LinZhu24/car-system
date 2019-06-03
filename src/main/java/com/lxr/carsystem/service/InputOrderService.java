package com.lxr.carsystem.service;

import com.lxr.carsystem.entity.InputOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.transaction.Transactional;
import java.util.List;

public interface InputOrderService {
    void saveAndFlush(InputOrder inputOrder);
    InputOrder findByInputorderid(Integer id);
    List<InputOrder> findAll();

    @Transactional
    Page<InputOrder> findInputOrderPageByInputid(Pageable pageable,Integer inputorderid);

    @Transactional
    Page<InputOrder> findInputOrderPage(Pageable pageable,Integer inputorderid);
}
