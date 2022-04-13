package com.example.java8;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Lambda {

    public static void main(String[] args) {

        Lambda lambda = new Lambda();
        lambda.run();

        Supplier<Greeting> greetingSupplier = Greeting::new;
        Greeting greeting = greetingSupplier.get();

        UnaryOperator<String> hi = Greeting::hi;
        UnaryOperator<String> hello = greeting::hello;

        System.out.println(hi.apply("jin"));
        System.out.println(hello.apply("tom"));

        Function<String, Greeting> greetingFunction = Greeting::new;
        Greeting jon = greetingFunction.apply("jon");

        System.out.println(greeting.getName());
        System.out.println(jon.getName());

    }

    private void run() {

        int baseNumber = 100;

        class LocalClass {

            int baseNumber = 1000;
            void multiply() {
                System.out.println(baseNumber * 2);
            }
        }

        Consumer<Integer> multiply2 = new Consumer<Integer>() {
            int baseNumber = 1000;
            @Override
            public void accept(Integer integer) {
                System.out.println(baseNumber * integer);
            }
        };

        IntConsumer multiply3 = (i) -> {
            System.out.println(i * baseNumber);
        };


        LocalClass localClass = new LocalClass();
        localClass.multiply();
        multiply2.accept(2);
        multiply3.accept(2);

    }

}
