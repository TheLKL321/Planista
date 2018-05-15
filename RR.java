package com.thelkl;

import java.util.ArrayList;
import java.util.LinkedList;

public class RR extends Strategy {
    private int q, currentQ;

    public RR(LinkedList<Task> processesToDispense, ArrayList<Task> allProcesses, int q) {
        super(new Dispenser(processesToDispense), allProcesses);
        queuedTasks = new LinkedList<>();
        this.q = q;
        this.currentQ = q;
    }

    @Override
    protected void handleTasks() {
        if (currentQ == 0)
            queuedTasks.add(queuedTasks.poll());

        Task handledTask = queuedTasks.element();
        handledTask.getHandled();
        for (Task task : queuedTasks)
            if (task.getId() != 1) task.waitInQueue();
        if (handledTask.ifCompleted()) {
            updateAverageTimes(queuedTasks.poll());
            currentQ = q;
        } else
            this.currentQ--;
    }

    @Override
    public String toString() {
        return "RR-" + q;
    }
}
