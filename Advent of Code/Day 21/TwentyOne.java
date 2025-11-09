
import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class TwentyOne {
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

    public static List<String> getOptimalPath(List<String> availablePaths) {
        int maxTransitions = availablePaths.get(0).length();
        List<String> optimalPath = new ArrayList<>();
        for (String pathString: availablePaths) {
            int pathTransitions = 0;
            for (int i = 0; i < pathString.length() - 1; i++) {
                if (pathString.charAt(i) != pathString.charAt(i + 1)) {
                    pathTransitions++;
                }
            }
            if (pathTransitions < maxTransitions) {
                maxTransitions = pathTransitions;
                optimalPath = new ArrayList<>();
            } 
            if (pathTransitions <= maxTransitions) {
                optimalPath.add(pathString);
            }
        }
        if (!optimalPath.isEmpty()) return optimalPath;
        throw new IllegalStateException("optimalPath not initialised");
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
        // System.err.println("getOptimalPath: " + getOptimalPath(availablePaths));
        // return getOptimalPath(availablePaths);
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

    // public static String convertInputToDirectional(String directionalInput) {
    //     Point starting = getCharacterPoint('A', directionalCharToPoint);
    //     Output output = new Output();
    //     for (char number: directionalInput.toCharArray()) {
    //         Point ending = getCharacterPoint(number, directionalCharToPoint);
    //         Difference diff = calculateDifference(starting, ending);
    //         output.add(validPaths(starting, ending, diff, directionalPointToChar));
    //         starting = ending;
    //     }
    //     return output.returnQuickestPath();
    // }
    public static List<String> convertInputToDirectional (List<String> prev) {
        List<String> newIteration = new ArrayList<>();
        for (String potentialPath: prev) {
            newIteration.addAll(applyDirectionalChanges(potentialPath));
        }
        return newIteration;
    }
    public static List<String> applyDirectionalChanges(String potentialPath) {
        String[] array = ("A" + potentialPath).split("");
        List<List<String>> output = new ArrayList<>();
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].equals(array[i+1])) {
                // Add an empty string in this case
                for (List<String> list : output) {
                    list.add("");
                }
                continue;
            };
            
            String key = array[i] + array[i+1];
            List<String> values = cache.get(key);
            List<List<String>> updated = new ArrayList<>();
            for (String value: values) {
                if (output.isEmpty()) {
                    List<String> newPath = new ArrayList<>();
                    newPath.add(value);
                    updated.add(newPath);
                } else {
                    for (List<String> existingPath: output) {
                        List<String> existingPathCopy = new ArrayList<>(existingPath);
                        existingPathCopy.add(value);
                        updated.add(existingPathCopy);
                    }
                }
            }
            output = updated;
        }
        return output.stream().map(list -> String.join("A", list) + "A").toList();
    }

    public static long getQuickestPath(List<String> paths) {
        return paths.stream().mapToLong(String::length).min().orElseThrow(() -> new IllegalStateException("No paths found"));
    }

    public static long determineBestPath(List<String> paths) {
        Long bestPath = null;
        for (String path: paths) {
            List<String> newIterations = applyDirectionalChanges(path);
            // System.err.println("\n"+path + " newIterations: " + newIterations);
            long quickest = getQuickestPath(newIterations);
            if (bestPath != null) {
                if (quickest < bestPath) bestPath = quickest;
            } else bestPath = quickest;
        }
        return bestPath;
    }

    public static long runConversion(String stringInput) {
        char starting = 'A';
        long total = 0;
        // System.out.println("StringInput: " + stringInput.trim());
        for (char number: stringInput.trim().toCharArray()) {
            // System.out.println("\nnumber: " + number);
            List<String> output1 = convertNumericToDirectional(starting, number);
            // System.err.println("output1: " + output1);
            List<String> output2 = convertInputToDirectional(output1);
            // System.err.println("output2: " + output2);
            // For each path in output2 I want to get the new paths and produce the lowest number
            long output3 = determineBestPath(output2);
            // System.err.println("output3: " + output3);
            starting = number;
            total += output3;
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

    static Map<String, List<String>> cache;

    public static void main(String[] args) {
        String[] splitInput = input.trim().split("\n");
        long total = 0;
        cache = createCache();
        // System.out.println(cache);
        for (String sequence: splitInput) {
            // System.err.println("sequence: " + sequence);
            // runConversion(input);
            long length = runConversion(sequence.trim());
            long num = removeLeadingZeros(sequence.trim());
            total += length * num;
            System.err.println(String.format("%d * %d = %d", length, num, length * num));
        }
        System.err.println("total: " + total);
    }
}

// 182932
