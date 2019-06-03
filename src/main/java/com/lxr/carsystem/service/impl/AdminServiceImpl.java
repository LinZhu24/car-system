package com.lxr.carsystem.service.impl;

import com.lxr.carsystem.entity.Admin;
import com.lxr.carsystem.entity.User;
import com.lxr.carsystem.repo.AdminRepo;
import com.lxr.carsystem.repo.UserRepo;
import com.lxr.carsystem.service.AdminService;
import com.lxr.carsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminRepo adminRepo;

    @Override
    public Admin validate(String adminname, String passwd) {
        return adminRepo.findByAdminnameAndPasswd(adminname,passwd);
    }

    @Override
    public List<Admin> findAll() {
        return adminRepo.findAll();
    }

    @Override
    public Admin findByAdminid(Integer adminid) {
        return adminRepo.findByAdminid(adminid);
    }
}
