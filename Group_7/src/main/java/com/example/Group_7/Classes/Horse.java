package com.example.Group_7.Classes;

public class Horse extends Herbivore {
    
    public Horse() {
        super(
            450,  // growth_rate from Excel
            Math.round(0.058795139 * 100.0) / 100.0,          // mass
            350,          // population
            4701,           // kg per year
            1825/450/350.0
        );
        setupCompetitionMatrix();
    }
    
    private void setupCompetitionMatrix() {
        competitionMatrix.setCompetition(Horse.class, Horse.class, 1.0);
        competitionMatrix.setCompetition(Horse.class, Cattle.class, 0.9);
        competitionMatrix.setCompetition(Horse.class, Deer.class, 0.30);
    }
}