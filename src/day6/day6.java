package day6;

import util.MyFileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class day6 {

    public static void main(String[] args) {

        ArrayList<String> input;
        try {
            input = MyFileReader.readFromFile("src/day6/input.txt");
        } catch (FileNotFoundException e) {
            return;
        }

        System.out.println("part one: " + partOne(input));
        System.out.println("part two: " + partTwo(input));

    }

    private static int partOne(ArrayList<String> input) {

        int[] time = new int[4];
        int[] distance = new int[4];

        String[] timeInput = input.get(0).split(" ");
        String[] distInput = input.get(1).split(" ");

        int x = 0;
        for (int i = 1; i < timeInput.length; i++) {
            String str = timeInput[i];
            if (!str.isBlank()) {
                time[x] = Integer.parseInt(timeInput[i]);
                x++;
            }
        }

        x = 0;
        for (int i = 1; i < distInput.length; i++) {
            String str = distInput[i];
            if (!str.isBlank()) {
                distance[x] = Integer.parseInt(distInput[i]);
                x++;
            }
        }

        int num = 1;
        for (int i = 0; i < 4; i++) {
            int wins = 0;
            for (int j = 0; j < time[i]; j++) {
                if ((time[i]-j)*j > distance[i]) {
                    wins++;
                }
            }
            num *= wins;
        }
        return num;
    }

    private static int partTwo(ArrayList<String> input) {
        long time = Long.parseLong(input.get(0).replaceAll(" ", "").substring(input.get(0).indexOf(":")+1));
        long distance = Long.parseLong(input.get(1).replaceAll(" ", "").substring(input.get(1).indexOf(":")+1));
        int wins = 0;
        for (int j = 0; j < time; j++) {
            if ((time-j)*j > distance) {
                wins++;
            }
        }
        return wins;
    }

}
