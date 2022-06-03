package com.example.java8.completableFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsApp {

    public static void main(String[] args) {

        useExecutorService();
        useExecutorServiceForThreadPools();
        useScheduledExecutorService();

    }

    private static void useScheduledExecutorService() {

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(getRunnable("scheduledExecutorService"), 3, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(getRunnable("scheduleAtFixedRate"), 2, 1, TimeUnit.SECONDS);

//        scheduledExecutorService.shutdown();
    }

    private static void useExecutorServiceForThreadPools() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(getRunnable("no1"));
        executorService.submit(getRunnable("no2"));
        executorService.submit(getRunnable("no3"));
        executorService.submit(getRunnable("no4"));
        executorService.submit(getRunnable("no5"));

        executorService.shutdown();
    }

    private static void useExecutorService() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("ExecutorService - 실행1 " + Thread.currentThread().getName());
            }
        });

        executorService.submit(() -> System.out.println("ExecutorService - 실행2 " + Thread.currentThread().getName()));

        executorService.shutdown();
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + ": " + Thread.currentThread().getName());
    }
}
