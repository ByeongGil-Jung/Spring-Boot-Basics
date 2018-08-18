package com.example.springbootwebmvc.hate_oas;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-08
 * Time: 오후 5:24
 */
public class HateoasSample {

    private String prefix;

    private String name;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HateoasSample{(" +
                prefix + '\'' +
                "), name='" + name + '\'' +
                '}';
    }
}
