package cz.schutzpetr.dxl;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("test-data.txt"));

        List<String[]> tosolve = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.replace("{", "");
            line = line.replace("}", "");
            line = line.replace("[", "");
            line = line.replace("]", "");

            String[] lines = line.split(",");
            tosolve.add(lines);
        }

        int[][] matrix = new int[tosolve.size()][];

        for (int i = 0; i < tosolve.size(); i++) {
            String[] row = tosolve.get(i);
            int[] parsed = new int[row.length];

            for (int j = 0; j < row.length; j++) {
                parsed[j] = Integer.parseInt(row[j].trim());
            }

            matrix[i] = parsed;
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

        DancingLinks dancingLinks = new DancingLinks(matrix);
        dancingLinks.run();
    }
}
