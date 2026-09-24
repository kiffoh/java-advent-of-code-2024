
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part1 {
    private static final String INPUT_PATH = "input.txt";

    private static String readInput() {
        try {
            return java.nio.file.Files.readString(java.nio.file.Path.of(INPUT_PATH));
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException("Could not read " + INPUT_PATH + ". Add your puzzle input there.", e);
        }
    }

    static String input = readInput();


    static List<Point> directions = List.of(new Point(0, -1), new Point(0, 1), new Point(-1, 0), new Point(1, 0));

    static int maxX;
    static int maxY;

    private static char[][] convertToCharArray(String input) {
        String[] inputArr = input.trim().split("\n");

        char[][] inputCharArray = new char[inputArr.length][];

        for (int i = 0; i < inputArr.length; i++) {
            inputCharArray[i] = inputArr[i].toCharArray();
        }

        return inputCharArray;
    }

    private static void dfs(int x, int y, Set<Point> overallPath, char[][] gardenPlot, Plant plant) {
        for (Point direction: directions) {
            int newX = x + direction.x;
            int newY = y + direction.y;

            if (Math.min(newX, newY) < 0 || newX >= maxX || newY >= maxY) {
                plant.perimeter += 1;
                continue;
            }

            Point newCoordinates = new Point(newX, newY);

            if (gardenPlot[newX][newY] == plant.type && !plant.path.contains(newCoordinates)) {
                // No perimeter between plants of same type
                // plant.perimeter += 0;

                // Add new coordinates to overall and plant paths
                overallPath.add(newCoordinates);
                plant.path.add(newCoordinates);

                // Search for other nearby plants of same type
                dfs(newX, newY, overallPath, gardenPlot, plant);
            } else if (gardenPlot[newX][newY] != plant.type) {
                // Nearby plant is not of same type, therefore a perimeter is available
                plant.perimeter += 1;
            }
        }
    }

    public static void main(String[] args) {
        char[][] inputCharArray = convertToCharArray(input);

        maxX = inputCharArray.length;
        maxY = inputCharArray[0].length;

        Set<Point> overallPath = new HashSet<>();

        List<Plant> allPlants = new ArrayList<>();

        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                Point currentCoordinate = new Point(x, y);
                if (!overallPath.contains(currentCoordinate)) {
                    // Set<Point> plantPath = new HashSet<>();
                    // Initialise a new plant region
                    char plantType = inputCharArray[x][y];
                    Plant region = new Plant(plantType);
                    region.path.add(new Point(x, y)); // Add starting coordinate to plant path

                    dfs(x, y, overallPath, inputCharArray, region);

                    region.area = region.path.size();
                    allPlants.add(region);
                }
            }
        }

        // System.err.println("allPlants: " + allPlants.toString());

        int totalPrice = 0;

        for (Plant plant: allPlants) {
            // For debugging
            // System.err.println("\nplant: " + plant.toString());
            
            totalPrice += plant.area * plant.perimeter;
        }

        System.err.println("totalPrice: " + totalPrice);
    }
}

class Plant {
    char type;
    int area;
    int perimeter;
    Set<Point> path;

    public Plant(char type) {
        this.type = type;
        this.path = new HashSet<>();
    }

    @Override
    public String toString() {
        return "{type="+type + ", area="+area + ", perimeter="+perimeter +"}";
    }
}

