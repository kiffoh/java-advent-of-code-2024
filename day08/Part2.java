
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Part2 {
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
        // Initialize antinodeArray with '.' characters
        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {
                antinodeArray[row][col] = '.';
            }
        }
        
        Set<Coordinate> antinodeCoordinates = new HashSet<>();
        
        // For each frequency
        for (char key : frequencyCoordinates.keySet()) {
            List<Coordinate> coordinates = frequencyCoordinates.get(key);
            
            // If there are at least 2 antennas of this frequency, they are all antinodes
            if (coordinates.size() >= 2) {
                for (Coordinate coordinate : coordinates) {
                    antinodeCoordinates.add(coordinate);
                }
            }
            
            // Check all pairs of antennas with the same frequency
            for (int i = 0; i < coordinates.size(); i++) {
                Coordinate a1 = coordinates.get(i);
                
                for (int j = i + 1; j < coordinates.size(); j++) {
                    Coordinate a2 = coordinates.get(j);
                    
                    // Find all points collinear with these two antennas
                    findCollinearPoints(a1, a2, antinodeCoordinates);
                }
            }
        }
        
        // Mark all antinodes in the array
        for (Coordinate antinode : antinodeCoordinates) {
            antinodeArray[antinode.row][antinode.col] = '#';
        }
    }

    private static void findCollinearPoints(Coordinate a1, Coordinate a2, Set<Coordinate> antinodeCoordinates) {
        // Calculate direction vector from a1 to a2
        int rowDir = a2.row - a1.row;
        int colDir = a2.col - a1.col;
        
        // Simplify the direction vector to its simplest form
        int gcd = gcd(Math.abs(rowDir), Math.abs(colDir));
        if (gcd > 0) {
            rowDir /= gcd;
            colDir /= gcd;
        }
        
        // Check points beyond a2 (continuing in the same direction)
        int row = a2.row + rowDir;
        int col = a2.col + colDir;
        
        while (row >= 0 && row < maxRows && col >= 0 && col < maxCols) {
            antinodeCoordinates.add(new Coordinate(row, col));
            row += rowDir;
            col += colDir;
        }
        
        // Check points beyond a1 (going in the opposite direction)
        row = a1.row - rowDir;
        col = a1.col - colDir;
        
        while (row >= 0 && row < maxRows && col >= 0 && col < maxCols) {
            antinodeCoordinates.add(new Coordinate(row, col));
            row -= rowDir;
            col -= colDir;
        }
    }

    private static void checkDirection(Coordinate start, int rowDir, int colDir, Set<Coordinate> antinodeCoordinates) {
        int row = start.row + rowDir;
        int col = start.col + colDir;
        
        while (row >= 0 && row < maxRows && col >= 0 && col < maxCols) {
            antinodeCoordinates.add(new Coordinate(row, col));
            row += rowDir;
            col += colDir;
        }
    }

    // Calculate GCD using Euclidean algorithm
    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
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

        
        // for (char[] line: antinodeArray) {
        //     System.out.println(Arrays.toString(line));
        // }
    }
}

    
class Coordinate {
    int row, col;

    Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Coordinate frequencyCoordinateTowards(Coordinate other) {
    //     int rowDiff = this.row - other.row;
    //     int colDiff = this.col - other.col;
    //     return new Coordinate()
    // }

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

// 1307 is too low
// 1333 is correct
