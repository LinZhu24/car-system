package com.lxr.carsystem.service;

import com.lxr.carsystem.entity.AppointLook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface AppointLookService {
    AppointLook findByUserid(Integer userid);
    AppointLook findByWscid(Integer wscid);
    AppointLook findByAptlkid(Integer aptlkid);
    void saveAndFlush(AppointLook appointLook);
    List<AppointLook> findAllByUserid(Integer userid);

    @Transactional
    Page<AppointLook> findAptLookPage(Pageable pageable,String apttime);

}
