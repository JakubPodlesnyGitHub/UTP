package zad1.B;


import java.util.concurrent.ArrayBlockingQueue;

public class Buffer {
    private ArrayBlockingQueue<Integer> arrayBlockingQueue;

    public Buffer(int size) {
        arrayBlockingQueue = new ArrayBlockingQueue(size);
    }

    public int get() {
        int elementTMP = 0;
        try {
            elementTMP = arrayBlockingQueue.take();
            System.out.println("ZWRACANA JEST LICZBA: " + elementTMP + " GET()");
            System.out.println("Rozmiar kolejki wynosi: " + arrayBlockingQueue.size());
        } catch (InterruptedException e) {
            return 1;
        }

        return elementTMP;
    }

    public void put(int n) {
        try {
            arrayBlockingQueue.put(n);
            System.out.println("WKLADANA JEST LICZBA: " + n + " PUT()");
            System.out.println("Rozmiar kolejki wynosi: " + arrayBlockingQueue.size());
        } catch (InterruptedException ignored) {

        }

    }

}
