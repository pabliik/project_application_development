package com.example.Group_7.Models;

import com.example.Group_7.Classes.Herbivore;
import com.example.Group_7.Classes.Wolf;

public class FinalModel {
    private String modelName;
    
    
    public FinalModel(){
        
    }
    
    /**
     * Helper: Calculate one step of discrete logistic growth
     * N(t+1) = N(t) + r × N(t) × (1 - N(t)/K)
     */
    public int calculateNextPopulation(int initialPopualtion, Herbivore animal, Herbivore comp1, Herbivore comp2, double availableGrass, Wolf wolf) {
        double N = initialPopualtion;
        double r = animal.getGrowth_rate();
        double K = availableGrass/animal.getConsumptionRate()*N;

        double N1 = comp1.getPopulation();
        double N2 = comp2.getPopulation();

        double a1 = animal.getCompetitionWith(comp1);
        double a2 = animal.getCompetitionWith(comp2);

        int P = wolf.getPopulation();
        double c = wolf.getSuccessRate();
        double consumeRate = wolf.getConsumeRate();

        int totalherbivores = (int)(N + N1 + N2);
        
        double animalsEaten = animal.getHuntRate();
        double deaths = animalsEaten * P *N;
        //                      1825 = 5 * 365      /  (  animal mass   * animal amount     ) = 
        // double animalsEaten = wolf.getConsumeRate() / (animal.getMass() * animal.getPopulation());
        // double deaths = animalsEaten * P * N;

        
        double nextPopulation = N + (r * N * (1 - (N + a1*N1 + a2*N2) / K)) - deaths;
        //double nextPopulation = N + r*N - deaths;
        nextPopulation = Math.max(0, nextPopulation);
        
        return (int) Math.round(nextPopulation);
    }
    // Store simulation results for later use (graphing, analysis)
        
        
        /**
         * Discrete logistic equation:
         * N(t+1) = N(t) + r × N(t) × (1 - N(t)/K)
         * 
         * @param herbivore The animal to simulate
         * @param carryingCapacity Maximum population (K)
         * @param timeStep Which time step to calculate (0 = initial)
         * @return Population at given time step
         */
        // public int[] simulate(Herbivore animal, Herbivore comp1, Herbivore comp2, double carryingCapacity, int timeSteps, int wolves, double successRate) {
        //     // Create array to store all populations
        //     int[] populations = new int[timeSteps + 1];
            
        //     // Set initial population
        //     populations[0] = animal.getPopulation();
            
        //     // Calculate each next step
        //     for (int t = 0; t < timeSteps; t++) {
        //         populations[t + 1] = calculateNextPopulation(
        //             populations[t],
        //             animal,
        //             comp1,comp2,
        //             carryingCapacity
        //         );
        //     }
            
        //     return populations;
        // }

    // public static void main(String[] args) {
    //     FinalModel model = new FinalModel();
    //     Cattle cattle = new Cattle();
    //     Deer deer = new Deer();
    //     Horse horse = new Horse();
    //     System.out.println(Arrays.toString(model.simulate(cattle,horse,deer, 500, 5)));
    // }
}
