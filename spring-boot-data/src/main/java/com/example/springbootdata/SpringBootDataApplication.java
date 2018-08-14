package com.example.springbootdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*

[ 1. In-memory Database ]

 - 지원하는 In-memory Database
   - H2
   - HSQL
   - Derby

 - Spring-JDBC 가 classpath 에 있으면 자동 설정이 필요한 bean(아래 것들) 을 설정 해준다.
   - DataSource
   - JdbcTemplate

 (1) DataSource
   - In-memory Database 의 기본 연결 정보 확인하는 방법 ?
     - URL : "testdb"
     - username : "sa"
     - password : ""

   - H2 Console 사용하는 방법 ? (택 1)
     1. spring-boot-devtools 추가
     2. application.properties 에 spring.h2.console.enabled=true 추가

     이후 /h2-console 로 접속 (path 역시 변경 가능)


 (2) JdbcTemplate
   - JdbcTemplate 에선 try, catch, finally 까지의 모든 과정이 잘 처리되어 있다.
     (따라서 간결한 코드 작성 가능)
   - Error 레이어를 세분화시켰기에 좀 더 섬세하게 error 를 볼 수 있다.


[ 2. DBCP 및 MariaDB ]

 - DBCP 란 ? (Database Connection Pool)
   :: Tomcat 과 같은 Container 에서 Connection 을 미리 여러개 만들어 놓고,
      Application 이 필요로 할 때마다, 미리 만들어진 Connection 을 사용하는 개념

   - 몇 개를 만들고, 얼마 동안 유지하고, 어떤 상황에서 error 을 던질 것인지 다양한 설정 가능하다.
   - DB performance 에 큰 영향을 주고, 디버깅이 힘들기에 다룰 때 매우 중요하다.

 - HikariCP 란 ?
   :: Spring Boot 는 기본적으로 HikariCP 를 사용한다.
   (Tomcat CP, Commons DBCP2 등이 있어도 default 는 HikariCP 이다.)

   (설정 설명)
   - autoCommit
     :: .commit() 을 하지 않아도 기본적으로 commit 을 적용시킨다. (default : True)

   - connectionTimeout
     :: DBCP 에서 '얼마 동안' Connection 객체를 application 에 전달 못하면 error 를 발생시킬 것인가 ?
     (default : 30s, 짧을 수록 error 가 날 확률이 높아짐)

   - maximumPoolSize (중요)
     :: Connection 객체를 몇 개를 생성할 것인가 ? (default : 10 개)
     (동시에 일을 할 수 있는 connection 갯수는 cpu 의 core 갯수랑 똑같다.
      -> 만약 core 갯수를 넘어가는 connection 이 존재한다면 '대기' 를 하게 된다.
      -> 보통 core 갯수보다 약간 많게 설정한다.
      따라서 컴퓨터의 상황에 맞춰 유동적으로 갯수를 설정해야 한다.)

   (설정 사용법 ?)
   application.properties 에서
    >> spring.datasource.hikari.maximum-pool-size=4
   와 같이 설정한다.


[ 4. ORM, JPA, Spring Data JPA (SDJ) 개요 ]

 - ORM (Object-Relational Mapping) 란 ?
   :: 객체와 relation 을 mapping 할 때 발생하는 개념적 불일치를 해결하는 프레임워크

 - JPA (Java Persistence API) 란 ?
   :: 여러가지 ORM 솔루션 중에서, Java 표준을 정한 것

   - 대부분의 JPA 스펙은 Hibernate 기반으로 만들어져 있음.
     (하지만, Hibernate 의 모든 기능을 JPA 에서 커버하지 않는다.
     -> 따라서 경우에 따라서 Hibernate 의 설정을 따로 해줘야 한다.)

     -> 즉, Hibernate 를 사용해도 크게 지장은 없다.

 - Spring Data JPA (SDJ) 란 ?
   :: JPA 표준 스펙을 쉽게 사용할 수 있게끔 Spring Data 로 추상화 시켜놓은 것이다.

   -> 그렇기 때문에 JPA ORM 을 쉽게 사용할 수 있다.
   -> JPA 의 구현체는 Hibernate 를 사용한다.
      (JPA Entity Manager 로 wrapping 해서 사용한다.)

   1. Repository bean 자동 생성
   2. 쿼리 method 자동 구현
   3. @EnableJpaRepositories (스프링 부트가 자동으로 설정 해줌)

   (구현 및 상속 순서)
   Spring Data JPA (SDJ) <- JPA <- Hibernate <- Datasource

   즉, SDJ 를 사용하면 Datasource 의 method 뿐만 아니라, Hibernate 및 JPA 의 기능까지 모두 사용할 수 있는 것이다.


[ 5. Spring Data JPA (SDJ) 연동 ]

 (여기선 lombok 을 사용하지 않는다고 가정한다)

 (JPA 사용하기 - jpa package 참조)
 1. @Entity 클래스를 만든다.
 2. Repository 만들기

 (Repository Test 만들기)
 1. H2 DB 를 테스트 dependency 에 추가하기
 2. @DataJpaTest (슬라이스 테스트) 작성

 (Test 에선 자동으로 DB 를 만들어준다.)

 -> Slicing Test 를 할 것이다.
   :: Slicing Test 란 Repository 와 관련된 bean 들만 등록해서 test 를 만드는 것.
   - In-memory DB 가 필요하며, 여기선 H2 를 사용할 것임

 ++)
  @SpringBootTest 를 통해 Test 할 수 도 있는데,
  이는 모든 bean 설정을 사전에 스캔하기 때문에 H2 가 아닌 MariaDB 로 테스트를 하게 된다.

  -> 이 방법은 테스트에서 데이터 변화가 일어날 경우, 실제 Application 의 데이터에도 변화가 일어난다.
  -> 따라서 In-memory DB 인 H2 를 이용하는 것이 테스트 상 안전하다.


