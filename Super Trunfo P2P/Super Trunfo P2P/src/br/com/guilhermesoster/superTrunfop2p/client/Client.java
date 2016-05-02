/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilhermesoster.superTrunfop2p.client;

import br.com.guilhermesoster.superTrunfop2p.common.Deck;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Soster Santos (GSoster)
 */
public class Client {

    private Socket socket;
    private Socket opponent;
    private final int port;
    private final String host;
    private Deck deck;

    public Client() {
        this.port = 1096;
        this.host = "localhost";
        this.connect();
    }

    public void connect() {
        try {
            socket = new Socket(host, port);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Socket getSocket() {
        return this.socket;
    }

    public static void main(String args[]) {
        Client client = new Client();

    }

}