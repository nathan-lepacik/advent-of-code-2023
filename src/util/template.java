package util;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class template {

    public static void main(String[] args) {

        ArrayList<String> input;
        try {
            input = MyFileReader.readFromFile("src/day0/input.txt");
        } catch (FileNotFoundException e) {
            return;
        }

        System.out.println("part one: " + partOne(input));
        System.out.println("part two: " + partTwo(input));

    }

    private static int partOne(ArrayList<String> input) {
        return 0;
    }

    private static int partTwo(ArrayList<String> input) {
        return 0;
    }

}
