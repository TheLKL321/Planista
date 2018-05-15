package com.thelkl;

import java.util.ArrayList;
import java.util.LinkedList;

public class FCFS extends Strategy {

    public FCFS(ArrayList<Task> allTasks) {
        super(new LinkedList<>(), new Dispenser(new LinkedList<>(allTasks)), allTasks);
    }

    @Override
    protected void handleTasks() {
        Task handledTask = queuedTasks.element();
        handledTask.getHandled();
        for (Task task : queuedTasks)
            if (task.getId() != handledTask.getId()) task.waitInQueue();
        if (handledTask.ifCompleted()) updateTimes(queuedTasks.poll());
    }

    @Override
    public String toString() {
        return "FCFS";
    }
}
