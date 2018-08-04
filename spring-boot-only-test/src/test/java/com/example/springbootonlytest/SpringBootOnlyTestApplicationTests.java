package com.example.springbootonlytest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*

[ SpringBoot Test Application 원리 ]

- 하위의 @SpringBootTest 가 src 의 최상위에 위치한 @SpringBootApplication 을 찾아간다.
  그 후, 그 하위의 모든 bean 을 스캔한다.
  그 후, test 용 application context 를 만들면서 모두 test 용 bean 으로 등록해준다.
  그 후, @MockBean 이 있으면 해당 bean 만 MockBean 으로 교체시킨다.
  (따라서 @MockBean 을 이용하면 처음부터 mocking 을 해도 지장이 없다.)

- 그렇다면, 수많은 bean 을 등록시키는게 싫고, 오직 test 하고픈 bean 만 등록시키고자 한다면 ?
-> 슬라이스 테스트(Slice Test) 어노테이션을 활용한다.
-> 이를 통해 레이어 별로 잘라서 테스트할 수 있다.

  @JsonTest
    :: 가진 model 이 json 으로 나갈 때 어떤 형태로 나갈 것인가 ?
  @WebMvcTest
    :: 슬라이싱 해서 controller 하나만 테스트 할 경우
  @WebFluxTest
  @DataJpaTest
  ...


 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootOnlyTestApplicationTests {

    @Test
    public void contextLoads() {
    }

}
