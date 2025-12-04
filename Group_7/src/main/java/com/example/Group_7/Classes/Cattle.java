package com.example.Group_7.Classes;

public class Cattle extends Herbivore {

    public Cattle() {
        super(750, 
            Math.round(0.029610587 * 100.0) / 100.0, 
            375, 
            88);
        setupCompetitionMatrix();
    }

    private void setupCompetitionMatrix() {
        competitionMatrix.setCompetition(Cattle.class, Cattle.class, 1.0);
        competitionMatrix.setCompetition(Cattle.class, Horse.class, 1.1);
        competitionMatrix.setCompetition(Cattle.class, Deer.class, 3.0);
    }

    




    



    

    


}
