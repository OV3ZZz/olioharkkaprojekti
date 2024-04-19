package com.example.olioharkkaprojekti;

public class MunicipalityData {

    private int year;
    private int population;
    private int tyokay;
    private int vaesto;
    private int work;

    public MunicipalityData(int y, int p, int t, int v, int w) {
        year = y;
        population = p;
        tyokay = t;
        vaesto = v;
        work = w;
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

    public int getTyokay() {
        return tyokay;
    }

    public int getVaesto() {
        return vaesto;
    }

    public int getWork() {
        return work;
    }
}
