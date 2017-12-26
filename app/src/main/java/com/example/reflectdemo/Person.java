package com.example.reflectdemo;


/**
 * Created by hasee on 2017/12/26.
 */

@Deprecated
public class Person {
    private String name ;
    public int age;

    public Person() {

    }

    @Deprecated
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Deprecated
    public Person(String name) {
        this.name = name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setAge(int age) {
        this.age = age;
    }

    private String getName() {
        return name;
    }

    @Deprecated
    public int getAge() {
        return age;
    }
}
