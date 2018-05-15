package com.thelkl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Strategy {
    private int completedTasks = 0;
    private ArrayList<Task> allTasks;
    private double averageTimeWaiting = 0, averageTimeCompleting = 0;
    protected Queue<Task> queuedTasks;
    protected Dispenser dispenser;

    public Strategy(Queue<Task> queuedTasks, Dispenser dispenser, ArrayList<Task> allTasks) {
        this.queuedTasks = queuedTasks;
        this.dispenser = dispenser;
        dispenser.dispense(queuedTasks);
        this.allTasks = allTasks;
    }

    //TODO: try summing up all the times and dividing by allTasks.size()
    protected void updateAverageTimes(Task task){
        this.completedTasks++;
        averageTimeWaiting = (averageTimeWaiting + task.getTimeWaiting()) / completedTasks;
        averageTimeCompleting = (averageTimeCompleting + task.getTimeExisting()) / completedTasks;
    }

    //for the first process (or every), work on it (them). If any finished, update average times.
    //for every process, update waiting time
    abstract protected void handleTasks();

    private void tick (){
        if (!queuedTasks.isEmpty()) handleTasks();
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
        System.out.println("Średni czas obrotu" + averageTimeCompleting);
        System.out.println("Średni czas oczekiwania:" + averageTimeWaiting);
    }

    abstract public String toString ();
}
