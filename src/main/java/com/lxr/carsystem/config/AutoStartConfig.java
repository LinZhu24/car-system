package com.lxr.carsystem.config;

import com.lxr.carsystem.tool.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: LinXueRui
 * @Date: 2019/7/5 16:26
 * @Desc: 项目启动后，自动打开地址
 * 参考链接：https://blog.csdn.net/weixin_41496975/article/details/87925361
 */
@Component
public class AutoStartConfig implements ApplicationRunner {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    @Value("${spring.web.loginUrl}")
    private String loginUrl;

    @Value("${spring.web.googleExecute}")
    private String googleExecutePath;

    @Value("${spring.web.isOpenUrl}")
    private boolean isOpen;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        application();
    }

    /**
     * 启动项目后自行启动该方法（可扩展：比如根据项目运行环境，调用不同的方法）
     */
    private void application() {
        if (isOpen){
            String cmd = googleExecutePath +" "+ loginUrl;
            logger.info("浏览地址：" + cmd);
            Runtime run = Runtime.getRuntime();
            try{
                run.exec(cmd);
                logger.info("启动浏览器打开项目成功");
            }catch (Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
    }

}
