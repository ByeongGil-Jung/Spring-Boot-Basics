package com.example.springbootstart.__2_spring_boot_utilization._3_profile;

import com.example.springbootstart.__2_spring_boot_utilization._2_external_settings.BeanProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-03
 * Time: 오후 6:50
 */
@Component
public class ProfileSimpleRunner implements ApplicationRunner {

    @Autowired
    private String hello;

    @Autowired
    private ProfileBeanProperties profileBeanProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n=== 2-3. Profile ===");
        System.out.println("Hello value : " + hello);
        System.out.println("(bean) name : " + profileBeanProperties.getName());
        System.out.println("(bean) price : " + profileBeanProperties.getPrice());
        System.out.println("(bean) weight : " + profileBeanProperties.getWeight());
    }
}
