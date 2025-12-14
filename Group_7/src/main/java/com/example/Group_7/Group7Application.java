package com.example.Group_7;

import com.example.Group_7.Classes.Cattle;
import com.example.Group_7.Classes.Deer;
import com.example.Group_7.Classes.Horse;
import com.example.Group_7.Classes.Wolf;
import com.example.Group_7.Models.EcoSystem;

// @SpringBootApplication
public class Group7Application {

	public static void main(String[] args) {
		// SpringApplication.run(Group7Application.class, args);

		EcoSystem ecoSystem = new EcoSystem(new Cattle(), new Horse(), new Deer(), new Wolf());
		ecoSystem.simulate(10);
        System.out.println(ecoSystem.getTotalHerbivoreHistory());
        System.out.println(ecoSystem.getWolfHistory());
	}

}
