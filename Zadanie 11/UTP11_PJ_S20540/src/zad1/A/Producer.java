package zad1.A;

import java.util.Random;
import java.util.concurrent.Callable;

public class Producer implements Callable<Integer> {
    private Buffer buffer;
    private Random random;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
        this.random = new Random();
    }

    @Override
    public Integer call() throws Exception {
        int number;
        while (true) {
            number = random.nextInt(100) + 1;
            buffer.put(number);

            if (Thread.currentThread().isInterrupted()) {
                buffer.wakeUPConsumer();
                System.out.println("Producer zakancza sie");
                return 0;
            }

            Thread.sleep(random.nextInt(2000));
        }
    }
}
