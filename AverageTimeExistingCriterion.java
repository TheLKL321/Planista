package com.thelkl;

import java.util.ArrayList;

public class AverageTimeExistingCriterion implements Criterion {

    @Override
    public void report(ArrayList<Task> allTasks) {
        double summedTimeExisting = 0.0;
        for (Task task : allTasks) {
            summedTimeExisting += task.getTimeExisting();
        }

        System.out.printf("Średni czas obrotu: %.2f\n", Math.round((summedTimeExisting / allTasks.size()) * 100.0) / 100.0);
    }
}
