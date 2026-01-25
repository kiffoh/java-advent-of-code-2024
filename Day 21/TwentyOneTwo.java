
import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.math.BigInteger;

class TwentyOneTwo {
    // static String input = """
    //     029A
    //     980A
    //     179A
    //     456A
    //     379A
    // """;
    // static String input = """
    //     029A
    // """;

    static String input = """
        480A
        682A
        140A
        246A
        938A
    """;

    static final Map<Character, Point> numericCharToPoint = new HashMap<>();
    static {
        numericCharToPoint.put('7', new Point(0, 0));
        numericCharToPoint.put('8', new Point(1, 0));
        numericCharToPoint.put('9', new Point(2, 0));
        numericCharToPoint.put('4', new Point(0, 1));
        numericCharToPoint.put('5', new Point(1, 1));
        numericCharToPoint.put('6', new Point(2, 1));
        numericCharToPoint.put('1', new Point(0, 2));
        numericCharToPoint.put('2', new Point(1, 2));
        numericCharToPoint.put('3', new Point(2, 2));
        numericCharToPoint.put(null, new Point(0, 3));
        numericCharToPoint.put('0', new Point(1, 3));
        numericCharToPoint.put('A', new Point(2, 3));
    }
    static final Map<Point, Character> numericPointToChar = new HashMap<>();
    static {
        numericPointToChar.put(new Point(0, 0), '7');
        numericPointToChar.put(new Point(1, 0), '8');
        numericPointToChar.put(new Point(2, 0), '9');
        numericPointToChar.put(new Point(0, 1), '4');
        numericPointToChar.put(new Point(1, 1), '5');
        numericPointToChar.put(new Point(2, 1), '6');
        numericPointToChar.put(new Point(0, 2), '1');
        numericPointToChar.put(new Point(1, 2), '2');
        numericPointToChar.put(new Point(2, 2), '3');
        numericPointToChar.put(new Point(0, 3), null);
        numericPointToChar.put(new Point(1, 3), '0');
        numericPointToChar.put(new Point(2, 3), 'A');
    }
    static final Map<Point, Character> directionalPointToChar = new HashMap<>();
    static {
        directionalPointToChar.put(new Point(0, 0), null);
        directionalPointToChar.put(new Point(1, 0), '^');
        directionalPointToChar.put(new Point(2, 0), 'A');
        directionalPointToChar.put(new Point(0, 1), '<');
        directionalPointToChar.put(new Point(1, 1), 'v');
        directionalPointToChar.put(new Point(2, 1), '>');
    }

    static final Map<Character, Point> directionalCharToPoint = new HashMap<>();
    static {
        directionalCharToPoint.put(null, new Point(0, 0));
        directionalCharToPoint.put('^', new Point(1, 0));
        directionalCharToPoint.put('A', new Point(2, 0));
        directionalCharToPoint.put('<', new Point(0, 1));
        directionalCharToPoint.put('v', new Point(1, 1));
        directionalCharToPoint.put('>', new Point(2, 1));
    }

    static final class Difference {
        int x, y;
        public Difference(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "dx: " + this.x + ", dy: " + this.y;
        }
    }

    public static Difference calculateDifference(Point starting, Point ending) {
        // System.out.println("Starting: " + starting + ", ending: " + ending);
        return new Difference(ending.x - starting.x, ending.y - starting.y);
    }

    public static Point getCharacterPoint(char character, Map<Character, Point> map) {
        return map.get(character);
    };

    static class Path extends Point{
        List<Integer> dx;
        List<Integer> dy;
        String str;

        Path(Path prev) {
            super(prev.x, prev.y);
            this.dx = new ArrayList<>(prev.dx);
            this.dy = new ArrayList<>(prev.dy);
            this.str = prev.str;
        }

        Path(int x, int y, Integer dx, Integer dy) {
            super(x, y);
            this.dx = createArray(dx);
            this.dy = createArray(dy);
            this.str = "";
        }

