package com.ohlengr.concurrentworkers;

import java.util.concurrent.*;

public class Application {
    public static void main(String[] args) {

        Callable<Integer> value = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName());
                return 100;
            }
        };

        Callable<Integer> value1 = ()-> {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
            return 200;
        };

        Callable<Integer> value2 = ()->{
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName());
            return 300;
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            Future<Integer> future = executorService.submit(value);
            Future<Integer> future1 = executorService.submit(value1);
            Future<Integer> future2 = executorService.submit(value2);
            System.out.println(future.get());
            System.out.println(future1.get());
            System.out.println(future2.get());
        }catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
