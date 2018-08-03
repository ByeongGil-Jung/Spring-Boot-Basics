package com.example.springbootstart.__2_spring_boot_utilization._2_external_settings;

import com.example.springbootstart.ApplicationStarter;
import com.example.springbootstart.SpringBootStartApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-03
 * Time: 오후 2:10
 */

/*

[ 외부 설정 - 1 ]

1. 사용할 수 있는 외부 설정
 - properties
 - YAML
 - 환경 변수
 - 커맨드 라인 arguments

2. Property 우선 순위
 (1) 유저 홈 directory 에 있는 spring-boot-dev-tools.properties
 (2) 테스트에 있는 @TestPropertySource
 (3) @SpringBootTest 애노테이션의 properties 애트리뷰트
 (4) 커맨드 라인 arguments
 (5) SPRING_APPLICATION_JSON (환경 변수 또는 시스템 프로퍼티) 에 들어있는 property
 (6) ServletConfig 파라미터
 (7) ServletContext 파라미터
 (8) java:comp/env JNDI 애트리뷰트
 (9) System.getProperties() 자바 시스템 프로퍼티
 (10) OS 환경변수
 (11) RandomValuePropertySource
 (12) jar 밖에 있는 특정 프로파일용 application properties
 (13) jar 안에 있는 특정 프로파일용 application properties
 (14) jar 밖에 있는 application properties
 (15) jar 안에 있는 application properties
 (16) @PropertySource

 >> 모든 value 들은 override 해서 사용할 수 있다.

3. application.properties 를 다른 곳에다 둘 수 있다.
(default 는 resources 폴더 아래이다.)
(참고로, classpath 는 source 파일이 있는 곳이다. - main 밑의 resources)

 - applcation.properties 우선 순위 (높은 것이 낮은 것을 덮어 씌운다.)
   (1) file:./config/
   (2) file:./
   - root 에 만드는 경우
   (3) classpath:/config/
   (4) classpath:/
   - (default) main(test)/resource 에 만드는 경우. (:: 현재 기본으로 존재하는 applcation.properties 임)

 */

/*

[ 외부 설정 - 2 ]

- 외부 설정이 많은 경우엔, 하나로 묶어서 bean 으로 등록 할 수 있다.
(PizzaProperties)

@ConfigurationProperties 을 사용할 수 있게끔 하는 (빈으로 등록 시켜주는) 어노테이션인
@EnableConfigurationProperties(PizzaProperties.class) 을 붙여 줘야하지만,
이미 @SpringBootApplication 에 자동으로 등록이 돼 있다.

 */


public class ExternalSettingsStarter implements ApplicationStarter {

    @Override
    public void applicationStart(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootStartApplication.class);
        app.run(args);
    }
}
