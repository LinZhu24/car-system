package com.lxr.carsystem.service.impl;



import com.lxr.carsystem.entity.AppointLook;
import com.lxr.carsystem.repo.AppointLookRepo;
import com.lxr.carsystem.service.AppointLookService;
import com.lxr.carsystem.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointLookServiceImpl implements AppointLookService{
    @Autowired
    AppointLookRepo appointLookRepo;

    @Override
    public AppointLook findByUserid(Integer userid) {
        return appointLookRepo.findByUserid(userid);
    }

    @Override
    public List<AppointLook> findAllByUserid(Integer userid) {
        return appointLookRepo.findAllByUserid(userid);
    }

    @Override
    public void saveAndFlush(AppointLook appointLook) {
        appointLookRepo.saveAndFlush(appointLook);
    }

    @Override
    public Page<AppointLook> findAptLookPage(Pageable pageable, String apttime) {
        return appointLookRepo.findAll((root, query, cb) -> {
            query.orderBy(cb.asc(root.get("apttime")));
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (apttime != null && !"".equals(apttime)) {
                predicates.add(cb.equal(root.get("apttime"), apttime));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }

    @Override
    public AppointLook findByWscid(Integer wscid) {
        return null;
    }

    @Override
    public AppointLook findByAptlkid(Integer aptlkid) {
        return appointLookRepo.findByAppointlookid(aptlkid);
    }
}
