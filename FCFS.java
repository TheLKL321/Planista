package com.thelkl;

import java.util.ArrayList;
import java.util.LinkedList;

public class FCFS extends Planista {

    public FCFS(LinkedList<Proces> processesToDispense) {
        super(new Dispenser(processesToDispense));
    }

    @Override
    protected void handleThisProcess(Proces process) {

    }

    @Override
    protected void handleProcesses() {

    }
}
