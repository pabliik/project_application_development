package com.example.Group_7.Models;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class grassModel {
    double intercept = -75.6284;
    double rain = -0.119366;
    double temp = 26.170346;
    double rainSquared = 0.000002;
    double rainTemp = 0.011072;
    double tempSquared = -1.604980;
    double area = 5600.0; // ha
    double conversionRate = 100.0; // kg per ha

    

    public long simulate(double r, double t){
        double height = intercept + 
                    r*rain +
                    t*temp + 
                    rainSquared * Math.pow(r, 2) +
                    tempSquared * Math.pow(t, 2) + 
                    rainTemp * r * t
                    ;
        return Math.round(height * area * conversionRate); // in kg 
    }

    public static void main(String[] args) {
        grassModel model = new grassModel();
        double result = model.simulate(710.37, 12.33);
        double result2 = model.simulate(710.37, 12.38);
        System.out.println("Result: " + result + " " + result2);
    }
}
