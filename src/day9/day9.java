package day9;

import util.MyFileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class day9 {

    public static void main(String[] args) {

        ArrayList<String> input;
        try {
            input = MyFileReader.readFromFile("src/day9/input.txt");
        } catch (FileNotFoundException e) {
            return;
        }

        System.out.println("part one: " + partOne(input));
        System.out.println("part two: " + partTwo(input));

    }

    private static int partOne(ArrayList<String> input) {
        int sum = 0;
        for (String s : input) {
            String[] numbersString = s.split(" ");
            int[] numbers = new int[numbersString.length];
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = Integer.parseInt(numbersString[i]);
            }
            sum += derivate(numbers) + numbers[numbers.length - 1];
        }
        return sum;
    }

    private static int derivate(int[] input) {

        // check if it is all zeroes
        boolean zeroes = true;
        for (int num : input) {
            if (num != 0) {
                zeroes = false;
                break;
            }
        }
        if (zeroes)
            return 0;

        // find the next order derivative
        int[] derivative = new int[input.length - 1];
        for (int i = 0; i < derivative.length; i++) {
            derivative[i] = input[i+1] - input[i];
        }

        int thingy = derivate(derivative) + derivative[derivative.length - 1];
        return thingy;
    }

    private static int partTwo(ArrayList<String> input) {
        int sum = 0;
        for (String s : input) {
            String[] numbersString = s.split(" ");
            int[] numbers = new int[numbersString.length];
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = Integer.parseInt(numbersString[i]);
            }
            sum += numbers[0] - derivateBackwards(numbers);
        }
        return sum;
    }

    private static int derivateBackwards(int[] input) {

        // check if it is all zeroes
        boolean zeroes = true;
        for (int num : input) {
            if (num != 0) {
                zeroes = false;
                break;
            }
        }
        if (zeroes)
            return 0;

        // find the next order derivative
        int[] derivative = new int[input.length - 1];
        for (int i = 0; i < derivative.length; i++) {
            derivative[i] = input[i+1] - input[i];
        }

        int thingy = derivative[0] - derivateBackwards(derivative);
        return thingy;
    }
}
