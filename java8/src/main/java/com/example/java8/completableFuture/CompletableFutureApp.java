package com.example.java8.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        makeCompletableFutures();

        useCallBackAPI();

        assignExecutors();

    }

    private static void assignExecutors() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        CompletableFuture<Void> futureThenAccept = CompletableFuture.supplyAsync(() -> {
            System.out.println("use ForkJoinPool: " + Thread.currentThread().getName());
            return "wow!";
        }).thenAccept(s ->
                System.out.println(String.format("%s : %s | %s", "thenAccept", s.toUpperCase(), Thread.currentThread().getName())));
        futureThenAccept.get();
        System.out.println();

        CompletableFuture<Void> futureThenAcceptOtherPools = CompletableFuture.supplyAsync(() -> {
            System.out.println("assign Pool: " + Thread.currentThread().getName());
            return "wow!";
        }, executorService).thenAccept(s ->
                System.out.println(String.format("%s : %s | %s", "thenAccept", s.toUpperCase(), Thread.currentThread().getName())));
        futureThenAcceptOtherPools.get();
        System.out.println();

        CompletableFuture<Void> futureThenAcceptOtherPools2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("assign Pool-callBack: " + Thread.currentThread().getName());
            return "wow!";
        }, executorService).thenAcceptAsync(s -> {
                System.out.println(String.format("%s : %s | %s",
                        "thenAccept",
                        s.toUpperCase(),
                        Thread.currentThread().getName()));
        }, executorService);
        futureThenAcceptOtherPools2.get();
    }

    private static void useCallBackAPI() throws InterruptedException, ExecutionException {
        CompletableFuture<String> futureThenApply = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            return "wow!";
        }).thenApply(s -> String.format("%s : %s | %s", "thenApply", s.toUpperCase(), Thread.currentThread().getName()));
        System.out.println(futureThenApply.get());
        System.out.println();

        CompletableFuture<Void> futureThenAccept = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            return "wow!";
        }).thenAccept(s ->
                System.out.println(String.format("%s : %s | %s", "thenAccept", s.toUpperCase(), Thread.currentThread().getName())));
        futureThenAccept.get();
        System.out.println();

        CompletableFuture<Void> futureThenRun = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            return "wow!";
        }).thenRun(() -> System.out.println("thenRun: " + Thread.currentThread().getName()));
        futureThenRun.get();
        System.out.println();
    }

    private static void makeCompletableFutures() throws InterruptedException, ExecutionException {
        CompletableFuture<Object> future = new CompletableFuture<>();
        future.complete("Hello Future: " + Thread.currentThread().getName());
        System.out.println(future.get());

        CompletableFuture<String> future2 =
                CompletableFuture.completedFuture("Hello Future By Static Method: " + Thread.currentThread().getName());
        System.out.println(future2.get());

        CompletableFuture<Void> futureNoReturn =
                CompletableFuture.runAsync(() -> System.out.println("Hello RunAsync: " + Thread.currentThread().getName()));
        System.out.println(futureNoReturn.get());

        CompletableFuture<String> futureHasReturn = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello supplyAsync: " + Thread.currentThread().getName());
            return "우와~";
        });
        System.out.println(futureHasReturn.get());
    }
}