        public List<Integer> createArray(int num) {
            int absNum = Math.abs(num);
            List<Integer> arr = new ArrayList<>();
            for (int i = 0; i < absNum; i++) {
                if (num > 0) {
                    arr.add(1);
                } else {
                    arr.add(-1);
                }
            }
            return arr;
        }

        public Integer popDx() {
            Integer newDx = this.dx.remove(this.dx.size() - 1);
            if (newDx > 0) {
                this.str += ">";
            } else {
                this.str += "<";
            }
            return newDx;
        }

        public Integer popDy() {
            Integer newDy = this.dy.remove(this.dy.size() - 1);
            if (newDy > 0) {
                this.str += "v";
            } else {
                this.str += "^";
            }
            return newDy;
        }


        
        public String fullToString() {
            return "(x,y): " + this.x + "," + this.y + ". dx: " + this.dx.toString() + ", dy: " + this.dy.toString() + ". str: " + this.str;
        }
    }

    public static List<String> validPaths(Point starting, Point ending, Difference diff, Map<Point, Character> map) {
        Queue<Path> queue = new ArrayDeque<>();
        Path startingPath = new Path(starting.x, starting.y, diff.x, diff.y);
        queue.add(startingPath);

        // System.err.println("starting: " + starting);
        // System.err.println("ending: " + ending);
        // System.err.println("diff: " + diff.toString());

        List<String> availablePaths = new ArrayList<>();

        while (!queue.isEmpty()) {

            // System.err.println("queue: " + queue);
            Path curr = queue.poll();
            if (curr.equals(ending)) {
                // System.err.println("curr: " + curr.fullToString());
                availablePaths.add(curr.str);
            }

            if (map.get(curr) == null) continue;
            // System.err.println("curr: " + curr.fullToString());

            if (!curr.dx.isEmpty()) {
                Path currCopy = new Path(curr);
                currCopy.x = currCopy.x + currCopy.popDx();
                queue.add(currCopy);
            }
            if (!curr.dy.isEmpty()) {
                Path currCopy = new Path(curr);
                currCopy.y = currCopy.y + currCopy.popDy();
                queue.add(currCopy);
            }
            
        }
        // System.err.println("availablePaths: " + availablePaths);
        return availablePaths;
    }

    static class Output {
        List<List<String>> array;
        public Output() {
            this.array = new ArrayList<>();
        }

        public void add(List<String> newPaths) {
            List<List<String>> updatedArray = new ArrayList<>();
            if (!this.array.isEmpty()) {
                for (List<String> path: this.array) {
                    for (String newPath: newPaths) {
                        List<String> existingPath = new ArrayList<>(path);
                        existingPath.add(newPath);
                        updatedArray.add(existingPath);
                    }
                }
            } else {
                for (String path: newPaths) {
                    List<String> freshPath = new ArrayList<>();
                    freshPath.add(path);
                    updatedArray.add(freshPath);
                }
            }
            this.array = updatedArray;
            // System.err.println("paths: " + this.array);
        }

        public List<String> printArray() {
            List<String> printedPaths = new ArrayList<>();
            for (List<String> path: this.array) {
                printedPaths.add(path.stream().reduce("", (x, y) -> x + y + "A"));
            }
            return printedPaths;
        }

        public String returnQuickestPath() {
            List<String> paths = this.printArray();
            return paths.stream().min(Comparator.comparingInt(String::length)).orElseThrow(() -> new IllegalStateException("No paths found"));
        }
    }

    public static List<String> convertNumericToDirectional(char starting, char ending) {
        Point start = getCharacterPoint(starting, numericCharToPoint);
        Output output = new Output();
        // System.out.println("Journey: " + starting + ", " + ending);
        Point end = getCharacterPoint(ending, numericCharToPoint);
        Difference diff = calculateDifference(start, end);
        output.add(validPaths(start, end, diff, numericPointToChar));
        return output.printArray();
    }


