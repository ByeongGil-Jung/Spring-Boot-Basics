package com.example.springbootstart.__1_spring_boot_principle._2_tomcat_in_spring;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-01
 * Time: 오후 7:49
 */

/*

[ Spring 내장 톰캣을 구동 시켜보기 ]

아래는 Spring 과 관련 없이 Java EE Servlet 에서 동작하는 서버 구동 로직이다.
- Spring 내부에 Tomcat 이 내장되어 있다는 것을 보여주기 위함
- 이 모든 과정이 Spring boot 내부에서 돌아감

!!
Spring Boot 는 서버가 아니다.
서버를 다룰 수 있도록 도와주는 tool 이다.

 */
public class TomcatInSpring {

    public void tomcatRun() throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        Context context = tomcat.addContext("/", "/");

        HttpServlet servlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                PrintWriter writer = response.getWriter();
                writer.println("<html><head><title>");
                writer.println("Hello Tomcat");
                writer.println("</title></head><body>");
                writer.println("<p>Hello World !</p>");
                writer.println("</body></html>");

                writer.close();
            }
        };

        String servletName = "HelloServlet";
        tomcat.addServlet("/", servletName, servlet);

        context.addServletMappingDecoded("/hello", servletName);

        tomcat.start();
        tomcat.getServer().await();
    }
}
