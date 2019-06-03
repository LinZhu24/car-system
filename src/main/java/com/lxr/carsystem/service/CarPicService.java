package com.lxr.carsystem.service;

import com.lxr.carsystem.entity.CarPic;
import com.lxr.carsystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface CarPicService {
    void saveAndFlush(CarPic carPic);
}
