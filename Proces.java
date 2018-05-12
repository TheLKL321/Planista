package com.thelkl;

public class Proces {
    private int id, when, required;

    public Proces(int id, int when, int required) {
        this.id = id;
        this.when = when;
        this.required = required;
    }

    public int getId() {
        return id;
    }

    public int getWhen() {
        return when;
    }

    public int getRequired() {
        return required;
    }
}
