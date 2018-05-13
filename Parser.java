package com.thelkl;


import java.util.ArrayList;
import java.util.LinkedList;

public class Parser {

    public static ArrayList<Planista> parseInput (String path){
        return parsePlanners(parseProcesses(path));
    }

    private static LinkedList<Proces> parseProcesses (String path){
        LinkedList<Proces> arr = new LinkedList<>();

        //TODO: parse

        return arr;
    }

    private static ArrayList<Planista> parsePlanners (LinkedList<Proces> processes){ //TODO: current scanner? bufferedreader?
        ArrayList<Planista> arr = new ArrayList<>();
        arr.add(new FCFS(processes));
        arr.add(new SJF(processes));
        arr.add(new SRT(processes));
        arr.add(new PS(processes));

        // TODO: RRs

        return arr;
    }
}
