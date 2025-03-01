package com.example.project;
import java.util.ArrayList;

public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
        allCards = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){ 
        return hand;
    }
    
    public ArrayList<Card> getAllCards(){
        return allCards;
    }

    public void addCard(Card c){
        hand.add(c);
    }

    public boolean isRoyalFlush(ArrayList<Card> allCards) {
        boolean isSameSuit = false; 
        String firstCardSuit = allCards.get(0).getSuit();
        int lastCardRank = Utility.getRankValue(allCards.get(0).getRank());
        for (int i = 1; i < allCards.size(); i++) {
            if (allCards.get(i).getSuit().equals(firstCardSuit)) {
                isSameSuit = true;
            } else {
                return false;
            }
        }
        if (isSameSuit && lastCardRank == 10) {
            for (int i = 1; i < allCards.size(); i++) {
                int nextCardRank = Utility.getRankValue(allCards.get(i).getRank()); 
                if (nextCardRank == lastCardRank + 1) {
                    lastCardRank = nextCardRank;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean isStraightFlush(ArrayList<Card> allCards) {
        boolean isSameSuit = false; 
        String firstCardSuit = allCards.get(0).getSuit();
        int lastCardRank = Utility.getRankValue(allCards.get(0).getRank());
        for (int i = 1; i < allCards.size(); i++) {
            if (allCards.get(i).getSuit().equals(firstCardSuit)) {
                isSameSuit = true;
            } else {
                return false;
            }
        }
        if (isSameSuit) {
            for (int i = 1; i < allCards.size(); i++) {
                int nextCardRank = Utility.getRankValue(allCards.get(i).getRank()); 
                if (nextCardRank == lastCardRank + 1) {
                    lastCardRank = nextCardRank;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean isFourOfAKind(ArrayList<Card> allCards) {
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        for (int i = 0; i < 13; i++) {
            if (rankFrequency.get(i) == 4) {
                return true;
            }
        }
        return false;
    }

    public boolean isFullHouse(ArrayList<Card> allCards) {
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        boolean two = false;
        boolean three = false;
        for (int i = 0; i < rankFrequency.size(); i++) {
            if (rankFrequency.get(i) == 2) {
                two = true;
            }
            if (rankFrequency.get(i) == 3) {
                three = true;
            }
        }
        if (two == true) {
            if (three == true) {
                return true;
            }
        }
        return false;
    }

    public boolean isFlush(ArrayList<Card> allCards) {
        String firstCardSuit = allCards.get(0).getSuit();
        for (int i = 1; i < allCards.size(); i++) {
            if (allCards.get(i).getSuit().equals(firstCardSuit) == false) {
                return false;
            }
        }
        return true;
    }

    public boolean isStraight(ArrayList<Card> allCards) {
        int lastCardRank = Utility.getRankValue(allCards.get(0).getRank()); 
        for (int i = 1; i < allCards.size(); i++) {
            int nextCardRank = Utility.getRankValue(allCards.get(i).getRank()); 
            if (nextCardRank == lastCardRank + 1) {
                lastCardRank = nextCardRank;
            } else {
                return false;
            }
        }
        return true;    
    }

    public boolean isThreeOfAKind(ArrayList<Card> allCards) {
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        for (int i = 0; i < 13; i++) {
            if (rankFrequency.get(i) == 3) {
                return true;
            }
        }
        return false;
    }

    public boolean isTwoPair(ArrayList<Card> allCards) {
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        int totalPairs = 0;
        for (int i = 0; i < 13; i++) {
            if (rankFrequency.get(i) == 2) {
                totalPairs++;
            }
        }
        if (totalPairs == 2) {
            return true;
        }
        return false;
    }

    public boolean isOnePair(ArrayList<Card> allCards) {
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        int totalPairs = 0;
        for (int i = 0; i < 13; i++) {
            if (rankFrequency.get(i) == 2) {
                return true; 
            }
        }
        return false;
    }

    public boolean isHighCard(ArrayList<Card> allCards) {
        Card highCard = allCards.get(allCards.size()-1);
        boolean hasHighCard = false;
        for (Card card: hand) {
            if (card.getRank().equals(highCard.getRank())) {
                hasHighCard = true;
            }
        }
        return hasHighCard;
    }

    public String playHand(ArrayList<Card> communityCards){     
        //copies all elements from hand into allCards 
        allCards = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            allCards.add(hand.get(i));
        }
        //appends all community cards to the allCards list
        for (int i = 0; i < communityCards.size(); i++) {
            allCards.add(communityCards.get(i));
        }
        sortAllCards();
        if (isRoyalFlush(allCards)) {
            return "Royal Flush";
        }
        if (isStraightFlush(allCards)) {
            return "Straight Flush";
        }
        if (isFourOfAKind(allCards)) {
            return "Four of a Kind";
        }
        if (isFullHouse(allCards)) {
            return "Full House";
        }
        if (isFlush(allCards)) {
            return "Flush"; 
        }
        if (isStraight(allCards)) {
            return "Straight"; 
        }
        if (isThreeOfAKind(allCards)) {
            return "Three of a Kind"; 
        }
        if (isTwoPair(allCards)) {
            return "Two Pair"; 
        }
        if (isOnePair(allCards)) {
            return "A Pair"; 
        }
        if (isHighCard(allCards)) {
            return "High Card";
        }
        return "Nothing";
    }

    public void sortAllCards() {
        for (int i = 0; i < allCards.size() - 1; i++) {
            for (int j = i + 1; j < allCards.size(); j++) {
                if (Utility.getRankValue(allCards.get(i).getRank()) > Utility.getRankValue(allCards.get(j).getRank())) {
                    Card temp = allCards.get(i);
                    allCards.set(i, allCards.get(j));
                    allCards.set(j, temp);
                }
            }
        }
    }

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> rankingFrequency = new ArrayList<Integer>(); 
        for (int i = 0; i < 13; i++) {
            rankingFrequency.add(0);
        }
        for (Card card : allCards) {
            int totalRank = 0; 
            for (int i = 0; i < ranks.length; i++) {
                if (card.getRank().equals(ranks[i])) {
                    rankingFrequency.set(i, rankingFrequency.get(i) + 1);
                }
            }
        }
        return rankingFrequency;
    }
    
    public ArrayList<Integer> findSuitFrequency() {
        ArrayList<Integer> suitFrequency = new ArrayList<Integer>(); 
        for (int i = 0; i < 4; i++) {
            suitFrequency.add(0);
        }
        for (Card card : allCards) {
            int totalSuit = 0; 
            for (int i = 0; i < suits.length; i++) {
                if (card.getSuit().equals(suits[i])) {
                    suitFrequency.set(i, suitFrequency.get(i) + 1);
                }
            }
        }
        return suitFrequency;
    }  

    @Override
    public String toString(){
        return hand.toString();
    }
}
