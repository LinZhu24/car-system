package com.lxr.carsystem.repo;

import com.lxr.carsystem.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepo extends JpaSpecificationExecutor<Admin>,JpaRepository<Admin,Integer>{
    Admin findByAdminnameAndPasswd(String adminname, String passwd);
    Admin findByAdminid(Integer adminid);

}
