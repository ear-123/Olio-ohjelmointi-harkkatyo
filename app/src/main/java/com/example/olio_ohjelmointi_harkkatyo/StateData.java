package com.example.olio_ohjelmointi_harkkatyo;

import java.util.HashMap;

public class StateData {




    private int year;
    private int population;

    public StateData(int y, int p) {
        year = y;
        population = p;
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
}
