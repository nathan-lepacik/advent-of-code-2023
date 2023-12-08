package day8;

import util.MyFileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class day8 {

    public static void main(String[] args) {

        ArrayList<String> input;
        try {
            input = MyFileReader.readFromFile("src/day8/input.txt");
        } catch (FileNotFoundException e) {
            return;
        }

        System.out.println("part one: " + partOne(input));
        System.out.println("part two: " + partTwo(input));

    }

    private static int partOne(ArrayList<String> input) {
        String pattern = input.get(0);

        boolean found = false;
        int count = 0;
        String start = "AAA";
        while (!found) {

            // check if we are at ZZZ
            if (start.equals("ZZZ")) {
                break;
            }

            // search for the starting point
            int i = 2;
            while (!input.get(i).substring(0, 3).equals(start)) {
                i++;
            }

            // get the next starting point based on the pattern
            if (pattern.charAt(count % pattern.length()) == 'L') {
                start = input.get(i).substring(7, 10);
            } else {
                start = input.get(i).substring(12, 15);
            }

            count++;
        }

        return count;
    }

    private static long partTwo(ArrayList<String> input) {
        String pattern = input.get(0);

        // create all the ghosts
        ArrayList<Ghost> ghosts = new ArrayList<>();
        for (int i = 2; i < input.size(); i++) {
            if (input.get(i).substring(0, 3).endsWith("A")) {
                ghosts.add(new Ghost(input.get(i).substring(0, 3), input, pattern));
            }
        }

        // find the least common multiple of all the ghost path lengths
        long sum = 1;
        for (Ghost ghost : ghosts) {
            sum = lcm(sum, ghost.endpoint);
        }

        return sum;
    }

    private static long lcm(long a, long b) {
        int gcd = 1;
        for (int i = 1; i < a && i < b; i++) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
            }
        }
        return (a * b) / gcd;
    }

    static class Ghost {
        private String start;
        int endpoint;

        public Ghost (String start, ArrayList<String> input, String pattern) {
            this.start = start;
            endpoint = findEndpoint(input, pattern);
        }

        public int findEndpoint(ArrayList<String> input, String pattern) {
            int count = 0;
            while (!start.endsWith("Z")) {

                // check if we are at ZZZ

                // search for the starting point
                int i = 2;
                while (!input.get(i).substring(0, 3).equals(start)) {
                    i++;
                }

                // get the next starting point based on the pattern
                if (pattern.charAt(count % pattern.length()) == 'L') {
                    start = input.get(i).substring(7, 10);
                } else {
                    start = input.get(i).substring(12, 15);
                }

                count++;
            }

            return count;
        }
    }

}
