package com.example.springbootdata.mariadb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-13
 * Time: 오전 6:15
 */
@Component
public class MariaDBRunner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        PreparedStatement pstmt;
        String sql;

        try (Connection connection = dataSource.getConnection()) {
            System.out.println("\n [ MariaDB Runner ]");
            // DBCP 를 사용하고 있는가 ?
            System.out.println("DBCP : " + dataSource.getClass());
            // URL 확인
            System.out.println("URL : " + connection.getMetaData().getURL());
            // username 확인
            System.out.println("username : " + connection.getMetaData().getUserName());

            sql = "DROP TABLE IF EXISTS springuser";
            pstmt = connection.prepareStatement(sql);
            if (pstmt.executeUpdate() == 0)
                System.out.println("- (MariaDB) Table drop successful : springuser");

            sql = "CREATE TABLE springuser(\n" +
                    "\tid INTEGER NOT NULL,\n" +
                    "\tname VARCHAR(255),\n" +
                    "\t\n" +
                    "\tPRIMARY KEY (id)\n" +
                    "\t)";
            pstmt = connection.prepareStatement(sql);
            if (pstmt.executeUpdate() == 0) {
                System.out.println("- (MariaDB) Table creation successful : springuser");
            }

            sql = "INSERT INTO springuser (id, name)\n" +
                    "VALUES (1, 'bk')";
            pstmt = connection.prepareStatement(sql);
            if (pstmt.executeUpdate() == 1) {
                System.out.println("- (MariaDB) Insertion into table successful");
            }
        }
    }
}
