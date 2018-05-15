package com.thelkl;

public class Task {
    private int id, when, initialRequired;
    private double required, timeExisting;

    public Task(int id, int when, int required) {
        this.id = id;
        this.when = when;
        this.initialRequired = required;
        this.required = required;
    }

    public void getHandled(){
        this.required -= 1;
        timeExisting += 1;
    }

    public void getHandled(double timeWorking, double timePassed){
        this.required -= timeWorking;
        timeExisting += timePassed;
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

    public double getTimeFromZero() {
        return timeExisting + when;
    }
}
