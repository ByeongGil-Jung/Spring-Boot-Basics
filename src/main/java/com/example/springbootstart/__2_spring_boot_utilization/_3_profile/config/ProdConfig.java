package com.example.springbootstart.__2_spring_boot_utilization._3_profile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-03
 * Time: 오후 6:43
 */

/*

profile 이 "production" 일 때만 사용 된다.
("production" 이 아닐 경우에는 이 bean 이 사용이 안된다.)

 */

@Profile("prod")
@Configuration
public class ProdConfig {

    @Bean
    public String hello() {
        return "(prod) hello";
    }
}
