package com.thelkl;

import java.util.ArrayList;
import java.util.LinkedList;

public class FCFS extends Strategy {

    public FCFS(LinkedList<Task> processesToDispense, ArrayList<Task> allProcesses) {
        super(new Dispenser(processesToDispense), allProcesses);
        queuedTasks = new LinkedList<>();
    }

    @Override
    protected void handleThisTask(Task task) {
        task.getHandled();
    }

    @Override
    protected void handleTasks() {
        Task handledTask = queuedTasks.peek();
        handleThisTask(handledTask);
        if (handledTask.ifCompleted()) updateAverageTimes(queuedTasks.poll());
        for (Task task : queuedTasks)
            if (task.getId() != 1) task.waitInQueue();
    }

    @Override
    public String toString() {
        return "FCFS";
    }
}
