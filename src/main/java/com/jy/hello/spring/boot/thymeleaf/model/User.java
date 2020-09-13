package com.jy.hello.spring.boot.thymeleaf.model;

import java.io.Serializable;

public class User implements Serializable {

    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
