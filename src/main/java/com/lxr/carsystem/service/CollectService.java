package com.lxr.carsystem.service;


import com.lxr.carsystem.entity.Collect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface CollectService {
    Collect findByUserid(Integer userid);
    void saveAndFlush(Collect collect);
    List<Collect> findAllByUserid(Integer userid);
    Collect findByWscid(Integer wscid);
}
