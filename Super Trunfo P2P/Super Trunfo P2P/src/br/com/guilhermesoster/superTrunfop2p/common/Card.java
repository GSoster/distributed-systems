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
    private int population;
    private int area;
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
    public Card(String name, int population, float HDI, int GDP) {
        this.name = name;
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

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
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

}
