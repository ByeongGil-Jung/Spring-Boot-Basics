package com.example.springbootwebmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*

[ 1. Introduce ]

 - Spring MVC 확장 ?

 WebMvcConfigurer 를 상속받는 Config 클래스를 만들고,
 @Configuration 어노테이션을 붙이면,

 해당 클래스에서 mvc 설정을 추가할 수 있다.


 ++ 절대 @EnableWebMvc 를 붙이면 안된다 !
 > 붙일 경우, Spring boot 가 제공하는 mvc 설정들이 모두 사라지고, 사용자가 직접 다시 세팅해줘야 한다.


==============================================================================================

[ 2. HttpMessageConverters ]

 - Spring 에서 제공하는 Interface 이다.
 - Spring MVC 의 일부분이다.
 - http body 로 들어오는 요청을 객체로 변환하거나,
   혹은 http body 로 보낼 response 객체로 변환할 때 사용한다.


 HttpMessageConverter 는 여러가지가 있는데,
 어떤 request 를 받았는지, 어떤 response 를 보내야 하는지에 따라 사용 객체가 달라진다.
 (이것은 Spring 엔진이 알아서 찾아준다.)

 ex)
 request : JSON (content-type: JSON, body 내용 역시 JSON)
 response : html

 -> JsonMessageConverter 가 사용됨

 (만약 return 형태가 String, int 등등... 이면 StringMessageConverter 가 사용된다. -> 모두 String 으로 타입 변환)


==============================================================================================

[ 3. ViewResolver ]

 - ViewResolver - ContentNegotiation ?
   :: ViewResolver 중에 하나로, request 의 accept-header 에 따라 response 가 달라진다.
   기본적으로 내장되어 있다. (즉, 상황에 따라 자동으로 converter 가 등록된다.)
   (Test 의 경우, .accept(MediaType.APPLICATION_JSON_UTF8) 부분)

   -> 이렇게 ViewResolver 의 ContentNegotiation 과 MessageConverter 와 서로 연관이 되어 있다 !

   만약 accept-header 가 없을 경우,
   "/users/create?format=pdf" 등과 같이 parameter 로 명시해주도록 한다.


  예제에선,
  JSON - XML 을 converting 하려 한다.

  하지만 XML message 로 converting 할 수 있는 MessageConverter 가 없기 때문에,
  jackson-dataformat-xml 을 의존성으로 주입해주었다.


==============================================================================================

