package com.thelkl;

public class Task {
    private int id, when;
    private double required, timeExisting, timeWithProcessor;

    public Task(int id, int when, double required) {
        this.id = id;
        this.when = when;
        this.required = required;
    }

    public void getHandled(){
        this.required--;
        this.timeExisting++;
        this.timeWithProcessor++;
    }

    public void getHandled(double timeWorking, double timePassed){
        this.required -= timeWorking;
        timeExisting += timePassed;
        timeWithProcessor += timePassed;
    }

    public void waitInQueue(){
        timeExisting++;
    }

    public boolean ifCompleted(){
        return required <= 0;
    }

    public double getTimeWaiting() {
        return timeExisting - timeWithProcessor;
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
