import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    private static Button parseButton(String line) {
        Pattern pattern = Pattern.compile("Button .: X\\+(\\d+), Y\\+(\\d+)");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return new Button(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        }
        return null;
    }
    
    private static Prize parsePrize(String line) {
        Pattern pattern = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            long x = Long.parseLong(matcher.group(1)) + 10000000000000L;
            long y = Long.parseLong(matcher.group(2)) + 10000000000000L;
            return new Prize(x, y);
        }
        return null;
    }

    private static Solution findBestSolution(Button buttonA, Button buttonB, Prize prize) {
        double det = buttonA.x * buttonB.y - buttonA.y * buttonB.x;

        if (det == 0) {
            System.out.println("Det == 0");
            return null;
        }

        double m_num = prize.x * buttonB.y - prize.y * buttonB.x;
        double n_num = buttonA.x * prize.y - buttonA.y * prize.x;

        System.out.println("m_num: " + m_num + ", n_num: " + n_num);
        

        if (m_num % det != 0 || n_num % det != 0) {
            System.out.println("m_num % det != 0: " + (m_num % det != 0) + ", n_num % det != 0: " + (n_num % det != 0));
            return null;
        }
    
        double m = m_num / det;
        double n = n_num / det;

        // Verify the solutions are non-negative
        if (m < 0 || n < 0) {
            System.out.println("m < 0 || n < 0");
            return null;
        }

        // Verify solution by substituting back
        if ((buttonA.x * m + buttonB.x * n != prize.x) || (buttonA.y * m + buttonB.y * n != prize.y)) {
            System.out.println("Solution incorrect when subbed back in");
            return null;
        }

        System.err.println("Before rounding! aPresses: " + m + ", bPresses: " + n);
        System.err.println("After rounding! aPresses: " + (int) m + ", bPresses: " + (int) n);

        return new Solution((long) m, (long) n);
    }

    public static void main(String[] args) {
        String[] machines = input.trim().split("\n\n");
        long totalTokens = 0;
        int prizesWon = 0;
        
        for (String machine : machines) {
            String[] lines = machine.trim().split("\n");
            Button buttonA = parseButton(lines[0]);
            Button buttonB = parseButton(lines[1]);
            Prize prize = parsePrize(lines[2]);
            
            System.out.println("Machine: ");
            System.out.println("Button A: " + buttonA);
            System.out.println("Button B: " + buttonB);
            System.out.println("Prize: " + prize);
            
            Solution solution = findBestSolution(buttonA, buttonB, prize);
            
            if (solution != null && solution.x > 100 && solution.y > 100) {
                long cost = solution.x * 3 + solution.y;
                System.out.println("totalTokensBefore: " + totalTokens);
                totalTokens += cost;
                System.out.println("totalTokensAfter: " + totalTokens);
                prizesWon++;
                System.out.println("Solution found: A=" + solution.x + ", B=" + solution.y + ", Cost=" + cost);
            } else {
                System.out.println("No solution exists for this machine");
            }
            
            System.out.println();
        }
        
        System.out.println("Total prizes won: " + prizesWon);
        System.out.println("Total tokens required: " + totalTokens);
    }
}

class Button {
    long x, y;
    
    Button(long x, long y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "X+" + x + ", Y+" + y;
    }
}

class Prize {
    long x, y;
    
    Prize(long x, long y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "X=" + x + ", Y=" + y;
    }
}

class Press {
    long x, y;
    
    Press(long x, long y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    long x, y;
    
    Solution(long x, long y) {
        this.x = x;
        this.y = y;
    }
}
