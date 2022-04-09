package zad1.A;

import java.util.Random;
import java.util.concurrent.Callable;

public class Consumer implements Callable<Integer> {
    private Buffer buffer;
    private Random random;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
        random = new Random();
    }

    @Override
    public Integer call() throws Exception {
        while (true) {
            buffer.get();
            if (Thread.currentThread().isInterrupted()) {
                buffer.wakeUpProducer();
                System.out.println("Consuemr zakancza sie");
                return 0;
            }
            Thread.sleep(random.nextInt(2000));

        }
    }
}
