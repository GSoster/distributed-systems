package br.com.guilhermesoster.superTrunfop2p.server;

import br.com.guilhermesoster.superTrunfop2p.common.Deck;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Soster Santos (GSoster) #1 - ler arquivo .csv - instanciar
 *  cartas - montar deck #2 - receber clientes - enviar deck - enviar
 * informações de um cliente para o outro #3 - finaliar. (a partir de então a
 * comunicação é entre clientes)
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
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(players[0].getOutputStream());

            objectOutputStream.writeObject(deck);
            objectOutputStream.close();

            //players[1] = listenSocket.accept();
            //System.out.println("Player two connected");                                    
            //pass Informations
            //first we send infos from the player 1 to player 2
            //oos = new ObjectOutputStream(players[1].getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
