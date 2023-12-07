package day7;

import util.MyFileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class day7 {

    public static void main(String[] args) {

        ArrayList<String> input;
        try {
            input = MyFileReader.readFromFile("src/day7/input.txt");
        } catch (FileNotFoundException e) {
            return;
        }

        System.out.println("part one: " + partOne(input));
        System.out.println("part two: " + partTwo(input));

    }

    private static int partOne(ArrayList<String> input) {
        final String cardString = "23456789TJQKA";
        Hand[] hands = new Hand[input.size()];
        int sum = 0;
        for (int l = 0; l < input.size(); l++) {
            String line = input.get(l);
            int[] cardCount = new int[100];
            char[] cards = line.substring(0, 5).toCharArray();
            int score;
            int bet = Integer.parseInt(line.substring(6));
            for (char card : cards) {
                cardCount[card]++;
            }
            Arrays.sort(cardCount);
            if (cardCount[99] == 5)
                // yahtzee
                score = 6;
            else if (cardCount[99] == 4)
                // four of a kind
                score = 5;
            else if (cardCount[99] == 3 && cardCount[98] == 2)
                // full house
                score = 4;
            else if (cardCount[99] == 3)
                // three of a kind
                score = 3;
            else if (cardCount[99] == 2 && cardCount[98] == 2)
                // two pair
                score = 2;
            else if (cardCount[99] == 2)
                // one pair
                score = 1;
            else {
                // high card
                score = 0;
            }

            for (char card : cards) {
                score *= 15;
                score += cardString.indexOf(card);
            }

            hands[l] = new Hand(cards, score, bet);
        }
        Arrays.sort(hands);
        for (int i = 0; i < hands.length; i++) {
//            System.out.println(hands[i].toString());
            sum += (i+1) * hands[i].bet;
        }
        return sum;
    }

    private static int partTwo(ArrayList<String> input) {
        final String cardString = "J23456789TQKA";
        Hand[] hands = new Hand[input.size()];
        int sum = 0;
        for (int l = 0; l < input.size(); l++) {
            String line = input.get(l);
            int[] cardCount = new int[100];
            char[] cards = line.substring(0, 5).toCharArray();
            int score;
            int bet = Integer.parseInt(line.substring(6));
            for (char card : cards) {
                cardCount[card]++;
            }
            int temp = cardCount['J'];
            cardCount['J'] = 0;
            Arrays.sort(cardCount);
            cardCount[99] += temp;
            if (cardCount[99] == 5)
                // yahtzee
                score = 6;
            else if (cardCount[99] == 4)
                // four of a kind
                score = 5;
            else if (cardCount[99] == 3 && cardCount[98] == 2)
                // full house
                score = 4;
            else if (cardCount[99] == 3)
                // three of a kind
                score = 3;
            else if (cardCount[99] == 2 && cardCount[98] == 2)
                // two pair
                score = 2;
            else if (cardCount[99] == 2)
                // one pair
                score = 1;
            else {
                // high card
                score = 0;
            }

            for (char card : cards) {
                score *= 15;
                score += cardString.indexOf(card);
            }

            hands[l] = new Hand(cards, score, bet);
        }
        Arrays.sort(hands);
        for (int i = 0; i < hands.length; i++) {
//            System.out.println(hands[i].toString());
            sum += (i+1) * hands[i].bet;
        }
        return sum;
    }

    static class Hand implements Comparable<Hand> {

        char[] cards;
        int score;
        int bet;

        public Hand(char[] cards, int score, int bet) {
            this.cards = cards;
            this.score = score;
            this.bet = bet;
        }

        @Override
        public int compareTo(Hand o) {
            return score - o.score;
        }

        @Override
        public String toString() {
            return "Hand{" +
                    "cards=" + Arrays.toString(cards) +
                    ", score=" + score +
                    ", bet=" + bet +
                    '}';
        }
    }
}
