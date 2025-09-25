package com.dev.sbWebapp.lib.javers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Person {

    private String id;
    private String name;
    private int age;

    // constructor, getter/setter
    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
