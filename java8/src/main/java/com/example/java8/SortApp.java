package com.example.java8;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class SortApp {

    public static void main(String[] args) {

        // int size = 15000;
        int size = 1000;
        // size 별로 다름

        int[] numbers = new int[size];
        Random random = new Random();

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        long startTime = System.nanoTime();
        Arrays.sort(numbers);
        System.out.println("serial sorting took: " + (System.nanoTime() - startTime));

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        startTime = System.nanoTime();
        Arrays.parallelSort(numbers);
        System.out.println("parallel sorting took: " + (System.nanoTime() - startTime));

    }

}
