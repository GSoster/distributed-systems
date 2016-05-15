/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilhermesoster.superTrunfop2p.common;

import br.com.guilhermesoster.superTrunfop2p.client.Client;

/**
 *
 * @author Guilherme
 */
public class Combat {

    private static Client clientOne;//the first starting to play
    private static Client clientTwo;

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
            return;
        }
        Client clientToAsk = getWhoseTurnIs();//returns the client that should be asked.
        int option = clientToAsk.askWhichAttribute();

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
            case 1:

                break;
        }
    }

    /**
     * Change who can make the next move
     */
    public static void inverseTurns() {
        clientOne.setIsTurn(!clientOne.isIsTurn());
        clientTwo.setIsTurn(!clientTwo.isIsTurn());
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
