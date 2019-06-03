package com.lxr.carsystem.service;

import com.lxr.carsystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    /**
    验证用户是否在数据库中
    * */
    User validate(String phone,String password);
    User findByPhone(String phone);
    List<User> findAll();
    User findByUserid(Integer id);
    void saveAndFlush(User user);

    @Transactional
    Page<User> findUserPage(Pageable pageable,String phone);
}
