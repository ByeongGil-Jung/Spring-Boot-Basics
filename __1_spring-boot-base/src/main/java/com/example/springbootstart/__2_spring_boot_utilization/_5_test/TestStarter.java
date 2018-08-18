package com.example.springbootstart.__2_spring_boot_utilization._5_test;

import com.example.springbootstart.ApplicationStarter;
import com.example.springbootstart.SpringBootStartApplication;
import org.springframework.boot.SpringApplication;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-05
 * Time: 오전 2:21
 */
/*

모든 내용은
spring-boot-only-test
모듈을 참조 ...

 */
public class TestStarter implements ApplicationStarter {

    @Override
    public void applicationStart(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootStartApplication.class);
        app.run(args);
    }
}
