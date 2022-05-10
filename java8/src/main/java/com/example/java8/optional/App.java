package com.example.java8.optional;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class App {

    public static void main(String[] args) {
        Lecture javaClass = new Lecture(1, "Java Class", false);

        System.out.println("==firstProgress==");
        Optional<Progress> firstProgress = javaClass.getProgress();
        firstProgress.ifPresent(p -> System.out.println(p.getStudyDuration()));

        System.out.println("==secondProgress==");
        javaClass.setProgress(new Progress(Duration.of(1, ChronoUnit.DAYS), false));
        Optional<Progress> secondProgress = javaClass.getProgress();
        secondProgress.ifPresent(p -> System.out.println(p.getStudyDuration().get(ChronoUnit.SECONDS)));

        Optional<Progress> emptyProgress = javaClass.getEmptyProgress();
        if (emptyProgress.isPresent()) {
            System.out.println("not empty!");
        } else {
            System.out.println("empty!");
        }

    }
}
