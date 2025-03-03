package com.example.project;
import java.util.ArrayList;

public class Game{
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
            HighCard(p1, p2);
        }
        return "Error!";
    }

    public static String HighCard (Player p1, Player p2) {
        p1.sortAllCards();
        p2.sortAllCards();
        ArrayList<Card> p1Cards = p1.getAllCards();
        ArrayList<Card> p2Cards = p2.getAllCards();
        int index = p1.getAllCards().size() - 1;
        int player1LastCardVal = Utility.getRankValue(p1Cards.get(index).getRank()); 
        int player2LastCardVal = Utility.getRankValue(p2Cards.get(index).getRank()); 
        while (player1LastCardVal != player2LastCardVal && index >= 0) {
            if (player1LastCardVal > player2LastCardVal) {
                return "Player 1 wins!";
            } 
            if (player2LastCardVal > player1LastCardVal) {
                return "Player 2 wins!";
            }
        }
        return "Tie!";
    }


    public static void play() { //simulate card playing
    
    }
        
        

}