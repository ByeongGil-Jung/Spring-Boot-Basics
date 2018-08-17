package com.example.springbootactuator;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
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


  * WEB 에서 expose 하기 위해선,
    >> management.endpoints.web.exposure.include=*
    >> management.endpoints.web.exposure.exclude=env,beans

    의 설정을 주어야 한다 !
    (하지만 보안상의 이슈가 되므로 되도록 하지 않는 것이 좋다.)


 [ 2. Actuator - JMX 와 HTTP ]

 :: JMX 를 통해서 actuator 가 제공하는 expose 된 endpoints 를 볼 수 있다.

 - JConsole
   (java 에서 제공하는 기본적인 console)
   >> command 에 jconsole 입력.
      (윈도우는 환경 변수 설정되어 있어야 함)

   >> endpoints 를 비롯하여, cpu 사용량, thread 사용량 등등 ...
      jvm 관련 다양한 정보들을 볼 수 있음.
      (나중에 써먹을 것 !)

 - VisualVM
   (JCondole 보다 눈으로 보기 편함. 게다가 plugin 을 지원함.
    그런데 개인적으로 JConsole 과 별다른 차이가 없다. (있다 해도 칼라라는 점)
    게다가 java 10 부터 지원을 안해서 따로 깔아야 함 ... )


 - HTTP
    >> management.endpoints.web.exposure.include=*
    >> management.endpoints.web.exposure.exclude=env,beans

    의 설정을 주고 /actuator 접속
    (하지만 보안상의 이슈가 되므로 되도록 하지 않는 것이 좋다.)


[ 3. Spring Boot Admin ]

  :: Spring 에서 제공하는 application 이 아닌,
     제 3자가 따로 만든 오픈소스 web tool 이다.


 1. Admin 서버 설정
   :: Admin 서버 역할을 할 application 이 필요하다.
      (여기선 현재 프로젝트를 사용할 것)

   (1) 의존성 주입
    >> compile('de.codecentric:spring-boot-admin-starter-server:2.0.2')

   (2) 어노테이션 설정
    ServerApplication 에 @EnableAdminServer 어노테이션을 붙어야 한다.

   포트는 겹치면 안되므로 일단 8008 로 바꾸도록 한다 ...


 2. 클라이언트 설정
   (여기선 spring-boot-web-mvc 프로젝트를 사용할 것)

   (1) 의존성 주입
    >> compile('de.codecentric:spring-boot-admin-starter-client:2.0.2')

   (2) 설정 방법 (택 1)
    - 1. 클라이언트 library 를 사용해서 설정 (위의 dependency 를 이용 하여 property 값 수정)
    - 2. spring cloud 사용해서 discovery 되도록 사용

    여기선 '1. 클라이언트' 방법 사용.
    (-> spring-boot-start 프로젝트의 application.properties 참조)

    이후 application.properties 설정.

   (3) client 의 endpoints 정보가 출력되어야 하므로, expose 시킨다.
       (하지만 보안상의 이슈가 있으므로 반드시 security 설정을 해 놓는다.)

     >> management.endpoints.web.exposure.include=*

   (4) client application 이 접속할 admin server 의 주소를 설정한다.

     >> spring.boot.admin.client.url=http://localhost:8008


 - JMX 나 일반 HTML 에서 보이는 것보다 훨씬 이쁘게 보인다.
 - 역시 보안상의 이슈가 있으므로 security 설정을 해주어야 한다.
 - app 작동 중에 log level 등을 실시간으로 바꿀 수 있다.

 (원리는, jolokia 를 이용해서 JMX 를 HTTP 에서 볼 수 있게 한 것이다.)

 */

@SpringBootApplication
@EnableAdminServer
public class SpringBootActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootActuatorApplication.class, args);
    }
}
