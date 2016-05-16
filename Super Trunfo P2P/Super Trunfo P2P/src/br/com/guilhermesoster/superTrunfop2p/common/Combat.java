/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilhermesoster.superTrunfop2p.common;

import br.com.guilhermesoster.superTrunfop2p.client.Client;
import java.io.Serializable;

/**
 *
 * @author Guilherme
 */
public class Combat implements Serializable {

    private static Client clientOne;//the first starting to play
    private static Client clientTwo;//the one that had to wait for connection

    public Combat() {

    }

    public static Client getClientOne() {
        return clientOne;
    }

    public static void setClientOne(Client clientOne) {
        Combat.clientOne = clientOne;
    }

    public static Client getClientTwo() {
        return clientTwo;
    }

    public static void setClientTwo(Client clientTwo) {
        Combat.clientTwo = clientTwo;
    }

    /**
     * verifica se o turno deve ocorrer partindo deste cliente; caso não
     * encerra; caso sim verifica qual cliente pode solicitar o atributo calcula
     * o atributo exibe retorno
     *
     * @param shouldOccur
     */
    public static void makeTurn(boolean shouldOccur) {
        if (!shouldOccur) {
            System.out.println("Turno não aceito, não é sua vez. " + clientTwo.getPlayersName() + " pode? " + clientTwo.isIsTurn());
            System.out.println("É a vez do jogador " + clientOne.getPlayersName());
            return;
        }
        Client clientToAsk = getWhoseTurnIs();//returns the client that should be asked.
        System.out.println("Vez do jogador: " + clientToAsk.getPlayersName());
        int option = clientToAsk.askWhichAttribute();
        calcWinner(option);
        System.out.println("TURNO OCORREU! Opção escolhida: " + option);
        inverseTurns();
    }

    /**
     * it will check which one of the player has the largest value in a specific
     * attribute (that is received by the option param)
     *
     * @param option
     */
    public static void calcWinner(int option) {
        switch (option) {
            case 1://area
                System.out.println("Escolheu area");
                break;
            case 2://population
                System.out.println("Escolheu populacao");
                break;

            case 3://GDP (produto interno bruto)
                System.out.println("Escolheu GDP");
                break;
            case 4://HDI (indice de desenvolvimento humano)
                System.out.println("Escolheu IDH");
                break;
        }
    }

    /**
     * Change who can make the next move
     */
    public static void inverseTurns() {
        System.out.println("ANTES Turno do cliente 1: " + clientOne.isIsTurn());
        clientOne.setIsTurn(!clientOne.isIsTurn());
        System.out.println("DEPOIS Turno do cliente 1: " + clientOne.isIsTurn());
        System.out.println("#ANTES#Turno do cliente 2: " + clientTwo.isIsTurn());
        clientTwo.setIsTurn(!clientTwo.isIsTurn());
        System.out.println("#DEPOIS#Turno do cliente 2: " + clientTwo.isIsTurn());
    }

    /**
     * Verify which player is able to play
     *
     * @return Client
     */
    public static Client getWhoseTurnIs() {
        if (clientOne.isIsTurn()) {
            return clientOne;
        }
        return clientTwo;
    }

}
