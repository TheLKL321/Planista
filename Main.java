package com.thelkl;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Strategy> strategies = Parser.parseInput(args[0]);

        for (Strategy strategy : strategies) {
            strategy.run();
            System.out.println();
        }
    }
}
