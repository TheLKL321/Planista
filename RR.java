package com.thelkl;

import java.util.ArrayList;
import java.util.LinkedList;

public class RR extends Strategy {
    private int q, currentQ;

    public RR(ArrayList<Task> allTasks, int q) {
        super(new LinkedList<>(), new Dispenser(new LinkedList<>(allTasks)), allTasks);
        this.q = q;
        this.currentQ = q;
    }

    @Override
    protected void handleTasks() {
        if (currentQ == 0) {
            queuedTasks.add(queuedTasks.poll());
            currentQ = q;
        }

        Task handledTask = queuedTasks.element();
        handledTask.getHandled();

        for (Task task : queuedTasks) {
            if (task.getId() != handledTask.getId())
                task.waitInQueue();
        }

        if (handledTask.ifCompleted()) {
            queuedTasks.poll();
            currentQ = q;
        } else
            this.currentQ--;
    }

    @Override
    public String toString() {
        return "RR-" + q;
    }
}
