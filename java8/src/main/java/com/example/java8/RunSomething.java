package com.example.java8;

@FunctionalInterface
public interface RunSomething {

    void doIt();

    static void printName() {
        System.out.println("name");
    }

    default void printAge() {
        System.out.println("age");
    }
}
