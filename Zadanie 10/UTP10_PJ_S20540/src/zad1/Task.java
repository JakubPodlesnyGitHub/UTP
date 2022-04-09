package zad1;

import java.util.Random;
import java.util.concurrent.*;

public class Task extends Thread implements Callable<Integer> {
    private String name;
    private Random random;
    private int limitOfThread;
    private NewTaskButton newTaskButton;
    private Future<Integer> resultTASK;

    private boolean suspended = false;

    public Task(String name, int limitOfThread, NewTaskButton newTaskButton) {
        this.name = name;
        this.limitOfThread = limitOfThread;
        this.newTaskButton = newTaskButton;
        random = new Random();
    }


    @Override
    public Integer call() throws Exception {
        int sum = 0;
        int randomNumber;
        while (sum <= limitOfThread) {
            taskWait();
            randomNumber = random.nextInt(100) + 1;
            newTaskButton.getTextArea().append(name + "(" + "limit = " + limitOfThread + ") " + randomNumber + "," + "sum = " + sum + "\n");
            sum += randomNumber;
            Thread.sleep(random.nextInt(5000));
        }
        if (Thread.currentThread().isInterrupted())
            return null;
        afterTaskDone();
        return sum;
    }

    public void afterTaskDone() throws InterruptedException {
        newTaskButton.getTextArea().append(name + ": DONE!\n");
        newTaskButton.setEnabled(false);
        newTaskButton.setText(name + ": DONE!");
        Thread.sleep(2000);
        newTaskButton.getPanel().remove(newTaskButton);
        newTaskButton.getList().remove(newTaskButton);
        newTaskButton.getPanel().revalidate();
        newTaskButton.getPanel().repaint();
    }


    ///////////////////////// 3 metody do sterowania zadaniem/watkiem////////////////////////////////////////////////
    public void taskStart() {
        try {
            Callable<Integer> taskForResult = this;
            resultTASK = newTaskButton.getExecutorService().submit(taskForResult);
        } catch (RejectedExecutionException exc) {
            newTaskButton.getTextArea().append("Execution rejected\n");
        }
    }

    public void taskStop() {
        suspended = true;
    }

    public void taskCancel() {
        resultTASK.cancel(true);
        newTaskButton.getTextArea().append(name + ": Cancelled!\n");
    }

    /////////////////////////////////2 metody pomocnicze///////////////////////////////////////////////

    public void taskWait() throws InterruptedException {
        try {
            synchronized (this) {
                while (suspended) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            newTaskButton.getExecutorService().awaitTermination(2, TimeUnit.SECONDS);
        }
    }

    public void taskResume() {
        suspended = false;
        synchronized (this) {
            notify();
        }
    }
}