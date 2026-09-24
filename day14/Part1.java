
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Part1 {
    private static final String INPUT_PATH = "input.txt";

    private static String readInput() {
        try {
            return java.nio.file.Files.readString(java.nio.file.Path.of(INPUT_PATH));
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException("Could not read " + INPUT_PATH + ". Add your puzzle input there.", e);
        }
    }

    static String rules = readInput();


    static int maxX = 101;
    static int maxY = 103;


    private static void createRobots(List<Robot> robots) {
        String[] splitRules = rules.split("\n");
        int id = 0;
        for (String ruleString: splitRules) {
            Pattern pattern = Pattern.compile("p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)");
            Matcher matcher = pattern.matcher(ruleString);
            if (matcher.find()) {
                // System.out.println("Rule: " + ruleString);
                // System.out.println("  Full match: " + matcher.group(0));
                // System.out.println("  Group 1 (x): " + matcher.group(1));
                // System.out.println("  Group 2 (y): " + matcher.group(2));
                // System.out.println("  Group 3 (deltaX): " + matcher.group(3));
                // System.out.println("  Group 4 (deltaY): " + matcher.group(4));

                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                int deltaX = Integer.parseInt(matcher.group(3));
                int deltaY = Integer.parseInt(matcher.group(4));
                robots.add(new Robot(x, y, deltaX, deltaY, id++));
            } else {
                System.out.println("Found no match");
            }
        }
    }

    private static int calculateNewPosition(int startingPosition, int delta, int maximum) {
        int newPosition = startingPosition + delta;

        if (newPosition >= maximum) {
            return (newPosition % maximum);
        }

        while (newPosition < 0) {
            newPosition = (maximum + newPosition);
            if (newPosition > 0 && newPosition < maximum) return newPosition;
        }

        return newPosition;
    }

    private static void moveRobot(Robot robot) {
        int newX = calculateNewPosition(robot.x, robot.deltaX, maxX);
        int newY = calculateNewPosition(robot.y, robot.deltaY, maxY);

        // if (robot.id == 10) {
        //     System.out.println("oldCoords: (" + robot.x + ", " + robot.y  + ").  With a delta: (" + robot.deltaX + ", " + robot.deltaY + ")");
        //     System.out.println("newCoords: (" + newX + ", " + newY  + ")\n");
        // }

        robot.x = newX;
        robot.y = newY;
    }

    private static int calculateSafetyFactor(int Q1Size, int Q2Size, int Q3Size, int Q4Size) {
        return Q1Size * Q2Size * Q3Size * Q4Size;
    }

    private static void placeRobotOnBathroomFloor(Robot robot, char[][] bathroomFloor) {
        if (bathroomFloor[robot.y][robot.x] == '.') {
            bathroomFloor[robot.y][robot.x] = '1'; 
        } else {
            int newAmountOfRobots = (int) bathroomFloor[robot.y][robot.x] + 1;
            bathroomFloor[robot.y][robot.x] = (char) newAmountOfRobots;
        }
    }

    public static void main(String[] args) {
        List<Robot> robots = new ArrayList<>();
        
        createRobots(robots);

        for (int i = 0; i < 100; i++) {
            for (Robot robot: robots) {
                moveRobot(robot);
            }
        }

        List<Robot> Q1 = new ArrayList<>();
        List<Robot> Q2 = new ArrayList<>();
        List<Robot> Q3 = new ArrayList<>();
        List<Robot> Q4 = new ArrayList<>();
        int midX = maxX / 2;
        int midY = maxY / 2;

        char[][] bathroomFloor = new char[maxX][];

        for (int i = 0; i < maxY; i++) {
            char[] row = new char[maxX];
            if (i == midY) {
                for (int j = 0; j < row.length; j++) {
                    row[j] = ' ';
                }
            } else {
                for (int j = 0; j < row.length; j++) {
                    row[j] = '.';
                }
                row[midX] = ' '; // optional: place a special character in the middle
            }
            bathroomFloor[i] = row;
        }

        for (Robot robot: robots) {
            if (robot.x == midX || robot.y == midY) continue;

            placeRobotOnBathroomFloor(robot, bathroomFloor);

            if (robot.x < midX) {
                if (robot.y < midY) {
                    Q1.add(robot);
                } else {
                    Q3.add(robot);
                }
            } else {
                if (robot.y < midY) {
                    Q2.add(robot);
                } else {
                    Q4.add(robot);
                }
            }
        }

        for (char[] col: bathroomFloor) {
            System.err.println(Arrays.toString(col));
        }

        int safetyFactor = calculateSafetyFactor(Q1.size(), Q2.size(), Q3.size(), Q4.size());

        System.out.println("Safety factor: " + safetyFactor);
    }

    static class Robot {
        int x, y, deltaX, deltaY, id;

        public Robot(int x, int y, int deltaX, int deltaY, int id) {
            this.x = x;
            this.y = y;
            this.deltaX = deltaX;
            this.deltaY = deltaY;
            this.id = id;
        }
    }
}

// 227366730 is too high
