package com.example.olio_ohjelmointi_harkkatyo;

import java.util.HashMap;

public class StateData {




    private int year, population, populationChange;
    private String workPlace, employment;

    public StateData(int year, int population, int populationChange, String workPlace, String employment) {
        this.year = year;
        this.population = population;
        this.populationChange = populationChange;
        this.workPlace = workPlace;
        this.employment = employment;
    }


    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }
    public int getPopulationChange() {
        return populationChange;
    }
    public void setPopulationChange(int populationChange) {
        this.populationChange = populationChange;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }
}
