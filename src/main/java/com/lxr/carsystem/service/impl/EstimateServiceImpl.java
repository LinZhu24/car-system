package com.lxr.carsystem.service.impl;

import com.lxr.carsystem.entity.Estimate;
import com.lxr.carsystem.repo.EstimateRepo;
import com.lxr.carsystem.repo.UserRepo;
import com.lxr.carsystem.service.EstimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EstimateServiceImpl implements EstimateService {

    @Autowired
    EstimateRepo estimateRepo;

    @Autowired
    UserRepo userRepo;

    @Override//按照contactway查找estimate对象
    public List<Estimate> findByContactWay(String contactway) {
        return estimateRepo.findByContactway(contactway);
    }

    @Override//保存estimate对象
    public void save(Estimate estimate) {
        this.estimateRepo.save(estimate);
    }

    @Override//按照estid查找estimate对象
    public Estimate findByEstid(Integer estid) {
        return estimateRepo.findByEstid(estid);
    }

    @Override
    public List<Estimate> findAllByContactway(String contactway) {
        return estimateRepo.findAllByContactway(contactway);
    }

    @Override
    public List<Estimate> findAll() {
        return estimateRepo.findAll();
    }

    @Override
    public void saveAndFlush(Estimate estimate) {
        estimateRepo.saveAndFlush(estimate);
    }

    @Override
    public Page<Estimate> findEstimatePage(Pageable pageable, String estid, String carbrand, String contactway, Integer userid){
        return estimateRepo.findAll((root, query, cb) -> {
            //root，其中Root<T> root中的T代表查询的根对象，本例子中代表Estimate,root.get("abc")代表获取该实体类的字段。
            //query代表一种查询规则，包含着查询的各个部分，比如：select 、from、where、group by、order by等
            //cb代表查询条件，比如cb.equal表示判相等，cb.asc表示按照某字段升序规则排序，cb.between表示查询处于两者之间
            query.orderBy(cb.asc(root.get("estid")));
            List<Predicate> predicates = new ArrayList<Predicate>();
            //predicates表示存放查询条件的容器，下面的add操作将查询条件加入容器中
            if (estid != null && !"".equals(estid)) {
                predicates.add(cb.equal(root.get("estid"),estid));
            }
            if(carbrand != null && !"".equals(carbrand)){

                predicates.add(cb.equal(root.get("carbrand"),carbrand));
            }
            if(contactway != null && !"".equals(contactway)){
                predicates.add(cb.equal(root.get("contactway"),contactway));
            }
            if(userid != null && !"".equals(userid)){
                predicates.add(cb.equal(root.get("user"),userRepo.findByUserid(userid)));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }

    public Estimate findOneTest(){
        return this.estimateRepo.findOne((root, query, cb) ->{
            return cb.equal(root.get("id"),"123456");
                } );//这个例子就是查询id=123456的
    }
}
