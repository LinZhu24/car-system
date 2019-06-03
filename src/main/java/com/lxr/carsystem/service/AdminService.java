package com.lxr.carsystem.service;

import com.lxr.carsystem.entity.Admin;

import java.util.List;

public interface AdminService {
    /**
    验证管理员是否在数据库中
    **/
    Admin validate(String adminname, String passwd);
    List<Admin> findAll();
    Admin findByAdminid(Integer adminid);
}
