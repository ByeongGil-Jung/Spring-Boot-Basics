package com.example.springbootdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*

[ 1. In-memory Database ]

 - 지원하는 In-memory Database
   - H2
   - HSQL
   - Derby

 - Spring-JDBC (아래 것들) 가 classpath 에 있으면 자동 설정이 필요한 빈을 설정 해준다.
   - DataSource
   - JdbcTemplate

*/

@SpringBootApplication
public class SpringBootDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataApplication.class, args);
    }
}
