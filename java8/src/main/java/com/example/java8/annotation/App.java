package com.example.java8.annotation;

import java.util.Arrays;
import java.util.List;

@Chickens("양념치킨")
@Chickens("후라이드치킨")
@Chickens("간장치킨")
@Chickens("마늘치킨")
@Chicken
public class App {

    public static void main(String[] args) {

        Chickens[] chickens = App.class.getAnnotationsByType(Chickens.class);
        Arrays.stream(chickens).forEach(c -> System.out.println(c.value()));
        System.out.println();

        ChickensContainer container = App.class.getAnnotation(ChickensContainer.class);
        Arrays.stream(container.value()).forEach(c -> System.out.println(c.value()));

    }

    static void getNames(@Chicken String[] c) throws @Chicken RuntimeException {
        List<@Chicken String> names = Arrays.asList("양념", "후라이드");
    }


    static class FeelsLikeChicken<@Chicken T> {

        public static <@Chicken C> void print(@Chicken C c) {
            System.out.println(c);
        }
    }

}
