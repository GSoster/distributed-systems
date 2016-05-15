/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilhermesoster.superTrunfop2p.server;

import br.com.guilhermesoster.superTrunfop2p.common.Deck;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme
 */
public class ServerThread extends Thread {

    private Deck deck;
    private Socket clientSocket;
    private OutputStream clientOutputStream;
    private String previousClientAddress;

    public ServerThread(Deck deck, Socket socket, String previousClientAddress) {
        this.deck = deck;
        this.deck.shuffle();//shuffle the deck so the clients will have cards in different positions.
        this.clientSocket = socket;
        try {
            this.clientOutputStream = this.clientSocket.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.previousClientAddress = previousClientAddress;
        System.out.println("ServerThread: Client connected!");
    }

    @Override
    public void run() {
        System.out.println("Metodo run executado!");
        sendDeck();
        System.out.println("ServerThread: Deck sent!");
        sendOpponentAddress();
        System.out.println("ServerThread: Opponent address sent!");
    }

    public void sendDeck() {
        ObjectOutputStream objectOutputStream
                = null;
        try {
            objectOutputStream = new ObjectOutputStream(this.clientOutputStream);
            objectOutputStream.writeObject(this.deck);
            //objectOutputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendOpponentAddress() {
        try {
            System.out.println("ServerThread: sending opponent Address: " + previousClientAddress);
            new DataOutputStream(this.clientOutputStream).writeUTF(previousClientAddress);
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void closeConnection() {
        try {
            Thread.sleep(600);
            this.deck = null;
            this.previousClientAddress = null;
            this.clientSocket.close();
            this.clientSocket = null;
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
