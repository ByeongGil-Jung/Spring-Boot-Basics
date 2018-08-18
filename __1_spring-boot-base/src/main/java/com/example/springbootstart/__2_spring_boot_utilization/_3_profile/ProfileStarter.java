package com.example.springbootstart.__2_spring_boot_utilization._3_profile;

import com.example.springbootstart.ApplicationStarter;
import com.example.springbootstart.SpringBootStartApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-03
 * Time: 오후 6:14
 */

/*

[ 프로파일(Profile) ]

- @Profile 애노테이션은 어디에 ?
:: 어떤 특정한 profile 에서만 특정한 bean 을 등록하고 싶다거나,
특정한 profile 에서만 동작을 다르게 하고 싶다거나 하는 등등

>> 그렇다면 profile 이 Spring 을 어떤 조건 하에서 구동시키는 지에 대한 세팅 switch 같은 건가 ?

이 때, 원하는 profile 에서 실행하기 위해선,
application.properties 에서

>> spring.profiles.active=profile 이름

을 입력시켜 줘야한다.

//////////////////////////////////////////////////////////////////////////////////

- 또한, Profile 용 properties 를 만들 수 있다.
여기선, application-prod.properties 로 만듦.

(application-'profile 명'.properties
의 파일 생성을 통해 원하는 profile 에서의 properties 를 사용할 수 있음.)

-> 왜냐하면 profile 에서 사용하기 위해 만든 properties 가 우선 순위가 더 높기 때문.

- 또한,
>> spring.profiles.include='profile 명'
을 입력 시킴으로써 다른 profile 을 properties 파일에 추가 할 수 있다.

이 때, application-include1.properties 를 보면 알겠지만,
include 시킨다면 이전의 property 에 override 되어 덮어 씌워진다.
새로운 property 라면, 새로운 값으로 적용한다.

(만약, override 할 값이 없거나, 새로운 값이 없다면 default 값으로 적용된다. (int 라면 0 이 입력됨))

 */
public class ProfileStarter implements ApplicationStarter {

    @Override
    public void applicationStart(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootStartApplication.class);
        app.run(args);
    }
}
