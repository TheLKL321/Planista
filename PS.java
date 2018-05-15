package com.thelkl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PS extends Strategy {

    public PS(Dispenser dispenser, ArrayList<Task> allTasks) {
        super(dispenser, allTasks);
        queuedTasks = new PriorityQueue<>(Comparator.comparingDouble(Task::getRequired));
    }

    @Override
    protected void handleTasks() {
        double timeLeft = 1.0;
        int divider = queuedTasks.size();

        double timePassed;
        while (queuedTasks.element().getRequired() < (1 / divider) * timeLeft){
            timePassed = queuedTasks.element().getRequired() * divider;
            for (Task task : queuedTasks)
                task.getHandled(queuedTasks.element().getRequired(), timePassed);
            timeLeft -= timePassed;

            while (queuedTasks.element().ifCompleted())
                updateAverageTimes(queuedTasks.poll());
        }

        if (timeLeft > 0) {
            for (Task task : queuedTasks)
                task.getHandled(queuedTasks.element().getRequired(), timeLeft);
            while (queuedTasks.element().ifCompleted())
                updateAverageTimes(queuedTasks.poll());
        }
    }

    @Override
    public String toString() {
        return "PS";
    }
}
