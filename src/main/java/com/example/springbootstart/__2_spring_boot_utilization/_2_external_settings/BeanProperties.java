package com.example.springbootstart.__2_spring_boot_utilization._2_external_settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

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

///////////////////////////////////////////////////////////////////////////

- @DurationUnit :: 시간 정보를 받을 수 있는 타입 (Spring Boot 가 제공하는 독특한 타입임)

- @Validate :: property 의 값을 검증할 수 있음

 */
@Component
@ConfigurationProperties("chicken")
@Validated
public class BeanProperties {

    // 비어있으면 에러를 뱉게 하는 validation
    @NotEmpty
    private String name;

    // min, max 값 사이에 있지 않으면 에러를 뱉게 하는 validation
    @Size(min=0, max=8000)
    private int price;

    private String fullName;

    /*
    @DurationUnit(ChronoUnit.SECONDS)
    을 붙인다면 application.properties 에서 단순히 숫자만 입력해도 된다.

    하지만 붙이지 않고 그냥 사용할 수도 있는데, 이럴 경우
    -> application.properties 에서
    30s
    와 같이 뒤에 시간 단위를 붙여야 한다 !
     */
    // @DurationUnit(ChronoUnit.SECONDS)
    private Duration sessionTimeout = Duration.ofSeconds(30);

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

    public Duration getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Duration sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }
}
