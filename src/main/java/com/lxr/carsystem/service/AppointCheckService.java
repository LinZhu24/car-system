package com.lxr.carsystem.service;

import com.lxr.carsystem.entity.AppointCheck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface AppointCheckService {
    void saveAndFlush(AppointCheck appointCheck);
    List<AppointCheck> findAllAppointcheck();
    List<AppointCheck> findAllByAdminid(Integer adminid);
    AppointCheck findByAppointid(Integer id);

    @Transactional
    Page<AppointCheck> findAppointCheckPage(Pageable pageable,Integer id,Integer flag);
    @Transactional
    Page<AppointCheck> findWorkngAppointCheckPage(Pageable pageable,Integer id,Integer workingflag,Integer doneflag);
    @Transactional
    Page<AppointCheck> findDoneAppointCheckPage(Pageable pageable,Integer id,Integer flag);


}
