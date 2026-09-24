
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class Part2 {
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
            if (newPosition > 0 && newPosition > maximum) return (newPosition % maximum);
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

    // private static int calculateSafetyFactor(int Q1Size, int Q2Size, int Q3Size, int Q4Size) {
    //     return Q1Size * Q2Size * Q3Size * Q4Size;
    // }

    private static char[][] createChristmasTree(int midX) {
        char[][] christmasTree = new char[maxY][maxX];

        int left = midX;
        int right = midX;
        int i = 0;

        // Build leaves of tree
        for (; i < maxY; i++) {
            // left + right have reached the edge of the array
            if (left <= 0) {
                break;
            }

            char[] row = new char[maxX];
            for (int j = 0; j < maxX; j++) {
                if (j >= left && j <= right) {
                    row[j] = '*';
                } else {
                    row[j] = ' ';
                }
            }
            christmasTree[i] = row;
            left--;
            right++;
        }

        // Build bottom leaves of tree
        char[] bottomRow = new char[maxX];
        for (int j = 0; j < maxX; j++) {
            bottomRow[j] = '*';
        }
        christmasTree[i] = bottomRow;
        i++;

        // Build stem of tree
        for (; i < maxY; i++) {
            char[] row = new char[maxX];
            for (int j = 0; j < maxX; j++) {
                if (j == midX) {
                    row[j] = '*';
                } else {
                    row[j] = ' ';
                }
            }
            christmasTree[i] = row;
        }

        return christmasTree;
    }

    private static char[][] createBathroomFloor() {
        char[][] bathroomFloor = new char[maxY][];

        for (int i = 0; i < maxY; i++) {
            char[] row = new char[maxX];
            for (int j = 0; j < row.length; j++) {
                row[j] = ' ';
            }
            bathroomFloor[i] = row;
        }

        return bathroomFloor;
    }

    private static boolean validChristmasTreePosition(Robot robot, char[][] christmasTree, char[][] bathroomFloor) {
        boolean validPosition = christmasTree[robot.y][robot.x] == '*';
        if (!validPosition) {
            return false;
        }
        // Mark robot position on bathroom floor if position is valid
        bathroomFloor[robot.y][robot.x] = '*';
        return validPosition; 
    }

    private static boolean validChristmasTree(char[][] robotPositions, int totalRobots) {
        int robotsWithNeighbours = 0;
        for (int i = 1; i < robotPositions.length - 1; i++) {
            for (int j = 1; j < robotPositions[0].length - 1; j++) {
                // If this amount of robots have neighbours then an xmas tree is probably formed
                if (robotsWithNeighbours >= Math.sqrt(totalRobots)) {
                    return true;
                }
                // Check for octoganol relationships
                if (robotPositions[i][j] == '*' && robotHasOrthogonalNeighbours(i, j, robotPositions)) {
                    robotsWithNeighbours++;
                }
            }
        }
        return false;
    }

    private static boolean robotHasOrthogonalNeighbours(int i, int j, char[][] robotPositions) {
        boolean left = robotPositions[i][j-1] == '*';
        boolean right = robotPositions[i][j+1] == '*';
        boolean up = robotPositions[i-1][j] == '*';
        boolean down = robotPositions[i+1][j] == '*';
        if (left && right && up && down) {
            // System.err.println("Current: " + i + ", " + j);
            // System.err.println("left: " + robotPositions[i][j-1] + ", right: " + robotPositions[i][j+1] + ", up: " + robotPositions[i-1][j] + ", down: " + robotPositions[i+1][j] +"\n");
        }
        return (left && right && up && down);
    }

    private static void markRobotsOnBathroomFloor(List<Robot> robots, char[][] bathroomFloor) {
        for (Robot robot: robots) {
            if (bathroomFloor[robot.y][robot.x] == ' ') {
                bathroomFloor[robot.y][robot.x] = '*';
            }
        }
    }

    public static void main(String[] args) {
        List<Robot> robots = new ArrayList<>();
        
        createRobots(robots);

        int midX = maxX / 2;
        int midY = maxY / 2;

        // char[][] christmasTree = createChristmasTree(midX);

        // System.out.println("christmasTree: ");
        // for (char[] row: christmasTree) {
        //     System.out.println(Arrays.toString(row));
        // }

        char[][] bathroomFloor = null;

        long i = 0;
        boolean validTree = false;
        System.err.println("amount of robots: " + (robots.size()));
        int expectedTotalRobots = robots.size();

        for (; i < 10000000000000000L; i++) {
            System.out.println("i: " + i);
            bathroomFloor = createBathroomFloor();

            // boolean allRobotsValidPositions = true;
            markRobotsOnBathroomFloor(robots, bathroomFloor);
            if (validChristmasTree(bathroomFloor, expectedTotalRobots)) {
                validTree = true;
                break;
            }

            // Move robots if this iteration is not a christmas tree
            for (Robot robot: robots) {
                moveRobot(robot);
                // if (!validChristmasTreePosition(robot, bathroomFloorCopy)) {
                //     allRobotsValidPositions = false;
                //     break;
                // }
            }
            

        }

        if (validTree) {
            System.out.println("Iterations needed: " + i);
            System.out.println("christmasTree: ");
            int actualTotalRobots = 0;

            for (char[] row: bathroomFloor) {
                actualTotalRobots += IntStream.range(0, row.length)
                                    .filter(j -> row[j] == '*')
                                    .count();
                System.out.println(Arrays.toString(row));
            }
            System.out.println("actualTotalRobots: " + actualTotalRobots);
        }
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
