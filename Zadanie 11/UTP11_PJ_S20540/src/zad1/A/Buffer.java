package zad1.A;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private int size;
    private Lock lock;
    private Queue<Integer> queueNumbers;
    private Condition conditionConsumer;
    private Condition conditionProducer;

    public Buffer(int size) {
        this.size = size;
        lock = new ReentrantLock();
        queueNumbers = new ArrayDeque<>();
        conditionConsumer = lock.newCondition();
        conditionProducer = lock.newCondition();
    }

    public int get() throws InterruptedException {
        lock.lock();
        int elementTMP=0;
        try {
            stopConsumer();
            elementTMP = queueNumbers.poll();
            System.out.println("ZWRACANA JEST LICZBA: " + elementTMP + " GET()");
            System.out.println("Rozmiar kolejki wynosi: " + queueNumbers.size());
            conditionProducer.signal();
        } finally {
            lock.unlock();
        }
        return elementTMP;
    }

    public void put(int n) throws InterruptedException {
        lock.lock();
        try {
            stopProducer();
            queueNumbers.add(n);
            conditionConsumer.signal();
            System.out.println("WKLADANA JEST LICZBA: " + n + " PUT()");
            System.out.println("Rozmiar kolejki wynosi: " + queueNumbers.size());

        } finally {
            lock.unlock();
        }
    }

    public void stopProducer() throws InterruptedException {
        System.out.println("Producer czeka na dostep do buffora");
        while (queueNumbers.size() >= size) {
            conditionProducer.await();
        }
        System.out.println("Producer nie czeka.");
    }


    public void stopConsumer() throws InterruptedException {
        System.out.println("Consumer czeka na dostep do buffora");
        while (queueNumbers.size() <= 0) {
            conditionConsumer.await();
        }
        System.out.println("Consumer nie czeka.");
    }

    public void wakeUpProducer() {
        lock.lock();
        try {
            System.out.println("WakeUpProducer");
            conditionProducer.signal();
        } finally {
            lock.unlock();
        }
    }

    public void wakeUPConsumer() {
        lock.lock();
        try {
            System.out.println("WakeUpConsumer");
            conditionProducer.signal();
        } finally {
            lock.unlock();
        }
    }

}
