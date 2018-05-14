package com.thelkl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Strategy {
    private int completedTasks = 0;
    private ArrayList<Task> allTasks;
    protected double averageTimeWaiting = 0, averageTimeCompleting = 0;
    protected Queue<Task> queuedTasks;
    protected Dispenser dispenser;

    public Strategy(Dispenser dispenser, ArrayList<Task> allTasks) {
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

    //update "required". If finished, update average times
    abstract protected void handleThisTask(Task task);

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
        // TODO: sortuj allTasks by (process.getWhen() + process.getTimeExisting())
        for (Task process : allTasks) {
            System.out.print("[" + process.getId() + " " + process.getWhen() + " " + (process.getWhen() + process.getTimeExisting()) + "]");
        }
        System.out.println();
        System.out.println("Średni czas obrotu" + averageTimeCompleting);
        System.out.println("Średni czas oczekiwania:" + averageTimeWaiting);
    }

    abstract public String toString ();
}
