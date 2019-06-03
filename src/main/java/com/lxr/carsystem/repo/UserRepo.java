package com.lxr.carsystem.repo;

import com.lxr.carsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepo extends JpaSpecificationExecutor<User>,JpaRepository<User,Integer>{
    User findByPhoneAndPassword(String phone,String password);
    User findByPhone(String phone);
    User findByUserid(Integer id);
}