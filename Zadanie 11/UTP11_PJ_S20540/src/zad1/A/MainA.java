package zad1.A;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainA {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Buffer buffer = new Buffer(10);
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        try {
            executorService.submit(producer);
            executorService.submit(consumer);
            Thread.sleep(15000);
            //zamykanie pracy watkow i wykonawcy
            executorService.shutdownNow();
            //buffer.wakeUpProducer();
            //buffer.wakeUPConsumer();
            //System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
