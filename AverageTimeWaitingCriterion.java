package com.thelkl;

import java.util.ArrayList;

public class AverageTimeWaitingCriterion implements Criterion{

    @Override
    public void report(ArrayList<Task> allTasks) {
        double summedTimeWaiting = 0.0;
        for (Task task : allTasks) {
            summedTimeWaiting += task.getTimeWaiting();
        }

        System.out.printf("Średni czas oczekiwania: %.2f\n", Math.round((summedTimeWaiting / allTasks.size()) * 100.0) / 100.0);
    }
}
