package com.thelkl;

import javafx.print.Collation;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Planista {
    private int completedProcesses = 0;
    private ArrayList<Proces> allProcesses;
    protected double averageTimeWaiting = 0, averageTimeCompleting = 0;
    protected LinkedList<Proces> queuedProcesses = new LinkedList<>();
    protected Dispenser dispenser;

    public Planista(Dispenser dispenser, ArrayList<Proces> allProcesses) {
        this.dispenser = dispenser;
        dispenser.dispense(queuedProcesses);
        this.allProcesses = allProcesses;
    }

    //TODO: try summing up all the times and dividing by allProcesses.size()
    private void updateAverageTimes(int initialRequired, double timeExisting){
        this.completedProcesses++;
        averageTimeWaiting = (averageTimeWaiting + timeExisting - initialRequired) / completedProcesses;
        averageTimeCompleting = (averageTimeCompleting + timeExisting) / completedProcesses;
    }

    //update "required". If finished, update average times
    abstract protected void handleThisProcess (Proces process);

    //for the first process (or every), work on it (them). If any finished, update average times.
    //for every process, update waiting time
    abstract protected void handleProcesses ();

    private void tick (){
        handleProcesses();
        dispenser.dispense(queuedProcesses);
    }

    public void run (){
        while (!queuedProcesses.isEmpty() && dispenser.ifEmpty())
            tick();

        System.out.println("Strategie: " + toString());
        // TODO: sortuj allProcesses by (process.getWhen() + process.getTimeExisting())
        for (Proces process : allProcesses) {
            System.out.print("[" + process.getId() + " " + process.getWhen() + " " + (process.getWhen() + process.getTimeExisting()) + "]");
        }
        System.out.println();
        System.out.println("Średni czas obrotu" + averageTimeCompleting);
        System.out.println("Średni czas oczekiwania:" + averageTimeWaiting);
    }

    abstract public String toString ();
}
