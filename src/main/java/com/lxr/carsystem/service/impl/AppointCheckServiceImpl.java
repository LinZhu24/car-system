package com.lxr.carsystem.service.impl;

import com.lxr.carsystem.entity.Admin;
import com.lxr.carsystem.entity.AppointCheck;
import com.lxr.carsystem.repo.AdminRepo;
import com.lxr.carsystem.repo.AppointCheckRepo;
import com.lxr.carsystem.service.AppointCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointCheckServiceImpl implements AppointCheckService{

    @Autowired
    AppointCheckRepo appointCheckRepo;
    @Autowired
    AdminRepo adminRepo;

    @Override
    public void saveAndFlush(AppointCheck appointCheck) {
        appointCheckRepo.saveAndFlush(appointCheck);
    }

    @Override
    public List<AppointCheck> findAllByAdminid(Integer adminid) {
        return appointCheckRepo.findAllByAdmin_adminid(adminid);
    }

    @Override
    public List<AppointCheck> findAllAppointcheck() {
        return appointCheckRepo.findAll();
    }

    @Override
    public Page<AppointCheck> findAppointCheckPage(Pageable pageable,Integer id,Integer flag) {
        return appointCheckRepo.findAll((root, query, cb) -> {
            query.orderBy(cb.asc(root.get("appointdate")));
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (id != null && !"".equals(id)) {
                predicates.add(cb.equal(root.get("admin"), adminRepo.findByAdminid(id)));
            }
            if (id != null && !"".equals(id)) {
                predicates.add(cb.equal(root.get("workingflag"),flag));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }

    @Override
    public AppointCheck findByAppointid(Integer id) {
        return appointCheckRepo.findByAppointid(id);
    }

    @Override
    public Page<AppointCheck> findWorkngAppointCheckPage(Pageable pageable, Integer id, Integer workingflag,Integer doneflag) {
        return appointCheckRepo.findAll((root, query, cb) -> {
            query.orderBy(cb.asc(root.get("appointdate")));
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (id != null && !"".equals(id)) {
                predicates.add(cb.equal(root.get("admin"), adminRepo.findByAdminid(id)));
            }
            if (workingflag != null && !"".equals(workingflag)) {
                predicates.add(cb.equal(root.get("workingflag"), workingflag));
            }
            if (doneflag != null && !"".equals(doneflag)) {
                predicates.add(cb.equal(root.get("doneflag"), doneflag));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }

    @Override
    public Page<AppointCheck> findDoneAppointCheckPage(Pageable pageable, Integer id, Integer flag) {
        return appointCheckRepo.findAll((root, query, cb) -> {
            query.orderBy(cb.asc(root.get("appointdate")));//时间距离现在远的，排在最上面
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (id != null && !"".equals(id)) {
                predicates.add(cb.equal(root.get("admin"), adminRepo.findByAdminid(id)));
            }
            if (flag != null && !"".equals(flag)) {
                predicates.add(cb.equal(root.get("doneflag"), flag));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }
}
