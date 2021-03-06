package com.thelkl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SJF extends Strategy {

    public SJF(ArrayList<Task> allTasks) {
        super(new PriorityQueue<>(new TaskComparator()), new Dispenser(new LinkedList<>(allTasks)), allTasks);
    }

    @Override
    protected void handleTasks() {
        Task handledTask = queuedTasks.element();
        while (!handledTask.ifCompleted()) {
            handledTask.getHandled();
            for (Task task : queuedTasks) {
                if (task.getId() != handledTask.getId())
                    task.waitInQueue();
            }
            if (!handledTask.ifCompleted())
                dispenser.dispense(queuedTasks);
        }

        queuedTasks.remove(handledTask);
    }

    @Override
    public String toString() {
        return "SJF";
    }
}
