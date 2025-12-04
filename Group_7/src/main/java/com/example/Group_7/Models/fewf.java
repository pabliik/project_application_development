package com.example.Group_7.Models;

import java.util.ArrayList;
import java.util.List;

import com.example.Group_7.Classes.Cattle;
import com.example.Group_7.Classes.Herbivore;

public class fewf {
    
    public int simulate(int population, double growth_rate, double carryingCapacity, int timeSteps){
        if (timeSteps == 0){
            return  population;
        }

        int N = population;
        double r = growth_rate;
        double K = carryingCapacity;

        int next_pop = (int) Math.round(N + (r*N*(1-N/K)));
        

        return simulate(next_pop, growth_rate, carryingCapacity, timeSteps-1);
    }

    public int calculatePopulation(int population, double growth_rate, double carryingCapacity){
        double N = population;
        double r = growth_rate;
        double K = carryingCapacity;
        
        // Discrete logistic equation
        double nextPopulation = N + (r * N * (1 - N / K));
        
        return (int) Math.round(nextPopulation);
    }

    public List<Integer> simulateTimeSeries(Herbivore animal, double carryingCapacity, int timeSteps){
        List<Integer> populations = new ArrayList<>();

        int currentPopulation = animal.getPopulation();
        populations.add(currentPopulation);

        for (int i =0; i < timeSteps; i++){
            currentPopulation = calculatePopulation(currentPopulation, animal.getGrowth_rate(), carryingCapacity);
            populations.add(currentPopulation);
        }

        return populations;
    }

    public static void main(String[] args) {
        fewf model = new fewf();
        Cattle cattle = new Cattle();
        // int output = (model.simulate(cattle.getPopulation(),
        //                                 cattle.getGrowth_rate(), 
        //                                 500, 
        //                                 5));
        // System.out.print(output);
        List<Integer> series = model.simulateTimeSeries(cattle, 500, 5);
        System.out.print(series);
    }
}
