package zad1.B;


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
            if (Thread.currentThread().isInterrupted()) {
                return 0;
            }
            buffer.get();
            Thread.sleep(random.nextInt(2000));
        }
    }
}
