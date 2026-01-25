
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;

class Twenty {

    static String longInput = """
    ###############
    #...#...#.....#
    #.#.#.#.#.###.#
    #S#...#.#.#...#
    #######.#.#.###
    #######.#.#...#
    #######.#.###.#
    ###..E#...#...#
    ###.#######.###
    #...###...#...#
    #.#####.#.###.#
    #.#...#.#.#...#
    #.#.#.#.#.#.###
    #...#...#...###
    ###############
    """;

    static char[][] parsedInput;
    static int maxY;
    static int maxX;

    public static void parseInput() {
        String[] splitInput = longInput.trim().split("\n");

        parsedInput = new char[splitInput.length][];

        for (int i = 0; i < splitInput.length; i++) {
            parsedInput[i] = splitInput[i].toCharArray();
        }
        maxY = parsedInput.length;
        maxX = parsedInput[0].length;
    }

    public static List<Point> findStartAndEndPoints() {
        List<Point> coordinates = new ArrayList<>(2);
        for (int y = 1; y < maxY - 1; y++) {
            for (int x = 1; x < maxX - 1; x++) {
                if (parsedInput[y][x] == 'S') {
                    coordinates.add(0, new Point(x, y));
                }
                if ( parsedInput[y][x] == 'E') {
                    coordinates.add(1, new Point(x, y));
                }
                if (coordinates.size() == 2) return coordinates;
            }
        }
        throw new IllegalStateException("Starting position not given");
    }

    static List<Point> DIRECTIONS = List.of(
        new Point(0, 1),  // North
        new Point(-1, 0), // East
        new Point(1, 0),  // West
        new Point(0, -1)  // South
    );

    static class Path {
        // I don't think I need to store direction
        int x, y, steps;
        Map<Point, Integer> distanceFromStart;

        public Path(int x, int y, int steps, Map<Point, Integer> distanceFromStart) {
            this.x = x;
            this.y = y;
            this.steps = steps;
            this.distanceFromStart = distanceFromStart;
            
        }
        public Path(int x, int y) {
            this.x = x;
            this.y = y;
            this.steps = 0;
            this.distanceFromStart = new HashMap<>();
        }
    }

    public static Map<Point, Integer> quickestPath(Point start, Point end) {
        PriorityQueue<Path> queue = new PriorityQueue<>((p1, p2) -> p2.distanceFromStart.size() - p1.distanceFromStart.size());
        queue.add(new Path(start.x, start.y));

        while (!queue.isEmpty()) {
            Path current = queue.poll();
            if (current.x == end.x && current.y == end.y) {
                return current.distanceFromStart;
            }
            for (Point dir: DIRECTIONS) {
                int newX = current.x + dir.x;
                int newY = current.y + dir.y;
                if (parsedInput[newY][newX] == '#' || current.distanceFromStart.containsKey(new Point(newX, newY))) {
                    continue;
                }
                Map<Point, Integer> newVisited = new HashMap<>(current.distanceFromStart);
                newVisited.put(new Point(newX, newY), current.steps + 1);
                queue.add(new Path(newX, newY, current.steps + 1, newVisited));
            }
        }
        throw new IllegalStateException("Valid path not available");
    }

    static Map<Point, Integer> enumeratedPath;
    static Map<Integer, Integer> picosMap = new TreeMap<>();

    public static void validateShortcut(Point coord, Point dir) {
        int endX = coord.x + dir.x * 2;
        int endY = coord.y + dir.y * 2;

        if (endX < 0 || endX >= maxX || endY < 0 || endY >= maxY) return;
        Point shortcutPoint = new Point(endX, endY);

        // End point must be valid
        if (parsedInput[shortcutPoint.y][shortcutPoint.x] == '#') {
            return;
        }

        // End point must be on valid path
        if (!enumeratedPath.containsKey(shortcutPoint)) return;

        int shortcutDistance = enumeratedPath.get(shortcutPoint);
        int originalDistance = enumeratedPath.get(coord);
        int timeSaved = shortcutDistance - originalDistance - 2;
        if (timeSaved > 0) {
            // System.err.println("coord: " + coord + " to shortcut " + shortcutPoint + " saves " + sum + " picoseconds");
            int picoseconds = picosMap.getOrDefault(timeSaved, 0);
            picosMap.put(timeSaved, picoseconds + 1);
        }
    }

    public static void main(String[] args) {
        parseInput();
        List<Point> coords = findStartAndEndPoints();
        Point start = coords.get(0);
        Point end = coords.get(1);

        enumeratedPath = quickestPath(start, end);

        // System.err.println("enumeratedPath: " + enumeratedPath);
        System.err.println("Size: " + enumeratedPath.size());

        for (Entry entry: enumeratedPath.entrySet()) {
            Point coord = (Point) entry.getKey();
            for (Point dir: DIRECTIONS) {
                validateShortcut(coord, dir);
            }
        }

        for (Map.Entry<Integer, Integer> entry : picosMap.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            
            System.err.println("There are " + val + " cheats that save " + key + " picoseconds.");
        }
    }
}
