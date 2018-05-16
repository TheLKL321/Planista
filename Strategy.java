package com.thelkl;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Queue;

public abstract class Strategy {
    private ArrayList<Task> allTasks;
    private double summedTimeWaiting = 0, summedTimeExisting = 0;
    protected Queue<Task> queuedTasks;
    protected Dispenser dispenser;

    public Strategy(Queue<Task> queuedTasks, Dispenser dispenser, ArrayList<Task> allTasks) {
        this.queuedTasks = queuedTasks;
        this.dispenser = dispenser;
        dispenser.dispense(queuedTasks);
        this.allTasks = allTasks;
    }

    protected void updateTimes(Task task){
        summedTimeWaiting += task.getTimeWaiting();
        summedTimeExisting += task.getTimeExisting();
    }

    //for the first process (or every), work on it (them). If any finished, update average times.
    //for every process, update waiting time
    abstract protected void handleTasks();

    private void tick (){
        handleTasks();
        dispenser.dispense(queuedTasks);
    }

    //TODO: two decimal places
    public void run (){
        while (!queuedTasks.isEmpty() && dispenser.ifEmpty())
            tick();

        System.out.println("Strategie: " + toString());
        allTasks.sort(Comparator.comparingDouble(Task::getTimeFromZero));
        for (Task task : allTasks) {
            System.out.printf("[%d %d %.2f]", task.getId(), task.getWhen(), Math.round(task.getTimeFromZero() * 100.0) / 100.0);
        }
        System.out.println();
        System.out.printf("Średni czas obrotu: %.2f\n", Math.round((summedTimeExisting / allTasks.size()) * 100.0) / 100.0);
        System.out.printf("Średni czas oczekiwania: %.2f\n", Math.round((summedTimeWaiting / allTasks.size()) * 100.0) / 100.0);
    }

    abstract public String toString ();
}
