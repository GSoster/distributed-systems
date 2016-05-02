/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilhermesoster.superTrunfop2p.server;

import br.com.guilhermesoster.superTrunfop2p.common.Deck;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Soster Santos (GSoster)
 */
public class Server {

    public static void main(String[] args) {

        ServerSocket listenSocket;
        Socket players[] = new Socket[2];
        ObjectOutputStream oos;
        int port = 1096;
        
        //initialize deck
        System.out.println("Server has started");
        Deck deck = new Deck("estados.csv");
        //deck.showAllCards();

        try {
            listenSocket = new ServerSocket(port);
            System.out.println("Waiting for players to connect.");
            players[0] = listenSocket.accept();
            System.out.println("Player one connected");
            players[1] = listenSocket.accept();
            System.out.println("Player two connected");                                    
            
            //pass Informations
            //first we send infos from the player 1 to player 2
             oos = new ObjectOutputStream(players[1].getOutputStream());
             
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
