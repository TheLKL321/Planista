package com.thelkl;

import java.util.ArrayList;
import java.util.LinkedList;

public class Dispenser {
    private int nextTime = 0;
    private LinkedList<Proces> processesToDispense;

    public Dispenser(LinkedList<Proces> processesToDispense) {
        this.processesToDispense = processesToDispense;
    }

    public void dispense (LinkedList<Proces> destination){
        // TODO
    }

    public boolean ifEmpty (){
        return processesToDispense.isEmpty();
    }
}
