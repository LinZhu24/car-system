package com.lxr.carsystem.controller;



import com.lxr.carsystem.entity.CarTip;
import com.lxr.carsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tip")
public class CarTipController {
    @Autowired
    CarTipService carTipService;

    @RequestMapping("/toCartip")
    public String tocartip(HttpSession session){
        List<CarTip> cartipList = new ArrayList<>();
        for (CarTip carTip:carTipService.findAll()) {
            cartipList.add(carTip);
        }
        session.setAttribute("cartipList",cartipList);
        return "/user/carBaike";
    }
    @RequestMapping("/save")
    public String save(String title,String content,String image){
        CarTip cartip=new CarTip();

        cartip.setTitle(title);
        cartip.setContent(content);
        cartip.setImage("AAA");
        carTipService.saveAndFlush(cartip);

        return "redirect:/tip/toCartip";
    }

    @RequestMapping("/addcartip")
    public String index(){
        return "/admin/addCartip";
    }
}