[ 4. Static Resources ]

 - 정적 리소스 (Static Resources) ?
   :: 동적으로 생성하지 않은 것들.
      client 에서 request 가 들어왔을 떄, resource 가 이미 만들어져 있는 상태이고,
      만들어진 resource 를 그대로 response 로 전달해주고자 하는 것.

   ex)
   서버에서 작업을 처리하는 상황에서,
   request 가 들어왔을 때, view 를 만들어내는 것이 아니라,
   이미 만들어져있는 view 를 제공하는 방법 등


 - 기본 리소스 위치
   * classpath:/static
   * classpath:/public
   * classpath:/resources/
   * classpath:/META-INF/resources

   기본적으로 위의 경로에 있는 resource 들은, "/**" 요청에 mapping 되어 제공된다.

   ex)
   hello.html 의 request 가 들어옴 -> /static/hello.html 을 response 로 보내줌

   ------------------------------------------------------------------------------------------

   ++
   * spring.mvc.static-path-pattern : mapping 설정 변경 가능
   :: default 는 root 이고,
      spring.mvc.static-path-pattern=/static/**
      을 준다면,

       >> localhost:8080/hello.html -> localhost:8080/static/hello.html

      로 접근해야 한다.

   * spring.mvc.static-locations : resource 찾을 위치 변경 가능
   :: 위의 4 가지 resource 에 override 해서 사용하게 됨
      위 방법은 딱히 딱히 권장하지 않으며, 밑의 configurer 방법을 볼 것

   * WebMvcConfigurer (@Configuration)
   >> @Configuration
   >> ~ implements WebMvcConfigurer

   에서
   addResourceHandlers 의 method 를 통해 커스터마이징 할 수 있다.

   >> registry.addResourceHandler("/m/**")
                // 맨 마지막에 '/' 을 붙여줘야 정상 작동한다.
                .addResourceLocations("classpath:/m/")
                // static 자원을 cache 로 저장할 때, 얼마동안 저장할지의 시간 지정
                // caching 을 반드시 설정해줘야 한다.
                // -> static 아래의 resource 는 기본 spring 의 cache 로 사용되고 있다.
                .setCachePeriod(20);

   이를 통해
   localhost:8080/hello.html
   localhost:8080/m/hello.html

   과 같이 별개의 resource 로 관리할 수 있게 되었다.
   (m 과 같이 모바일 버전을 따로 관리하고자 할 때 유용하다.)

   !! 반드시 cache 설정을 따로 해줘야 한다.

   ------------------------------------------------------------------------------------------

 ++
 Build(Ctrl F9) 를 통해 변경사항을 쉽게 확인할 수 있는데,
 이는 ResourceHttpRequestHandler 가 처리한다

 last modified 라는 header 정보를 보고, 그 후에 modify 되었으면 (If-Modified-Since) 200 status 를 뱉는다.


==============================================================================================

[ 5. Web jar ]

 - Web jar ?
   :: client 에서 사용하는 javascript 라이브러리들을 jar 파일로 추가할 수 있다. (즉, 일종의 의존성이다.)
      (jquery, bootstrap, react, vue, angular, ... 등등 사용 가능)

   - template 를 사용하여 동적으로 생성된 content 에서, 혹은
     static 내부의 정적 resource 에서 참조하여 사용 가능하다.

 -> gradle(maven) 의 의존성 주입에서 추가할 수 있다.

 ex)
 jQuery 를 넣는다면,

  >> compile('org.webjars.bower:jquery:3.3.1')

 와 같이 추가하고,
 html 에

  <script src="/webjars/jquery/3.3.1/dist/jquery.min.js" type="text/javascript"></script>

 를 넣음으로써 사용할 수 있다.


 - webjars-locator-core
   :: Web jar 를 등록하고, html 내에서 javascript 를 호출할 때 버전을 안적어줘도 되게 하는 의존성이다.
   (원리는 resource chaining 과 관련이 있다.)

 의존성에

  >> compile('org.webjars:webjars-locator-core')

 를 주입하면,

  <script src="/webjars/jquery/dist/jquery.min.js" type="text/javascript"></script>

 와 같이 버전을 생략할 수 있다. (-> 유지보수에 용이하게 만든다.)

 -- 그런데 예제로 만든 html 에선 왜 안되는지 잘 모르겠다.


==============================================================================================

[ 6. Index Page, Favicon ]

 - Index Page
   :: Index Page == index.html (root 를 요청했을 때 나오는 Page)
   (아직 동적 page 를 접하지 않았으니, static resource 로 만들 것임)

 (1) index page
 -> resource 폴더(위 참조)에서 index.html 을 만들면 Spring 에서 알아서 찾는다.

 (2) favicon
 -> resource 폴더에서 favicon.ico 를 넣으면 알아서 찾는다.


==============================================================================================

[ 7. Thymeleaf ]

 - Thymeleaf ?
   :: 비교적 최근에 만들어진 동적 contents 를 생성할 수 있는 템플릿 엔진 중 하나로, MVC 에서 주로 View 를 만드는 데 사용된다.

   - 학습이 비교적 필요한 템플릿이다.
   - resources/templates 폴더에서 그 파일들을 찾는다.

   (템플릿 엔진을 쓰는 이유 ?
     -> template 내부에 들어가는 값들이 경우에 따라 바뀔 때, 정적인(static) resource 를 사용할 수 없게 된다.
     -> 이런 경우 동적으로 content 를 생성해서 제공해줘야 하는데, 이런 경우 template 엔진을 유용하게 사용할 수 있다.

   (템플릿 엔진 중 JSP 를 지양하는 이유 ?
     -> Spring Boot 가 지향하는 방향과 다르다.
        (Spring Boot 는 독립적으로 실행 가능한 내장 톰캣으로 application 을 빠르고 쉽게 만들어서 배포하길 원함.
         하지만 JSP 를 사용하게 되면, jar 패키징을 못하고 war 패키징을 해야만 한다.
         또한 Undertow 가 JSP 를 지원하지 않는다.
         또한 JSP 에 대한 의존성을 넣으면, Spring Boot 내에서 의존성에 대해 문제가 발생할 수 있다.)

 - 사용
   - html
   html 네임스페이스에
    >> <html xmlns:th="http://www.thymeleaf.org"> ~ </html>
   을 넣어줘야 thymeleaf 문법 사용이 가능하다.

   - Controller

    // parameter 인 model 은 Model 의 데이터를 받을 객체이다.
    // return 하는 String 은 view 의 이름이다.
    // (@RestController 처럼 response 의 본문을 띄워주지 않는다.)
    @GetMapping("/thyme")
    public String thyme(Model model) {
        // Model 에 정보를 담아야 한다. -> Map 과 같이 사용한다.
        model.addAttribute("name", "Bread");
        return "thyme"; // view 의 이름
    }

    를 보면 충분히 알 수 있지 않을까 싶다.

 - 장점 ?
   - 실제로 서버를 작동시켜야 볼 수 있는 jsp 와는 달리, html 로 되어 있어 자체적으로 구동 가능하다.
   - 값이 없을 경우 default 값을 출력시키므로, 안전하다.


 **
 -> 따로 공부를 해보도록 하자.


==============================================================================================

[ 8. HtmlUnit ]

 - Html 을 단위 테스트하기 위한 툴 중 하나.
 -> HtmlUnit 의 의존성을 추가해줘야 함

 WebClient 객체를 만들어서 사용함.

 -> html 을 좀 더 섬세하게 Test 할 수 있음.
 (MockMvc 를 사용해도 좋지만, MockMvc 는 controller, model 단에서 test 하기 용이함)

 // HtmlUnit 의존성 추가 (in test)
    testcompile('org.seleniumhq.selenium:htmlunit-driver')
    testcompile('net.sourceforge.htmlunit:htmlunit')


==============================================================================================

[ 9. Exception Handler ]

 - Spring Boot 가 지원하는 Spring MVC Error Handler
  -> Spring Boot Application 에선 기본적으로 Error Handler 가 탑재되어 있다.

 ex)
  -> BasicErrorController (Spring Boot 가 제공하는 기본 예외 처리기)
  (HTML 과 JSON 응답으로 에러 메세지를 표현)
 index page 를 만들지 않았을 때 html 로 접속했을 떄 보이는 화면.
 만약 console 등 (Machine Client) 에서 접근하려 하면 JSON 으로 error message 를 출력한다.

 ---------------------------------------------------------------------------------------

 - BasicErrorController (Spring Boot 가 제공하는 기본 예외 처리기)
   :: HTMl 과 JSON 응답 지원

 - Spring MVC 예외 처리 방법
  - @ControllerAdvice
    :: 예제에서 ExceptionController
    (여기선 학습을 위해 어노테이션으로 @Controller 를 붙인다.)

    ////
    -> 나중에 시간 나면 @ControllerAdvice 공부 할 것

  - 커스텀 에러 페이지
   (위의 방법보다 훨씬 편리하다.)
   :: 상태 코드 값에 따라 에러 페이지를 보여준다.

   -> static 이나 templates 에서 에러 페이지를 만든다.

   이 때,
   404.html 과 같이 error 를 정확하게 명시해 줄 수 있고,
   5xx.html 과 같이 500 번대 error 들을 두루뭉실하게 redirect 시킬 수 있다.


==============================================================================================

[ 10. HATEOAS ]

 - HATEOAS (Hypermedia As The Engine Of Application State) 란 ?
   (서버)
    > 현재 resource 와 '연관된 링크 정보' 를 클라이언트에 제공한다.
   (클라이언트)
    > '연관된 링크 정보' 를 바탕으로 resource 에 접근한다.

   ('연관된 링크 정보' ??)
    > Relation (rel)
    > Hypertext Reference (href)

   < 개념 ex >
   만약 root 페이지에 대한 resource 를 요청했다고 가정하자. (연관된 내용은 books 라고 가정)
   : 요청한 페이지 - 현재 (relation = self, link(href) = "/root")
   : 연관된 링크 (relation = books, link(href) = "/books")

   ->
   client 입장에선
   root 를 요청한 다음에, root 와 연결된 resource 에는 books 가 있구나
   라는 것을 알게 된다.

   < Reference ex >
   :: https://spring.io/understanding/HATEOAS
    {
        "content": [ {
            "price": 499.00,
            "description": "Apple tablet device",
            "name": "iPad",
            "links": [ {
                "rel": "self",
                "href": "http://localhost:8080/product/1"
            } ],
            "attributes": {
                "connector": "socket"
            }
        }, {
            "price": 49.00,
            "description": "Dock for iPhone/iPad",
            "name": "Dock",
            "links": [ {
                "rel": "self",
                "href": "http://localhost:8080/product/3"
            } ],
            "attributes": {
                "connector": "plug"
            }
        } ],
        "links": [ {
            "rel": "product.search",
            "href": "http://localhost:8080/product/search"
        } ]
    }


 - Spring 에선 HATEOAS 를 구현하기 위한 편리한 툴이라고 생각하면 된다.

 - dependency 를 추가하면, 다양한 자동설정을 제공 및 메소드를 사용 가능하다.
  >> compile('org.springframework.boot:spring-boot-starter-hateoas')

  그 중 특히 유용한 것은,
  (1) ObjectMapper
    :: 제공하는 resource 를 JSON 으로 변환할 때 사용하는 interface.
       (사실, hateoas 를 주입하지 않아도, Spring Boot Web 에 내장되어 있다.)

    - spring.jackson.* 라이브러리에서 제공

    -> 자세한 내용은 test 의 HateoasControllerTest 클래스 참조

  (2) LinkDiscovers (사용하게 될 일이 많지 않을 것)
    :: xpath 를 확장해서 만든 hateoas 용 client API 이다.

    > REST API 를 통해 다른 서버에서 데이터를 받아왔는데,
      그것이 hateoas 를 지원 할 경우, ("_links" 정보들이 있음 등등 ...)
      손쉽게 self 에 해당하는 link 정보를 가져올 수 있다.

      (유틸리티 성 클래스이다.)

 **
 -> REST_API 를 견고하게 만들고 싶담녀 따로 공부를 해보도록 하자...

 */

@SpringBootApplication
public class SpringBootWebMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebMvcApplication.class, args);
    }
}
