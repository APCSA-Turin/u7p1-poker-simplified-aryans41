package com.example.project;
import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        int player1Ranking = Utility.getHandRanking(p1Hand);
        int player2Ranking = Utility.getHandRanking(p2Hand); 
        if (player1Ranking > player2Ranking) {
            return "Player 1 wins!";
        }
        if (player2Ranking > player1Ranking) {
            return "Player 2 wins!"; 
        }
        return "Tie!";
    }

    public static void play() { //simulate card playing
    
    }
        
        

}