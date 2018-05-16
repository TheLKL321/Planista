package com.thelkl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class PS extends Strategy {

    public PS(ArrayList<Task> allTasks) {
        super(new PriorityQueue<>(new TaskComparator()), new Dispenser(new LinkedList<>(allTasks)), allTasks);
    }

    //TODO:fix ps
    @Override
    protected void handleTasks() {
        double timeLeft = 1.0;
        int divider = queuedTasks.size();

        double timePassed;
        while (!queuedTasks.isEmpty() && queuedTasks.element().getRequired() < (1 / divider) * timeLeft){
            timePassed = queuedTasks.element().getRequired() * divider;
            for (Task task : queuedTasks)
                task.getHandled(queuedTasks.element().getRequired(), timePassed);
            timeLeft -= timePassed;

            while (!queuedTasks.isEmpty() && queuedTasks.element().ifCompleted())
                updateTimes(queuedTasks.poll());
        }

        if (timeLeft > 0) {
            for (Task task : queuedTasks)
                task.getHandled(timeLeft / divider, timeLeft);
            while (!queuedTasks.isEmpty() && queuedTasks.element().ifCompleted())
                updateTimes(queuedTasks.poll());
        }
    }

    @Override
    public String toString() {
        return "PS";
    }
}
