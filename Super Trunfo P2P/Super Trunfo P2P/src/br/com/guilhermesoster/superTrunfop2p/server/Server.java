package br.com.guilhermesoster.superTrunfop2p.server;

import br.com.guilhermesoster.superTrunfop2p.common.Deck;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Soster Santos (GSoster) #1 - ler arquivo .csv - instanciar
 * cartas - montar deck #2 - receber clientes - enviar deck - enviar informações
 * de um cliente para o outro #3 - finaliar. (a partir de então a comunicação é
 * entre clientes)
 */
public class Server {

    private ServerSocket listenSocket;
    private Socket players[] = new Socket[2];
    private final int port = 1096;
    private Deck deck;
    private String previousClientAddress = "-";

    public Server() {
        //initialize deck
        System.out.println("Server has started");
        this.deck = new Deck("estados.csv");//deck is responsible for loading its cards
        //deck.showAllCards();
    }

    public void communicate() {
        try {
            listenSocket = new ServerSocket(port);
            System.out.println("Waiting for players to connect.");
            while (true) {
                Socket clientSocket = listenSocket.accept();
                deck.shuffle();
                ServerThread serverThread = new ServerThread(deck, clientSocket, previousClientAddress);
                //this.previousClientAddress = clientSocket.getInetAddress().toString();
                this.previousClientAddress = clientSocket.getInetAddress().getCanonicalHostName();
                System.out.println("Server: previous client Adrress: " + this.previousClientAddress);
                serverThread.run();
                serverThread.closeConnection();
                clientSocket = null;
                serverThread = null;
                System.out.println("Server: Connection closed");
            }

            //player 1
            /*players[0] = listenSocket.accept();
             System.out.println("Player one connected");
             ObjectOutputStream objectOutputStream
             = new ObjectOutputStream(players[0].getOutputStream());

             objectOutputStream.writeObject(this.deck);
             objectOutputStream.close();*/
            //player 2
            //players[1] = listenSocket.accept();
            //System.out.println("Player two connected");                                    
            //pass Informations
            //first we send infos from the player 1 to player 2
            //oos = new ObjectOutputStream(players[1].getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.communicate();
    }

}
