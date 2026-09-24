import java.awt.Point;
import java.util.*;

class Part2 {
    private static final String INPUT_PATH = "input.txt";

    private static String readInput() {
        try {
            return java.nio.file.Files.readString(java.nio.file.Path.of(INPUT_PATH));
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException("Could not read " + INPUT_PATH + ". Add your puzzle input there.", e);
        }
    }

    static String input = readInput();


    public static String[] splitInput;

    public static int maxX = 71, maxY = 71;
    public static int bytesToSimulate = 12;

    public static List<int[]> DIRECTIONS = List.of(
        new int[] {0, -1}, // North
        new int[] {1, 0}, // East
        new int[] {0, 1}, // South
        new int[] {-1, 0} // West
    );


    public static class Path extends Point {
        int steps;

        public Path(int x, int y, int steps) {
            super(x, y);
            this.steps = steps;
        }
    }

    public static TreeSet<String> getCorruptedPositions(int length) {
        TreeSet<String> corruptedPositions = new TreeSet<>();
        for (int i = 0; i < length; i++) {
            String[] coord = splitInput[i].split(",");
            int x = Integer.parseInt(coord[0]);
            int y = Integer.parseInt(coord[1]);
            corruptedPositions.add(String.format("(%d,%d)", x, y));
        }
        return corruptedPositions;
    }

    public static boolean isValidPosition(int x, int y, TreeSet<String> corruptedPositions, String positionKey, Set<String> visited) {
        boolean withinXRange = (x >= 0 && x < maxX);
        boolean withinYRange = (y >= 0 && y < maxY);
        boolean corruptedPosition = corruptedPositions.contains(positionKey);
        boolean positionVisited = visited.contains(positionKey);
        return withinXRange && withinYRange && !corruptedPosition && !positionVisited;
    }

    public static int calculateShortestDistance(TreeSet<String> corruptedPositions) {

        PriorityQueue<Path> queue = new PriorityQueue<>(Comparator.comparingInt(path -> path.steps));
        Set<String> visited = new HashSet<>();

        queue.add(new Path(0, 0, 0));
        visited.add("(0,0)");

        while (!queue.isEmpty()) {
            Path current = queue.poll();

            if ((current.x == maxX - 1) && (current.y == maxY - 1)) return current.steps;

            for (int[] direction : DIRECTIONS) {
                int dx = direction[0];
                int dy = direction[1];

                int newX = current.x + dx;
                int newY = current.y + dy;
                String positionKey = String.format("(%d,%d)", newX, newY);

                // New position valid -> Add to queue
                if (isValidPosition(newX, newY, corruptedPositions, positionKey, visited)) {
                    visited.add(positionKey);
                    queue.add(new Path(newX, newY, current.steps + 1));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        splitInput = input.trim().split("\n");
        TreeSet<String> corruptedPositions = getCorruptedPositions(splitInput.length);
        String byteWhichPreventsExit = "Undefined";
        int i = corruptedPositions.size() - 1;

        for (; i >= 0; i--) {
            String[] coord = splitInput[i].split(",");
            String x = coord[0];
            String y = coord[1];
            String positionKey = "("+x+","+y+")";
            corruptedPositions.remove(positionKey);
            if (calculateShortestDistance(corruptedPositions) != -1) {
                byteWhichPreventsExit = splitInput[i];
                break;
            }
        }

        TreeSet<String> validatedCorruptedPositions = getCorruptedPositions(i);

        // Validation
        System.out.println("Shortest Distance: " + calculateShortestDistance(validatedCorruptedPositions));

        System.out.println("integer: " + i);
        System.out.println("byteWhichPreventsExit: " + byteWhichPreventsExit);
    }
}

// 33,19
// 26,31
// 10,5
// 58, 52
