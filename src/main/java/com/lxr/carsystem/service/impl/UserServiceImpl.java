package com.lxr.carsystem.service.impl;

import com.lxr.carsystem.entity.User;
import com.lxr.carsystem.repo.UserRepo;
import com.lxr.carsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepo userRepo;

    @Override
    public User validate(String phone, String password) {
        return userRepo.findByPhoneAndPassword(phone,password);
    }

    @Override
    public User findByPhone(String phone) {
        return userRepo.findByPhone(phone);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findByUserid(Integer id) {
        return userRepo.findByUserid(id);
    }

    @Override
    public void saveAndFlush(User user) {
        userRepo.saveAndFlush(user);
    }

    @Override
    public Page<User> findUserPage(Pageable pageable, String phone) {
        return userRepo.findAll((root, query, cb) -> {
            query.orderBy(cb.asc(root.get("userid")));
            List<Predicate> predicates = new ArrayList<Predicate>();
            if(phone != null && !"".equals(phone)){
                predicates.add(cb.equal(root.get("phone"),phone));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }
}
