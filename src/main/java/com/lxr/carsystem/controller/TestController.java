package com.lxr.carsystem.controller;


import com.lxr.carsystem.common.ResponseEntity;
import com.lxr.carsystem.entity.Admin;
import com.lxr.carsystem.entity.AppointCheck;
import com.lxr.carsystem.entity.CarBrand;
import com.lxr.carsystem.entity.User;
import com.lxr.carsystem.repo.AdminRepo;
import com.lxr.carsystem.service.*;
import com.lxr.carsystem.util.enums.BreakDownAlarmEnum;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    AdminService adminService;
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    UserService userService;

    @Autowired
    CarBrandService carBrandService;

    @Autowired
    EstimateService estimateService;
    @Autowired
    AppointCheckService appointCheckService;

    @RequestMapping("/exper")
    public String exper(){
        return "/test/exper";
    }

    @RequestMapping("/theMainPage")//跳转到主页面
    public String theMainPage(){
        return "/test/main";
    }


    @RequestMapping("/adminPage")//跳转到主页面
    public String adminPage(){
        return "/test/main";
    }


    @RequestMapping("/login")//跳转至管理员登录页
    public String login(String adminname, String passwd, HttpSession session){
        Admin admin=adminService.validate(adminname,passwd);
        if (admin != null) {
            session.setAttribute("currentAdmin",admin);
            //return "/admin/main";//成功则跳转到登陆成功页面
            //return "redirect:findAllUser";
            return "/test/main";
        }else{
            //清空session
            session.invalidate();
        }
        return "/test/error";
    }

    @RequestMapping("/reg")//实现管理员注册功能
    public String reg(String adminname,String passwd){
        Admin admin=new Admin();
        admin.setAdminname(adminname);
        admin.setPasswd(passwd);
        adminRepo.save(admin);
        return "/test/result";
    }
/*    @RequestMapping("/personInfo")
    public ModelAndView personInfo(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("/index2");
        return mv;
    }*/

    @RequestMapping("/quit")//实现注销当前管理员的功能
    public String quit(HttpSession session){
        Admin admin=(Admin)session.getAttribute("currentAdmin");
        session.removeAttribute("currentAdmin");
        session.removeAttribute("adminInfo");
        //return "/admin/main";
        return "redirect:theMainPage";
    }

    @RequestMapping("/updateAdmin")//修改管理员信息
    public String editstop(Integer adminid,String adminname,String passwd){
        Admin admin=new Admin();
        admin=adminRepo.findByAdminid(adminid);
        if(adminname!=null){
            admin.setAdminname(adminname);
        }
        if(passwd!=null){
            admin.setPasswd(passwd);
        }
        adminRepo.save(admin);
        //return "redirect:findAllInfo";
        return "/test/main";
    }

    @RequestMapping("/findAllInfo")//查看所有的管理员信息
    public String findAll(HttpSession session){
        List<Admin> adminList=adminRepo.findAll();
        session.setAttribute("adminInfo",adminList);
        return "/test/main";
    }

    @RequestMapping("/findAllUser")//查看所有的管理员信息
    public String findAllUser(Model model){
        List<User> userList=userService.findAll();
        model.addAttribute("userList",userList);
        return "/test/lookUsers";
    }

    @RequestMapping("/findUserRequests")//查看所有的预约信息
    public String findUserRequests(Model model){
        List<AppointCheck> appointCheckList=appointCheckService.findAllAppointcheck();
        model.addAttribute("appointCheckList",appointCheckList);
        return "/test/lookRequests";
    }

    @RequestMapping("/findAllCar")//查看所有的汽车信息
    public String findAllCar(Model model,Pageable pageable,String brandname,String brandinitial){
        pageable=new PageRequest(pageable.getPageNumber(),3);
        Page<CarBrand> page = carBrandService.findCarBrandPage(pageable,brandname,brandinitial);
        model.addAttribute("page",page);
        model.addAttribute("brandname",brandname != null && !"".equals(brandname) ? brandname:null);
        model.addAttribute("brandinitial",brandinitial != null && !"".equals(brandinitial) ? brandinitial:null);
        return "/test/test1";
    }

    @RequestMapping("/deleteCarBrand/{id}")//删除汽车的相关信息
    public String deleteCarBrand(@PathVariable("id") Integer id){
        System.out.println("***"+id+"***");
        return "redirect:/test/findAllCar";
    }

    @RequestMapping("/editCarBrand")//编辑汽车的相关信息
    public String editCarBrand(String modelstopid,String newleaderman,String newaddress){
        return "redirect:/test/findAllCar";
    }

    @RequestMapping("/test2")//编辑汽车的相关信息
    public String test2(){
        long currentTime=System.currentTimeMillis();
        System.out.println("currentTime:"+currentTime);
        return "/test/test2";
    }


    @RequestMapping("/saveOrder")
    @ResponseBody
    public String saveOrder(String dealdate) {
        System.out.println(dealdate);
        String kkk="2016年，8月";
        Integer year=Integer.parseInt(kkk.substring(0,4));
        Integer month=Integer.parseInt(kkk.substring(6,7));
        System.out.println("year:"+year);
        System.out.println("month:"+month);
        return "success";
    }

    @GetMapping("/exportQuitReason")
    @ResponseBody
    public ResponseEntity exportQuitReason(String vendor, String startDate, String endDate) {

        if (StringUtils.isEmpty(vendor)) {
            return ResponseEntity.failResponse("厂家为空");
        }
        if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
            return ResponseEntity.failResponse("开始时间或结束时间为空");
        }

        List<String> alarmList = BreakDownAlarmEnum.getAlarmListByVendor(vendor);
        if (CollectionUtil.isEmpty(alarmList)) {
            return ResponseEntity.failResponse("该厂家对应的参考告警名称暂未提供");
        }

        return ResponseEntity.successResponse(alarmList);
    }

}
