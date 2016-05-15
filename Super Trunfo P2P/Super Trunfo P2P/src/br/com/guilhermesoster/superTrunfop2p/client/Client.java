/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilhermesoster.superTrunfop2p.client;

import br.com.guilhermesoster.superTrunfop2p.common.Deck;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Soster Santos (GSoster)
 */
public class Client {

    private Socket serverConnectionSocket;
    private Socket opponent;
    private final int port;
    private final String host;
    private Deck deck;
    private boolean isConnected = false;

    public Client() {
        this.port = 1096;
        this.host = "localhost";
        this.connect();
        this.receiveDeck();        
        if(this.deck != null){
            System.out.println("RECEBIDO!");
            deck.shuffle();
            deck.showAllCards();
        }else{
            System.out.println("NOP");
        }
    }

    public void connect() {
        try {
            serverConnectionSocket = new Socket(host, port);
            this.isConnected = true;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiveDeck() {        
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(serverConnectionSocket.getInputStream());
                this.deck = (Deck) objectInputStream.readObject();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }        
    }

    public Socket getSocket() {
        return this.serverConnectionSocket;
    }

    public static void main(String args[]) {
        Client client = new Client();

    }

}
