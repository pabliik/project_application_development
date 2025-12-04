package com.example.Group_7.Classes;

import java.util.HashMap;
import java.util.Map;

public class Competition {

    private Map<String, Double> matrix;

    public Competition(){
        this.matrix = new HashMap<>();
    }

    public String makeKey(Class <? extends Herbivore> source,
                        Class <? extends Herbivore> target){
                            return source.getSimpleName() + "_" + target.getSimpleName(); 
                        }

    public void setCompetition(Class <? extends Herbivore> source,
                        Class <? extends Herbivore> target,
                        Double coef){
                            String key = makeKey(source,target);
                            this.matrix.put(key,coef);
                        }
            
    public double getCompetition(Class<? extends Herbivore> source, 
                                 Class<? extends Herbivore> target){
                                String key = makeKey(source, target);
                                return this.matrix.get(key);
                                 }

    // double getCompetition(Class<? extends Herbivore> aClass, Class<? extends Herbivore> aClass0) {
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }
}
