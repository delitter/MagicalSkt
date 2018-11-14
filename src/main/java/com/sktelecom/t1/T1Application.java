package com.sktelecom.t1;

import com.sktelecom.t1.Settings.PageSettings;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@MapperScan("com.sktelecom.t1.Daos")
public class T1Application {
    @Autowired
    private PageSettings pageSettings;

    @Value("${page.content}")
    private String content;

    @RequestMapping(value="/",method= RequestMethod.GET)
    public String index(){
        return pageSettings.getTitle() + "</br>" + content;
    }
    public static void main(String[] args) {
        /*
        关闭banner
        SpringApplication app = new SpringApplication(T1Application.class);
        app.setShowBanner(false);
        app.run(args);
         */

        SpringApplication.run(T1Application.class, args);
    }
}