    public static BigInteger runConversion(String stringInput, int maxDepth) {
        char starting = 'A';
        BigInteger total = BigInteger.ZERO;
        // System.out.println("StringInput: " + stringInput.trim());
        for (char number: stringInput.trim().toCharArray()) {
            // System.out.println("\nnumber: " + number);
            List<String> numericPaths = convertNumericToDirectional(starting, number);
            // System.out.println("\nstarting: " + starting + ", ending: " + number + ", paths: " + numericPaths);
            // System.err.println("output1: " + output1);
            BigInteger min = getQuickestPath(numericPaths, maxDepth);
            starting = number;
            total = total.add(min);
        }
        return total;
    }

    public static long removeLeadingZeros(String num) {
        Set<Character> digits = Set.of('1', '2', '3', '4', '5', '6', '7', '8', '9');
        char[] trimmedString = num.substring(0, num.length() - 1).toCharArray();
        int i = 0;
        for (; i < trimmedString.length; i++) {
            if (digits.contains(trimmedString[i])) {
                break;
            }
        }
        return Long.parseLong(num.substring(i, num.length() - 1));
    }

    public static Map<String, List<String>> createCache() {
        char[] characters = new char[]{'A', '^', '<', '>', 'v'};
        Map<String, List<String>> cache = new HashMap<>();
        for (char c1: characters) {
            Point p1 = getCharacterPoint(c1, directionalCharToPoint);
            for (char c2: characters) {
                Point p2 = getCharacterPoint(c2, directionalCharToPoint);
                if (c1 == c2) continue;
                String key = String.valueOf(c1) + String.valueOf(c2);
                cache.put(key, validPaths(p1, p2, calculateDifference(p1, p2), directionalPointToChar));
            }
        }
        return cache;
    }

    public static List<String> getShortestPaths(char from, char to) {
        String key = from + "" + to;
        return cache.getOrDefault(key, new ArrayList<>());
    }

    static Map<String, List<String>> cache;

    static Map<String, BigInteger> dpCache = new HashMap<>();

    public static BigInteger getQuickestPath(List<String> pathsAtDepth1, int maxDepth) {
        BigInteger quickestPath = null;
        for (String path: pathsAtDepth1) {
            char start = 'A';
            BigInteger pathCost = BigInteger.ZERO;
            for (char step: path.toCharArray()) {
                BigInteger min = minLength(start, step, 0, maxDepth);
                pathCost = pathCost.add(min);
                start = step;
            }
            if (quickestPath == null || pathCost.compareTo(quickestPath) < 0) {
                quickestPath = pathCost;
            }
        }
        return quickestPath;
    };

    public static BigInteger minLength(char from, char to, int depth, int maxDepth) {
        if (depth == maxDepth) {
            return BigInteger.ONE;
        }

        String key = from + "" + to + depth;

        if (dpCache.containsKey(key)) {
            return dpCache.get(key);
        }

        List<String> paths = getShortestPaths(from, to);
        if (paths.isEmpty()) {
            dpCache.put(key, BigInteger.ONE);
            return BigInteger.ONE;
        }

        BigInteger minCost = null;

        for (String path: paths) {
            BigInteger cost = BigInteger.ZERO;
            char current = 'A';
            for (char next: (path + 'A').toCharArray()) {
                System.out.println("\n" + from + to);
                cost = cost.add(minLength(current, next, depth + 1, maxDepth));
                current = next;
            }
            if (minCost == null || cost.compareTo(minCost) < 0) {
                minCost = cost;
            }
        }
        dpCache.put(key, minCost);
        return minCost;
    }

    public static void main(String[] args) {
        String[] splitInput = input.trim().split("\n");
        BigInteger total = BigInteger.ZERO;
        cache = createCache();
        int maxDepth = 25;
        for (String sequence: splitInput) {
            BigInteger length = runConversion(sequence.trim(), maxDepth);
            long num = removeLeadingZeros(sequence.trim());
            BigInteger result = length.multiply(BigInteger.valueOf(num));
            total = total.add(result);
            System.err.println(String.format("%d * %d = %d", length, num, result));
        }
        System.err.println("total: " + total);
    }
}

// 87758115919708 - too low
