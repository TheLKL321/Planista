package com.thelkl;

import javafx.print.Collation;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Planista {
    protected int currentTime = 0;
    protected LinkedList<Proces> processes = new LinkedList<>();
    protected Dispenser dispenser;

    public Planista(Dispenser dispenser) {
        this.dispenser = dispenser;
        dispenser.dispense(processes);
    }

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
