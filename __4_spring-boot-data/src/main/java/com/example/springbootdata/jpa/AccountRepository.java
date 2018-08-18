package com.example.springbootdata.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-13
 * Time: 오후 5:03
 */
// JpaRepository<T, id> :: T == Entity Class / id == primary key
public interface AccountRepository extends JpaRepository<Account, Long> {

    /*
    만약 @Query 어노테이션을 주면 Native Query 를 줄 수 있다.
    @Query(nativeQuery = true, value = "SELECT * FROM springuser WHERE name = '{0}'")
    (하지만 기본값은 JPQL 이다. (nativeQuery 가 아니다.))
    */
    Account findByUsername(String username);

    /*
    또한, Optional<T> 의 형태로 만들 수 있다.
     */
    Optional<Account> findByPassword(String password);
}
