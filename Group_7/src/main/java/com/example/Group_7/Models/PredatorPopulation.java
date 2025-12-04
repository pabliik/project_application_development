package com.example.Group_7.Models;

import com.example.Group_7.Classes.Wolf;

public class PredatorPopulation {

    public PredatorPopulation() {
    }



    public int predict(Wolf wolf, double[] herbivorePopualtion ){
        double totalPrey = 0;
        int wolfPopualtion = wolf.getPopulation();

        for (double pop: herbivorePopualtion){
            totalPrey += pop;
        }

        double a = wolf.getConversionEfficiency() * wolf.getSuccessRate();


        double newWolves = a * totalPrey * (wolfPopualtion/6) - wolf.getDeathRate()* wolfPopualtion;
        newWolves = Math.max(0, newWolves);
        return (int) Math.round(newWolves);

    }



}
