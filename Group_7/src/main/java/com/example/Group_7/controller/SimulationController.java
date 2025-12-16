package com.example.Group_7.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Group_7.Classes.Cattle;
import com.example.Group_7.Classes.Deer;
import com.example.Group_7.Classes.Horse;
import com.example.Group_7.Classes.Wolf;
import com.example.Group_7.Models.EcoSystem;
import com.example.Group_7.Models.grassModel;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
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
     * Creates an error response.
     */
    private ResponseEntity<Map<String, Object>> createErrorResponse(String errorMessage) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("error", errorMessage);
        return ResponseEntity.status(500).body(response);
    }
    
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

    @param endYear
    @GetMapping("/grass-data")
    public ResponseEntity<Map<String, Object>> getGrassData(
            @RequestParam int endYear) {
        try {
            if (endYear < GRASS_START_YEAR) {
                return createErrorResponse("End year must be >= " + GRASS_START_YEAR);
            }
            
            List<Map<String, Object>> grassData = buildGrassData(GRASS_START_YEAR, endYear);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", grassData);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return createErrorResponse(e.getMessage());
        }
    }

    /**
     * Builds grass data from start year to end year.
     */
    private List<Map<String, Object>> buildGrassData(int startYear, int endYear) {
        List<Map<String, Object>> grassData = new ArrayList<>();
        grassModel grassModel = new grassModel();
        
        for (int year = startYear; year <= endYear; year++) {
            int yearIndex = year - startYear;
            double currentTemp = BASE_TEMPERATURE + (yearIndex * TEMP_INCREASE_PER_YEAR);
            long grassMass = grassModel.simulate(RAINFALL, currentTemp);
            double grassHeight = calculateGrassHeight(grassMass);
            
            Map<String, Object> dataPoint = new HashMap<>();
            dataPoint.put("year", year);
            dataPoint.put("grassMass", grassMass);
            dataPoint.put("grassHeight", grassHeight);
            dataPoint.put("temperature", currentTemp);
            dataPoint.put("rainfall", RAINFALL);
            
            grassData.add(dataPoint);
        }
        
        return grassData;
    }

    /**
     * Gets initial model parameters and values.
     */
    @GetMapping("/load-data")
    public ResponseEntity<Map<String, Object>> loadData() {
        try {
            // Create animal instances to get initial values
            Cattle cattle = new Cattle();
            Horse horse = new Horse();
            Deer deer = new Deer();
            Wolf wolf = new Wolf();
            
            // Build initial values map
            Map<String, Object> initialValues = buildInitialValues(cattle, horse, deer, wolf);
            
            // Build model parameters map
            Map<String, Object> modelParams = buildModelParams(horse, cattle, deer, wolf);
            
            // Build grass model info map
            Map<String, Object> grassModelInfo = buildGrassModelInfo();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("initialValues", initialValues);
            response.put("modelParams", modelParams);
            response.put("modelInfo", grassModelInfo);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return createErrorResponse(e.getMessage());
        }
    }

    /**
     * Builds initial population values for year 2024.
     */
    private Map<String, Object> buildInitialValues(Cattle cattle, Horse horse, Deer deer, Wolf wolf) {
        Map<String, Object> initialValues = new HashMap<>();
        initialValues.put("year", START_YEAR);
        initialValues.put("horses", horse.getPopulation());
        initialValues.put("cattle", cattle.getPopulation());
        initialValues.put("deer", deer.getPopulation());
        initialValues.put("wolves", wolf.getPopulation());
        
        // Calculate initial grass height
        grassModel grassModel = new grassModel();
        long grassMass = grassModel.simulate(RAINFALL, BASE_TEMPERATURE);
        double grassHeight = calculateGrassHeight(grassMass);
        initialValues.put("grassHeight", grassHeight);
        
        return initialValues;
    }
    
    private Map<String, Object> buildModelParams(Horse horse, Cattle cattle, Deer deer, Wolf wolf) {
        Map<String, Object> modelParams = new HashMap<>();

        // Growth rates
        modelParams.put("horsesGrowthRate", horse.getGrowth_rate());
        modelParams.put("cattleGrowthRate", cattle.getGrowth_rate());
        modelParams.put("deerGrowthRate", deer.getGrowth_rate());

}   



