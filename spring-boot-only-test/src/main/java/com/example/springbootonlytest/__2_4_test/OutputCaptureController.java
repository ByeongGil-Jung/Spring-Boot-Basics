package com.example.springbootonlytest.__2_4_test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-05
 * Time: 오전 5:13
 */

/*

[ OutputCapture 사용하기 ]

- Junit 에 있는 Rule 을 확장해서 만든 것으로,
  log 를 비록하여 console 에 찍히는 모든 것을 출력한다.

 */
@RestController
public class OutputCaptureController {

    private Logger logger = LoggerFactory.getLogger(OutputCaptureController.class);

    @Autowired
    private SampleService sampleService;

    @GetMapping("/bonjour")
    public String bongjour() {
        logger.info("bon bon bonjour ~");
        // 정말 안좋은 방법이지만, sout 도 capture 가 된다는 사실을 보여주기 위함
        System.out.println("Please don't use logging using sout");
        return "bonjour " + sampleService.getName();
    }
}
