package com.example.springbootdata.h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-12
 * Time: 오전 10:06
 */
@Component
public class H2Runner implements ApplicationRunner {

    // DataSource bean 주입
    @Autowired
    DataSource dataSource;

    // JDBC bean 주입
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        PreparedStatement pstmt;
        String sql;

        // 아래와 같이 connection 생성을 try 로 묶어주면,
        // finally 에서 close() 를 안해줘도 될 뿐만 아니라, 각종 에러를 자동으로 잡아준다.
        try (Connection connection = dataSource.getConnection()) {
            // Transaction 적용
            connection.setAutoCommit(false);

            System.out.println("\n [ H2 Runner ]");
            // URL 확인
            System.out.println("URL : " + connection.getMetaData().getURL());
            // username 확인
            System.out.println("username : " + connection.getMetaData().getUserName());

            // CREATE TABLE
            sql = "CREATE TABLE \"user\"(\n" +
                    "\tid INTEGER NOT NULL,\n" +
                    "\tname VARCHAR2(255),\n" +
                    "\t\n" +
                    "\tPRIMARY KEY (id)\n" +
                    "\t)";

            pstmt = connection.prepareStatement(sql);
            if (pstmt.executeUpdate() == 0)
                System.out.println(":: Succeed creating table");

            // connection 생성을 try 로 묶어줘서 아래와 같이 close() 는 불필요함
            // connection.close();
        }

        // - 아래서부턴 JdbcTemplate 의 INSERT 테스트
        // JdbcTemplate 에선 try, catch, finally 까지의 모든 과정이 잘 처리되어 있다.
        // (따라서 간결한 코드 작성 가능)
        // 또한 Error 레이어를 세분화시켜 좀 더 섬세하게 error 를 볼 수 있다.
        sql = "INSERT INTO \"user\" (id, name)\n" +
                "VALUES (1, 'bk')";
        jdbcTemplate.execute(sql);
    }
}
