package com.example.java8.completableFuture;

public class ConcurrentApp {

    public static void main(String[] args) {


        MyThread myThreadExtends = new MyThread();
        myThreadExtends.start();

        Thread MyThreadRunnable = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("MyThread-runnable: " + Thread.currentThread().getName());
            }
        });
        MyThreadRunnable.start();

        Thread MyThreadLambda = new Thread(() -> System.out.println("MyThread-lambda: " + Thread.currentThread().getName()));
        MyThreadLambda.start();

        System.out.println("Hello: " + Thread.currentThread().getName());
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread-extends: " + Thread.currentThread().getName());
        }
    }
}
