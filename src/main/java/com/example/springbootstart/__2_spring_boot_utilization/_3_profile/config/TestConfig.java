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

profile 이 "test" 일 때만 사용 된다.

 */

@Profile("test")
@Configuration
public class TestConfig {

    @Bean
    public String hello() {
        return "(test) hello";
    }
}
