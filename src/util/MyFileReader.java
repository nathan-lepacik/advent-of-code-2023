package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyFileReader {

    public static ArrayList<String> readFromFile(String file) throws FileNotFoundException{
        Scanner in = new Scanner(new File(file));
        ArrayList<String> out = new ArrayList<>();
        while (in.hasNextLine()) {
            out.add(in.nextLine());
        }
        return out;
    }

}
