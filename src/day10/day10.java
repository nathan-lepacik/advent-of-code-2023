package day10;

import util.MyFileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class day10 {

    // this is so stupid
    private static final int[][]
            dashPipe = new int[][] {{0, -1}, {0, 1}},
            pipePipe = new int[][] {{-1, 0}, {1, 0}},
            jPipe = new int[][] {{-1, 0}, {0, -1}},
            lPipe = new int[][] {{-1, 0}, {0, 1}},
            fPipe = new int[][] {{1, 0}, {0, 1}},
            sevenPipe = new int[][] {{1, 0}, {0, -1}};

    public static void main(String[] args) {

        ArrayList<String> input;
        try {
            input = MyFileReader.readFromFile("src/day10/input.txt");
        } catch (FileNotFoundException e) {
            return;
        }

        System.out.println("part one: " + partOne(input));
        System.out.println("part two: " + partTwo(input));

    }

    private static int partOne(ArrayList<String> input) {

        int[][] map = new int[input.size()][input.get(0).length()];

        // search for the starting point
        int[] startingPoint = new int[2];
        for (int i = 0; i < input.size(); i++ ) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if (input.get(i).charAt(j) == 'S') {
                    startingPoint = new int[] {i, j};
                    break;
                }
            }
        }
        map[startingPoint[0]][startingPoint[1]] = 0;

        // get the starting direction
        // surround in a for loop so i can use a break statement
        for (int i = 0; i < 1; i++) {
            int x = startingPoint[0];
            int y = startingPoint[1] - 1;
            if (y > 0) {
                char nextPipe = input.get(x).charAt(y);
                if (nextPipe == '-' || nextPipe == 'F' || nextPipe == 'L') {
                    startingPoint = new int[]{x, y};
                    break;
                }
            }
            y = startingPoint[1] + 1;
            if (y < input.get(0).length()) {
                char nextPipe = input.get(x).charAt(y);
                if (nextPipe == '-' || nextPipe == 'J' || nextPipe == '7') {
                    startingPoint = new int[]{x, y};
                    break;
                }
            }
            x = startingPoint[0] - 1;
            y = startingPoint[1];
            if (x > 0) {
                char nextPipe = input.get(x).charAt(y);
                if (nextPipe == '|' || nextPipe == 'F' || nextPipe == '7') {
                    startingPoint = new int[]{x, y};
                    break;
                }
            }
            x = startingPoint[0] + 1;
            if (x < input.size()) {
                char nextPipe = input.get(x).charAt(y);
                if (nextPipe == '|' || nextPipe == 'J' || nextPipe == 'L') {
                    startingPoint = new int[]{x, y};
                    break;
                }
            }
        }

        int i = 1;
        while (input.get(startingPoint[0]).charAt(startingPoint[1]) != 'S') {
            map[startingPoint[0]][startingPoint[1]] = i;
            char currentPipe = input.get(startingPoint[0]).charAt(startingPoint[1]);
            int[][] connections = switch (currentPipe) {
                case '-' -> dashPipe;
                case '|' -> pipePipe;
                case 'L' -> lPipe;
                case 'J' -> jPipe;
                case '7' -> sevenPipe;
                case 'F' -> fPipe;
                default -> throw new RuntimeException("bad pipe");
            };
            for (int j = 0; j < 2; j++) {
                int x = startingPoint[0] + connections[j][0];
                int y = startingPoint[1] + connections[j][1];
                if (map[x][y] == 0) {
                    startingPoint = new int[] {x, y};
                    break;
                }
            }
            i++;
        }

        return i/2;
    }

    private static int partTwo(ArrayList<String> input) {
        char[][] map = new char[input.size()][input.get(0).length()];
        for (char[] chars : map) {
            Arrays.fill(chars, '.');
        }

        // search for the starting point
        int[] startingPoint = new int[2];
        for (int i = 0; i < input.size(); i++ ) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if (input.get(i).charAt(j) == 'S') {
                    startingPoint = new int[] {i, j};
                    break;
                }
            }
        }

        // get the starting direction
        // surround in a for loop so i can use a break statement
        for (int i = 0; i < 1; i++) {
            int x = startingPoint[0];
            int y = startingPoint[1] - 1;
            if (y > 0) {
                char nextPipe = input.get(x).charAt(y);
                if (nextPipe == '-' || nextPipe == 'F' || nextPipe == 'L') {
                    map[startingPoint[0]][startingPoint[1]] = '<';
                    startingPoint = new int[]{x, y};
                    break;
                }
            }
            y = startingPoint[1] + 1;
            if (y < input.get(0).length()) {
                char nextPipe = input.get(x).charAt(y);
                if (nextPipe == '-' || nextPipe == 'J' || nextPipe == '7') {
                    map[startingPoint[0]][startingPoint[1]] = '>';
                    startingPoint = new int[]{x, y};
                    break;
                }
            }
            x = startingPoint[0] - 1;
            y = startingPoint[1];
            if (x > 0) {
                char nextPipe = input.get(x).charAt(y);
                if (nextPipe == '|' || nextPipe == 'F' || nextPipe == '7') {
                    map[startingPoint[0]][startingPoint[1]] = '^';
                    startingPoint = new int[]{x, y};
                    break;
                }
            }
            x = startingPoint[0] + 1;
            if (x < input.size()) {
                char nextPipe = input.get(x).charAt(y);
                if (nextPipe == '|' || nextPipe == 'J' || nextPipe == 'L') {
                    map[startingPoint[0]][startingPoint[1]] = 'v';
                    startingPoint = new int[]{x, y};
                    break;
                }
            }
        }

        int i = 0;
        while (input.get(startingPoint[0]).charAt(startingPoint[1]) != 'S') {
            char currentPipe = input.get(startingPoint[0]).charAt(startingPoint[1]);
            int[][] connections = switch (currentPipe) {
                case '-' -> dashPipe;
                case '|' -> pipePipe;
                case 'L' -> lPipe;
                case 'J' -> jPipe;
                case '7' -> sevenPipe;
                case 'F' -> fPipe;
                default -> throw new RuntimeException("bad pipe");
            };
            int[] newStartingPont = new int[2];
            for (int j = 0; j < 2; j++) {
                int x = startingPoint[0] + connections[j][0];
                int y = startingPoint[1] + connections[j][1];
                if (map[x][y] == '.' || (input.get(x).charAt(y) == 'S' && i > 1)) {
                    newStartingPont = new int[] {x, y};
                    if (connections[j][0] == 1 && connections[j][1] == 0) {
                        map[startingPoint[0]][startingPoint[1]] = 'v';
                    } else if (connections[j][0] == -1 && connections[j][1] == 0) {
                        map[startingPoint[0]][startingPoint[1]] = '^';
                    } else if (connections[j][0] == 0 && connections[j][1] == 1) {
                        map[startingPoint[0]][startingPoint[1]] = '>';
                    } else if (connections[j][0] == 0 && connections[j][1] == -1) {
                        map[startingPoint[0]][startingPoint[1]] = '<';
                    }
                    break;
                }
            }

            i++;
            startingPoint = newStartingPont;
        }

        // calculate how many tiles are enclosed
        do {
            // some of these numbers may have to be reversed depending on the input
            // i know its cringe but im too lazy to fix
            int x, y, nx, ny;
            switch (map[startingPoint[0]][startingPoint[1]]) {
                case '>':
                    x = -1; y = 0; nx = 0; ny = 1;
                    break;
                case '<':
                    x = 1; y = 0; nx = 0; ny = -1;
                    break;
                case 'v':
                    x = 0; y = 1; nx = 1; ny = 0;
                    break;
                case '^':
                    x = 0; y = -1; nx = -1; ny = 0;
                    break;
                default:
                    throw new RuntimeException("bad pipe in map");
            }
            floodFill(map, new int[] {startingPoint[0] + x, startingPoint[1] + y});
            floodFill(map, new int[] {startingPoint[0] + x + nx, startingPoint[1] + y + ny});
            startingPoint[0] += nx;
            startingPoint[1] += ny;
        } while (input.get(startingPoint[0]).charAt(startingPoint[1]) != 'S');

        int count = 0;
        for (char[] line : map) {
            for (char tile : line) {
//                System.out.print(tile);
                if (tile == 'O') count++;
            }
//            System.out.print("\n");
        }

        return count;
    }

    private static int[][] fillRange = new int[][] {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    private static void floodFill(char[][] map, int[] pos) {
        if (map[pos[0]][pos[1]] == '.') {
            map[pos[0]][pos[1]] = 'O';
            for (int i = 0; i < fillRange.length; i++) {
                int[] tile = new int[] {pos[0] + fillRange[i][0], pos[1] + fillRange[i][1]};
                if (tile[0] > 0 && tile[1] > 0 && tile[0] < map.length && tile[1] < map[0].length && map[tile[0]][tile[1]] == '.') {
                    floodFill(map, tile);
                }
            }
        }

    }

}