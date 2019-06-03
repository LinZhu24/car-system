package com.lxr.carsystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//执行save操作之后estimate就已经更新为已经保存的实体了
    private Integer userid;

    @Column(length = 255,nullable = false)//密码不可为空
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Estimate> estimateList=new ArrayList<>();
    public List<Estimate> getEstimateList() {
        return estimateList;
    }

    public void setEstimateList(List<Estimate> estimateList) {
        this.estimateList = estimateList;
    }

    @Column(length = 255,nullable = false)//手机号不可为空
    private String phone;

    @Column(length = 255,nullable = true)//性别可为空
    private String gender;

    @Column(length = 255,nullable = true)//姓氏可为空
    private String familyname;

    @Column(length = 255,nullable = true)//头像可为空
    private String headpic;

    public String getHeadpic() {
        return headpic;
    }
    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

}
