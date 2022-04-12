package com.example.java8;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class FunctionalInterfaceApplication {

    public static void main(String[] args) {

        Plus plus1 = new Plus();
        System.out.println(plus1.apply(1));

        Function<Integer, Integer> multiply3 = (i) -> i * 3;
        System.out.println(multiply3.apply(2));

        System.out.println(plus1.compose(multiply3).apply(5));
        System.out.println(plus1.andThen(multiply3).apply(5));


        BiFunction<String, Integer, Boolean> checkStrLenth = (s, i) -> s.length() == i ? true : false;
        System.out.println(checkStrLenth.apply("haha", 3));

        Supplier<Integer> getNumber = () -> 5;
        System.out.println(getNumber.get());

        Consumer<String> printName = s -> System.out.println(s);
        printName.accept("yeah~");

        Predicate<String> startsWithOh = (s) -> s.startsWith("Oh");
        Predicate<String> endsWithOh = (s) -> s.endsWith("Oh");

        System.out.println(startsWithOh.and(endsWithOh).test("OhhhhhhhOh"));
        System.out.println(startsWithOh.and(endsWithOh).test("hhhhhhhhOh"));
        System.out.println(startsWithOh.and(endsWithOh).test("Ohhhhhhhhh"));

        UnaryOperator<Integer> unary = (i) -> i + 10;
        BinaryOperator<String> binary = (s1, s2) -> s1.concat(s2);

        System.out.println(unary.apply(11));
        System.out.println(binary.apply("abc","cba"));


    }
}
