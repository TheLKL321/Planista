package com.thelkl;


import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class Parser {

    public static ArrayList<Planista> parseInput (String path){
        ArrayList<Proces> allProcesses = new ArrayList<>();

        BufferedReader br;
        int i = 1;
        try {
            br = new BufferedReader(new FileReader(new File(path));
            String[] initialArgument = br.readLine().split(" ");
            if (initialArgument.length != 1) throw new TokenCountException();
            int processCount = Integer.parseInt(initialArgument[0]);

            String line;
            for(i = 1; i < processCount + 1; i++){
                line = br.readLine();
                String[] tokens = line.split(" ");
                if (tokens.length != 2) throw new TokenCountException();
                allProcesses.add(new Proces(i, Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
            }
        } catch (IOException e) {
            System.out.println("Plik z danymi nie jest dostępny.");
            return null;
        } catch (NumberFormatException e){
            System.out.println("Błąd w wierszu " + i + " : Podana wartość nie jest liczbą całkowitą.");
            return null;
        } catch (TokenCountException e) {
            System.out.println("Błąd w wierszu " + i + " : Podano nieprawidłową liczbę argumentów.");
            return null;
        }

        return parsePlanners(allProcesses, br, i);
    }

    private static ArrayList<Planista> parsePlanners (ArrayList<Proces> allProcesses, BufferedReader br, int i){
        ArrayList<Planista> plannerArray = new ArrayList<>();
        LinkedList<Proces> processesToDispense = new LinkedList<>(allProcesses);
        plannerArray.add(new FCFS(processesToDispense, allProcesses));
        plannerArray.add(new SJF(processesToDispense, allProcesses));
        plannerArray.add(new SRT(processesToDispense, allProcesses));
        plannerArray.add(new PS(processesToDispense, allProcesses));

        try {
            String[] initialArgument = br.readLine().split(" ");
            if (initialArgument.length != 1) throw new TokenCountException();
            int rrCount = Integer.parseInt(initialArgument[0]);

            String[] tokens = br.readLine().split(" ");
            if (tokens.length != rrCount) throw new TokenCountException();
            for (int j = 0; j < rrCount; j++)
                plannerArray.add(new RR(processesToDispense, allProcesses, tokens[j]);

        } catch (IOException e) {
            System.out.println("Plik z danymi nie jest dostępny.");
            return null;
        } catch (NumberFormatException e){
            System.out.println("Błąd w wierszu " + i + " : Podana wartość nie jest liczbą całkowitą.");
            return null;
        } catch (TokenCountException e) {
            System.out.println("Błąd w wierszu " + i + " : Podano nieprawidłową liczbę argumentów.");
            return null;
        }

        return plannerArray;
    }
}
