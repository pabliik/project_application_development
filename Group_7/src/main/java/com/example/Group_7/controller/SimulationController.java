package com.example.Group_7.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Group_7.Classes.Cattle;
import com.example.Group_7.Classes.Deer;
import com.example.Group_7.Classes.Horse;
import com.example.Group_7.Classes.Wolf;
import com.example.Group_7.Models.EcoSystem;
import com.example.Group_7.Models.grassModel;

public class SimulationController {
    // Constants for grass calculations
    private static final double AREA_HA = 5600.0;
    private static final double CONVERSION_RATE_KG_PER_HA = 100.0;
    private static final double BASE_TEMPERATURE = 12.0;
    private static final double RAINFALL = 710.37;
    private static final double TEMP_INCREASE_PER_YEAR = 0.05;
    private static final int START_YEAR = 2024;
    private static final int GRASS_START_YEAR = 2015;
    
    /**
     * Runs a prediction starting from year 2024.
     * @param years Number of years to predict into the future
     * @param wolves Number of wolves in the ecosystem
     */

    @GetMapping("/prediction-2024")
    public ResponseEntity<Map<String, Object>> runPrediction2024(
            @RequestParam(defaultValue = "10") int years,
            @RequestParam(defaultValue = "0") int wolves) {
        try {
            // Create ecosystem with initial populations
            Cattle cattle = new Cattle();
            Horse horse = new Horse();
            Deer deer = new Deer();
            Wolf wolf = new Wolf();
            wolf.setPopulation(wolves);
            
            EcoSystem ecoSystem = new EcoSystem(cattle, horse, deer, wolf);
            ecoSystem.simulate(years);

            // Get population histories from simulation
            List<Integer> cattleHistory = ecoSystem.getCattleHistory();
            List<Integer> horseHistory = ecoSystem.getHorseHistory();
            List<Integer> deerHistory = ecoSystem.getDeerHistory();
            List<Integer> wolfHistory = ecoSystem.getWolfHistory();
            List<Integer> grassHistory = ecoSystem.getGrassHistory();
            
            // Build results with populations and grass data for each year
            List<Map<String, Object>> results = buildPredictionResults(
                cattleHistory, horseHistory, deerHistory, wolfHistory, grassHistory);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("results", results);
            response.put("startYear", START_YEAR);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return createErrorResponse(e.getMessage());
        }
    }

    /**
     * Builds prediction results with population and grass data for each year.
     */
    private List<Map<String, Object>> buildPredictionResults(
            List<Integer> cattleHistory, List<Integer> horseHistory,
            List<Integer> deerHistory, List<Integer> wolfHistory, List<Integer> grassHistory) {

        List<Map<String, Object>> results = new ArrayList<>();

        for (int i = 0; i < cattleHistory.size(); i++) {
            int year = START_YEAR + i;
            double currentTemp = BASE_TEMPERATURE + (i * TEMP_INCREASE_PER_YEAR);

            // Use grass history from simulation if available, otherwise calculate
            long grassMass;
            if (grassHistory != null && i < grassHistory.size()) {
                grassMass = grassHistory.get(i);
            } else {
                grassModel grassModel = new grassModel();
                grassMass = grassModel.simulate(RAINFALL, currentTemp);
            }
            double grassHeight = calculateGrassHeight(grassMass);
            
            Map<String, Object> result = new HashMap<>();
            result.put("year", year);
            result.put("cattle", cattleHistory.get(i));
            result.put("horses", horseHistory.get(i));
            result.put("deer", deerHistory.get(i));
            result.put("predators", wolfHistory.get(i));
            result.put("grassHeight", grassHeight);
            result.put("grassMass", grassMass);
            result.put("temperature", currentTemp);
            result.put("rainfall", RAINFALL);
            
            results.add(result);
        }
        return results;
    }

    /**
     * Calculates grass height from mass.
     * Formula: height = mass / (area * conversionRate)
     */
    private double calculateGrassHeight(long grassMass) {
        return grassMass / (AREA_HA * CONVERSION_RATE_KG_PER_HA);
    }


