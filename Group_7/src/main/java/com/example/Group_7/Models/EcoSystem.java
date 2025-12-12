package com.example.Group_7.Models;

import java.util.ArrayList;
import java.util.List;

import com.example.Group_7.Classes.Cattle;
import com.example.Group_7.Classes.Deer;
import com.example.Group_7.Classes.Horse;
import com.example.Group_7.Classes.Wolf;

public class EcoSystem {
    private FinalModel herbivoreModel;
    private PredatorPopulation predatorModel;
    
    // Current state
    private Cattle cattle;
    private Horse horse;
    private Deer deer;
    private Wolf wolf;

    private int initialCattle;
    private int initialHorse;
    private int initialDeer;
    private int initialWolves;
    
    // History storage
    private List<Integer> cattleHistory;
    private List<Integer> horseHistory;
    private List<Integer> deerHistory;
    private List<Integer> wolfHistory;
    private grassModel grassModel;
    private double initialTemp;
    private double rain;
                    
    public EcoSystem(Cattle cattle, Horse horse, Deer deer, Wolf wolf) {
        this.cattle = cattle;
        this.horse = horse;
        this.deer = deer;
        this.wolf = wolf;
        // this.carryingCapacity = carryingCapacity;
        
        this.initialCattle = cattle.getPopulation();
        this.initialHorse = horse.getPopulation();
        this.initialDeer = deer.getPopulation();
        this.initialWolves = wolf.getPopulation();

        this.herbivoreModel = new FinalModel();
        this.predatorModel = new PredatorPopulation();
        this.grassModel = new grassModel();
    
        this.cattleHistory = new ArrayList<>();
        this.horseHistory = new ArrayList<>();
        this.deerHistory = new ArrayList<>();
        this.wolfHistory = new ArrayList<>();

        this.initialTemp = 12.0;
        this.rain = 710.37;
    }
    
    public void simulate(int years) {
        // Store initial state (year 0)
        cattleHistory.add(cattle.getPopulation());
        horseHistory.add(horse.getPopulation());
        deerHistory.add(deer.getPopulation());
        wolfHistory.add(wolf.getPopulation());
        
        for (int t = 0; t < years; t++) {
            // Get current populations
            int currentCattle = cattle.getPopulation();
            int currentHorse = horse.getPopulation();
            int currentDeer = deer.getPopulation();
            int currentWolves = wolf.getPopulation();

            int grass = (int) grassModel.simulate(rain, initialTemp);
            
            double[] currentHerbivores = {currentCattle, currentHorse, currentDeer};
            
            // Calculate next herbivore populations (using current wolf population)
            int nextCattle = herbivoreModel.calculateNextPopulation(
                currentCattle, cattle, horse, deer, grass, currentWolves, wolf.getSuccessRate()
            );
            int nextHorse = herbivoreModel.calculateNextPopulation(
                currentHorse, horse, cattle, deer, grass, currentWolves, wolf.getSuccessRate()
            );
            int nextDeer = herbivoreModel.calculateNextPopulation(
                currentDeer, deer, cattle, horse, grass, currentWolves, wolf.getSuccessRate()
            );
            
            // Calculate next wolf population (using current herbivore populations)
            int nextWolves = predatorModel.predict(wolf, currentHerbivores);
            
            // Update all populations together
            cattle.setPopulation(nextCattle);
            horse.setPopulation(nextHorse);
            deer.setPopulation(nextDeer);
            wolf.setPopulation(nextWolves);
            
            // Store state for this year
            cattleHistory.add(nextCattle);
            horseHistory.add(nextHorse);
            deerHistory.add(nextDeer);
            wolfHistory.add(nextWolves);

            initialTemp += 0.1;

            
        }

        //reset all values
    cattle.setPopulation(initialCattle);
    horse.setPopulation(initialHorse);
    deer.setPopulation(initialDeer);
    wolf.setPopulation(initialWolves);
}
    
    // Getters for history
    public List<Integer> getCattleHistory() {
        return cattleHistory;
    }
    
    public List<Integer> getHorseHistory() {
        return horseHistory;
    }
    
    public List<Integer> getDeerHistory() {
        return deerHistory;
    }
    
    public List<Integer> getWolfHistory() {
        return wolfHistory;
    }
    
    // Get total herbivore history
    public List<Integer> getTotalHerbivoreHistory() {
        List<Integer> total = new ArrayList<>();
        for (int i = 0; i < cattleHistory.size(); i++) {
            total.add(cattleHistory.get(i) + horseHistory.get(i) + deerHistory.get(i));
        }
        return total;
    }


    public static void main(String[] args) {
        EcoSystem ecoSystem = new EcoSystem(new Cattle(), new Horse(),new Deer(),new Wolf());
        ecoSystem.simulate(10);
        System.out.println(ecoSystem.getTotalHerbivoreHistory());
        System.out.println(ecoSystem.getWolfHistory());
    }
}