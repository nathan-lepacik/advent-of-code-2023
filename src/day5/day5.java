package day5;

import util.MyFileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class day5 {

    public static void main(String[] args) {

        ArrayList<String> input;
        try {
            input = MyFileReader.readFromFile("src/day5/input.txt");
        } catch (FileNotFoundException e) {
            return;
        }

        System.out.println("part one: " + partOne(input));
        System.out.println("part two: " + partTwo(input));

    }

    public static long partOne(ArrayList<String> input) {
        String[] seeds = input.get(0).split(" ");
        ArrayList<ArrayList<Long[]>> shit = new ArrayList<>();
        int a = 0;
        shit.add(new ArrayList<>());
        for (int i = 1; i < input.size(); i++) {
            if (input.get(i).isBlank()) {
                a++;
                shit.add(new ArrayList<>());
                i ++;
                continue;
            }
            String[] numbers = input.get(i).split(" ");
            shit.get(a).add(new Long[] {Long.parseLong(numbers[0]), Long.parseLong(numbers[1]), Long.parseLong(numbers[2])});
        }
        long min = Long.MAX_VALUE;
        for (int i = 1; i < seeds.length; i++) {
            long seed = Long.parseLong(seeds[i]);
            for (ArrayList<Long[]> map : shit) {
                for (Long[] line : map) {
                    if (seed >= line[1] && seed <= line[1] + line[2] - 1) {
                        seed = seed - line[1] + line[0];
                        break;
                    }
                }
            }
            if (seed < min) min = seed;
        }
        return min;
    }

    // this took 10 minutes to run
    public static long partTwo(ArrayList<String> input) {
        String[] seeds = input.get(0).split(" ");
        ArrayList<ArrayList<Long[]>> shit = new ArrayList<>();
        int a = 0;
        shit.add(new ArrayList<>());
        for (int i = 1; i < input.size(); i++) {
            if (input.get(i).isBlank()) {
                a++;
                shit.add(new ArrayList<>());
                i ++;
                continue;
            }
            String[] numbers = input.get(i).split(" ");
            shit.get(a).add(new Long[] {Long.parseLong(numbers[0]), Long.parseLong(numbers[1]), Long.parseLong(numbers[2])});
        }
        long min = Long.MAX_VALUE;
        for (int i = 1; i < seeds.length; i+=2) {
            long range = Long.parseLong(seeds[i+1]);
            for (int j = 0; j < range; j++) {
                long seed = Long.parseLong(seeds[i])+j;
                for (ArrayList<Long[]> map : shit) {
                    for (Long[] line : map) {
                        if (seed >= line[1] && seed <= line[1] + line[2] - 1) {
                            seed = seed - line[1] + line[0];
                            break;
                        }
                    }
                }
                if (seed < min) min = seed;
            }



        }
        return min;
    }
}
