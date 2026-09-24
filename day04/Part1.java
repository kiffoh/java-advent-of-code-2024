
import java.util.Map;

class Part1 {
    private static final String INPUT_PATH = "input.txt";

    private static String readInput() {
        try {
            return java.nio.file.Files.readString(java.nio.file.Path.of(INPUT_PATH));
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException("Could not read " + INPUT_PATH + ". Add your puzzle input there.", e);
        }
    }

    static char[] xmas = {'X','M','A','S'};

    static char[][] inputCharArray;

    static int maxRows;
    static int maxColumns;

    static Map<String, int[]> directions = Map.of(
        "tl", new int[]{-1, -1},
        "t", new int[]{-1, 0},
        "tr", new int[]{-1, 1},
        "ml", new int[]{0, -1},
        "m", new int[]{0, 0},
        "mr", new int[]{0, 1},
        "bl", new int[]{1, -1},
        "b", new int[]{1, 0},
        "br", new int[]{1, 1}
    );

    static int totalOccuranceOfXmas = 0;

    // Don't need to enter a set bc the values of XMAS are all unique
    public static void checkForValidXMAS(int row, int column, String direction, int lengthOfXmas) {
        if (lengthOfXmas == 4) {
            totalOccuranceOfXmas += 1;
            return;
        }

        if (direction != null) {
            int newRow = row + directions.get(direction)[0];
            int newColumn = column + directions.get(direction)[1];

            if (Math.min(newRow, newColumn) < 0  || newRow >= maxRows || newColumn >= maxColumns ) return;

            // Check if the new position in the charArray matches the letter in xmas
            if (inputCharArray[newRow][newColumn] == xmas[lengthOfXmas]) {
                checkForValidXMAS(newRow, newColumn, direction, lengthOfXmas + 1);
                return;
            }
        }

        if (direction == null) {
            directions.forEach((key, value) -> {
                int newRow = row + value[0];
                int newColumn = column + value[1];

                if (Math.min(newRow, newColumn) < 0 || newRow >= maxRows || newColumn >= maxColumns ) return;

                // Check if the new position in the charArray matches the letter in xmas
                if (inputCharArray[newRow][newColumn] == xmas[lengthOfXmas]) {
                    checkForValidXMAS(newRow, newColumn, key, lengthOfXmas + 1);
                    return;
                }
            });
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
                if (inputCharArray[row][col] == 'X') {
                    checkForValidXMAS(row, col, null, 1);
                }
            }
        }

        // Print the total occurrences of XMAS
        System.err.println(totalOccuranceOfXmas);
    }
}
