/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilhermesoster.superTrunfop2p.server;

import br.com.guilhermesoster.superTrunfop2p.common.Deck;

/**
 *
 * @author Guilherme Soster Santos (GSoster)
 */
public class Server {    
    public static void main(String []args){
        
        System.out.println("Server has started");
        Deck deck = new Deck("estados.csv");
        
        deck.showAllCards();
    }
    
}
