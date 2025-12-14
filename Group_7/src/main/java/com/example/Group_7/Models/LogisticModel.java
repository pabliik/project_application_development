package com.example.Group_7.Models;

import java.util.Arrays;

import com.example.Group_7.Classes.Cattle;
import com.example.Group_7.Classes.Herbivore;

public class LogisticModel {
    private String modelName;
    
    // Store simulation results for later use (graphing, analysis)
    
    public LogisticModel() {
        this.modelName = "Discrete Logistic Growth Model";
    }
    
    /**
     * Discrete logistic equation:
     * N(t+1) = N(t) + r × N(t) × (1 - N(t)/K)
     * 
     * @param herbivore The animal to simulate
     * @param carryingCapacity Maximum population (K)
     * @param timeStep Which time step to calculate (0 = initial)
     * @return Population at given time step
     */
    public int[] simulate(Herbivore animal, double carryingCapacity, int timeSteps) {
        // Create array to store all populations
        int[] populations = new int[timeSteps + 1];
        
        // Set initial population
        populations[0] = animal.getPopulation();
        
        // Calculate each next step
        for (int t = 0; t < timeSteps; t++) {
            populations[t + 1] = calculateNextPopulation(
                populations[t],
                animal.getGrowth_rate(),
                carryingCapacity
            );
        }
        
        return populations;
    }
    
    /**
     * Helper: Calculate one step of discrete logistic growth
     * N(t+1) = N(t) + r × N(t) × (1 - N(t)/K)
     */
    private int calculateNextPopulation(int population, double growthRate, double carryingCapacity) {
        double N = population;
        double r = growthRate;
        double K = carryingCapacity;
        
        double nextPopulation = N + (r * N * (1 - N / K));
        nextPopulation = Math.max(0, nextPopulation);
        
        return (int) Math.round(nextPopulation);
    }


    public static void main(String[] args) {
        LogisticModel model = new LogisticModel();
        Cattle cattle = new Cattle();
        System.out.println(Arrays.toString(model.simulate(cattle, 500, 5)));
    }
}
