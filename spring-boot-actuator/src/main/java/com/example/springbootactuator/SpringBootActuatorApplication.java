package com.example.springbootactuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*

[ 1. Actuator 란 ]

 :: Spring Boot 는 Application 운영 환경에서 유용한 기능을 제공한다. -> Actuator
 Actuator 가 제공하는 endpoints 와 metrics(외부 모니터링 app 과 연동), 그리고 그 데이터를 활용하는 모니터링 등을 사용할 수 있다.

 - 의존성 추가 (spring-boot-starter-actuator)
 - Application 의 각종 정보를 확인할 수 있는 Endpoints
   - 다양한 Endpoints 제공
 (https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-endpoints-enabling-endpoints)


 >> /actuator 로 접속
 (아무 설정 안했다면 self, heath, info 세가지 정보밖에 뜨지 않는다.
  -> 즉, 활성화의 여부와, endpoint 의 공개 여부에 따라 보여지는 정보가 달라진다.)

  * 활성화의 여부 :
    (하지만 기본적으로 밑의 설정은 다 되어있다. -> 즉, 이미 대부분은 활성화 되어 있단 의미)

    application.properties 에서
    >> management.endpoint.shutdown.enabled=true

    >> management.endpoints.enabled-by-default=false
    >> management.endpoint.{endpoint 이름}.enabled=true


  * 하지만 기본적으로 모든 endpoint 는 활성 되어 있다.
  (web 에서 안보이는 이유는 expose 를 안해서 그런 것 !! )

  ==> JMX 는 기본적으로 expose 가 되어 있다.
      WEB 에서는 health, info 만 expose 되어 있다 ! (즉, HTML 에선 health, web 만 볼 수 있다.)
      (보안적인 이슈 때문)


  * 공개하기 위해선,
    >> management.endpoints.web.exposure.include=*
    >> management.endpoints.web.exposure.exclude=env,beans

    의 설정을 주어야 한다 !

 - JConsole
 - VisualVM
 - HTTP

 */

@SpringBootApplication
public class SpringBootActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootActuatorApplication.class, args);
    }
}
