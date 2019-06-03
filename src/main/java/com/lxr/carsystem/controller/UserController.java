package com.lxr.carsystem.controller;
import com.lxr.carsystem.config.RedisDao;
import com.lxr.carsystem.entity.*;
import com.lxr.carsystem.repo.AdminRepo;
import com.lxr.carsystem.repo.AppointCheckRepo;
import com.lxr.carsystem.repo.EstimateRepo;
import com.lxr.carsystem.repo.UserRepo;
import com.lxr.carsystem.service.*;
import com.lxr.carsystem.tool.FileUtil;
import com.lxr.carsystem.tool.Helper;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;//1.用户service
    @Autowired
    EstimateService estimateService;//2.估价记录service
    @Autowired
    AdminService adminService;//3.管理员service
    @Autowired
    AppointCheckService appointCheckService;//4.预约检车service
    @Autowired
    CarBrandService carBrandService;//6.
    @Autowired
    CarSeriesService carSeriesService;//7.
    @Autowired
    CarModelService carModelService;//8.
    @Autowired
    CarDetailService carDetailService;//.9
    @Autowired
    InputOrderService inputOrderService;//10.
    @Autowired
    WaitSellCarService waitSellCarService;//11.
    @Autowired
    CarPicService carPicService;//12.
    @Autowired
    CollectService collectService;//13
    @Autowired
    AppointLookService appointLookService;//14
    @Autowired
    CarTipService carTipService;//15

    @Autowired
    RedisDao<User> redisDao;

    /*
    **跳转到首页
     */
    @RequestMapping("/theMainPage")//
    public String theMainPage(Model model,Pageable pageable,String brandname){
        pageable = new PageRequest(pageable.getPageNumber(), 5);
        Page<WaitSellCar> page = waitSellCarService.findAllCar(pageable,brandname);
        model.addAttribute("page", page);
        model.addAttribute("brandname", brandname);
        return "/user/main";
    }

    /*
    *跳转到注册页面
     */
    @RequestMapping("/temp")
    public String temp(){
        return "/user/register";
    }

    /*
     *利用拦截器实现登录控制
     */
    @RequestMapping("/info")
    public String info(){
        return "/user/login";
    }

    /*
    跳转至用户登录页login（User）
    * */
    @RequestMapping("/login")
    public String login(){
        return "/user/login";
    }



    /*
    实现注销当前用户的功能
    * */
    @RequestMapping("/quit")
    public String quit(HttpSession session){
        User user=(User)session.getAttribute("currentUser");
        session.removeAttribute("currentUser");
        return "/user/main";
    }

    /*
    *登录验证的controller层，填写完登录表单时，进入该方法
    * */
    @RequestMapping("/sign")
    public String sign(String phone, String password, HttpSession session){
//        User user = redisDao.get("u","user");
//        if(ObjectUtils.isEmpty(user)){
//            user = userService.validate(phone, password);
//            redisDao.set("u","user",user);
//        }
        User user=userService.validate(phone,password);
        if (user != null) {
            session.setAttribute("currentUser",user);
            return "/user/main";//成功则跳转到登陆成功页面
        }else{
            //清空session
            session.invalidate();
        }
        return "/user/login";//失败则依旧停留在登录页面
    }

    /*
    *实现用户注册功能
    * */
    @RequestMapping("/register")
    public String register(String phone,String familyname,String password,String gender){
        User user=new User();
        user.setPhone(phone);
        user.setFamilyname(familyname);
        user.setPassword(password);
        user.setGender(gender);
        userService.saveAndFlush(user);
        return "/user/success";
    }


    @RequestMapping("/estimate")//跳转到估价表单填写页面
    public String estimate(Model model){
        List<CarBrand> carBrandList=carBrandService.findAll();
        model.addAttribute("carBrandList",carBrandList);
        return "/user/estimate";
    }

    @RequestMapping("/startEstimate")//进行估价操作，同时将估价记录插入到estimate表中
    public String startEstimate(Integer carbrand,Integer carseries,Integer carmodel,String ownercity,
                                String regcity, String regdate, Double drivemiles, String contactway,
                                Integer userid,Model model){

        User user=userService.findByUserid(userid);
        Estimate estimate=new Estimate();

        estimate.setCarbrand(carBrandService.findByBrandid(carbrand).getBrandname());
        estimate.setCarseries(carSeriesService.findBySeriesid(carseries).getSeriesname());
        estimate.setCarmodel(carModelService.findByModelid(carmodel).getModelname());
        estimate.setModelid(carmodel);
        estimate.setOwnercity(ownercity);
        estimate.setRegcity(regcity);
        estimate.setRegdate(regdate);
        estimate.setDrivemiles(drivemiles);
        estimate.setContactway(contactway);
        estimate.setUser(user);
        estimateService.saveAndFlush(estimate);//向数据库中插入股价记录,同时更新estimate

        int estid=estimate.getEstid();
        model.addAttribute("estid",estid);
        model.addAttribute("estimate",estimate);

        Integer regyear=Integer.parseInt(regdate.substring(0,4));
        Integer regmonth=Integer.parseInt(regdate.substring(6,7));

        System.out.println("上牌时间："+regdate);
        System.out.println("上牌年份:"+regyear);
        System.out.println("上牌月份:"+regmonth);
        Date day=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowtime=simpleDateFormat.format(day);
        Integer nowyear=Integer.parseInt(nowtime.substring(0,4));
        Integer nowmonth=Integer.parseInt(nowtime.substring(6,7));
        Integer num=0;
        if(nowmonth>=regmonth){
            num=(nowyear-regyear)*12+(nowmonth-regmonth);
        }
        else{
            num=(nowyear-regyear)*12-(nowmonth-regmonth);
        }
        System.out.println("汽车使用总月份:"+num);

        String tempprice=carDetailService.findByModelid(carmodel).getFirmguideprice();
        Double guideprice=Double.parseDouble(tempprice);
        System.out.println("guideprice:"+guideprice);//市场价格
        Double price1;//车况优秀--低
        Double price2;//车况优秀--高
        Double price3;//车况良好--低
        Double price4;//车况良好--高
        Double price5;//车况正常--低
        Double price6;//车况正常--高
        /*
        **第一种估价算法：重置成本法
        * 二手车价格=新车价格*（180-已经使用的二手车月份）/180
        * 参考网站http://www.jnesc.com/escpg/448.html
        */
        BigDecimal b1=new BigDecimal(guideprice*(180-num)/180);
        price1=b1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        price2=price1+1.0;
        /*
         *第二种估价算法：粗略算法
         *第一年汽车贬值20%，从第二年开始每年贬值10%，也就是说第一年打八折，往后每年打九折，比较简单
         * 参考网站http://www.jnesc.com/escpg/448.html
        */

/*        if((nowyear-regyear)<1){
            BigDecimal b2=new BigDecimal(guideprice*0.85);
            price3=b2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            price4=price3+0.5;
        }else if((nowyear-regyear)>1&&(nowyear-regyear)<2){
            BigDecimal b2=new BigDecimal(guideprice*0.8);
            price3=b2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            price4=price3+0.5;
        }else if((nowyear-regyear)>2&&(nowyear-regyear)<3){
            BigDecimal b2=new BigDecimal(guideprice*0.8*0.9);
            price3=b2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            price4=price3+0.5;
        }else if((nowyear-regyear)>3&&(nowyear-regyear)<4){
            BigDecimal b2=new BigDecimal(guideprice*0.8*0.9*0.9);
            price3=b2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            price4=price3+0.5;
        }else if((nowyear-regyear)>4&&(nowyear-regyear)<5){
            BigDecimal b2=new BigDecimal(guideprice*0.8*0.9*0.9*0.9);
            price3=b2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            price4=price3+0.5;
        }else if((nowyear-regyear)>5&&(nowyear-regyear)<6){
            BigDecimal b2=new BigDecimal(guideprice*0.8*0.9*0.9*0.9*0.9);
            price3=b2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            price4=price3+0.5;
        }else{
            BigDecimal b2=new BigDecimal(guideprice*0.8*0.9*0.9*0.9*0.9*0.9);
            price3=b2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            price4=price3+0.5;
        }*/

        BigDecimal b3=new BigDecimal(price1*0.9);
        price3=b3.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

        BigDecimal b4=new BigDecimal(price1*0.95);
        price4=b4.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

        BigDecimal b5=new BigDecimal(price3*0.9);
        price5=b5.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

        BigDecimal b6=new BigDecimal(price3*0.95);
        price6=b6.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

        //将估价的价格分三段存入Model中
        model.addAttribute("price1",price1);//车况优秀--低价格
        model.addAttribute("price2",price2);//车况优秀--高价格
        model.addAttribute("price3",price3);//车况良好--低价格
        model.addAttribute("price4",price4);//车况良好--高价格
        model.addAttribute("price5",price5);//车况正常--低价格
        model.addAttribute("price6",price6);//车况正常--高价格
        model.addAttribute("guideprice",guideprice);

        /*
        * 折旧率计算公式：前三年每年折旧15%，中间四年每年折旧10%，最后三年每年折旧5%
        */
        BigDecimal bbone=new BigDecimal(guideprice*(1-0.15));
        double oney=bbone.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

        BigDecimal bbtwo=new BigDecimal(guideprice*(1-0.15)*(1-0.15));
        double twoy=bbtwo.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

        BigDecimal bbthree=new BigDecimal(guideprice*(1-0.15)*(1-0.15)*(1-0.15));
        double threey=bbthree.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

        model.addAttribute("oney",oney);
        model.addAttribute("twoy",twoy);
        model.addAttribute("threey",threey);
        return "/user/estimateResult";//返回估价结果页面
    }

    @RequestMapping("/toAppointCheck/{estid}")//跳转到预约工作人员检查汽车的页面
    public String toAppointSell(Model model, @PathVariable("estid") Integer estid){
        Estimate estimate=estimateService.findByEstid(estid);//按照estid查找估价记录
        model.addAttribute("estimate",estimate);
        User user=userService.findByPhone(estimate.getContactway());//按照手机号查找user
        model.addAttribute("user",user);//将查到的user存入model中
        List<Admin> adminList=adminService.findAll();
        model.addAttribute("adminList",adminList);//将所有的管理员查找出来
        return "/user/appointCheck";
    }

    @RequestMapping("/lookEstimateInfo/{phone}")//跳转到查看estimate信息的页面
    public String lookEstimateInfo(Model model,@PathVariable("phone") String phone){
        List<User> userList=new ArrayList<>();
        userList=userService.findAll();//得到所有用户信息的list
        model.addAttribute("userList",userList);

        User user=userService.findByPhone(phone);//利用手机号得到User
        List<Estimate> estimateList=user.getEstimateList();//利用user来得到estimate的信息list
/*        Estimate estimate=new Estimate();
        estimate.setUser(user);*/
        model.addAttribute("estimateList",estimateList);
        return "/user/temp";
    }

    @RequestMapping("/sendEmail")//向管理员(工作人员)发送预约检车的请求
    public String appointSell(Model model, String appointdate, String appointplace, Integer estid, Integer adminid){
        String ymd=appointdate.substring(0,10); //获取年月日
        String hm=appointdate.substring(11,16); //获取时分
        String newtime=ymd+' '+hm+":00";        //组装拼接年月日时分秒

        Admin admin = adminService.findByAdminid(adminid);//根据管理员id查询出管理员
        Estimate estimate = estimateService.findByEstid(estid);//根据估价记录id查出估价记录

        AppointCheck appointCheck=new AppointCheck();
        appointCheck.setAppointdate(newtime);//设置预约时间
        appointCheck.setAdmin(admin);//对应的工作人员
        appointCheck.setAppointplace(appointplace);//选择看车地点
        appointCheck.setConditionflag(0);//置状态为0（未完成）
        appointCheck.setWorkingflag(0);//置状态为0,状态为1表示正在办理中
        appointCheck.setDoneflag(0);//置状态为0，状态为1表示已归档
        appointCheck.setOrderflag(0);//置状态为0，状态为1表示已录入订单
        appointCheck.setEstimate(estimate);//对应的估价记录
        appointCheckService.saveAndFlush(appointCheck);//保存并刷新

        model.addAttribute("appointCheck", appointCheck);//预约记录
        model.addAttribute("admin", admin);//为您服务的管理员
        model.addAttribute("estimate", estimate);//估价记录
        return "/user/appointResult";
    }

    @RequestMapping("/showEstimate")//关联查询操作
    public String showEstimate(Model model, Pageable pageable,String estid,String carbrand,String contactway,Integer userid){
        pageable=new PageRequest(pageable.getPageNumber(),1);
        Page<Estimate> page = estimateService.findEstimatePage(pageable,estid,carbrand,contactway,userid);
        model.addAttribute("page",page);
        model.addAttribute("userid",userid != null && !"".equals(userid) ? userService.findByUserid(userid).getUserid():null);
        model.addAttribute("carbrand",carbrand != null && !"".equals(carbrand) ? carbrand:null);
        return "/user/UnionSelect";
    }

    @RequestMapping("/toAboutUs")//向管理员发送预约检测车辆的请求
    public String toAboutUs(){
        return "/user/aboutUs";
    }

    @RequestMapping("/test1/{estid}{phone}")//跳转到预约工作人员检查汽车的页面
    public String test1(Model model,@PathVariable("estid") Integer estid,@PathVariable("phone") String phone){
        User user=userService.findByPhone(phone);//按照手机号查找user
        model.addAttribute("user",user);//将查到的user存入model中
        Estimate estimate=estimateService.findByEstid(estid);
        model.addAttribute("estimate",estimate);
/*        List<Estimate> estimateList=new ArrayList<>();
        estimateList=estimateService.findAll();//查找所有的估价记录
        model.addAttribute("estimateList",estimateList);*/
/*        List<Estimate> estimateListByPhone=new ArrayList<>();
        estimateListByPhone=estimateService.findByContactWay(phone);//按照手机号查找所有的估价记录
        model.addAttribute("estimateListByPhone",estimateListByPhone);*/
        return "/user/appointSell";
    }


    @RequestMapping("/sellCar")//跳转到卖车页面
    public String sellCar(){
        return "/user/sellCar";
    }

    @RequestMapping("/carBaike")//跳转到卖车页面
    public String carBike(Model model){
        List<CarTip> carTipList=carTipService.findAll();
        model.addAttribute("carTipList",carTipList);
        return "/user/carBaike";
    }

    @RequestMapping("/personCenter")//跳转到个人中心
    public String personCenter(){
        return "/user/personCenter";
    }


    @RequestMapping("/checkPhone")
    public String checkPhone(String phone,HttpServletResponse httpServletResponse) throws IOException {
        if(userService.findByPhone(phone)==null) {
            PrintWriter print=httpServletResponse.getWriter();
            print.write("yes");
            print.flush();
            print.close();
        }else {
            PrintWriter print=httpServletResponse.getWriter();
            print.write("no");
            print.flush();
            print.close();
        }
        return "/user/register";
    }

    /**
     * 修改个人信息
     **/
    @RequestMapping("/updatePersonInfo")
    public String updatePersonInfo(String newpwd,Integer userid){
        User user=userService.findByUserid(userid);
        user.setPassword(newpwd);
        userService.saveAndFlush(user);
        return "/user/login";
    }

    /**
     * 我的收藏
     **/
    @RequestMapping("/lookCollect/{id}")
    public String lookCollect(Model model,@PathVariable("id") Integer id){
        List<Collect> collectList=collectService.findAllByUserid(id);
        model.addAttribute("collectList",collectList);
        return "/user/myCollect";
    }

    /**
     * 我买的车
     **/
    @RequestMapping("/lookBrought/{id}")
    public String lookBrought(Pageable pageable,Model model,@PathVariable("id") Integer userid){

        pageable = new PageRequest(pageable.getPageNumber(), 2);
        Page<WaitSellCar> page = waitSellCarService.findUserBroughtPage(pageable,userid);
        model.addAttribute("page", page);
        model.addAttribute("userid", userid);
        return "/user/myBrought";
    }


    /**
     * 我卖的车
     **/
    @RequestMapping("/lookSold/{id}")
    public String lookSold(Model model,@PathVariable("id") Integer id){
        List<WaitSellCar> waitSellCarList=waitSellCarService.findAllBySellid(id);
        if(waitSellCarList.size()!=0){
            String msg="您好，您卖过的车辆如下所示：";
            model.addAttribute("waitSellCarList",waitSellCarList);
            model.addAttribute("msg",msg);
        }else {
            String word="对不起，您还没有卖过汽车";
            model.addAttribute("word",word);
        }
        return "/user/mySold";
    }

    /**
     * 已卖过的车的细节
     **/
    @RequestMapping("/lookSoldDetail/{id}")
    public String lookSoldDetail(Model model,@PathVariable("id") Integer id) {
        WaitSellCar waitSellCar=waitSellCarService.findByWaitsellcarid(id);

        CarDetail carDetail=carDetailService.findByModelid(waitSellCar.getModelid());

        Integer inputorderid=waitSellCar.getInputorderid();
        InputOrder inputOrder=inputOrderService.findByInputorderid(inputorderid);

        model.addAttribute("waitSellCar",waitSellCar);
        model.addAttribute("carDetail",carDetail);
        model.addAttribute("dealprice",inputOrder.getInputprice());

        List<CarPic> carPicList=waitSellCar.getCarPicList();
        model.addAttribute("carPicList",carPicList);




        return "/user/soldDetail";
    }

    /**
     * 进入买车页面查看所有在售汽车信息
     **/
    @RequestMapping("/buyCar")
    public String buyCar(Model model,Pageable pageable,String brandname ){

        pageable = new PageRequest(pageable.getPageNumber(), 10);
        Page<WaitSellCar> page = waitSellCarService.findAllCar(pageable,brandname);
        model.addAttribute("page", page);
        model.addAttribute("brandname", brandname);
        return "/user/buyCar";
    }

    /**
     * 按照品牌搜索车辆
     **/
    @RequestMapping("/searchCar/{brand}")
    public String searchCar(Model model,@PathVariable("brand") String brand, Pageable pageable ){
        pageable = new PageRequest(pageable.getPageNumber(), 10);
        Page<WaitSellCar> page = waitSellCarService.findAllCar(pageable,brand);
        model.addAttribute("page", page);
        model.addAttribute("brandname", brand);
        return "/user/buyCar";
    }
    /**
     * 按照1万到5万价格搜索车辆
     **/
    @RequestMapping("/search1To5")
    public String search1To5(Model model, Pageable pageable ){
        double aa=1;
        double bb=5;
        pageable = new PageRequest(pageable.getPageNumber(), 10);
        Page<WaitSellCar> page = waitSellCarService.findAllCarByPrice(pageable,aa,bb);
        model.addAttribute("page", page);
        String brandname="";
        model.addAttribute("brandname", brandname);
        return "/user/buyCar";
    }

    /**
     * 按照5万到10万价格搜索车辆
     **/
    @RequestMapping("/search5To10")
    public String search5To10(Model model, Pageable pageable ){
        double aa=5;
        double bb=10;
        pageable = new PageRequest(pageable.getPageNumber(), 10);
        Page<WaitSellCar> page = waitSellCarService.findAllCarByPrice(pageable,aa,bb);
        model.addAttribute("page", page);
        String brandname="";
        model.addAttribute("brandname", brandname);
        return "/user/buyCar";
    }

    /**
     * 按照10万到15万价格搜索车辆
     **/
    @RequestMapping("/search10To15")
    public String search10To15(Model model, Pageable pageable ){
        double aa=10;
        double bb=15;
        pageable = new PageRequest(pageable.getPageNumber(), 10);
        Page<WaitSellCar> page = waitSellCarService.findAllCarByPrice(pageable,aa,bb);
        model.addAttribute("page", page);
        String brandname="";
        model.addAttribute("brandname", brandname);
        return "/user/buyCar";
    }

    /**
     * 按照15万到20万价格搜索车辆
     **/
    @RequestMapping("/search15To20")
    public String search15To20(Model model, Pageable pageable ){
        double aa=15;
        double bb=20;
        pageable = new PageRequest(pageable.getPageNumber(), 10);
        Page<WaitSellCar> page = waitSellCarService.findAllCarByPrice(pageable,aa,bb);
        model.addAttribute("page", page);
        String brandname="";
        model.addAttribute("brandname", brandname);
        return "/user/buyCar";
    }
    /**
     * 按照20万到50万价格搜索车辆
     **/
    @RequestMapping("/search20To50")
    public String search20To50(Model model, Pageable pageable ){
        double aa=20;
        double bb=50;
        pageable = new PageRequest(pageable.getPageNumber(), 10);
        Page<WaitSellCar> page = waitSellCarService.findAllCarByPrice(pageable,aa,bb);
        model.addAttribute("page", page);
        String brandname="";
        model.addAttribute("brandname", brandname);
        return "/user/buyCar";
    }

    /**
     * 按照50万以上价格搜索车辆
     **/
    @RequestMapping("/search50")
    public String search50(Model model, Pageable pageable ){
        double aa=50;
        pageable = new PageRequest(pageable.getPageNumber(), 10);
        Page<WaitSellCar> page = waitSellCarService.findAllCarByPriceMoreThan(pageable,aa);
        model.addAttribute("page", page);
        String brandname="";
        model.addAttribute("brandname", brandname);
        return "/user/buyCar";
    }

    /**
     *根据汽车id进入详情页
     **/
    @RequestMapping("/goCarDetail/{id}")
    public String goCarDetail(Model model,String brandname,@PathVariable("id") Integer id){
        WaitSellCar waitSellCar=waitSellCarService.findByWaitsellcarid(id);
        Integer modelid=waitSellCar.getModelid();
        CarDetail carDetail=carDetailService.findByModelid(modelid);
        List<CarPic> carPicList=waitSellCar.getCarPicList();

        model.addAttribute("carDetail",carDetail);
        model.addAttribute("carPicList",carPicList);
        model.addAttribute("waitSellCar",waitSellCar);

        return "/user/carDetail";
    }

    /**
     *按照品牌名搜索在售车辆
     **/
    @RequestMapping("/lookCarInSell/{id}")
    public String lookCarInSell(Model model,@PathVariable("id") Integer id) {

        return "redirect:/user/buyCar";
    }

    /**
     * 上传用户头像
     **/
    @RequestMapping(value = "/updatePic",method= RequestMethod.POST)
    public String updatePic(@RequestParam("headpic") MultipartFile file,Integer userid,Model model,HttpSession session){
        String fileName = file.getOriginalFilename();//获取文件名
        String filePath ="D:\\Program Files\\apache-tomcat-8.5.29\\webapps\\upload\\";//存到外置tomcat服务器
        try {

            File targetFile = new File(filePath);
            if(!targetFile.exists()){
                targetFile.mkdirs();//若目录不存在则创建目录
            }
            FileOutputStream out = new FileOutputStream(filePath+fileName);//创建一个向指定File对象表示的文件中写入数据的文件输出流。
            out.write(file.getBytes());//将 file的每个字节从指定 byte 数组写入此文件输出流中。
            out.flush();
            out.close();
            //修改图片
            User user=userService.findByUserid(userid);
            user.setHeadpic( "http://localhost:8081/upload/"+fileName);//将服务器路径存到数据库表中
            userService.saveAndFlush(user);
            session.setAttribute("currentUser",user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/user/updateSelfInfo";
    }


    /**
     * 生成用户预约看车记录，生成成功返回前台一个success，提醒用户预约成功
     **/
    @RequestMapping("/appointLookCar")
    @ResponseBody
    public String appointLookCar(Integer userid,Integer wscid) {
        Date day=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String aptdate=simpleDateFormat.format(day);//生成预约时间

        AppointLook appointLook=new AppointLook();

        appointLook.setUserid(userid);
        appointLook.setWscid(wscid);
        appointLook.setApttime(aptdate);
        appointLook.setOutputflag(0);

        appointLookService.saveAndFlush(appointLook);

        return "success";
    }

    /**
     * 实现收藏车辆的功能
     **/
    @RequestMapping("/collectCar")
    @ResponseBody
    public String collectCar(Integer userid,Integer wscid) {
        Collect collect=new Collect();
        collect.setUserid(userid);
        collect.setWscid(wscid);
        collectService.saveAndFlush(collect);
        return "success";
    }

    /**
     *  收藏的汽车的细节
     **/
    @RequestMapping("/lookCollectDetail/{id}")
    public String lookCollectDetail(Model model,@PathVariable("id") Integer id) {
        WaitSellCar waitSellCar=waitSellCarService.findByWaitsellcarid(id);
        CarDetail carDetail=carDetailService.findByModelid(waitSellCar.getModelid());
        Integer inputorderid=waitSellCar.getInputorderid();
        InputOrder inputOrder=inputOrderService.findByInputorderid(inputorderid);
        model.addAttribute("waitSellCar",waitSellCar);
        model.addAttribute("carDetail",carDetail);
        List<CarPic> carPicList=waitSellCar.getCarPicList();
        model.addAttribute("carPicList",carPicList);
        return "/user/collectDetail";
    }

    /**
     * 我卖的车
     **/
    @RequestMapping("/goUpdateInfo/{id}")
    public String goUpdateInfo(@PathVariable("id") Integer id,HttpSession session){
        User currentUser=userService.findByUserid(id);
        session.setAttribute("currentUser",currentUser);
        return "/user/updateSelfInfo";
    }
}
