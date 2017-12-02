package cz.schutzpetr.dxl;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("test-data.txt"));

        int[][] tosolve = new int[809][82];

        int count = 0;

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            line = line.replace("{", "");
            line = line.replace("}", "");
            line = line.replace("[", "");
            line = line.replace("]", "");

            String[] lines = line.split(",");
            int[] parsed = new int[lines.length];

            for (int i = 0; i < lines.length; i++) {
                try{
                    parsed[i] = Integer.parseInt(lines[i].trim());
                } catch (NumberFormatException e){
                    e.printStackTrace();
                }
            }
            tosolve[count] = parsed;
            count++;
        }

        int[][] example = {
                {0, 0, 1, 0, 1, 1, 0},
                {1, 0, 0, 1, 0, 0, 1},
                {0, 1, 1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1, 0, 1}
        };

        int[][] example1 = {
                {1, 0, 0, 0},
                {0, 1, 1, 0},
                {1, 0, 0, 1},
                {0, 0, 1, 1},
                {0, 1, 0, 0},
                {0, 0, 1, 0}
        };

        DancingLinks dancingLinks = new DancingLinks(tosolve);
        dancingLinks.run();
    }
}
