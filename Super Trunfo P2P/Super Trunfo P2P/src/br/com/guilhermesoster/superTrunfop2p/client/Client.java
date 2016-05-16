package br.com.guilhermesoster.superTrunfop2p.client;

import br.com.guilhermesoster.superTrunfop2p.common.Card;
import br.com.guilhermesoster.superTrunfop2p.common.Combat;
import br.com.guilhermesoster.superTrunfop2p.common.Deck;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme Soster Santos (GSoster) 1 - Conectar ao servidor - receber
 * cartas - receber informações de outro jogador 2 - se informações estiverem
 * vazias, esperar outro jogador conectar. 3 - começar jogo
 */
public class Client implements Serializable {

    //to connect with initial server
    private Socket serverConnectionSocket;//used to connect with the initial server
    private final int port;//used to connect with the initial server
    private final String host;//used to connect with initial server
    //client's attributes            
    private final String playersName;
    private Deck deck;
    private boolean isConnected = false;
    private boolean isTurn = false;
    //client communication with other client
    private Socket opponent;//used to connect with opponent
    private String opponentAddress = "-";
    private final int opponentPort = 1095;
    private ServerSocket listenSocket;
    private OutputStream opponentOutPutStream;
    private InputStream opponentInputStream;

    //opponent    
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
        this.playersName = JOptionPane.showInputDialog("Insert your name: ");
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
            this.deck.shuffle();
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
            this.opponentOutPutStream = this.opponent.getOutputStream();
            this.opponentInputStream = this.opponent.getInputStream();
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
            this.opponentOutPutStream = this.opponent.getOutputStream();
            this.opponentInputStream = this.opponent.getInputStream();
            System.out.println("\t #Client: CONNECTED to opponent!!! ");
            System.out.println("\t #Client: Sendind instance of itself");
            System.out.println("\t #Client: client was sent!");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sends a boolean (if the opponent lose = true) to the opponent.
     *
     * @param lose
     */
    public void sendComparationResult(boolean lose) {
        try {
            new DataOutputStream(getOpponentOutPutStream()).writeBoolean(lose);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendCardToOpponent(Card c) {
        try {
            new ObjectOutputStream(this.opponentOutPutStream).writeObject(c);
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

    public OutputStream getOpponentOutPutStream() {
        return opponentOutPutStream;
    }

    public void setOpponentOutPutStream(OutputStream opponentOutPutStream) {
        this.opponentOutPutStream = opponentOutPutStream;
    }

    public InputStream getOpponentInputStream() {
        return opponentInputStream;
    }

    public void setOpponentInputStream(InputStream opponentInputStream) {
        this.opponentInputStream = opponentInputStream;
    }

    public String getPlayersName() {
        return this.playersName;
    }

    /*
     ########### / GETS & SETS #########
     */
    /*
     ###########   CALC/GAME/UI Related #########
     */
    /**
     * returns the last card in the deck
     *
     * @return
     */
    public Card getLastCard() {
        return this.getDeck().getLast();

    }

    /**
     * Shows the last card on deck and asks player with attribute he wants to
     * use.
     *
     * @return
     */
    public int askWhichAttribute() {
        Card c = getLastCard();
        int attribute = Integer.parseInt(JOptionPane.showInputDialog(null, this.getPlayersName() + "\n" + c.toString(), "escolha atributo (restantes: " + this.getDeck().getDeck().size() + ")", JOptionPane.DEFAULT_OPTION));
        return attribute;
    }

    /**
     * Check who won and sends information to opponent
     *
     * @param opponentLose
     * @param opponentsCard
     */
    public void calcWinner(boolean opponentLose, Card opponentsCard) {
        if (opponentLose) {//this won add new card
            this.sendComparationResult(opponentLose);
            this.getDeck().getDeck().add(opponentsCard);
        } else {//this lose, remove card
            Card c = this.getDeck().getLast();
            this.sendCardToOpponent(c);
            this.getDeck().removeLast();
        }
    }

    /**
     * it will check which one of the player has the largest value in a specific
     * attribute (that is received by the option param)
     *
     * @param option
     * @param opponentsCard
     */
    public void calcWinCondition(int option, Card opponentsCard) {
        boolean opponentLose;
        switch (option) {
            case 1://area
                System.out.println("Escolheu area");
                System.out.println("This Card Area: " + this.getLastCard().getArea());
                System.out.println("Opponents Card Area: " + opponentsCard.getArea());
                opponentLose = this.getLastCard().areaBiggerThan(opponentsCard);//se verdadeiro pega a carta, se falso remove de si e envia.                
                this.calcWinner(opponentLose, opponentsCard);
                break;
            case 2://population
                System.out.println("Escolheu populacao");
                System.out.println("This Card Population: " + this.getLastCard().getPopulation());
                System.out.println("Opponents Card Population: " + opponentsCard.getPopulation());
                opponentLose = this.getLastCard().populationBiggerThan(opponentsCard);
                this.calcWinner(opponentLose, opponentsCard);
                break;

            case 3://GDP (produto interno bruto)
                System.out.println("Escolheu GDP");
                System.out.println("This Card GDP: " + this.getLastCard().getGDP());
                System.out.println("Opponents Card GDP: " + opponentsCard.getGDP());
                opponentLose = this.getLastCard().GDPBiggerThan(opponentsCard);
                this.calcWinner(opponentLose, opponentsCard);
                break;
            case 4://HDI (indice de desenvolvimento humano)
                System.out.println("Escolheu IDH");
                System.out.println("This Card HDI: " + this.getLastCard().getHDI());
                System.out.println("Opponents Card HDI: " + opponentsCard.getHDI());
                opponentLose = this.getLastCard().HDIBiggerThan(opponentsCard);
                this.calcWinner(opponentLose, opponentsCard);
                break;
        }
    }

    /*
     ########### / UI Related #########
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
            client.setIsTurn(false);
            Combat.setClientTwo(client);//the one that is going to start to play
        } else {//already know the informations of other client, so connects to it.
            System.out.println("\t #Client: opponent isn't null");
            client.setIsTurn(true);
            client.connectToOpponent();
            //the second one that will start to play
            Combat.setClientOne(client);
        }

        System.out.println("#####################################");
        System.out.println("\t #Client: Now the game can start!");
        int attribute;
        while (true) {
            //verifica se é meu turno
            if (client.isIsTurn()) {
                //  -> se for executa ações de ação
                //      -> exibe ultima carta e pergunta qual atributo
                attribute = client.askWhichAttribute();
                System.out.println("\t #Client: Atributo escolhido: " + attribute);
                try {
                    //      -> envia numero do atributo
                    new DataOutputStream(client.getOpponentOutPutStream()).writeInt(attribute);
                    //      -> enviar carta
                    new ObjectOutputStream(client.getOpponentOutPutStream()).writeObject(client.getLastCard());
                    System.out.println("\t #Client: carta enviada " + client.getLastCard().getName());
                    boolean didILost = new DataInputStream(client.getOpponentInputStream()).readBoolean();//recebe resultado
                    if (didILost) {//if lost remove last card (the winner already have it)
                        System.out.println("\t #Client: Lost, removing card");
                        client.getDeck().removeLast();
                    } else {//receives new card
                        Card c;
                        try {
                            c = (Card) new ObjectInputStream(client.getOpponentInputStream()).readObject();
                            client.getDeck().getDeck().add(c);
                            System.out.println("\t #Client: Won, added card!");
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {// -> se não for executa ações de espera
                try {//      -> espera receber numero de atributo                    
                    attribute = new DataInputStream(client.getOpponentInputStream()).readInt();
                    System.out.println("\t #Client: Atributo receido! " + attribute);
                    try {//      -> espera receber carta                        
                        Card c = (Card) new ObjectInputStream(client.getOpponentInputStream()).readObject();
                        System.out.println("\t #Client: Carta recebida " + c.getName());
                        //      -> calcula qual carta ganhou
                        client.calcWinCondition(attribute, c);//      -> retorna resultado (ganhou? + carta) p/quem iniciou turno
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }//else
            client.setIsTurn(!client.isIsTurn());//inverte turno
        }//while
    }

}
