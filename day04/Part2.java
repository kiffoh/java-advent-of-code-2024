
import java.io.Console;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

class Part2 {
    private static final String INPUT_PATH = "input.txt";

    private static String readInput() {
        try {
            return java.nio.file.Files.readString(java.nio.file.Path.of(INPUT_PATH));
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException("Could not read " + INPUT_PATH + ". Add your puzzle input there.", e);
        }
    }

    static char[][] inputCharArray;

    static int maxRows;
    static int maxColumns;

    static Map<String, int[]> directions = Map.of(
        "tl", new int[]{-1, -1},
        "tr", new int[]{-1, 1},
        "bl", new int[]{1, -1},
        "br", new int[]{1, 1}
    );

    static int totalOccuranceOfXmas = 0;

    public static void checkForValidXMAS(int row, int column) {
        int[][] validCoordinates = new int[4][2];
        int index = 0;

        for (int[] value : directions.values()) {
            int newRow = row + value[0];
            int newColumn = column + value[1];

            if (Math.min(newRow, newColumn) < 0  || newRow >= maxRows || newColumn >= maxColumns ) {
                return; // Coordinates are invalid
            }

            if (inputCharArray[newRow][newColumn] != 'M' && inputCharArray[newRow][newColumn] != 'S') {
                return; // Coordinates are invalid
            }

            validCoordinates[index++] = new int[] {newRow, newColumn};
        };

        


        // Assign values of inputCharArray to correct positions
        char tl = inputCharArray[validCoordinates[0][0]][validCoordinates[0][1]];
        char tr = inputCharArray[validCoordinates[1][0]][validCoordinates[1][1]];
        char bl = inputCharArray[validCoordinates[2][0]][validCoordinates[2][1]];
        char br = inputCharArray[validCoordinates[3][0]][validCoordinates[3][1]];

        // Use HashSet to allow duplicate values
        Map<Character, Integer> mapOfCharacters = new HashMap<>();
        mapOfCharacters.put('M', 0);
        mapOfCharacters.put('S', 0);

        mapOfCharacters.put(tl, mapOfCharacters.getOrDefault(tl, 0) + 1);
        mapOfCharacters.put(tr, mapOfCharacters.getOrDefault(tr, 0) + 1);
        mapOfCharacters.put(bl, mapOfCharacters.getOrDefault(bl, 0) + 1);
        mapOfCharacters.put(br, mapOfCharacters.getOrDefault(br, 0) + 1);


        if (mapOfCharacters.get('M') == 2 && mapOfCharacters.get('S') == 2 && ((tl == tr && bl == br) || (tr == br && bl == tl))) {
            if (mapOfCharacters.get('S') == null || mapOfCharacters.get('M') == null) {
                System.out.println("Current array:");
                System.out.println(inputCharArray[row-1][column-1] + "" + inputCharArray[row-1][column] + "" + inputCharArray[row-1][column+1]);
                System.out.println(inputCharArray[row][column-1] + "" + inputCharArray[row][column] + "" + inputCharArray[row][column+1]);
                System.out.println(inputCharArray[row+1][column-1] + "" + inputCharArray[row+1][column] + "" + inputCharArray[row+1][column+1]);
                System.out.println("S: " + mapOfCharacters.get('S'));
                System.out.println("M: " + mapOfCharacters.get('M'));
            }
            totalOccuranceOfXmas += 1;
        }

    }

    // Method to initialse array line-by-line otherwise recieve a too much data error
    private static void initialiseArray() {

        // Create each row separately
        inputCharArray = readInput().lines().map(String::toCharArray).toArray(char[][]::new);
maxRows = inputCharArray.length;
        maxColumns = inputCharArray[0].length;
    }








    public static void main(String[] args) {

        initialiseArray();

        for (int row = 0; row < inputCharArray.length; row++) {
            for (int col = 0; col < inputCharArray[0].length; col++) {
                if (inputCharArray[row][col] == 'A') {
                    checkForValidXMAS(row, col);
                }
            }
        }

        // Print the total occurrences of XMAS
        System.err.println(totalOccuranceOfXmas);
    }
}
