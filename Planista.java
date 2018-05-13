package com.thelkl;

import javafx.print.Collation;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Planista {
    private int completedProcesses = 0;
    protected int currentTime = 0;
    protected double averageTimeWaiting = 0, averageTimeCompleting = 0;
    protected LinkedList<Proces> processes = new LinkedList<>();
    protected Dispenser dispenser;

    public Planista(Dispenser dispenser) {
        this.dispenser = dispenser;
        dispenser.dispense(processes);
    }

    private void updateAverageTimes(double timeWaiting, double timeCompleting){
        this.completedProcesses++;
        averageTimeWaiting = (averageTimeWaiting + timeWaiting) / completedProcesses;
        averageTimeCompleting = (averageTimeCompleting + timeCompleting) / completedProcesses;
    }

    //update "required". If finished, update average times
    abstract protected void handleThisProcess (Proces process);

    //for the first process (or every), work on it (them). If any finished, update average times.
    //for every process, update waiting time
    abstract protected void handleProcesses ();

    private void tick (){
        handleProcesses();
        currentTime++;
        dispenser.dispense(processes);
    }

    public void run (){
        while (!processes.isEmpty() && dispenser.ifEmpty()){
            tick();
        }

        //TODO sout stuff
    }
}
