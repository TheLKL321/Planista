package com.thelkl;

import java.util.ArrayList;

public class Controller {
    public static void runSimulations(ArrayList<Strategy> strategies){
        if (strategies != null) {
            for (Strategy strategy : strategies) {
                strategy.run();
                System.out.println();
            }
        }
    }
}
