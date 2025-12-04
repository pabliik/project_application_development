package com.example.Group_7.Classes;

public class Wolf {
    private int population;
    private double deathRate;
    private double successRate;
    private double conversionEfficiency;

    public Wolf(){
        this.population = 6;
        this.deathRate = 0.333;
        this.successRate = 0.1;
        this.conversionEfficiency = 0.10;

    }

    public int getPopulation() {
        return population;
    }

    public double getDeathRate() {
        return deathRate;
    }

    public double getSuccessRate() {
        return successRate;
    }

    public double getConversionEfficiency() {
        return conversionEfficiency;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setDeathRate(double deathRate) {
        this.deathRate = deathRate;
    }

    public void setSuccessRate(double successRate) {
        this.successRate = successRate;
    }

    public void setConversionEfficiency(double conversionEfficiency) {
        this.conversionEfficiency = conversionEfficiency;
    }

    



    






}
