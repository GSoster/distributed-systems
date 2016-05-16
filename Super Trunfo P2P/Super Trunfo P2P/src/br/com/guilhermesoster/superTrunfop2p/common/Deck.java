package br.com.guilhermesoster.superTrunfop2p.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Soster Santos (GSoster)
 */
public class Deck implements Serializable {

    private Card[] cards;
    private ArrayList<Card> deck;//to make it easier to work with card (withdraw, etc)
    private int numCards;

    public Deck(String pathToFile) {
        try {
            deck = new ArrayList<Card>();
            Scanner sc = new Scanner(new FileReader(pathToFile));
            numCards = Integer.parseInt(sc.nextLine());//first line contains how many cards we have in the file                                    
            cards = new Card[numCards];
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String card[] = line.split(";");
                String name = card[0];
                float area = Float.parseFloat(card[1].trim());
                int population = Integer.parseInt(card[2].trim());
                int gdp = Integer.parseInt(card[3].trim());
                float hdi = Float.parseFloat(card[4].trim());
                cards[numCards - 1] = new Card(name, area, population, gdp, hdi);
                numCards--;
            }
            //to make it easier to work with the deck:
            fromCardsToDeck();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Deck.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * add the cards that are in the array into the arrayList passa as cartas do
     * array p/arrayList
     */
    private void fromCardsToDeck() {
        for (Card card : cards) {
            deck.add(card);
            System.out.println("Carta adicionada");
        }
    }

    /**
     * shuffle all the cards inside the arrayList Embaralha as cartas dentro da
     * lista
     */
    public void shuffle() {
        //Collections.shuffle(Arrays.asList(cards));
        Collections.shuffle(deck);
    }

    /**
     * display cards Exibe todas as cartas no console
     */
    public void showAllCards() {
        for (Card c : deck) {
            c.printCard();
        }
    }

    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    public Card getLast() {
        return this.getDeck().get(this.getDeck().size() - 1);
    }

    public void removeLast() {
        this.getDeck().remove(this.getDeck().size() - 1);
    }
}
