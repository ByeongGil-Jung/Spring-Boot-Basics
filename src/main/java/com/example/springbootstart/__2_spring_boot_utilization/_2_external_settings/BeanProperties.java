package com.example.springbootstart.__2_spring_boot_utilization._2_external_settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-03
 * Time: 오후 5:15
 */

/*

property 를 bean 으로 등록한다는 어노테이션을 붙여줘야 한다.
>> @ConfigurationProperties("name")

또한 getter, setter 를 입력 해주어야 한다.

// 이후 강의 나오면 공부

 */
@Component
@ConfigurationProperties("chicken")
public class BeanProperties {

    private String name;
    private int price;
    private String fullName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
