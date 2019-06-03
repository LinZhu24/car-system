package com.lxr.carsystem.service;

import com.lxr.carsystem.entity.CarTip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface CarTipService {
    void saveAndFlush(CarTip carTip);
    List<CarTip> findAll();
}
