package com.example.springbootdata.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-13
 * Time: 오후 5:06
 */

// 여기선 Slicing Test 로 만들 것임. (In-memory Database 가 반드시 필요하다. -> 여기선 H2 를 테스트용으로 사용)
// -> Repository 와 관련된 bean 들만 등록해서 test 를 만드는 것임 (repository 포함)
@RunWith(SpringRunner.class)
@DataJpaTest // Slicing Test 용 어노테이션
/*
@SpringBootTest 를 통해 Test 할 수 도 있는데,
이는 모든 bean 설정을 사전에 스캔하기 때문에 H2 가 아닌 MariaDB 로 테스트를 하게 된다.
-> 이 방법은 테스트에서 데이터 변화가 일어날 경우, 실제 Application 의 데이터에도 변화가 일어난다.
-> 따라서 In-memory DB 인 H2 를 이용하는 것이 테스트 상 안전하다.
*/
public class AccountRepositoryTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void di() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("Class : " + metaData.getClass());
            System.out.println("Driver Name : " + metaData.getDriverName());
            System.out.println("URL : " + metaData.getURL());
            System.out.println("Username : " + metaData.getUserName());
        }

        // DB 에 넣는 Test
        Account account = new Account();

        account.setUsername("Pizza");
        account.setPassword("1234");

        // AccountRepository 를 사용해서 저장
        Account saveAccount = accountRepository.save(account);

        // value 가 있는지 확인
        assertThat(saveAccount).isNotNull();

        // userName 을 통해 쿼리문을 조회할 수 있는데, 임의로 만들 수 있다.
        // 올바른 데이터일 경우
        Account existingAccount = accountRepository.findByUserName(saveAccount.getUsername());
        assertThat(existingAccount).isNotNull();

        // 올바르지 않은 데이터일 경우
        Account nonExistingAccount = accountRepository.findByUserName("Chicken");
        assertThat(nonExistingAccount).isNull();
    }
}