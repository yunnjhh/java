package com.example.java8;

import java.util.function.Function;

public class Plus implements Function<Integer, Integer> {

    @Override
    public Integer apply(Integer integer) {
        return integer + 1;
    }

}
