package day3;

import util.MyFileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class day3 {

    private static final String symbols = "!@#$%^&*()/-+=";
    private static final String numbers = "0123456789";

    public static void main(String[] args) {

        ArrayList<String> input;
        try {
            input = MyFileReader.readFromFile("src/day3/input.txt");
        } catch (FileNotFoundException e) {
            return;
        }

        System.out.println("part one: " + partOne(input));
        System.out.println("part two: " + partTwo(input));

    }

    public static boolean searchForSymbol(ArrayList<String> input, int x, int y, int length) {
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - length; j <= x + 1; j++) {
                if (i > 0 && i < input.size() && j > 0 && j < input.get(i).length()) {
                    if (symbols.contains(input.get(i).substring(j, j+1))) return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<Gear> searchForGear(ArrayList<String> input, int x, int y, int length, int num) {
        ArrayList<Gear> gears = new ArrayList<>();
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - length; j <= x + 1; j++) {
                if (i > 0 && i < input.size() && j > 0 && j < input.get(i).length()) {
                    if (symbols.contains(input.get(i).substring(j, j+1))) {
                        gears.add(new Gear(num, 1, i, j));
                    }
                }
            }
        }
        return gears;
    }

    public static int partOne(ArrayList<String> input) {
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (!numbers.contains(line.substring(j, j + 1))) {
                    continue;
                }
                int num = Math.max(0, numbers.indexOf(line.substring(j, j+1)));
                while (j+1 < input.get(i).length() && numbers.contains(line.substring(j+1, j+2))) {
                    j++;
                    num *= 10;
                    num += Math.max(0, numbers.indexOf(line.substring(j, j+1)));
                }
                int length = Integer.toString(num).length();
                if (searchForSymbol(input, j, i, length)) {
                    sum += num;
                }
            }
        }
        return sum;
    }

    public static int partTwo(ArrayList<String> input) {
        ArrayList<Gear> gearList = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (!numbers.contains(line.substring(j, j + 1))) {
                    continue;
                }
                int num = Math.max(0, numbers.indexOf(line.substring(j, j+1)));
                while (j+1 < input.get(i).length() && numbers.contains(line.substring(j+1, j+2))) {
                    j++;
                    num *= 10;
                    num += Math.max(0, numbers.indexOf(line.substring(j, j+1)));
                }
                int length = Integer.toString(num).length();
                gearList.addAll(searchForGear(input, j, i, length, num));
            }
        }
        Gear[][] gears = new Gear[input.size()][input.get(0).length()];
        for (Gear gear : gearList) {
            if (gears[gear.i][gear.j] != null) {
                gears[gear.i][gear.j].numCount++;
                gears[gear.i][gear.j].ratio *= gear.ratio;
                continue;
            }
            gears[gear.i][gear.j] = gear;
        }
        int ratio = 0;
        for (int i = 0; i < gears.length; i++) {
            for (int j = 0; j < gears[i].length; j++) {
                if (gears[i][j] != null && gears[i][j].numCount >= 2)
                    ratio += gears[i][j].ratio;
            }
        }
        return ratio;
    }

    static class Gear {
        public int ratio;
        public int numCount;
        public int i;
        public int j;

        public Gear(int ratio, int numCount, int i, int j) {
            this.ratio = ratio;
            this.numCount = numCount;
            this.i = i;
            this.j = j;
        }
    }
}
