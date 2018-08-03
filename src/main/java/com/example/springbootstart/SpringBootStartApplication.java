package com.example.springbootstart;

import com.example.springbootstart.__1_spring_boot_principle._1_adding_dependency.AddingDependencyStarter;
import com.example.springbootstart.__1_spring_boot_principle._2_tomcat_in_spring.TomcatInSpring;
import com.example.springbootstart.__1_spring_boot_principle._3_http_https_http2.ProtocolCheckStarter;
import com.example.springbootstart.__2_spring_boot_utilization._1_SpringApplication_eventhandler_argumentshandling.SpringApplicationStarter;
import com.example.springbootstart.__2_spring_boot_utilization._2_external_settings.ExternalSettingsStarter;
import com.example.springbootstart.__2_spring_boot_utilization._3_profile.ProfileStarter;
import org.apache.catalina.LifecycleException;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringBootStartApplication {

    public static void main(String[] args) throws LifecycleException {
        // SpringApplication.run(SpringBootStartApplication.class, args);
        ApplicationStarter starter;

        System.out.println("Select :");
        System.out.println("\n [1 - Spring Boot Principle]");
        System.out.println("0: Basic server run");
        System.out.println("1: Tomcat in Spring (X)");
        System.out.println("2: http / https / http2 (h2)");
        System.out.println("\n [2 - Spring boot Utilization]");
        System.out.println("3: SpringApplication");
        System.out.println("4: External Settings");
        System.out.println("5: Profile");

        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();

        switch (i) {
            case 0:
                starter = new AddingDependencyStarter();
                starter.applicationStart(args);
                break;
            case 1:
                TomcatInSpring tomcatInSpring = new TomcatInSpring();
                tomcatInSpring.tomcatRun();
                break;
            case 2:
                starter = new ProtocolCheckStarter();
                starter.applicationStart(args);
                break;
            case 3:
                starter = new SpringApplicationStarter();
                starter.applicationStart(args);
                break;
            case 4:
                starter = new ExternalSettingsStarter();
                starter.applicationStart(args);
            case 5:
                starter = new ProfileStarter();
                starter.applicationStart(args);
        }
    }
}
