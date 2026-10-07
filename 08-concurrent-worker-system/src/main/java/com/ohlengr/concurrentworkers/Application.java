package com.ohlengr.concurrentworkers;

import java.util.concurrent.*;

public class Application {
    public static void main(String[] args) throws Exception {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " Work Done");
            }
        };

//        Thread thread = new Thread(task);
//        thread.start();
//
//        Thread thread2 = new Thread(task);
//        thread2.start();

        Callable<Integer> value = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName());
                return 10+30;
            }
        };

//        int result = value.call();
//        System.out.println(result);

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(value);
        System.out.println("Future received");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Main is doing other work: " + i);
            Thread.sleep(500);
        }
        System.out.println(future.get());
        executorService.shutdown();
    }
}
