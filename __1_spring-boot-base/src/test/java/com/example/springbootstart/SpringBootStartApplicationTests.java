package com.example.springbootstart;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/*
[ 2-2. External Settings ]

- Test 용 properties 를 정의 (프로퍼티 2순위 3순위 이다.)
- Environment 를 통해 확인 가능하다.

만약, test 용 properties 를 바꿔야 한다면,
resources 에 application_properties.txt 를 추가하여 그 값을 넣는다.
(그리고 project structure/modules 에 resources 폴더를 추가 해 줘야 한다.)

// 여기선 안되는데, 아마 SpringBootStartApplication 에서 다른 값을 입력하고 시작하게 만들었기 때문인 것 같다.

application_properties.txt 를 보면 알겠지만,
각 properties 들은 우선순위에 따라 override 된다.

Test 의 경우,
만약 override 하려는데 source 에서의 값이 없다면, error 가 발생한다.

source 에서의 값을 귀찮게 모두 application.properties 에서 받아오지 않으려면,

@TestPropertySource(properties = {"bread.fullname=JBK's {$bread.name}", "bread.price=3000"})
@TestPropertySource(locations = "classpath:/test.properties")

과 같이 따로 properties 를 관리하여 등록시켜 주면 된다.

 */

@RunWith(SpringRunner.class)
// 아래와 같이
// @TestPropertySource(properties = {"bread.fullname=JBK's {$bread.name}", "bread.price=3000"})
@TestPropertySource(locations = "classpath:/test.properties")
@SpringBootTest
public class SpringBootStartApplicationTests {

    // 2-2. External Settings
    @Autowired
    Environment environment;

    @Test
    public void contextLoads() {
        // 2-2. External Settings
        assertThat(environment.getProperty("bread.name"))
                .isEqualTo("Normal Bread");
    }
}
