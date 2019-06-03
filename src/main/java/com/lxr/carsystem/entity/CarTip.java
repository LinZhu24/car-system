package com.lxr.carsystem.entity;


import javax.persistence.*;

@Entity
@Table(name="cartip")
public class CarTip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tipid;

    @Column(length = 255,nullable = false)
    private String title;

    @Column(length = 255,nullable = false)
    private String content;

    @Column(length = 255,nullable = false)
    private String image;



    public Integer getTipid() {
        return tipid;
    }

    public void setTipid(Integer tipid) {
        this.tipid = tipid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
