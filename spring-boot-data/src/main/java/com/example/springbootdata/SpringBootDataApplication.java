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


[ 2. DBCP 및 MariaDB ]

 - DBCP 란 ? (Database Connection Pool)
   :: Tomcat 과 같은 Container 에서 Connection 을 미리 여러개 만들어 놓고,
      Application 이 필요로 할 때마다, 미리 만들어진 Connection 을 사용하는 개념

   - 몇 개를 만들고, 얼마 동안 유지하고, 어떤 상황에서 error 을 던질 것인지 다양한 설정 가능하다.
   - DB performance 에 큰 영향을 주고, 디버깅이 힘들기에 다룰 때 매우 중요하다.

 - HikariCP 란 ?
   :: Spring Boot 는 기본적으로 HikariCP 를 사용한다.
   (Tomcat CP, Commons DBCP2 등이 있어도 default 는 HikariCP 이다.)

   (설정 설명)
   - autoCommit
     :: .commit() 을 하지 않아도 기본적으로 commit 을 적용시킨다. (default : True)

   - connectionTimeout
     :: DBCP 에서 '얼마 동안' Connection 객체를 application 에 전달 못하면 error 를 발생시킬 것인가 ?
     (default : 30s, 짧을 수록 error 가 날 확률이 높아짐)

   - maximumPoolSize (중요)
     :: Connection 객체를 몇 개를 생성할 것인가 ? (default : 10 개)
     (동시에 일을 할 수 있는 connection 갯수는 cpu 의 core 갯수랑 똑같다.
      -> 만약 core 갯수를 넘어가는 connection 이 존재한다면 '대기' 를 하게 된다.
      -> 보통 core 갯수보다 약간 많게 설정한다.
      따라서 컴퓨터의 상황에 맞춰 유동적으로 갯수를 설정해야 한다.)

   (설정 사용법 ?)
   application.properties 에서
    >> spring.datasource.hikari.maximum-pool-size=4
   와 같이 설정한다.

*/

@SpringBootApplication
public class SpringBootDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataApplication.class, args);
    }
}
