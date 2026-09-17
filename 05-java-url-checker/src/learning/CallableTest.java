package learning;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTest {

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Callable<String> task1 = () -> {
            System.out.println("Task 1 started");

            Thread.sleep(2000);

            return "Result from Task 1";
        };

        Callable<String> task2 = () -> {
            System.out.println("Task 2 started");

            Thread.sleep(2000);

            return "Result from Task 2";
        };

        Callable<String> task3 = () -> {
            System.out.println("Task 3 started");

            Thread.sleep(2000);

            return "Result from Task 3";
        };

        Future<String> future1 = executorService.submit(task1);
        Future<String> future2 = executorService.submit(task2);
        Future<String> future3 = executorService.submit(task3);

        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());

        executorService.shutdown();
    }
}