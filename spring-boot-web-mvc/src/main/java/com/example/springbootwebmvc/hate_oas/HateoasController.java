package com.example.springbootwebmvc.hate_oas;

import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-08
 * Time: 오후 5:05
 */
@RestController
public class HateoasController {

    @GetMapping("/hateoas")
    public Resource<HateoasSample> hateoas() {
        HateoasSample hateoasSample = new HateoasSample();

        hateoasSample.setPrefix("Gooooood");
        hateoasSample.setName("Tomato");

        /*
        > link 정보 추가하기 (다양한 방법이 존재한다)

        여기선 hateoas 라이브러리의 Resource 를 사용
        (제공할 resource + link 정보로 이루어져 있다.)
        */
        Resource<HateoasSample> hateoasSampleResource = new Resource<>(hateoasSample);
        hateoasSampleResource.add(
                // HateoasController 클래스에서 제공하는
                // hateoas() 라는 method 에 대한 link 를 따서,
                // 이 link 를 self 라는 relation 으로 만들어서 추가를 함
                linkTo(methodOn(HateoasController.class).hateoas())
                        .withSelfRel());

        /*

        {
            "prefix": "Gooooood",
            "name": "Tomato",
            "_links": {
                "self": {
                    "href":"http://localhost/hateoas"
                }
            }
        }

        를 결과로 내뱉는데,
        Resource 객체 내부에
        '_links' 로 Link 정보가 추가 된 것을 알 수 있다.

         */

        return hateoasSampleResource;
    }
}
