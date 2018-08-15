package com.example.springbootsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-15
 * Time: 오후 6:50
 */
/*

Security 설정을 여기서 할 것임.

[ 2. Spring Security Customizing ]
에서 /my2 에만 authentication 을 주는 설정도 여기서 할 것임.

** 중요 ! **
-> WebSecurityConfigurerAdapter 을 상속받는 이 클래스 (WebSecurityConfig) 를 bean 으로 만듦으로써
   더 이상 spring-security 에서 제공하는 SecurityAutoConfiguration 은 사용이 안된다 !

-> 즉, 이제부터 WebSecurityConfiguration 설정은 여기서 맡게 된다 !


 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // root 경로와, /board, /board2 로 들어오는 req 는 인증이 필요 없음
                .antMatchers("/", "/board", "/board2").permitAll()
                // 나머지 모든 경로로 들어오는 req 는 인증이 필요함
                .anyRequest().authenticated()
                    .and()
                // form login 을 사용할 것이며,
                .formLogin()
                    .and()
                // http basic authentication 도 사용할 것이다.
                .httpBasic();

        /*
        원래는,

        http
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.formLogin().and()
			.httpBasic();

		으로 되어 있던 것을 @Override 하여 입맛에 맞춰 수정한 것이다.
         */
    }

    /*
    [ Password Encoder ]

    1. Noop PasswordEncoder (사용하지 말 것 !) -> password 가 그대로 노출 됨 !

     @Bean
     public PasswordEncoder passwordEncoder() {
         return NoOpPasswordEncoder.getInstance();
     }

    2. PasswordEncoderFactories.createDelegatingPasswordEncoder() -> spring-security 에서 권장
    이후 db 에 insert 하는 작업에서,
     >> passwordEncoder.encode(password) 로 encoding 한 후 넣어야 한다.

    */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
