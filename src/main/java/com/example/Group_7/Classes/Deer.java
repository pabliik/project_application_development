package com.example.Group_7.Classes;

public class Deer extends Herbivore {
    
    public Deer() {
        super(
            240,  // growth_rate from Excel
            Math.round(0.051251603 * 100.0) / 100.0,          // mass
            870,          // population
            2003,             // consumptionRate per kilo
            1825/240/870.0
        );
        setupCompetitionMatrix();
    }
    
    private void setupCompetitionMatrix() {
        competitionMatrix.setCompetition(Deer.class, Deer.class, 1.0);
        competitionMatrix.setCompetition(Deer.class, Cattle.class, 0.30);
        competitionMatrix.setCompetition(Deer.class, Horse.class, 0.30);
    }
}