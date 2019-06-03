package com.lxr.carsystem.controller;


import com.lxr.carsystem.entity.*;
import com.lxr.carsystem.repo.AdminRepo;
import com.lxr.carsystem.service.*;
import com.lxr.carsystem.tool.Helper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.expression.Maps;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    UserService userService;
    @Autowired
    CarBrandService carBrandService;
    @Autowired
    CarSeriesService carSeriesService;
    @Autowired
    CarModelService carModelService;
    @Autowired
    CarGradeService carGradeService;
    @Autowired
    CarDetailService carDetailService;
    @Autowired
    EstimateService estimateService;
    @Autowired
    AppointCheckService appointCheckService;
    @Autowired
    InputOrderService inputOrderService;
    @Autowired
    WaitSellCarService waitSellCarService;
    @Autowired
    CarPicService carPicService;
    @Autowired
    AppointLookService appointLookService;
    @Autowired
    CarTipService carTipService;

    /**
     * 进入实验页面
     **/
    @RequestMapping("/exper")
    public String exper(){
        return "/admin/exper";
    }
    /**
     * 跳转到主页面
     **/
    @RequestMapping("/theMainPage")
    public String theMainPage(){
        return "/admin/main";
    }

    /**
     * 跳转至管理员登录页面
     **/
    @RequestMapping("/go")
    public String go(){
        return "/admin/login";
    }

    /**
     * 实现登录操作
     **/
    @RequestMapping("/login")
    public String login(String adminname, String passwd, HttpSession session,Model model){
        Admin admin=adminService.validate(adminname,passwd);
        if (admin != null) {
            session.setAttribute("currentAdmin",admin);//currentAdmin存放当前管理员的信息
            model.addAttribute("admin1",admin);
            return "/admin/main";
        }else{
            session.invalidate();
        }
        return "/admin/error";
    }

     /**
     * 注销当前管理员
     **/
    @RequestMapping("/quit")
    public String quit(HttpSession session){
        Admin admin=(Admin)session.getAttribute("currentAdmin");
        session.removeAttribute("currentAdmin");
        session.removeAttribute("adminInfo");
        return "/admin/login";
    }

    /**
     * 分页查看所有用户基本信息
     **/
    @RequestMapping("/findAllUserByPage")
    public String findAllUserByPage(Model model,Pageable pageable,String phone){
        pageable=new PageRequest(pageable.getPageNumber(),7);
        Page<User> page = userService.findUserPage(pageable,phone);
        model.addAttribute("page",page);
        model.addAttribute("phone",phone != null && !"".equals(phone) ? phone:null);
        return "/admin/lookUsers";
    }

    /**
     * 分页查询卖家预约检测汽车的请求
     **/
    @RequestMapping("/findUserRequestsByPage/{id}")
    public String findUserRequestsByPage(Model model, Pageable pageable,@PathVariable("id") Integer id){
        pageable=new PageRequest(pageable.getPageNumber(),7);
        Page<AppointCheck> page = appointCheckService.findAppointCheckPage(pageable,id,0);
        model.addAttribute("page",page);
        model.addAttribute("adminid",id);
        return "/admin/lookRequests";
    }

    /**
     * 重新刷新上面的方法对应的页面的（中转站）
     **/
    @RequestMapping("/findUserRequestsByPage1")
    public String findUserRequestsByPage1(Model model, Pageable pageable,@ModelAttribute("adminid") Integer adminid ){
        pageable=new PageRequest(pageable.getPageNumber(),7);
        Page<AppointCheck> page = appointCheckService.findAppointCheckPage(pageable,adminid,0);
        model.addAttribute("page",page);
        model.addAttribute("adminid",adminid);
        return "/admin/lookRequests";
    }

    /**
     * 上一页和下一页操作
     **/
    @RequestMapping("/findUserRequestsByPageNextPage")
    public String findUserRequestsByPageNextPage(Model model, Pageable pageable,Integer id){
        pageable=new PageRequest(pageable.getPageNumber(),7);
        Page<AppointCheck> page = appointCheckService.findAppointCheckPage(pageable,id,0);
        model.addAttribute("page",page);
        model.addAttribute("adminid",id);
        return "/admin/lookRequests";
    }

    /**
     * 将卖家的预约请求加入办理中队列
     **/
    @RequestMapping("/addWorkingList/{id}")
    public ModelAndView  addWaitList(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes){
        AppointCheck appointCheck=appointCheckService.findByAppointid(id);
        appointCheck.setWorkingflag(1);
        appointCheckService.saveAndFlush(appointCheck);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/findUserRequestsByPage1");
        redirectAttributes.addFlashAttribute("adminid",appointCheck.getAdmin().getAdminid());
        return modelAndView;
    }
    /**
     * 分页查看正在办理中的卖家请求
     **/
    @RequestMapping("/findUserWorkingRequestsByPage/{id}")
    public String findUserWorkingRequestsByPage(Model model, Pageable pageable,@PathVariable("id") Integer id) {
        pageable = new PageRequest(pageable.getPageNumber(), 7);
        Page<AppointCheck> page = appointCheckService.findWorkngAppointCheckPage(pageable, id,1,0);
        model.addAttribute("page", page);
        model.addAttribute("adminid", id);
        return "/admin/lookRequestsWorking";
    }

    /**
     *重新刷新上面的方法对应的页面的（中转站）
     */
    @RequestMapping("/findUserWorkingRequestsByPage1")
    public String findUserWorkingRequestsByPage1(Model model, Pageable pageable,@ModelAttribute("adminid") Integer adminid) {
        pageable = new PageRequest(pageable.getPageNumber(), 7);
        Page<AppointCheck> page = appointCheckService.findWorkngAppointCheckPage(pageable, adminid,1,0);
        model.addAttribute("page", page);
        model.addAttribute("adminid", adminid);
        return "/admin/lookRequestsWorking";
    }

    /**
     * 上一页和下一页
     **/
    @RequestMapping("/findUserWorkingRequestsByPageNextPage")
    public String findUserWorkingRequestsByPageNextPage(Model model, Pageable pageable,Integer id) {
        pageable = new PageRequest(pageable.getPageNumber(), 7);
        Page<AppointCheck> page = appointCheckService.findWorkngAppointCheckPage(pageable, id,1,0);
        model.addAttribute("page", page);
        model.addAttribute("adminid", id);
        return "/admin/lookRequestsWorking";
    }

    /**
     * 加入已完成（卖家请求归档）队列
     **/
    @RequestMapping("/addDoneList/{id}")
    public ModelAndView  addDoneList(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes){
        AppointCheck appointCheck=appointCheckService.findByAppointid(id);
        appointCheck.setDoneflag(1);
        appointCheckService.saveAndFlush(appointCheck);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/findUserWorkingRequestsByPage1");
        redirectAttributes.addFlashAttribute("adminid",appointCheck.getAdmin().getAdminid());
        return modelAndView;
    }


    /**
     * 分页查看已归档的卖家请求
     **/
    @RequestMapping("/findUserDoneRequestsByPage/{id}")
    public String findUserDoneRequestsByPage(Model model, Pageable pageable,@PathVariable("id") Integer id) {
        pageable = new PageRequest(pageable.getPageNumber(), 7);
        Page<AppointCheck> page = appointCheckService.findDoneAppointCheckPage(pageable, id,1);
        model.addAttribute("page", page);
        model.addAttribute("adminid", id);
        return "/admin/lookRequestsDone";
    }

    /**
     * 上一页和下一页
     **/
    @RequestMapping("/findUserDoneRequestsByPageNextPage")
    public String findUserDoneRequestsByPageNextPage(Model model, Pageable pageable,Integer id) {
        pageable = new PageRequest(pageable.getPageNumber(), 7);
        Page<AppointCheck> page = appointCheckService.findDoneAppointCheckPage(pageable, id,1);
        model.addAttribute("page", page);
        model.addAttribute("adminid", id);
        return "/admin/lookRequestsDone";
    }


    /**
     * 分页查询买家预约检测汽车的请求
     **/
    @RequestMapping("/findBuyerRequestsByPage")
    public String findBuyerRequestsByPage(Model model, Pageable pageable,String apttime){
        pageable=new PageRequest(pageable.getPageNumber(),7);
        Page<AppointLook> page = appointLookService.findAptLookPage(pageable,apttime);
        model.addAttribute("page",page);
        model.addAttribute("apttime",apttime);
        return "/admin/lookBuyerRequest";
    }
    /**
     * 重新刷新上面的方法对应的页面的（中转站）
     **/

    /**
     * 分页查看所有已维护汽车品牌信息
     **/
    @RequestMapping("/findAllCarByBrand")
    public String findAllCarByBrand(Model model,Pageable pageable,String brandname,String brandinitial){
        pageable=new PageRequest(pageable.getPageNumber(),7);
        Page<CarBrand> page = carBrandService.findCarBrandPage(pageable,brandname,brandinitial);
        model.addAttribute("page",page);
        model.addAttribute("brandname",brandname != null && !"".equals(brandname) ? brandname:null);
        model.addAttribute("brandinitial",brandinitial != null && !"".equals(brandinitial) ? brandinitial:null);
        return "/admin/lookCars";
    }
    /**
     * 分页按照品牌查看车系信息
     **/
    @RequestMapping("/lookSeriesrByBrand/{id}")
    public String lookSeriesrByBrand(Model model,Pageable pageable,String seriesname,@PathVariable("id") Integer brandid){
        pageable=new PageRequest(pageable.getPageNumber(),7);
        Page<CarSeries> page = carSeriesService.findCarSeriesPageByBrandid(pageable,seriesname,brandid);
        model.addAttribute("page",page);
        model.addAttribute("seriesname",seriesname != null && !"".equals(seriesname) ? seriesname:null);
        model.addAttribute("brandid",brandid != null && !"".equals(brandid) ? brandid:null);
        model.addAttribute("brandname",carBrandService.findByBrandid(brandid).getBrandname());
        return "/admin/lookSeriesByBrand";
    }

    /**
     * 分页按照品牌查看车系信息（中转站）
     **/
    @RequestMapping("/lookSeriesrByBrand1")
    public String lookSeriesrByBrand1(Model model,Pageable pageable,String seriesname,Integer brandid){
        pageable=new PageRequest(pageable.getPageNumber(),7);
        Page<CarSeries> page = carSeriesService.findCarSeriesPageByBrandid(pageable,seriesname,brandid);
        model.addAttribute("page",page);
        model.addAttribute("seriesname",seriesname != null && !"".equals(seriesname) ? seriesname:null);
        model.addAttribute("brandid",brandid != null && !"".equals(brandid) ? brandid:null);
        model.addAttribute("brandname",carBrandService.findByBrandid(brandid).getBrandname());
        return "/admin/lookSeriesByBrand";
    }

    /**
     * 分页按照车系查看车型信息
     **/
    @RequestMapping("/lookModelBySeries/{id}")
    public String lookModelBySeries(Model model,Pageable pageable,@PathVariable("id") Integer seriesid){
        pageable=new PageRequest(pageable.getPageNumber(),7);
        Page<CarModel> page = carModelService.findCarModelPageBySeriesid(pageable,seriesid);
        model.addAttribute("page",page);
        model.addAttribute("seriesid",seriesid != null && !"".equals(seriesid) ? seriesid:null);
        model.addAttribute("seriesname",carSeriesService.findBySeriesid(seriesid).getSeriesname());
        return "/admin/lookModelBySeries";
    }

    /**
     * 分页按照车系查看车型信息(中转站)
     **/
    @RequestMapping("/lookModelBySeries1")
    public String lookModelBySeries1(Model model,Pageable pageable,Integer seriesid){
        pageable=new PageRequest(pageable.getPageNumber(),7);
        Page<CarModel> page = carModelService.findCarModelPageBySeriesid(pageable,seriesid);
        model.addAttribute("page",page);
        model.addAttribute("seriesid",seriesid != null && !"".equals(seriesid) ? seriesid:null);
        model.addAttribute("seriesname",carSeriesService.findBySeriesid(seriesid).getSeriesname());
        return "/admin/lookModelBySeries";
    }

    /**
     * 按照车型id查看汽车详情
     **/
    @RequestMapping("/lookDetailByModel/{id}")
    public String lookDetailByModel(Model model,@PathVariable("id") Integer modelid){
        model.addAttribute("cardetail",carDetailService.findByModelid(modelid));
        model.addAttribute("bname",carBrandService.findByBrandid(carModelService.findByModelid(modelid).getBrandid()).getBrandname());
        model.addAttribute("sname",carSeriesService.findBySeriesid(carModelService.findByModelid(modelid).getSeriesid()).getSeriesname());
        model.addAttribute("mname",carModelService.findByModelid(modelid).getModelname());
        return "/admin/lookDetailByModel";
    }

    /**
     * 分页查看库存车辆
     **/
    @RequestMapping("/findStock")
    public String findStock(Model model,Pageable pageable,Integer inputorderid){
        pageable=new PageRequest(pageable.getPageNumber(),7);
        Page<InputOrder> page = inputOrderService.findInputOrderPage(pageable,inputorderid);
        model.addAttribute("page",page);
        model.addAttribute("inputorderid",inputorderid != null && !"".equals(inputorderid) ? inputorderid:null);
        return "/admin/lookStock";
    }
    /**
     * 分页查看入库订单
     **/
    @RequestMapping("/findInputOrderPage")
    public String findInputOrderPage(Model model,Pageable pageable,Integer inputorderid){
        pageable=new PageRequest(pageable.getPageNumber(),7);
        Page<InputOrder> page = inputOrderService.findInputOrderPageByInputid(pageable,inputorderid);
        model.addAttribute("page",page);
        model.addAttribute("inputorderid",inputorderid != null && !"".equals(inputorderid) ? inputorderid:null);
        return "/admin/lookInputOrder";
    }

    /**
     * 分页查看出库订单
     **/
    @RequestMapping("/findOutputOrderPage")
    public String findOutputOrderPage(Model model,Pageable pageable,String brandname){
        pageable=new PageRequest(pageable.getPageNumber(),7);
        Page<WaitSellCar> page = waitSellCarService.findAllCar(pageable,brandname);
        model.addAttribute("page",page);
        model.addAttribute("brandname",brandname != null && !"".equals(brandname) ? brandname:null);
        return "/admin/lookOutputOrder";
    }
    /**
     * 编辑汽车的相关信息
     **/
    @RequestMapping("/editCarBrand")
    public String editCarBrand(String modelstopid,String newleaderman,String newaddress){
        return "redirect:/admin/findAllCar";
    }

    /**
     * 上传待售汽车信息
     **/
    @RequestMapping("/uploadWaitedSellCarInfo")
    public String uploadWaitedSellCarInfo(Model model) {
        List<CarBrand> carBrandList=carBrandService.findAll();
        model.addAttribute("carBrandList",carBrandList);
        return "/admin/uploadCarInfo";
    }

    /**
     * 按照Appointid来上传该用户的待售汽车信息
     **/
    @RequestMapping("/upload/{appointid}")
    public String upload(Model model,@PathVariable("appointid") Integer appointid) {
        AppointCheck appointCheck=appointCheckService.findByAppointid(appointid);
        Estimate estimate=appointCheck.getEstimate();
        model.addAttribute("estimate",estimate);
        model.addAttribute("appointCheck",appointCheck);
        return "/admin/uploadCar";
    }
    /**
     * 获得车系列表实现三级联动
     **/
    @RequestMapping("/getCarBrandList")
    public List<CarBrand> getCarBrandList() {
        List<CarBrand> carBrandList=carBrandService.findAll();
        return carBrandList;
    }
    /**
     * 获得车系列表实现三级联动
     **/
    @RequestMapping(value = "/series")
    @ResponseBody
    public List<CarSeries> getCarSeriesList(Integer provinceId) {
        List<CarSeries> carSeriesList= carSeriesService.findAllByBrandid(provinceId);
        return carSeriesList;
    }

    /**
     * 获得车型列表实现三级联动
     **/
    @RequestMapping(value = "/model")
    @ResponseBody
    public List<CarModel> getCarModelList(Integer cityId) {
        List<CarModel> carModelList=carModelService.findAllBySeriesid(cityId);
        return carModelList;
    }
    /**
     *
     **/
    @RequestMapping("/order")
    public String order(Model model) {
        return "/admin/uploadCar";
    }

    /**
     * 以出现弹框的方式，填写入库订单的表单
     **/
    @RequestMapping("/showOrderForm/{id}")
    public String toEnterOrder(Model model,@PathVariable("id") Integer id) {
        AppointCheck appointCheck=appointCheckService.findByAppointid(id);
        Estimate estimate=appointCheck.getEstimate();
        User user=estimate.getUser();

        Double guideprice=Double.parseDouble(carDetailService.findByModelid(estimate.getModelid()).getFirmguideprice());
        model.addAttribute("guideprice",guideprice);
        model.addAttribute("user",user);
        model.addAttribute("estimate",estimate);
        model.addAttribute("appointCheck",appointCheck);
        return "/admin/inputOrderForm";
    }

    /**
     * 生成订单
     **/
    @RequestMapping("/saveOrder")
    @ResponseBody
    public String produceOrder(Double inputprice,Integer appointid) {
        Date day=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dealdate=simpleDateFormat.format(day);

        AppointCheck appointCheck=appointCheckService.findByAppointid(appointid);
        InputOrder inputOrder=new InputOrder();
        inputOrder.setAppointCheck(appointCheck);//预约记录对象赋值
        inputOrder.setCondiction(0);//订单状态
        inputOrder.setDealdate(dealdate);//订单生成时间
        inputOrder.setInputprice(inputprice);//入库价格
        inputOrder.setInputordernumber(Helper.getUUID());//订单号
        inputOrderService.saveAndFlush(inputOrder);

        appointCheck.setOrderflag(1);//置该记录状态为已生成订单
        appointCheck.setInputOrder(inputOrder);
        appointCheckService.saveAndFlush(appointCheck);
        return "success";
    }

    /**
     * 以出现弹框的方式，填写待售卖的汽车信息
     **/
