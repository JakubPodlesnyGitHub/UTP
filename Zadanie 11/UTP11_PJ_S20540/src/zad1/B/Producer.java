package zad1.B;


import java.util.Random;
import java.util.concurrent.Callable;

public class Producer implements Callable<Integer> {
    private Buffer buffer;
    private Random random;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
        random = new Random();
    }

    @Override
    public Integer call() throws Exception {
        int number;
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                return 0;
            }
            number = random.nextInt(100) + 1;
            buffer.put(number);
            Thread.sleep(random.nextInt(2000));
        }
    }
}
