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

            
}
