package com.example.Group_7.Classes;

public class Wolf {
    private int population;
    private double deathRate;
    private double successRate;
    private double conversionEfficiency;
    private double consumeRate;

    public Wolf(){
        this.population = 10;
        this.deathRate = 1/3;
        this.successRate = 0.1;
        this.conversionEfficiency = 0.1;
        this.consumeRate = 5.0 * 365;

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

    public double getConsumeRate() {
        return consumeRate;
    }

    public void setConsumeRate(double consumeRate) {
        this.consumeRate = consumeRate;
    }

    



    






}