/*    @RequestMapping("/uploadCarInfo/{id}")
    public String uploadCarInfo(Model model,@PathVariable("id") Integer inputorderid) {
        InputOrder inputOrder=inputOrderService.findByInputorderid(inputorderid);
        AppointCheck appointCheck=inputOrder.getAppointCheck();
        Estimate estimate=appointCheck.getEstimate();

        Integer brandid=carBrandService.findByBrandname(estimate.getCarbrand()).getBrandid();
        Integer seriesid=carSeriesService.findBySeriesname(estimate.getCarseries()).getSeriesid();
        Integer modelid=estimate.getModelid();
        model.addAttribute("brandid",brandid);
        model.addAttribute("seriesid",seriesid);
        model.addAttribute("modelid",modelid);
        model.addAttribute("inputOrder",inputOrder);
        return "/admin/uploadForm";
    }*/

    @RequestMapping("/startUploadCarInfo")
    @ResponseBody
    public Integer startUploadCarInfo(Integer sellid,
            Double waitsellprice,String  carbrand,String carseries,
            String carmodel,Integer brandid,Integer seriesid,
            Integer modelid,String regdate,String regcity,
            String ownercity,Double drivemiles,Integer inputorderid) {
        InputOrder inputOrder=inputOrderService.findByInputorderid(inputorderid);
        inputOrder.setCondiction(1);//置状态为已发布售卖信息
        inputOrderService.saveAndFlush(inputOrder);//修改并刷新

        Date day=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String uploadtime=simpleDateFormat.format(day);//获得当前系统时间

        WaitSellCar waitSellCar=new WaitSellCar();

        waitSellCar.setUploadtime(uploadtime);//1.上传时间
        waitSellCar.setSellprice(waitsellprice);//2.售价
        waitSellCar.setCarbrand(carbrand);//3.品牌
        waitSellCar.setCarseries(carseries);//4.车系
        waitSellCar.setCarmodel(carmodel);//5.车型
        waitSellCar.setBrandid(brandid);//6.品牌ID
        waitSellCar.setSeriesid(seriesid);//7.车系ID
        waitSellCar.setModelid(modelid);//8.车型ID
        waitSellCar.setRegdate(regdate);//9.上牌时间
        waitSellCar.setRegcity(regcity);//10.上牌城市
        waitSellCar.setOwnercity(ownercity);//11.卖车城市
        waitSellCar.setDrivemiles(drivemiles);//12.行驶里程
        waitSellCar.setInputorderid(inputorderid);//13.订单ID
        waitSellCar.setSellflag(0);//14.该汽车状态
        waitSellCar.setSellid(sellid);//15.卖家ID
        waitSellCarService.saveAndFlush(waitSellCar);

        return waitSellCar.getWscid();//将生成的主键ID
    }

    /**
     * 保存图片至数据库
     **/
    @RequestMapping("/savePic")
    public String savePic(String [] img,Integer wscid) {
        WaitSellCar waitSellCar=waitSellCarService.findByWaitsellcarid(wscid);

        CarPic headPic=new CarPic();

        headPic.setPath(img[0]);
        waitSellCar.setHeadpath(img[0]);//设置在售汽车的开头简略图

        headPic.setHeadpic(1);//设置领头羊图
        headPic.setWsc(waitSellCar);
        carPicService.saveAndFlush(headPic);

        waitSellCarService.saveAndFlush(waitSellCar);

        for(int i=1;i<img.length;i++){
            System.out.println(i+"-------->"+img[i]);
            CarPic carPic=new CarPic();
            carPic.setPath(img[i]);
            carPic.setWsc(waitSellCar);
            carPic.setHeadpic(0);
            carPicService.saveAndFlush(carPic);
        }
        return "redirect:/admin/findInputOrderPage";
    }

    /**
     * 填写待售汽车信息（跳转页面）
     **/
    @RequestMapping("/uploadCarInfo/{id}")
    public String uploadCarInfo(Model model,@PathVariable("id") Integer inputorderid) {
        InputOrder inputOrder=inputOrderService.findByInputorderid(inputorderid);
        AppointCheck appointCheck=inputOrder.getAppointCheck();
        Estimate estimate=appointCheck.getEstimate();

        Integer sellid=estimate.getUser().getUserid();
        Integer brandid=carBrandService.findByBrandname(estimate.getCarbrand()).getBrandid();
        Integer seriesid=carSeriesService.findBySeriesname(estimate.getCarseries()).getSeriesid();
        Integer modelid=estimate.getModelid();

        model.addAttribute("sellid",sellid);
        model.addAttribute("brandid",brandid);
        model.addAttribute("seriesid",seriesid);
        model.addAttribute("modelid",modelid);
        model.addAttribute("inputOrder",inputOrder);
        return "/admin/uploadForm";
    }

    /**
     * 以出现弹框的方式，填写入库订单的表单
     **/
    @RequestMapping("/lookBuyerRqstCarInfo/{id}")
    public String lookBuyerRqstCarInfo(Model model,@PathVariable("id") Integer id) {
        WaitSellCar waitSellCar=waitSellCarService.findByWaitsellcarid(id);
        List<CarPic> carPicList=waitSellCar.getCarPicList();

        model.addAttribute("carPicList",carPicList);
        model.addAttribute("waitSellCar",waitSellCar);
        return "/admin/BuyerRqstDetail";
    }

    @RequestMapping("/saveOutputOrder/{id}")
    public String  saveOutputOrder(@PathVariable("id") Integer id){
        AppointLook appointLook=appointLookService.findByAptlkid(id);
        appointLook.setOutputflag(1);
        WaitSellCar waitSellCar=waitSellCarService.findByWaitsellcarid(appointLook.getWscid());
        waitSellCar.setBuyerid(appointLook.getUserid());

        Date day=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dealdate=simpleDateFormat.format(day);

        waitSellCar.setDealdate(dealdate);
        appointLookService.saveAndFlush(appointLook);
        waitSellCarService.saveAndFlush(waitSellCar);

        return "redirect:/admin/findBuyerRequestsByPage";
    }

    /**
     * 上传百科
     **/
    @RequestMapping("/uploadTip")
    public String uploadTip() {
        return "/admin/addCartip";
    }

    /**
     * 查看百科
     **/
    @RequestMapping("/lookTip")
    public String lookTip(Model model) {
        List<CarTip> carTipList=carTipService.findAll();
        model.addAttribute("carTipList",carTipList);
        return "/admin/lookTips";
    }
}
