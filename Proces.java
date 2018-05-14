package com.thelkl;

public class Proces {
    private int id, when, initialRequired;
    private double required, timeExisting;

    public Proces(int id, int when, int required) {
        this.id = id;
        this.when = when;
        this.initialRequired = required;
        this.required = required;
    }

    public void getHandled(double timeWorking, int divider){
        this.required -= timeWorking;
        timeExisting += timeWorking * divider;
    }

    public void waitInQueue(){
        timeExisting++;
    }

    public boolean ifCompleted(){
        return required == 0;
    }

    public double getTimeWaiting() {
        return timeExisting - initialRequired;
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

    public double getTimeExisting() {
        return timeExisting;
    }
}
