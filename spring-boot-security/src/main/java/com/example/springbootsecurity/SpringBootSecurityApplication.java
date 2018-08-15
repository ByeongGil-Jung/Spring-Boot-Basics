package com.example.springbootsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*

[ 1. Spring Security ]

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


[ 2. Spring Security Customizing ]
 - 저번의 예제에서 발전시키도록 한다.
 (/my2 로 가는 요청만 authentication 이 필요하도록 만들 것임)

  (1) Web Security 설정

   - Security 설정을 여기서 할 것임.
     >> WebSecurityConfig

      ** 중요 ! **
    -> WebSecurityConfigurerAdapter 을 상속받는 이 클래스 (WebSecurityConfig) 를 bean 으로 만듦으로써
       더 이상 spring-security 에서 제공하는 SecurityAutoConfiguration 은 사용이 안된다 !

    -> 즉, 이제부터 WebSecurityConfiguration 설정은 여기서 맡게 된다 !


  (2) UserDetailsService 구현

   - 실제론 user 정보를 관리자가 직접 관리하지, spring-security 가 만들어준 user 정보를 활용하진 않음.
     >> 따라서 이 부분을 custromizing 할 것임.
     (account 패키지 참조)

   - 여기까지 하고 username, password 를 입력하면 아마 error 가 날 것임.
     -> password 의 encoding 이 다르기 떄문이므로, (3) 의 과정을 이행해야 한다.


  (3) PasswordEncoder 설정 및 사용

   - 다양한 encoding 방법이 존재한다 !
     (spring-security 는
       >> PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
      의 사용을 권장한다.)

   (1) Noop encoding (password encoding 을 안하는 방식)
     (사실, security encoding 을 하지 않고 password 를 그대로 db 에 넣는 것은 아주 아주 아주 위험하다 !
      -> db 에 password 가 바로 노출되어 저장되기 때문 !)

   (2) PasswordEncoderFactories.createDelegatingPasswordEncoder()
     -> spring-security 에서 권장하는 방법

     > WebSecurityConfig 에서 다음과 같이 설정한다.

     @Bean
     public PasswordEncoder passwordEncoder() {
         return PasswordEncoderFactories.createDelegatingPasswordEncoder();
     }

     > 이후 db 에 insert 하는 작업에서,
       passwordEncoder.encode(password) 로 encoding 한 후 넣어야 한다.


 - 위의 것들은 기본적인 내용이다.
   spring-security 를 견고하게 하기 위해선, 알아야 할 내용이 더 많다.

   >> 어떤 요청들을 security 로 처리 할 것인지 ...
   >> CSRF 설정을 한다든지 ...
   >> 인증 방식으로 OAuth 를 사용 한다든지 ...

   >> REST-API 를 통해 전달받은 값으로 유저 정보를 입력하는 방식도 필요할 것이고 ...
   >> Form 인증 화면도 customizing 할 필요가 있고 ...

 */

@SpringBootApplication
public class SpringBootSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityApplication.class, args);
    }
}
