package com.thelkl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SRT extends Strategy {

    public SRT(LinkedList<Task> processesToDispense, ArrayList<Task> allProcesses) {
        super(new Dispenser(processesToDispense), allProcesses);
        queuedTasks = new PriorityQueue<>(Comparator.comparingDouble(Task::getRequired));
    }

    // TODO: handle exceptions from queue
    @Override
    protected void handleTasks() {
        Task handledTask = queuedTasks.element();
        while (!handledTask.ifCompleted() && queuedTasks.element().getRequired() == handledTask.getRequired()) {
            handledTask.getHandled();
            for (Task task : queuedTasks)
                if (task.getId() != handledTask.getId()) task.waitInQueue();
            if (!handledTask.ifCompleted()) dispenser.dispense(queuedTasks);
        }

        if (handledTask.ifCompleted()) {
            updateAverageTimes(handledTask);
            queuedTasks.remove(handledTask);
        }
    }

    @Override
    public String toString() {
        return "SRT";
    }
}
