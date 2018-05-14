package com.thelkl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PS extends Strategy {

    public PS(Dispenser dispenser, ArrayList<Task> allTasks) {
        super(dispenser, allTasks);
        queuedTasks = new PriorityQueue<>(Comparator.comparingDouble(Task::getRequired));
    }

    @Override
    protected void handleThisTask(Task task) {

    }

    @Override
    protected void handleTasks() {

    }

    @Override
    public String toString() {
        return "PS";
    }
    // Sortuj wzgledem pozostalego zapotrzebowania, za kazdym tickiem sprawdzaj zapotrzebowanie pierwszego, jak jest
    // mniejsze od 1/n to wszystkim odejmij to zapotrzebowanie i dodaj czas rowny liczba pozostalych procesow przed
    // zakonczeniem pierwszego * to zapotrzebowanie


}
