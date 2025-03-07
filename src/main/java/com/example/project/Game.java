package com.example.project;
import java.util.ArrayList;  
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Aryan's Poker Game. Press Enter to play.");
        scan.nextLine();
        while(true){
            play();
            System.out.println("Do you want to play again?(-1 for no)");
            String input = scan.nextLine();
            if(input.equals("-1")){
                break;
            }
        }
        scan.close();
    }

    //this algorithm returns a winner between two Player objects based on their handRanks.
    //if the hand ranks are the same, it returns the player with the highest card in their Hand
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        ArrayList<Card> p1All = p1.getAllCards(); 
        ArrayList<Card> p2All = p2.getAllCards(); 
        int player1Ranking = Utility.getHandRanking(p1Hand);
        int player2Ranking = Utility.getHandRanking(p2Hand); 
        if (player1Ranking > player2Ranking) {
            return "Player 1 wins!";
        } else if (player2Ranking > player1Ranking) {
            return "Player 2 wins!"; 
        } else {
            return HighCard(p1, p2);
        }
    }

    //this static method returns the player with the highest Hand between two player objects 
    //this method is beneficial when both players have the same Hand, otherewise it will be tied
    public static String HighCard (Player p1, Player p2) {
        p1.sortHand();
        p2.sortHand();
        ArrayList<Card> p1HandCards = p1.getHand();
        ArrayList<Card> p2HandCards = p2.getHand(); 
        int index = p1HandCards.size() - 1;
        for (int i = index; i >= 0; i--) {
            int player1LastCardVal = Utility.getRankValue(p1HandCards.get(index).getRank()); 
            int player2LastCardVal = Utility.getRankValue(p2HandCards.get(index).getRank()); 
            if (player1LastCardVal > player2LastCardVal) {
                return "Player 1 wins!";
            } else if (player2LastCardVal > player1LastCardVal) {
                return "Player 2 wins!";
            }
        }
        return "Tie!";
    }


    public static void play() { //simulate card playing
        Deck deck = new Deck();
        deck.initializeDeck();
        deck.shuffleDeck(); //creates Card Deck and shuffles it

        //player Hand Cards
        Player player1 = new Player();
        Player player2 = new Player();
        player1.addCard(deck.drawCard());
        player1.addCard(deck.drawCard());
        player2.addCard(deck.drawCard());
        player2.addCard(deck.drawCard());

        //Community Cards
        ArrayList<Card> community = new ArrayList<>();
        community.add(deck.drawCard());
        community.add(deck.drawCard());
        community.add(deck.drawCard());
        
        //prints each players cards and community cards and then prints the winner
        System.out.println("Community Cards: \n" + community);
        System.out.println("Player 1: \n" + player1.getHand());
        System.out.println("Player 2: \n" + player2.getHand());

        System.out.println(determineWinner(player1, player2, player1.playHand(community), player2.playHand(community), community));
    }
}