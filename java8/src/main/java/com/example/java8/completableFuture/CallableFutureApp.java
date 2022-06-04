package com.example.java8.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        useCallableAndFuture();
        useFutureApi();
        useInvokeAllAndAny();

    }

    private static void useInvokeAllAndAny() throws InterruptedException, ExecutionException {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService fixedThreadPoolBy2 = Executors.newFixedThreadPool(2);
        ExecutorService fixedThreadPoolBy4 = Executors.newFixedThreadPool(4);

        Callable<String> sleepFor2Sec = () -> {
            Thread.sleep(2000L);
            return "2 Seconds";
        };

        Callable<String> sleepFor3Sec = () -> {
            Thread.sleep(3000L);
            return "3 Seconds";
        };

        Callable<String> sleepFor1Sec = () -> {
            Thread.sleep(1000L);
            return "1 Seconds";
        };

        List<Future<String>> futures = singleThreadExecutor.invokeAll(Arrays.asList(sleepFor3Sec, sleepFor2Sec, sleepFor1Sec));
        for (Future<String> f : futures) {
            System.out.println(f.get());
        }

        System.out.println("===========================");

        String returnSingleThread = singleThreadExecutor.invokeAny(Arrays.asList(sleepFor3Sec, sleepFor2Sec, sleepFor1Sec));
        String return2ThreadPool = fixedThreadPoolBy2.invokeAny(Arrays.asList(sleepFor3Sec, sleepFor2Sec, sleepFor1Sec));
        String return4ThreadPool = fixedThreadPoolBy4.invokeAny(Arrays.asList(sleepFor3Sec, sleepFor2Sec, sleepFor1Sec));

        System.out.println("invokeAny");
        System.out.println(returnSingleThread);
        System.out.println(return2ThreadPool);
        System.out.println(return4ThreadPool);

        singleThreadExecutor.shutdown();
        fixedThreadPoolBy2.shutdown();
        fixedThreadPoolBy4.shutdown();
    }

    private static void useFutureApi() throws InterruptedException, ExecutionException {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        Callable<String> stringCallable = () -> {
            Thread.sleep(3000L);
            return "after 3 seconds";
        };

        Future<String> futureGet = singleThreadExecutor.submit(stringCallable);

        System.out.println("try Get !");
        System.out.println("Start~");
        System.out.println("is Done? -> " + futureGet.isDone());

        System.out.println(futureGet.get());
        System.out.println("is Done? -> " + futureGet.isDone());

        System.out.println("End~");

        System.out.println("===========================");

        Future<String> futureCancel = singleThreadExecutor.submit(stringCallable);
        System.out.println("try Cancel !");
        System.out.println("Start~");
        System.out.println("is Done? -> " + futureCancel.isDone());

        futureCancel.cancel(true);
        System.out.println("is Done? -> " + futureCancel.isDone());

        futureCancel.get();

        singleThreadExecutor.shutdown();
    }

    private static void useCallableAndFuture() throws InterruptedException, ExecutionException {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        Callable<String> stringCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello1";
            }
        };

        Callable<String> stringCallable2 = () -> "hello2";

        Future<String> stringFuture = singleThreadExecutor.submit(() -> "hello");
        Future<String> stringFuture1 = singleThreadExecutor.submit(stringCallable);
        Future<String> stringFuture2 = singleThreadExecutor.submit(stringCallable2);

        System.out.println(stringFuture.get());
        System.out.println(stringFuture1.get());
        System.out.println(stringFuture2.get());

        singleThreadExecutor.shutdown();
    }
}
