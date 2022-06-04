package com.example.java8.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CompletableFutureApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        makeCompletableFutures();

        useCallBackAPI();

        assignExecutors();

        joinFutures();

        joinFutures2();

        useExceptionHandling();

    }

    private static void useExceptionHandling() throws InterruptedException, ExecutionException {
        boolean throwError = true;
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("hello: " + Thread.currentThread().getName());
            return "hello";

        }).exceptionally(ex -> {
            System.out.println(ex);
            return "Error!";
        });

        System.out.println(future.get());
        System.out.println();

        CompletableFuture<String> handleFuture = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("hello: " + Thread.currentThread().getName());
            return "Hello";

        }).handle((s, e) -> {
            if (e != null) {
                System.out.println(e);
                return "Error!";
            }
            return s + " Success!";
        });

        System.out.println(handleFuture.get());
    }

    private static void joinFutures2() throws InterruptedException, ExecutionException {
        CompletableFuture<String> w = CompletableFuture.supplyAsync(() -> {
            System.out.println("w: " + Thread.currentThread().getName());
            return "w";
        });

        CompletableFuture<String> o = CompletableFuture.supplyAsync(() -> {
            System.out.println("o: " + Thread.currentThread().getName());
            return "o";
        });

        CompletableFuture<String> r = CompletableFuture.supplyAsync(() -> {
            System.out.println("r: " + Thread.currentThread().getName());
            return "r";
        });

        CompletableFuture<String> k = CompletableFuture.supplyAsync(() -> {
            System.out.println("k: " + Thread.currentThread().getName());
            return "k";
        });

        CompletableFuture<Void> futures = CompletableFuture.allOf(w, o, r, k)
                .thenAccept(System.out::println);

        futures.get();
        System.out.println();

        List<CompletableFuture<String>> futureList = Arrays.asList(w, o, r, k);
        CompletableFuture[] futureArray = futureList.toArray(new CompletableFuture[futureList.size()]);

        CompletableFuture<List<String>> results = CompletableFuture.allOf(futureArray)
                .thenApply(v -> futureList.stream().map(CompletableFuture::join).collect(Collectors.toList()));
        results.get().forEach(System.out::println);
        System.out.println();

        CompletableFuture<Void> futureAnyOf = CompletableFuture.anyOf(w, o, r, k).thenAccept(System.out::println);
        futureAnyOf.get();
    }

    private static void joinFutures() throws InterruptedException, ExecutionException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello: " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = getWorld("Hello");

        System.out.println(hello.get());
        System.out.println(world.get());
        System.out.println();

        // 두 개의 future 간의 의존성이 있는 경우
        CompletableFuture<String> composeFuture = hello.thenCompose(CompletableFutureApp::getWorld);
        System.out.println(composeFuture.get());
        System.out.println();

        // 두 개의 future 간의 의존성이 없는 경우
        CompletableFuture<String> combineFuture = hello.thenCombine(world, (h, w) -> h + " " + w);
        System.out.println(combineFuture.get());
        System.out.println();
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

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World: " + Thread.currentThread().getName());
            return message + " World";
        });
    }
}
