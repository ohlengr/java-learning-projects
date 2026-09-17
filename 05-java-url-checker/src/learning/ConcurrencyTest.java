package learning;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyTest {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(int i=1; i<=5; i++){
            int taskNumber = i;
            executorService.submit(() -> {
                System.out.println("Task: " + taskNumber + " started");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Task: " + taskNumber + " finished");
            });
        }

        executorService.close();
    }
}
