package day2;

import util.MyFileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class day2 {

    public static void main(String[] args) {

        ArrayList<String> input;
        try {
            input = MyFileReader.readFromFile("src/day2/input.txt");
        } catch (FileNotFoundException e) {
            return;
        }

        System.out.println("part one: " + partOne(input));
        System.out.println("part two: " + partTwo(input));

    }

    public static int partOne(ArrayList<String> input) {
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            String[] cubes = line.split(" ");
            boolean possible = true;
            for (int j = 2; j < cubes.length; j+=2) {
                int value = Integer.parseInt(cubes[j]);
                int max = switch (cubes[j + 1].charAt(0)) {
                    case 'r' -> 12;
                    case 'g' -> 13;
                    case 'b' -> 14;
                    default -> 0;
                };
                if (value > max) {
                    possible = false;
                    break;
                }
            }
            if (possible)
                sum += i + 1;
        }
        return sum;
    }

    public static int partTwo(ArrayList<String> input) {
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            String[] cubes = line.split(" ");
            HashMap<Character, Integer> bag = new HashMap<>();
            for (int j = 2; j < cubes.length; j += 2) {
                int value = Integer.parseInt(cubes[j]);
                char color = cubes[j+1].charAt(0);
                try {
                    bag.put(color, Math.max(value, bag.get(color)));
                } catch (NullPointerException e) {
                    bag.put(color, value);
                }
            }
            sum += bag.get('r') * bag.get('g') * bag.get('b');
        }
        return sum;
    }

}
