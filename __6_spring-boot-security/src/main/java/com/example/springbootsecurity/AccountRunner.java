package com.example.springbootsecurity;

import com.example.springbootsecurity.account.Account;
import com.example.springbootsecurity.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-15
 * Time: 오후 11:20
 */
// 여기선 login 인증을 할 수 있는 user 를 하나 만들 것.
@Component
public class AccountRunner implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account user = accountService.createAccount("pizza", "1234");
        System.out.println(String.format("id: %d ,username : %s, password : %s",
                user.getId(), user.getUsername(), user.getPassword()));

    }
}
