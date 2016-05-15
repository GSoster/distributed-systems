/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilhermesoster.superTrunfop2p.common;

import java.io.Serializable;

/**
 *
 * @author Guilherme Soster Santos (GSoster)
 */
public class Card implements Serializable {

    private String name;
    private float area;
    private int population;
    private float HDI;//idh (indice desenvolvimento humano)
    private int GDP;//pib (produto interno bruto) Bilhões

    public Card() {

    }

    /**
     *
     * @param name
     * @param population
     * @param HDI
     * @param GDP
     */
    public Card(String name, float area, int population, int GDP, float HDI) {
        this.name = name;
        this.area = area;
        this.population = population;
        this.HDI = HDI;
        this.GDP = GDP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public float getHDI() {
        return HDI;
    }

    public void setHDI(float HDI) {
        this.HDI = HDI;
    }

    public int getGDP() {
        return GDP;
    }

    public void setGDP(int GDP) {
        this.GDP = GDP;
    }

    @Override
    public String toString() {
        String toString = "1 - Name: " + this.name + "\n 2 - Area: " + this.area + "\n 3 - Population: " + this.population + "\n 4 - GDP: " + this.GDP + "\n 5 - HDI: " + this.HDI;
        return toString;
    }

    public void printCard() {
        System.out.println("#############################");
        System.out.println("Name: " + this.name);
        System.out.println("Area: " + this.area);
        System.out.println("Population: " + this.population);
        System.out.println("GDP: " + this.GDP);
        System.out.println("HDI: " + this.HDI);
        System.out.println("#############################");
        System.out.println("");
    }

    
    
    
}
