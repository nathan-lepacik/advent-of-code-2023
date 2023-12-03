package day1;

import util.MyFileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day1 {

    private static Scanner in;
    private static final String[] nums = new String[] {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "zero", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public static void main(String[] args) {

        ArrayList<String> input;
        try {
            input = MyFileReader.readFromFile("src/day1/input.txt");
        } catch (FileNotFoundException e) {
            return;
        }

        System.out.println("part one: " + partOne(input));
        System.out.println("part two: " + partTwo(input));

    }

    public static int partOne(ArrayList<String> input) {
        int sum = 0;
        for (String line : input) {
            int i = 0, j = line.length() - 1;
            while (line.charAt(i) > 57) {
                i++;
            }
            while (line.charAt(j) > 57) {
                j--;
            }
            sum += ((line.charAt(i)-48)*10) + ((line.charAt(j)-48));
        }
        return sum;
    }

    public static int partTwo(ArrayList<String> input) {
        int sum = 0;
        for (String line : input) {
            int[] map = new int[line.length()];
            for (int i = 0; i < nums.length; i++) {
                String num = nums[i];
                int startIndex;
                int numIndex = line.indexOf(num);
                while (numIndex != -1) {
                    map[numIndex] = (i+1)%10;
                    startIndex = numIndex + 1;
                    numIndex = line.indexOf(num, startIndex);
                }
            }
            int a = 0, b = map.length - 1;
            while (map[a] == 0) {
                a++;
            }
            while (map[b] == 0) {
                b--;
            }
            sum += map[a]*10 + map[b];
        }
        return sum;
    }

}
