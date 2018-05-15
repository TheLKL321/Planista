package com.thelkl;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Dispenser {
    private int nextTime = 0;
    private Queue<Task> tasksToDispense;

    public Dispenser(Queue<Task> tasksToDispense) {
        this.tasksToDispense = tasksToDispense;
    }

    public void dispense (Queue<Task> destination){
        try {
            while (!tasksToDispense.isEmpty() && tasksToDispense.element().getWhen() == nextTime)
                destination.add(tasksToDispense.remove());
            this.nextTime++;
        } catch (NoSuchElementException e){
            // No handling needed
        }
    }

    public boolean ifEmpty (){
        return tasksToDispense.isEmpty();
    }
}
