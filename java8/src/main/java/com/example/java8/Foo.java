package com.example.java8;

public class Foo {

    public static void main(String[] args) {

        RunSomething runSomething1 = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("do it");
            }
        };

        RunSomething runSomething2 = () -> System.out.println("do it do it");

        runSomething1.doIt();
        runSomething2.doIt();

        runSomething2.printAge();

    }
}
