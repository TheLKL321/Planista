package com.thelkl;


import java.io.*;
import java.util.ArrayList;

public class Parser {

    private static String[] tokenize (String line, int argumentCount) throws TokenCountException, EOFException {
        if (line == null)
            throw new EOFException();

        String[] tokens = line.split(" ");
        if (tokens.length != argumentCount)
            throw new TokenCountException();

        return tokens;
    }

    public static ArrayList<Strategy> parseInput (String[] args){
        ArrayList<Task> allTasks = new ArrayList<>();
        BufferedReader br;
        int i = 1;

        try {
            Reader r;
            if (args.length == 0)
                r = new InputStreamReader(System.in);
            else
                r = new FileReader(new File(args[0]));

            br = new BufferedReader(r);

            String[] initialArgument = tokenize(br.readLine(), 1);
            int taskCount = Integer.parseInt(initialArgument[0]);

            for(i = 2; i < taskCount + 2; i++){
                String[] tokens = tokenize(br.readLine(), 2);
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

    private static ArrayList<Task> deepCopy (ArrayList<Task> arr){
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : arr){
            result.add(new Task(task.getId(), task.getWhen(), task.getRequired()));
        }
        return result;
    }

    private static ArrayList<Strategy> parsePlanners (ArrayList<Task> allTasks, BufferedReader br, int i){
        ArrayList<Strategy> strategyArray = new ArrayList<>();
        strategyArray.add(new FCFS(allTasks));
        strategyArray.add(new SJF(deepCopy(allTasks)));
        strategyArray.add(new SRT(deepCopy(allTasks)));
        strategyArray.add(new PS(deepCopy(allTasks)));

        try {
            String[] initialArgument = tokenize(br.readLine(), 1);
            int rrCount = Integer.parseInt(initialArgument[0]);
            i++;

            String[] tokens = tokenize(br.readLine(), rrCount);
            for (int j = 0; j < rrCount; j++)
                strategyArray.add(new RR(deepCopy(allTasks), Integer.parseInt(tokens[j])));

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
