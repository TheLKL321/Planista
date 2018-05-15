package com.thelkl;

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
        summedTimeExisting = task.getTimeExisting();
    }

    //for the first process (or every), work on it (them). If any finished, update average times.
    //for every process, update waiting time
    abstract protected void handleTasks();

    private void tick (){
        handleTasks();
        dispenser.dispense(queuedTasks);
    }

    public void run (){
        while (!queuedTasks.isEmpty() && dispenser.ifEmpty())
            tick();

        System.out.println("Strategie: " + toString());
        allTasks.sort(Comparator.comparingDouble(Task::getTimeFromZero));
        for (Task task : allTasks) {
            System.out.print("[" + task.getId() + " " + task.getWhen() + " " + task.getTimeFromZero() + "]");
        }
        System.out.println();
        System.out.println("Średni czas obrotu: " +  (summedTimeExisting / allTasks.size()));
        System.out.println("Średni czas oczekiwania: " + (summedTimeWaiting / allTasks.size()));
    }

    abstract public String toString ();
}
