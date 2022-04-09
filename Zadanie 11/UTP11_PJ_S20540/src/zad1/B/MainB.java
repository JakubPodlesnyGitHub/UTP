package zad1.B;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainB {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Buffer buffer = new Buffer(10);
        Producer p = new Producer(buffer);
        Consumer c = new Consumer(buffer);

        try {
            executorService.submit(p);
            executorService.submit(c);
            Thread.sleep(15000);
            //zamykanie pracy watkow i wykonawcy
            executorService.shutdownNow();
            //System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
