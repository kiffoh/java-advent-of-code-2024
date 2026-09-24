
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.security.auth.Subject;

class Part2 {
    private static final String INPUT_PATH = "input.txt";

    private static String readInput() {
        try {
            return java.nio.file.Files.readString(java.nio.file.Path.of(INPUT_PATH));
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException("Could not read " + INPUT_PATH + ". Add your puzzle input there.", e);
        }
    }

    static List<Point> coordinates = List.of(new Point(0, -1), new Point(0, 1), new Point(-1, 0), new Point(1, 0));

    static int maxX;
    static int maxY;
    
    public static int solve(String input) {
        int[][] parsedInput = parseInput(input);

        maxX = parsedInput.length;
        maxY = parsedInput[0].length;

        List<Integer> trailheadScores = new ArrayList<>();

        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                if (parsedInput[x][y] == 0) {
                    // System.out.println("0 hit at x: " + x + ", y: " + y);
                    List<Point> trailhead9Coordinates = new ArrayList<>(); 
                    dfs(0, x, y, trailhead9Coordinates, parsedInput);
                    trailheadScores.add(trailhead9Coordinates.size());
                }
            }
        }

        System.out.println(Arrays.toString(trailheadScores.toArray()));

        int total = trailheadScores.stream().reduce(0, (a,b) -> a + b);
        return total;
    }

    private static int[][] parseInput(String input) {
        String trimmedInput = input.trim();
        String[] splitInput = trimmedInput.split("\n");

        int[][] output = new int[splitInput.length][];
        for (int i = 0; i < splitInput.length; i++) {
            output[i] = splitInput[i].chars().map(c -> c - '0').toArray();
        }

        return output;
    }
    
    private static void dfs(int currentHeight, int x, int y, List<Point> trailhead9Coordinates, int[][] parsedInput) {
        if (currentHeight == 9) {
            trailhead9Coordinates.add(new Point(x, y));
            // return;
        }
        // System.out.println("Current height: " + currentHeight + ".   For x: " + x + ", y: " + y);

        for (Point delta: coordinates) {
            int newX = x + delta.x;
            int newY = y + delta.y;

            // System.out.println("New X: " + newX + ", newY: " + newY);

            if (Math.min(newX, newY) < 0 || newX >= maxX || newY >= maxY) {
                continue;
            }

            if (parsedInput[newX][newY] == currentHeight + 1) {
                dfs(currentHeight + 1, newX, newY, trailhead9Coordinates, parsedInput);
            }

        }
    }

    static String input = readInput();


    public static void main(String[] args) {
        int total = solve(input);

        System.out.println("total: " + total);
    }
}

// 1540 is too low
// 1610