[ 6. 데이터베이스 초기화 ]
- jpa 패키지에서 계속 진행

 (1) JPA 를 사용한 데이터베이스 초기화

   1. spring.jpa.hibernate.ddl-auto=?

    (develop 상황)
    update : app 실행 시에 기존 스키마를 유지하고, 이후에 변경된 사항을 적용한다.
    create : app 실행 시에 기존 스키마를 삭제하고, 새로운 스키마를 만든다.
    create-drop : app 실행 시에 스키마를 생성하고, 종료 시에 스키마를 삭제한다.

    (deploy 상황)
    validate : 실제로 운영하는 db 에서 사용하며, Entity 와 db 사이의 mapping 이 잘 이루어졌는지 확인한다.
    -> 즉, 운영되는 db 와 비교했을 때, 갑자기 Entity 의 구조가 바뀐다면 error 를 내뱉게 된다. (missing column)
    (적용하기 위해선, spring.jpa.generate-dll=false 로 해줘야 한다.)

   2. spring.jpa.generate-dll=true

   의 두 과정을 통해 JPA 를 사용하여 데이터베이스를 초기화 할 수 있다.

 (2) SQL 스크립트를 사용한 데이터베이스 초기화
   - schema.sql 또는 schema-${platform}.sql (1 순위)
   - data.sql 또는 data-${platform}.sql (2 순위)
   - ${platform} 값은 spring.datasource.platform 으로 설정 가능

   >> application.properties 참조


 < 개발 시 방향 >

   (1 - 개발 초기)
   개발 초기에는,
     >> spring.jpa.hibernate.ddl-auto=update
   로 쓴다.

   (2 - 배포 시기)
   배포 할 때 쯤, test 코드에서 깔끔한 schema 를 생성하도록 쭉 만든다.
   이후 console 등과 같이 출력된 값을 schema.sql 에 복사해서 쓴다.
   (-> 이러면 굉장히 깔끔한 schema 코드를 완성할 수 있다.)

   (update 로 설정하고 개발을 하게 되면, schema 가 굉장히 지저분해진다.
    이름을 바꾼 col 이 그대로 남아있다는 등 ... )

   -> 즉, update 는 개발 시에는 굉장히 편리하지만, 운영 시에는 사용하지 않도록 한다.

   **
   -> 만약 schema 와 데이터를 더 체계적으로 관리하고 싶다면 db 마이그레이션 툴을 사용하도록 한다.


[ 7. 데이터베이스 마이그레이션 ]
- jpa 패키지에서 계속 진행
- 여기선 DB 마이그레이션 툴로 Flyway 를 사용할 것

 - DB 마이그레이션 툴 ?
   :: DB schema 변경이나, 데이터 변경을 버전 관리하듯이 차곡차곡 sql 파일로 관리할 수 있다. (DB 의 Git)
   (Flyway, Liquibase 가 대표적이지만, 여기선 Flyway 를 사용할 것)

 1. 의존성 추가
   >> compile('org.flywaydb:flyway-core')

 2. 마이그레이션 directory
   - db/migration 또는 db/migration/{vendor}
     (ex : db/migration/mariaDB)
   - spring.flyway.locations 로 path 변경 가능

 (순서)
  (1) directory 아래에 V1__init.sql 과 같이 만듦. (처음에 V + 숫자 + __ )
  (2) spring.jpa.hibernate.ddl-auto=validate 로 변경

 (sql 파일 이름 ?)
  - V + 숫자 + __ + 이름 .sql
  - V 는 꼭 대문자로
  - 숫자는 순차적으로 (타임스탬프 권장)
  - 숫자와 이름 사이에 언더바 두 개
  - 이름은 가능한 서술적으로

 (validation 검증 과정)
  flyway 를 먼저 실행 -> 마이그레이션 directory 내의 sql 문 실행 -> hibernate 가 validation 함)


 - app 실행 시 생기는 'flyway_schema_history' 라는 schema ?
   :: flyway 가 자기 정보를 관리하는 table 이다.
      (어떤 sql 은 언제 실행 됐고, 언제 성공적으로 끝났는가, 등등 ... )

 ...

 - 그렇다면 중간에 새로운 col 을 추가하는 등의 schema 변화를 주고 싶다면 ?

  -> 한 번 적용이 된 migration sql 파일은 절대 건드려선 안된다 !
  -> 반드시 V2__add_active.sql 과 같이 새로운 version 의 sql 을 생성해야 한다.
  (col 변경 뿐 아니라, INSERT 등 모든 sql 문이 가능하다.)

  (flyway_schema_history 를 보면 알겠지만, 다 version 관리 되어있다.)

*/

@SpringBootApplication
public class SpringBootDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataApplication.class, args);
    }
}
