package com.example.springbootstart;

import com.example.springbootstart._1_adding_dependency.AddingDependencyStarter;
import com.example.springbootstart._2_tomcat_in_spring.TomcatInSpring;
import com.example.springbootstart._3_http_https_http2.ProtocolCheckStarter;
import org.apache.catalina.LifecycleException;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringBootStartApplication {

    public static void main(String[] args) throws LifecycleException {
        // SpringApplication.run(SpringBootStartApplication.class, args);
        ApplicationStarter starter;

        System.out.println("Select :");
        System.out.println("0: Basic server run");
        System.out.println("1: Tomcat in Spring (X)");
        System.out.println("2: http / https / http2 (h2)");

        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();

        switch (i) {
            case 0:
                starter = new AddingDependencyStarter();
                starter.applicationStart();
                break;
            case 1:
                TomcatInSpring tomcatInSpring = new TomcatInSpring();
                tomcatInSpring.tomcatRun();
                break;
            case 2:
                starter = new ProtocolCheckStarter();
                starter.applicationStart();
                break;
        }
    }
}
