package com.example.Group_7.Classes;



public abstract class Herbivore {
    protected int population;
    protected int mass;
    protected double  growth_rate;
    protected Competition competitionMatrix;
    protected int consumptionRate;

    public Herbivore(int mass,double growth_rate, int population, int consumptionRate) {
        this.growth_rate = growth_rate;
        this.mass = mass;
        this.population = population;
        this.competitionMatrix = new Competition();
        this.consumptionRate = consumptionRate;
    }

    public Herbivore(){
        this.competitionMatrix = new Competition();
    }



    public double getGrassConsumptionPerYear(int population){
        return this.consumptionRate * Math.pow(mass, 0.75) * population * 365  ;
    };

    public double getCompetitionWith(Herbivore other){
        return this.competitionMatrix.getCompetition(this.getClass(), other.getClass());
    }

    public int getPopulation() {
        return population;
    }

    public int getMass() {
        return mass;
    }

    public double getGrowth_rate() {
        return growth_rate;
    }

    public Competition getCompetitionMatrix() {
        return competitionMatrix;
    }

    public int getConsumptionRate() {
        return consumptionRate;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public void setGrowth_rate(double growth_rate) {
        this.growth_rate = growth_rate;
    }

    public void setCompetitionMatrix(Competition competitionMatrix) {
        this.competitionMatrix = competitionMatrix;
    }

    public void setConsumptionRate(int consumptionRate) {
        this.consumptionRate = consumptionRate;
    }



    
}
