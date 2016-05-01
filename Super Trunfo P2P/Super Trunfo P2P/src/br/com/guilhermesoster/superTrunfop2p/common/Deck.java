/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilhermesoster.superTrunfop2p.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme
 */
public class Deck {

    private Card[] cards;
    private int numCards;

    public Deck(String pathToFile) {
        try {
            Scanner sc = new Scanner(new FileReader(pathToFile));
            numCards = Integer.parseInt(sc.nextLine());//first line contains how many cards we have in the file                                    
            cards = new Card[numCards];
            while (sc.hasNext()) {
                String line = sc.nextLine();                
                String card[] = line.split(";");                
                String name = card[0];
                int population = Integer.parseInt(card[1]);
                float hdi = Float.parseFloat(card[2]);
                int gdp = Integer.parseInt(card[3]);
                cards[numCards-1] = new Card(name, population, hdi, gdp);                                
                numCards--;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Deck.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
