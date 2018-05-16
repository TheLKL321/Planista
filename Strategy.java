package com.thelkl;

import java.util.ArrayList;
import java.util.Queue;

public abstract class Strategy {
    private ArrayList<Task> allTasks;
    protected Queue<Task> queuedTasks; // initialized in a child class's constructor
    protected Dispenser dispenser;

    public Strategy(Queue<Task> queuedTasks, Dispenser dispenser, ArrayList<Task> allTasks) {
        this.queuedTasks = queuedTasks;
        this.dispenser = dispenser;
        dispenser.dispense(queuedTasks);
        this.allTasks = allTasks;
    }

    abstract protected void handleTasks();

    private void tick (){
        handleTasks();
        dispenser.dispense(queuedTasks);
    }

    public void run (){
        while (!queuedTasks.isEmpty() || !dispenser.ifEmpty())
            tick();

        fullReport();
    }

    private void fullReport(){
        System.out.println("Strategie: " + toString());
        allTasks.sort(new TaskReportComperator());
        for (Task task : allTasks) {
            System.out.printf("[%d %d %.2f]", task.getId(), task.getWhen(), Math.round(task.getTimeFromZero() * 100.0) / 100.0);
        }
        System.out.println();
        // Add new criteria as needed
        (new AverageTimeExistingCriterion()).report(allTasks);
        (new AverageTimeWaitingCriterion()).report(allTasks);
    }

    abstract public String toString ();
}
