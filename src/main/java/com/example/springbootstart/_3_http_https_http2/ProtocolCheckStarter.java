package com.example.springbootstart._3_http_https_http2;

import com.example.springbootstart.ApplicationStarter;
import com.example.springbootstart.SpringBootStartApplication;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-02
 * Time: 오후 7:59
 */
/*
1. https 사용 (9090)
- 우선 ssl key 를 만들어야 함 (keytool.exe 활용)

 [Window 의 경우]
(1) jdk 설치 폴더의 bin 으로 이동
(2) ./keytool -genkey -alias spring -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore
C:\Users\계정이름\키 이름.keystore -validity 4000
 의 command 를 입력하여 연습용 키 생성 (PKCS12 는 key type 의 종류 중 하나이다.)

 Alias(별칭)
 Password(비밀번호)
 confirm(비밀번호 확인)
 Validity(기간)


2. http 사용
- http 1.1 을 사용한다.
- 이 때 위의 https 와 다른 connector 를 생성하여, 서로 다른 port 로 진입하게 만든다. (8080)


 */
@RestController
public class ProtocolCheckStarter implements ApplicationStarter {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring";
    }

    //새로운 http 1.1 tomcat connector 를 만든다.
    @Bean
    public ServletWebServerFactory serverFactory() {
        // connector 가 모여있는 server factory 를 생성
        TomcatServletWebServerFactory tomcatFactory = new TomcatServletWebServerFactory();
        // http 1.1 과 연결시켜 줄 새로운 tomcat connector 를 받고
        tomcatFactory.addAdditionalTomcatConnectors(createStandardConnector());
        // server factory 를 return
        return tomcatFactory;
    }

    private Connector createStandardConnector() {
        // http 1.1 의 protocol 을 연결시켜주는 url
        String protocol = "org.apache.coyote.http11.Http11NioProtocol";
        Connector connector = new Connector(protocol);
        connector.setPort(8080);
        // 8080 와 연결된 http 1.1 protocol connector 를 return
        return connector;
    }

    @Override
    public void applicationStart(String... args) {
        SpringApplication.run(SpringBootStartApplication.class, args);
    }
}
