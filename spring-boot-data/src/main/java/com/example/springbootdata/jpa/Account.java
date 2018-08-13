package com.example.springbootdata.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-13
 * Time: 오후 4:52
 */
@Entity
public class Account {

    // @Id 는 primary key 를 의미한다
    // @GeneratedValue 는 자동으로 생성되는 값으로,
    // repository 를 통해 저장할 때 자동으로 생성된 값으로 저장하게 된다.
    @Id @GeneratedValue
    private Long id;

    private String username;

    private String password;

    // 만약 Lombok 을 사용하면 아래와 같은 getter, setter 작업은 안해도 된다.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(username, account.username) &&
                Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
