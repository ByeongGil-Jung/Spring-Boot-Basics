package com.example.springbootdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*

[ 1. In-memory Database ]

 - 지원하는 In-memory Database
   - H2
   - HSQL
   - Derby

 - Spring-JDBC 가 classpath 에 있으면 자동 설정이 필요한 bean(아래 것들) 을 설정 해준다.
   - DataSource
   - JdbcTemplate

 (1) DataSource
   - In-memory Database 의 기본 연결 정보 확인하는 방법 ?
     - URL : "testdb"
     - username : "sa"
     - password : ""

   - H2 Console 사용하는 방법 ? (택 1)
     1. spring-boot-devtools 추가
     2. application.properties 에 spring.h2.console.enabled=true 추가

     이후 /h2-console 로 접속 (path 역시 변경 가능)


 (2) JdbcTemplate
   - JdbcTemplate 에선 try, catch, finally 까지의 모든 과정이 잘 처리되어 있다.
     (따라서 간결한 코드 작성 가능)
   - Error 레이어를 세분화시켰기에 좀 더 섬세하게 error 를 볼 수 있다.

*/

@SpringBootApplication
public class SpringBootDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataApplication.class, args);
    }
}
