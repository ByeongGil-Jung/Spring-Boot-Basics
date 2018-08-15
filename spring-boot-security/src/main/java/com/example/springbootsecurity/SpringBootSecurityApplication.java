package com.example.springbootsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*

[1. Spring Security ]

 (1) Spring Security 란 ?
  :: 모든 요청이 spring-security 로 인해 인증을 필요로 한다. (Test 역시 마찬가지)
  :: Basic Authentication, Form Authentication 둘 다 적용이 된다.

   > Basic Authentication 은 요구하는 Accept-Header 에 따라 달라진다.
     (> 즉, 인증 정보가 없을때 나오는 응답이 Accept-Header 에 따라 달라진다.)
   (Basic Authentication 을 요청하게 될 경우, username, password 를 입력하라는 창이 나옴.)

   (* Accept-Header ?
     :: 요청이 원하는 응답의 형태.)


  -> 보통 Ajax 로 call 하는 등의 특별한 경우가 아니라면,
     일반적으로 text-html 을 accept-header 에 담아서 보낸다.
     (이런 경우 Form Authentication 으로 넘어가게 돼있다.)

     -> 일반적으로 spring-security 를 의존성 주입하고 app 에 접속하면 보이게 된다.


  - spring-security 의 login-form 페이지의 기본적인 정보 ?
    username : user
    password : application 실행 시 console 에 보이는 password 값 (매 번 바뀜)


 (2) Spring Security 의 설정 파일 ?

   - SecurityAutoConfiguration
     :: Security Auth 의 우선순위를 설정해주고, 대부분 Spring Security 가 기본으로 제공해주는 것을 사용한다.
   - UserDetailsServiceAutoConfiguration
     :: spring-security 의 user 정보를 담는 객체를 생성함. (password 등등 ... )

     만약, UserDetailsService, AuthenticationManager, AuthenticationProvider 의 class 가 없으면
     기본으로 제공된 위의 UserDetailsServiceAutoConfiguration 을 사용한다.
     ...
     이는 WebSecurityConfigurerAdapter 를 포함한다 ...

     -> 따라서, 위의 자동 설정을 사용하지 않으려면,
        WebSecurityConfigurerAdapter 를 상속받는 Config 클래스를 만들어서 관리하면 된다 !

        (이를 통해, Web Security 설정을 임의로 설정할 수 있다.)
        -> WebSecurityConfig 클래스 참조
           (하지만, user 가 생성되는 설정은 다른 곳(UserDetailsServiceAutoConfiguration)에서 하므로,
           이것과는 관련 없다.)


   - 보통 spring-security 를 적용하는 프로젝트들은 거의 전부 프로젝트만의 UserDetailsService 를 등록한다.

     (거의 100%. 따라서 Spring Boot 가 지원하는 Spring Security 에 관련된 기능들은 사실상 쓸 일이 없다 ㅠㅠ)
     >> 여기선 Spring Security 만 추가하면 어떤 일이 발생하는지를 보여주기 위함이다.


 (예제)
 여기선 /board 는 모두가 볼 수 있도록 하고,
 /my 는 로그인 한 사람만 볼 수 있도록 하기

 */

@SpringBootApplication
public class SpringBootSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityApplication.class, args);
    }
}
