package com.example.springbootstart.__2_spring_boot_utilization._2_external_settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-03
 * Time: 오후 5:32
 */
@Component
public class BeanPropertiesRunner implements ApplicationRunner {

    @Autowired
    BeanProperties beanProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n=== 2-2. External Settings (2) ===");
        System.out.println("\nIf using a bean converted properties");
        System.out.println("(bean) name : " + beanProperties.getName());
        System.out.println("(bean) price : " + beanProperties.getPrice());
        System.out.println("(bean) fullName : " + beanProperties.getFullName());
    }
}
