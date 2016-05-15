package br.com.guilhermesoster.superTrunfop2p.client;

import br.com.guilhermesoster.superTrunfop2p.common.Deck;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Soster Santos (GSoster) 1 - Conectar ao servidor - receber
 * cartas - receber informações de outro jogador 2 - se informações estiverem
 * vazias, esperar outro jogador conectar. 3 - começar jogo
 */
public class Client {

    private Socket serverConnectionSocket;//used to connect with the initial server
    private final int opponentPort = 1095;
    private ServerSocket listenSocket;
    private String opponentAddress = "-";
    private Socket opponent;
    private final int port;//used to connect with the initial server
    private final String host;
    private Deck deck;
    private boolean isConnected = false;
    private boolean isTurn = false;

    public Client() {
        this.port = 1096;
        this.host = "localhost";
        this.connect();
        this.receiveDeck();
        if (this.deck != null) {
            System.out.println("\t #Client: deck received!");
            deck.showAllCards();
        } else {
            System.out.println("\t #Client: deck wasn't received");
        }
        System.out.println("\t #Client: waiting for opponent info");
        this.receiveOpponentAddress();
    }

    /*
     ########### Initial Server related stuff #########
     */
    /**
     * Connecta com server
     */
    public void connect() {
        try {
            serverConnectionSocket = new Socket(host, port);
            this.isConnected = true;
            System.out.println("Client: Connected to server");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Receives a shuffled deck from the intial server
     */
    public void receiveDeck() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(serverConnectionSocket.getInputStream());
            this.deck = (Deck) objectInputStream.readObject();
            System.out.println("Client: Deck received");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Receveis the opponent address from the initial server
     */
    public void receiveOpponentAddress() {
        try {
            System.out.println("\t #Client: receiving opponent info..");
            this.opponentAddress = new DataInputStream(serverConnectionSocket.getInputStream()).readUTF();
            System.out.println("\t #Client opoonent address received: " + this.opponentAddress);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     ########### Receive from opponent  #########
     */
    public void waitOpponentConnection() {
        try {
            this.listenSocket = new ServerSocket(opponentPort);
            this.opponent = this.listenSocket.accept();
            this.opponentAddress = this.opponent.getInetAddress().getCanonicalHostName();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     ########### / Receive from opponent #########
     */
    /*
    
     //###########################################################3
    
     ###########  Transfer to opponent #########
     */
    /*
     ########### / Transfer to opponent #########
     */
    public void connectToOpponent() {
        try {
            this.opponent = new Socket(this.opponentAddress, opponentPort);
            System.out.println("\t #Client: CONNECTED !!! ");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     ########### GETS & SETS #########
     */
    public Socket getSocket() {
        return this.serverConnectionSocket;
    }

    public String getOpponentAddress() {
        return opponentAddress;
    }

    public void setOpponentAddress(String opponentAddress) {
        this.opponentAddress = opponentAddress;
    }

    public Socket getOpponent() {
        return opponent;
    }

    public void setOpponent(Socket opponent) {
        this.opponent = opponent;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public ServerSocket getListenSocket() {
        return listenSocket;
    }

    public void setListenSocket(ServerSocket listenSocket) {
        this.listenSocket = listenSocket;
    }

    public boolean isIsTurn() {
        return isTurn;
    }

    public void setIsTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    /*
     ########### / GETS & SETS #########
     */
    //###################################3333
    //Main
    public static void main(String args[]) {
        Client client = new Client();
        //first to connect, so wait for other client to connect to this one
        if (client.getOpponentAddress() == null || "-".equals(client.getOpponentAddress())) {
            System.out.println("\t #Client: opponent is null");
            System.out.println("\t #Client: waiting opponent to connect");
            client.waitOpponentConnection();
            System.out.println("\t #Client: opponent connected, infos taken: " + client.getOpponentAddress());
        } else {//already know the informations of other client, so connects to it.
            System.out.println("\t #Client: opponent isn't null");
            client.connectToOpponent();
        }
        
        System.out.println("#####################################");
        System.out.println("\t #Client: Now the game can start!");
        
    }

}
