package com.example.springbootwebmvc.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-07
 * Time: 오후 4:21
 */
@Controller
public class ExceptionController {

    @GetMapping("/raiseerror")
    public String raiseError() {
        throw new SampleException();
    }

    // SampleException() 을 만드는 raiseError 이 발생하면,
    // 아래와 같은 handler 를 호출하겠다.
    // 라는 의미.
    @ExceptionHandler(SampleException.class)
    public @ResponseBody AppError exceptionHandler(SampleException e) {
        // 여기선 JSON 을 던지는 예시를 만들 것
        AppError appError = new AppError();

        // error 의 메세지 키, 리소스 등을 문자열로 입력시키는 형식
        appError.setMessage("error.app.key");
        // error 의 발생 원인은 ?
        appError.setReason("I don't know");

        return appError;
    }
}
