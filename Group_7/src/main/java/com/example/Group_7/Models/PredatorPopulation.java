package com.example.Group_7.Models;

import com.example.Group_7.Classes.Wolf;

public class PredatorPopulation {

    public PredatorPopulation() {
    }



    public int predict(Wolf wolf, double[] herbivorePopulation) {
        double totalPrey = 0;
        int wolfPopulation = wolf.getPopulation();

        for (double pop: herbivorePopulation){
            totalPrey += pop;
        }

        double a = wolf.getConversionEfficiency() * 0.5;


        double newWolves = wolfPopulation + (a * totalPrey) - (wolfPopulation/3);
        // double newWolves = wolfPopulation + wolfPopulation * (a * totalPrey - wolf.getDeathRate());
        newWolves = Math.max(0, newWolves);
        
        // If population would decline (deaths > births) and is below 1.5, extinction occurs
        if (newWolves < 1.5) {
            return 0;
        }
        
        return (int) Math.round(newWolves);
        // return wolf.getPopulation();
        // so they 10 animals 50% wolfs are female

    }

    // public int predict(Wolf wolf, double[] herbivoreKills, double[] herbivoreMasses) {
    //     int wolfPopulation = wolf.getPopulation();
        
    //     if (wolfPopulation == 0) {
    //         return 0;
    //     }
    
    //     // Calculate total biomass consumed
    //     double biomassConsumed = 0;
    //     for (int i = 0; i < herbivoreKills.length; i++) {
    //         biomassConsumed += herbivoreKills[i] * herbivoreMasses[i];
    //     }
        
    //     // Food per wolf determines survival and reproduction
    //     double foodPerWolf = biomassConsumed / wolfPopulation;
        
    //     // Each wolf needs ~1825kg/year to survive (5kg/day * 365 days)
    //     // This is already in consumeRate, but let's use a baseline
    //     double minFoodForSurvival = 1825.0; // kg per wolf per year
        
    //     // Calculate survival rate based on food availability
    //     double survivalRate = Math.min(1.0, foodPerWolf / minFoodForSurvival);
        
    //     // Natural deaths (only survivors can reproduce)
    //     double survivors = wolfPopulation * (1.0 - wolf.getDeathRate()) * survivalRate;
        
    //     // Reproduction only if food exceeds survival needs
    //     double excessFood = Math.max(0, foodPerWolf - minFoodForSurvival);
    //     double birthRate = Math.min(0.5, (excessFood * wolf.getConversionEfficiency()) / 40.0);
    //     double births = wolfPopulation * birthRate * survivalRate;
        
    //     double newWolves = survivors + births;
    //     newWolves = Math.max(0, newWolves);
        
    //     return (int) Math.round(newWolves);
    // }

    public static void main(String[] args) {
        Wolf wolf = new Wolf();
        PredatorPopulation predatorPopulation = new PredatorPopulation();
        double[] herbivorePopulations = {70, 0, 0};
        for (int year = 1; year <= 5; year++) {
            int newPopulation = predatorPopulation.predict(wolf, herbivorePopulations);
            wolf.setPopulation(newPopulation);
            System.out.println("Year " + year + ": Wolf population = " + newPopulation);
        }
    }



}
