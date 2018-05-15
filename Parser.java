package com.thelkl;


import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Parser {

    public static ArrayList<Strategy> parseInput (String path){
        ArrayList<Task> allTasks = new ArrayList<>();

        BufferedReader br;
        int i = 1;
        try {
            br = new BufferedReader(new FileReader(new File(path)));
            String line = br.readLine();
            if (line == null) throw new EOFException();
            String[] initialArgument = line.split(" ");
            if (initialArgument.length != 1) throw new TokenCountException();
            int taskCount = Integer.parseInt(initialArgument[0]);

            for(i = 2; i < taskCount + 2; i++){
                line = br.readLine();
                if (line == null) throw new EOFException();
                String[] tokens = line.split(" ");
                if (tokens.length != 2) throw new TokenCountException();
                allTasks.add(new Task(i - 1, Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
            }
        } catch (EOFException e){
            System.out.println("Błąd w wierszu " + i + " : Plik nie posiada wszystkich danych.");
            return null;
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

        return parsePlanners(allTasks, br, i);
    }

    private static ArrayList<Strategy> parsePlanners (ArrayList<Task> allTasks, BufferedReader br, int i){
        ArrayList<Strategy> strategyArray = new ArrayList<>();
        LinkedList<Task> tasksToDispense = new LinkedList<>(allTasks);
        strategyArray.add(new FCFS(tasksToDispense, allTasks));
        strategyArray.add(new SJF(tasksToDispense, allTasks));
        strategyArray.add(new SRT(tasksToDispense, allTasks));
        strategyArray.add(new PS(tasksToDispense, allTasks));

        try {
            String line = br.readLine();
            if (line == null) throw new EOFException();
            String[] initialArgument = line.split(" ");
            if (initialArgument.length != 1) throw new TokenCountException();
            int rrCount = Integer.parseInt(initialArgument[0]);
            i++;

            line = br.readLine();
            if (line == null) throw new EOFException();
            String[] tokens = line.split(" ");
            if (tokens.length != rrCount) throw new TokenCountException();
            for (int j = 0; j < rrCount; j++)
                strategyArray.add(new RR(tasksToDispense, allTasks, Integer.parseInt(tokens[j])));
        } catch (EOFException e){
            System.out.println("Błąd w wierszu " + i + " : Plik nie posiada wszystkich danych.");
            return null;
        }catch (IOException e) {
            System.out.println("Plik z danymi nie jest dostępny.");
            return null;
        } catch (NumberFormatException e){
            System.out.println("Błąd w wierszu " + i + " : Podana wartość nie jest liczbą całkowitą.");
            return null;
        } catch (TokenCountException e) {
            System.out.println("Błąd w wierszu " + i + " : Podano nieprawidłową liczbę argumentów.");
            return null;
        }

        return strategyArray;
    }
}
