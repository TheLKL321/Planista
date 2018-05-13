package com.thelkl;

public class Proces {
    private int id, when;
    private double required, timeWaiting, timeCompleting;

    public Proces(int id, int when, int required) {
        this.id = id;
        this.when = when;
        this.required = required;
    }

    public void getHandled(double timeWorking){
        this.required -= timeWorking;
        timeCompleting += timeWorking;
    }

    public void waitInQueue(){
        timeWaiting++;
    }

    public boolean ifCompleted(){
        return required == 0;
    }

    public int getId() {
        return id;
    }

    public int getWhen() {
        return when;
    }

    public double getRequired() {
        return required;
    }
}
