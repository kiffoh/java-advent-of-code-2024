import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class SixFive {
    static Map<Integer, int[]> directions = Map.of(
        0, new int[]{-1, 0}, // Up
        1, new int[]{0, 1}, // Right
        2, new int[]{1, 0}, // Down
        3, new int[]{0, -1} // Left
    );

    static char[][] input;

    // static final String inputString = """
    //     ....#.....
    //     .........#
    //     ..........
    //     ..#.......
    //     .......#..
    //     ..........
    //     .#..^.....
    //     ........#.
    //     #.........
    //     ......#...
    //     """;

private static void convertInputStringToCharArray() {
        String[] splitLines = inputString.split("\n");
        input = new char[splitLines.length][];

        for (int i = 0; i < splitLines.length; i++) {
            input[i] = splitLines[i].toCharArray();
        }
    }

    static int maxRows;
    static int maxCols;

    public static void simulateGuardPath() {
        // I want to search the previous path and see if there is a coordinate in the new direction in visited
        // Afterwards I can check if there is an obstacle in the way if there is a coordinate in that section

        maxRows = input.length;
        maxCols = input[0].length;
        
        // Find the starting position
        int startRow = -1;
        int startCol = -1;
        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {
                if (input[row][col] == '^') {
                    startRow = row;
                    startCol = col;
                    break;
                }
            }
            if (startRow != -1) break;
        }
        
        if (startRow == -1 || startCol == -1) {
            System.out.println("No guard found on the map");
            return;
        }
        
        System.out.println("Guard starting at: Row " + startRow + ", Col " + startCol);
        
        // Track visited positions
        Set<Triple> visited = new HashSet<>();
        Set<Point> loopAvailable = new HashSet<>();
        
        // Starting position and direction
        int row = startRow;
        int col = startCol;
        int direction = 0; // Start facing up
        
        // Add starting position
        visited.add(new Triple(row, col, direction));
        
        // Main simulation loop
        while (true) {
            // Calculate next position
            int[] dirs = directions.get(direction);
            int newRow = row + dirs[0];
            int newCol = col + dirs[1];

            // See if there is a visited square to the RHS of the guard (e.g. one rotation)
            // IN THE DIRECTION!
            int tempRow = row;
            int tempCol = col;
            int tempDirection = (direction + 1) % 4;
            int[] tempDirs = directions.get(tempDirection);

            
            // Check if we're leaving the map
            if (newRow < 0 || newRow >= maxRows || newCol < 0 || newCol >= maxCols) {
                // System.out.println("Guard left the map at: Row " + newRow + ", Col " + newCol);
                break;
            }

            // System.out.println("Starting: Row " + row + ", Col " + col + ", direction " + direction);
            // Iterate in the new coordinates to see if there is a potential loop
            while (true) {
                tempRow += tempDirs[0];
                tempCol += tempDirs[1];

                // Check if we're leaving the map
                if (Math.min(tempRow, tempCol) < 1 || tempCol > maxCols - 2 || tempRow > maxRows - 2) {
                    // System.out.println("Outside of coordinates for an obstacle at (" + tempRow + ", " + tempCol + ", " + tempDirection + ")");
                    break;
                }

                // If an iteration hits an obstacle before a visited coordinate
                if (input[tempRow][tempCol] == '#') {
                    // System.out.println("Hit obstacle at (" + tempRow + ", " + tempCol + ", " + tempDirection + ")");
                    break;
                }
                
                // First instance of a temp coordinate matching a visited coordinate
                Triple tempCoordinates = new Triple(tempRow, tempCol, tempDirection);
                if (visited.contains(tempCoordinates)) {
                    // Coordinates to check that there isn't already an obstacle in place
                    int futureRow = newRow;
                    int futureCol = newCol;

                    // Coordinates to place obstacle to create infinite loop
                    Point coordinatesToPlaceObstacle = new Point(futureRow, futureCol);
                    // System.out.println("Adding obstacle at (" + futureRow + ", " + futureCol + ", " + tempDirection + ")");   
                    // Not sure if loop available is needed
                    // coordinatesToPlaceObstacle not already in there and it is not an obtacle
                    if (input[futureRow][futureCol] != '#' && !loopAvailable.contains(coordinatesToPlaceObstacle)) {
                        loopAvailable.add(coordinatesToPlaceObstacle);
                    }

                    // Add previous coordinates to visited
                    // Point currentCoordinates = new Point(row, col);
                    // visited.add(currentCoordinates);
                }
            }

            // Check if there's an obstacle
            if (input[newRow][newCol] == '#') {
                // Turn right
                direction = (direction + 1) % 4;
            } else {
                // Move forward
                row = newRow;
                col = newCol;
                visited.add(new Triple(row, col, direction));
            }
        }
        
        // System.out.println("Infinite loop coordinates: ");
        // for (Point coord: loopAvailable) {
        //     System.out.println(coord);

        // }
        System.out.println("Total infinite loop positions available: " + loopAvailable.size());
    }

    public static void main(String[] args) {
        convertInputStringToCharArray();
        simulateGuardPath();
    }
}



class Triple {
    private final int x;
    private final int y;
    private final int z;

    public Triple(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triple triple = (Triple) o;
        return x == triple.x && y == triple.y && z == triple.z;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public int hashCode() {
        return 31 * x + y + z;
    }
}

class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
} 
