package com.example.java8.completableFuture;

public class ConcurrentApp {

    public static void main(String[] args) throws InterruptedException {

        makeThreads();
        functionsOfThread();

    }

    static void functionsOfThread() throws InterruptedException {

        // sleep , interrupt, join
        threadSleep();
        threadInterrupt();
        threadJoin();
    }

    private static void threadJoin() throws InterruptedException {
        Thread threadJoin = new Thread(() -> {
            System.out.println("threadJoin: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadJoin.start();

        System.out.println("main: " + Thread.currentThread().getName());
        threadJoin.join();

        System.out.println("threadJoin is finished ");
    }

    static void threadSleep() {

        Thread threadSleep = new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("threadSleep Test: " + Thread.currentThread().getName());
        });

        threadSleep.start();
        System.out.println("main Thread Start: " + Thread.currentThread().getName());

    }

    static void threadInterrupt() throws InterruptedException {

        Thread threadInterrupt = new Thread(() -> {
            while (true) {
                System.out.println("threadInterrupt: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("exit!");
                    return;
                }
            }
        });

        threadInterrupt.start();

        System.out.println("Hello! Get Start " + Thread.currentThread().getName());
        Thread.sleep(3000L);
        threadInterrupt.interrupt();

    }


    private static void makeThreads() {
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
