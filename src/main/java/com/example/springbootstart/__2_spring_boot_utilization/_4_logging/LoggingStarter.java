package com.example.springbootstart.__2_spring_boot_utilization._4_logging;

import com.example.springbootstart.ApplicationStarter;
import com.example.springbootstart.SpringBootStartApplication;
import org.springframework.boot.SpringApplication;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-04
 * Time: 오전 2:03
 */

/*

[ 로깅(Logging) - 1부 ]

Spring Boot 는 기본적으로
'Commons Logging' 을 사용한다.
이를 SLF4j 혹은 Log4j2 라는 logging 라이브러리로 변환하고,
다시 LogBack 으로 반환한다.

< 과거 변천사 >
(Commons Logging 은 불안정하고, SLF4j 는 간편하고 안정적이다.
Spring 프레임워크를 만들 땐 SLF4j 가 없어서 Commons Logging 을 사용하였다.

이전에는 Commons Logging 을 exclude 하고 SLF4j 를 넣을 수 있었다.

이후 Spring 5 가 되면서 Spring-JCL 라는 모듈이 들어왔고,
Commons Logging -> SLF4j or Log4j2
로 변환하도록 만들어져있다.

이후 LogBack 으로 넘긴다.)

즉,
Commons Logging -> SLF4j / Log4j2 -> LogBack

따라서, Spring 을 돌렸을 때 나오는 log 는
모두 LogBack 이 찍은 것이다.

/////////////////////////////////////////////////////////////////////////////

- 디버그 모드 :
(하지만 container, Hibernate, Spring Boot 만 debug 로 찍는다.)
>> VM options : -Ddebug

- 전체 디버그 모드 :
>> VM options : -Dtrace

- 컬러 출력 :
(application.properties 에서)
>> spring.output.ansi.enabled=always

- 파일 출력 :
(기본은 console 에만 출력되고, 이를 따로 파일로 저장하고 싶을 때,)
(application.properties 에서)
>> logging.path=logs // logs 라는 폴더에 spring.log 로 만들어 진다.
>> logging.file=my.log // my.log 라는 이름으로 만들어진다.

-> 10Mb 까지 새로운 것만 기록한다.

- 로그 레벨 조정 :
(log level 을 패키지 마다 설정 가능하다.)
(application.properties 에서)
>> logging.level.패키지주소=DEBUG

이후에 runner 등과 같이 돌아가는 클래스에서,

>> private Logger logger = LoggerFactory.getLogger(클래스명.class);
...
>> logger.debug("==============");
>> logger.debug("hello");
>> logger.debug(property.getName());
>> logger.debug(property.getPrice());
>> logger.debug("==============");

와 같이 입력하면,
LogBack 에 찍히게 된다.
(내용은 조금 뒷부분에 message 부분에 찍한다.)

*/

/*

[ 로깅(Logging) - 2부 ]

- 만약, log 에 관해 좀 더 섬세한 control 을 하고 싶다면 ??
>> logback-spring.xml
을 resources 폴더에 생성해서 사용한다.

<logger name="패키지 주소" level="DEBUG 와 같은 로그 레벨"/>

(매우 추천 !! -> Spring 에서 다양한 method 들을 지원함)


- 그렇다면 LogBack 이 아닌, Log4j2 로 바꾸는 방법 ?
build.gradle 에서
spring-boot-starter-logging 을 exclude 하고,
spring-boot-starter-log4j2 를 의존성 주입한다.

 */
public class LoggingStarter implements ApplicationStarter {

    @Override
    public void applicationStart(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootStartApplication.class);
        app.run(args);
    }
}
