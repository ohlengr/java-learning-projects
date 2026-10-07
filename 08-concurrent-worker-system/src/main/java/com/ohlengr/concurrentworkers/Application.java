package com.ohlengr.concurrentworkers;

import java.util.concurrent.*;

public class Application {
    public static void main(String[] args) {

        Callable<Integer> value = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
//                throw new Exception("Something went wrong");
                throw new IllegalArgumentException("Invalid value");
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try {
            Future<Integer> future = executorService.submit(value);
            System.out.println(future.get());
        }catch (ExecutionException e) {
//            System.out.println("Something went wrong");
            System.out.println(e.getCause());
        }catch (InterruptedException e){
            System.out.println("Interrupted");
        }
        executorService.shutdown();
    }
}
