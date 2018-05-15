package com.thelkl;

import java.util.ArrayList;
import java.util.LinkedList;

public class FCFS extends Strategy {

    public FCFS(LinkedList<Task> processesToDispense, ArrayList<Task> allProcesses) {
        super(new LinkedList<>(), new Dispenser(processesToDispense), allProcesses);
    }

    @Override
    protected void handleTasks() {
        Task handledTask = queuedTasks.element();
        handledTask.getHandled();
        for (Task task : queuedTasks)
            if (task.getId() != 1) task.waitInQueue();
        if (handledTask.ifCompleted()) updateAverageTimes(queuedTasks.poll());
    }

    @Override
    public String toString() {
        return "FCFS";
    }
}
