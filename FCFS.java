package com.thelkl;

import java.util.ArrayList;
import java.util.LinkedList;

public class FCFS extends Planista {

    public FCFS(LinkedList<Proces> processesToDispense, ArrayList<Proces> allProcesses) {
        super(new Dispenser(processesToDispense), allProcesses);
    }

    @Override
    protected void handleThisProcess(Proces process) {

    }

    @Override
    protected void handleProcesses() {

    }

    @Override
    public String toString() {
        return "FCFS";
    }
}
