package com.example.java8;

public class PracticeInterfaceClass implements PracticeInterface {

    String name;

    public PracticeInterfaceClass(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }
}
