
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Part1 {
    private static final String INPUT_PATH = "input.txt";

    private static String readInput() {
        try {
            return java.nio.file.Files.readString(java.nio.file.Path.of(INPUT_PATH));
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException("Could not read " + INPUT_PATH + ". Add your puzzle input there.", e);
        }
    }

    static String inputString = readInput();


    static char[][] inputArray;
    static char[][] antinodeArray;

    static int maxRows;
    static int maxCols;

    static Map<Character, List<Coordinate>> frequencyCoordinates = new HashMap<>();

    private static void convertInputStringToCharArray() {
        String[] splitLines = inputString.strip().split("\n");
        inputArray = new char[splitLines.length][];

        for (int i = 0; i < splitLines.length; i++) {
            inputArray[i] = splitLines[i].toCharArray();
        }
    }

    public static void readInputArray() {
        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {
                if (inputArray[row][col] != '.') {
                    char key = inputArray[row][col];

                    frequencyCoordinates
                            .computeIfAbsent(key, k -> new ArrayList<>())
                            .add(new Coordinate(row, col));
                }
            }
        }
    }

    public static void populateAntinodeArray() {
        Set<Coordinate> antinodeCoordinates = new HashSet<>();
        
        for (char key: frequencyCoordinates.keySet()) {
            List<Coordinate> coordinates = frequencyCoordinates.get(key);

            for (int i = 0; i < coordinates.size(); i++) {
                Coordinate a1 = coordinates.get(i);

                for (int j = i + 1; j < coordinates.size(); j++) {
                    Coordinate a2 = coordinates.get(j);
                    
                    Coordinate antinode1 = a1.antinodeTowards(a2);
                    if (antinode1.row < maxRows && antinode1.col < maxCols && Math.min(antinode1.row, antinode1.col) >= 0) antinodeCoordinates.add(antinode1);
                    
                    Coordinate antinode2 = a2.antinodeTowards(a1);
                    if (antinode2.row < maxRows && antinode2.col < maxCols && Math.min(antinode2.row, antinode2.col) >= 0) antinodeCoordinates.add(antinode2);
                }
            }
        }

        for (Coordinate antenna: antinodeCoordinates) {
            antinodeArray[antenna.row][antenna.col] = '#';
        }
    }

    public static void main(String[] args) {
        convertInputStringToCharArray();

        int totalAntinodes = 0;

        maxRows = inputArray.length;
        maxCols = inputArray[0].length;

        antinodeArray = new char[maxRows][maxCols];

        readInputArray();

        System.out.println("frequencyCoordinates: " + frequencyCoordinates);

        populateAntinodeArray();

        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {
                if (antinodeArray[row][col] == '#') totalAntinodes++;
            }
        }

        System.out.println("Total antinodes: " + totalAntinodes);
    }
}

    
class Coordinate {
    int row, col;

    Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    Coordinate antinodeTowards(Coordinate other) {
        int rowDiff = this.row - other.row;
        int colDiff = this.col - other.col;
        return new Coordinate(this.row + rowDiff, this.col + colDiff);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate)) return false;
        Coordinate c = (Coordinate) o;
        return this.row == c.row && this.col == c.col;
    }

    @Override
    public int hashCode() {
        return 31 * row + col;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
