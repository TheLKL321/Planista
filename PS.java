package com.thelkl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class PS extends Strategy {
    private static final double EPSILON = 0.0001;

    public PS(ArrayList<Task> allTasks) {
        super(new PriorityQueue<>(new TaskComparator()), new Dispenser(new LinkedList<>(allTasks)), allTasks);
    }

    @Override
    protected void handleTasks() {
        double timeLeft = 1.0;
        int divider = queuedTasks.size();

        double timePassed, timeWorked;
        while (!queuedTasks.isEmpty() && queuedTasks.element().getRequired() < timeLeft / divider){
            timeWorked = queuedTasks.element().getRequired();
            timePassed = timeWorked * divider;
            for (Task task : queuedTasks)
                task.getHandled(timeWorked, timePassed);
            timeLeft -= timePassed;

            while (!queuedTasks.isEmpty() && queuedTasks.element().ifCompleted())
                queuedTasks.poll();

            divider = queuedTasks.size();
        }

        if (timeLeft > 0) {
            for (Task task : queuedTasks)
                task.getHandled(timeLeft / divider , timeLeft);

            while (!queuedTasks.isEmpty() && queuedTasks.element().ifCompleted(EPSILON))
                queuedTasks.poll();
        }
    }

    @Override
    public String toString() {
        return "PS";
    }
}
