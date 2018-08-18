package com.example.springbootsecurity.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-15
 * Time: 오후 10:43
 */
/*

AccountService 에선,
Repository 를 bean 으로 받아와서 method 를 두 개 만들 것.

 1. 새로운 Account 를 저장하는 method
   - Account 를 return 하여 새로 만든 객체를 바로 활용할 수 있도록 만듦.

 2. UserDetailsService 구현 (아주 중요함 !!)
   - implements UserDetailsService 를 통해 구현 가능
     (이를 통해, spring-boot 가 기본으로 생성하는 user 를 더 이상 만들지 않게 함)

   - loadUserByUsername method 를 @Override 를 함
     (원래 login-form 에서 username 과 password 의 입력값은 모두 이 method 로 들어오게 돼 있음.
      그 뒤 UserDetails 에서 실제 유저 정보를 확인 한 뒤,
      입력한 password 와 저장된 password 를 서로 확인하고 인증 검사를 한다.

      이후 UserDetails 로 변환하는 과정이다.)

   >> 여기서 UserDetails 라는 interface 는 개개인의 application 마다 제각각 구현되어 있는
      user 정보 (여기선 Account) 들의 interface 이다.
      이를 spring-security 가 제공을 해준다. (-> User 로 제공 해준다.)

      >> User("username", "password", authorities())
        (authorities() 는 자동 생성 할 수 있다. -> ROLE_USER 라는 권한을 가진다고 명시 해줘야 한다.)

 */
@Service
public class AccountService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    // PasswordEncoder 의 bean 주입
    @Autowired
    PasswordEncoder passwordEncoder;

    public Account createAccount(String username, String password) {
        Account newAccount = new Account();
        newAccount.setUsername(username);
        // 원래는
        //  >> newAccount.setPassword(password);
        // 이지만, password encoding 을 해야하므로 아래와 같이 처리한다.
        newAccount.setPassword(passwordEncoder.encode(password));

        //Account savedAccount = accountRepository.save(newAccount);
        return accountRepository.save(newAccount);
    }

    /*
     login-form 에서 입력 받은 username 과 password 에서
     username 의 값을 UserDetails 의 실제 유저 정보와 비교 한 뒤,
     입력한 password 와 저장된 password 를 서로 확인하고 인증 검사를 한다.)

     이후 UserDetails 로 변환하는 과정이다.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> byUsername = accountRepository.findByUsername(username);
        // 만약 byUsername 에 실제 데이터가 없으면 UsernameNotFountException(username) 을 던짐
        // 있으면 실제 Account 객체를 받음
        Account account = byUsername.orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(account.getUsername(), account.getPassword(), authorities());
    }

    // authorities() 는 자동 생성 할 수 있다. -> ROLE_USER 라는 권한을 가진다고 명시 해줘야 한다.
    private Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
