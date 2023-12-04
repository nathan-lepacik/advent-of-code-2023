package day4;

import util.MyFileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class day4 {

    public static void main(String[] args) {

        ArrayList<String> input;
        try {
            input = MyFileReader.readFromFile("src/day4/input.txt");
        } catch (FileNotFoundException e) {
            return;
        }

        System.out.println("part one: " + partOne(input));
        System.out.println("part two: " + partTwo(input));

    }

    private static int partOne(ArrayList<String> input) {
        int sum = 0;
        for (String line : input) {
            String[] numbers = line.split(" ");
            ArrayList<Integer> winningNumbers = new ArrayList<>();
            ArrayList<Integer> chosenNumbers = new ArrayList<>();
            int i = 0;
            while (winningNumbers.size() < 10) {
                try {
                    winningNumbers.add(Integer.parseInt(numbers[i].trim()));
                } catch (NumberFormatException e) {
                    // ignore
                }
                i++;
            }
            i++;
            while (chosenNumbers.size() < 25) {
                try {
                    chosenNumbers.add(Integer.parseInt(numbers[i].trim()));
                } catch (NumberFormatException e) {
                    // ignore
                }
                i++;
            }
            int score = 0;
            for (int number : chosenNumbers) {
                if (winningNumbers.contains(number)) {
                    score++;
                }
            }
            if (score > 0) {
                sum += Math.pow(2, score - 1);
            }
        }
        return sum;
    }

    private static int partTwo(ArrayList<String> input) {
        int[] scores = new int[input.size()];
        ArrayList<Integer> scratchcards = new ArrayList<>();
        for (int j = 0; j < input.size(); j++) {
            String line = input.get(j);
            String[] numbers = line.split(" ");
            ArrayList<Integer> winningNumbers = new ArrayList<>();
            ArrayList<Integer> chosenNumbers = new ArrayList<>();
            int i = 0;
            while (winningNumbers.size() < 10) {
                try {
                    winningNumbers.add(Integer.parseInt(numbers[i].trim()));
                } catch (NumberFormatException e) {
                    // ignore
                }
                i++;
            }
            i++;
            while (chosenNumbers.size() < 25) {
                try {
                    chosenNumbers.add(Integer.parseInt(numbers[i].trim()));
                } catch (NumberFormatException e) {
                    // ignore
                }
                i++;
            }
            int score = 0;
            for (int number : chosenNumbers) {
                if (winningNumbers.contains(number)) {
                    score++;
                }
            }
            scores[j] = score;
        }
        for (int i = 0; i < 202; i++) {
            scratchcards.add(i);
        }
        for (int i = 0; i < scratchcards.size(); i++) {
            for (int j = 0; j < scores[scratchcards.get(i)]; j++) {
                scratchcards.add(scratchcards.get(i) + j + 1);
            }
        }
        return scratchcards.size();
    }

}
