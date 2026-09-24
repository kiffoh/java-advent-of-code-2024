import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.HashMap;


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


    // private static Map<String, Double> getCoefficients(Button buttonA, Button buttonB, Prize prize) {
    //     double aPresses = (prize.y - (buttonB.y * prize.x / buttonB.x)) / (buttonA.y - (buttonB.y * buttonA.x / buttonB.x));
    //     double b = (prize.x - buttonA.x * aPresses) / buttonB.x;

    //     double bPresses = (prize.x - (buttonA.x * prize.y / buttonA.y)) / (buttonB.x - (buttonA.x * buttonB.y / buttonA.y));
    //     double a = (prize.y - buttonB.y * bPresses) / buttonA.y;

    //     Map<String, Double> coefficients = new HashMap<>();
    //     coefficients.put("a", a);
    //     coefficients.put("b", b);

    //     return coefficients;
    // }

    // private static void findNonNegativeSolutions(Button buttonA, Button buttonB, Prize prize, List<Solution> result) {
    //     Map<String, Double> coefficients = getCoefficients(buttonA, buttonB, prize);

    //     double a = coefficients.get("a");
    //     double b = coefficients.get("b");

    //     System.err.println("aPresses: " + a + ", bPresses: " + b);
    //     // One or both of the coefficients is not equal to 0
    //     if (a % 1 != 0 || b % 1 != 0) {
    //         // Min and Max for a coefficient
    //         int aMin = (int) Math.floor(a);
    //         int aMax = (int) Math.ceil(a);

    //         // Min and Max for b coefficient
    //         int bMin = (int) Math.floor(b);
    //         int bMax = (int) Math.ceil(b);

    //         for (int aIteration = aMin; aIteration <= aMax; aIteration++) {
    //             for (int bIteration = bMin; bIteration <= bMax; bIteration++) {
    //                 boolean equation1 = buttonA.x * aIteration + buttonB.x * bIteration == prize.x;
    //                 boolean equation2 = buttonA.y * aIteration + buttonB.y * bIteration == prize.y;
    //                 // System.err.println("a: " + aIteration + ", b: " + bIteration + ".   Equation1: " + equation1 + ", equation2: " + equation2);
    //                 if (equation1 && equation2) {
    //                     Solution foundSolution = new Solution(aIteration, bIteration);
    //                     result.add(foundSolution);
    //                 }
    //             }
    //         }

    //     } else {
    //         int aInteger = (int) a;
    //         int bInteger = (int) b;
    //         boolean equation1 = buttonA.x * aInteger + buttonB.x * bInteger == prize.x;
    //         boolean equation2 = buttonA.y * aInteger + buttonB.y * bInteger == prize.y;

    //         System.err.println("equation1: " + equation1 + ", equation2: " + equation2);

    //         if (equation1 && equation2) {
    //             Solution foundSolution = new Solution(aInteger, bInteger);
    //             result.add(foundSolution);
    //         }
    //     }

    // }
    
    // private static int solve(Button buttonA, Button buttonB, Prize prize) {
        
    //     // double aPresses_1 = (prize.y - buttonB.y * bPresses_1) / buttonA.y;

    //     List<Solution> result = new ArrayList<>();

    //     // System.out.println("aPresses_1: " + aPresses_1 + ", bPresses_1: " + bPresses_1);
        
    //     findNonNegativeSolutions(buttonA, buttonB, prize, result);

    //     int bestCost = Integer.MAX_VALUE;
    //     if (result.size() > 1) {
    //         // Find best solution
    //         for (Solution foundSolution: result) {
    //             int cost = foundSolution.x * 3 + foundSolution.y;
    //             if (cost < bestCost) {
    //                 bestCost = cost;
    //             }
    //         }
    //     } else if (result.size() == 1) {
    //         Solution onlyFoundSolution = result.get(0);
    //         bestCost = onlyFoundSolution.x * 3 + onlyFoundSolution.y;
    //     } else {
    //         bestCost = 0;
    //     }

    //     // System.err.println("");

    //     // double bPresses_2 = (prize.x - buttonA.x * aPresses_2) / buttonB.x;

    //     // System.out.println("aPresses_2: " + aPresses_2 + ", bPresses_2: " + bPresses_2);
        
    //     // boolean equation1_2 = buttonA.x * aPresses_2 + buttonB.x * bPresses_2 == prize.x;
    //     // boolean equation2_2 = buttonA.y * aPresses_2 + buttonB.y * bPresses_2 == prize.y;

    //     // System.err.println("equation1_2: " + equation1_2 + ", equation2_2: " + equation2_2);

    //     return bestCost;
    // }

    // // Parse the buttons and prize
    // private static Button parseButton(String line) {
    //     Pattern pattern = Pattern.compile("Button .: X\\+(\\d+), Y\\+(\\d+)");
    //     Matcher matcher = pattern.matcher(line);
    //     if (matcher.find()) {
    //         return new Button(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
    //     }
    //     return null;
    // }
    
    // private static Prize parsePrize(String line) {
    //     Pattern pattern = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");
    //     Matcher matcher = pattern.matcher(line);
    //     if (matcher.find()) {
    //         return new Prize(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
    //     }
    //     return null;
    // }

    // public static void main(String[] args) {
    //     String[] machines = input.trim().split("\n\n");
    //     int totalTokens = 0;
    //     int prizesWon = 0;
        
    //     for (String machine : machines) {
    //         String[] lines = machine.trim().split("\n");
    //         Button buttonA = parseButton(lines[0]);
    //         Button buttonB = parseButton(lines[1]);
    //         Prize prize = parsePrize(lines[2]);
            
    //         System.out.println("Machine: ");
    //         System.out.println("Button A: " + buttonA);
    //         System.out.println("Button B: " + buttonB);
    //         System.out.println("Prize: " + prize);
            
    //         int solutionCost = solve(buttonA, buttonB, prize);
            
    //         if (solutionCost != 0) {
    //             totalTokens += solutionCost;
    //             prizesWon++;
    //         } else {
    //             System.out.println("No solution exists for this machine");
    //         }
            
    //         System.out.println();
    //     }
        
    //     System.out.println("Total prizes won: " + prizesWon);
    //     System.out.println("Total tokens required: " + totalTokens);
    // }

    public static void main(String[] args) {
        String[] machines = input.trim().split("\n\n");
        int totalTokens = 0;
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
            
            if (solution != null) {
                int cost = solution.x * 3 + solution.y;
                totalTokens += cost;
                prizesWon++;
                System.out.println("Solution found: A=" + solution.a + ", B=" + solution.b + ", Cost=" + cost);
            } else {
                System.out.println("No solution exists for this machine");
            }
            
            System.out.println();
        }
        
        System.out.println("Total prizes won: " + prizesWon);
        System.out.println("Total tokens required: " + totalTokens);
    }
    
    private static Solution findBestSolution(Button buttonA, Button buttonB, Prize prize) {
        // Linear Diophantine equation solver for:
        // a*buttonA.x + b*buttonB.x = prize.x
        // a*buttonA.y + b*buttonB.y = prize.y
        
        // We need to find integer solutions where a >= 0 and b >= 0
        // and minimize 3*a + b
        
        Solution bestSolution = null;
        int bestCost = Integer.MAX_VALUE;
        
        // Search a reasonable range (problem states no more than 100 presses)
        // But we'll search a wider range just to be safe
        for (int a = 0; a <= 200; a++) {
            // Calculate the required b value to satisfy the X equation
            // a*buttonA.x + b*buttonB.x = prize.x
            // b = (prize.x - a*buttonA.x) / buttonB.x
            
            if (buttonB.x == 0) {
                // Check if a solution exists with this a value
                if (a * buttonA.x == prize.x) {
                    // Calculate b from the Y equation
                    int neededY = prize.y - a * buttonA.y;
                    if (neededY % buttonB.y == 0) {
                        int b = neededY / buttonB.y;
                        if (b >= 0) {
                            int cost = 3 * a + b;
                            if (cost < bestCost) {
                                bestCost = cost;
                                bestSolution = new Solution(a, b);
                            }
                        }
                    }
                }
                continue;
            }
            
            if ((prize.x - a * buttonA.x) % buttonB.x != 0) {
                continue; // b must be an integer
            }
            
            int b = (prize.x - a * buttonA.x) / buttonB.x;
            if (b < 0) {
                continue; // b must be non-negative
            }
            
            // Verify the Y equation is also satisfied
            if (a * buttonA.y + b * buttonB.y == prize.y) {
                int cost = 3 * a + b;
                if (cost < bestCost) {
                    bestCost = cost;
                    bestSolution = new Solution(a, b);
                }
            }
        }
        
        return bestSolution;
    }

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
            return new Prize(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        }
        return null;
    }

}


// public class Part1 {

//     // Extended Euclidean Algorithm to find GCD and Bézout coefficients
//     public static GcdResult extendedGcd(int a, int b) {
//         if (a == 0) {
//             return new GcdResult(b, 0, 1);
//         }
        
//         GcdResult result = extendedGcd(b % a, a);
//         int gcd = result.gcd;
//         int x = result.y - (b / a) * result.x;
//         int y = result.x;
        
//         return new GcdResult(gcd, x, y);
//     }
    
//     // Find all non-negative solutions to ax + by = c
//     private static List<Press> findNonNegativeSolutions(int a, int b, int c) {
//         List<Press> solutions = new ArrayList<>();
        
//         // Check if a solution exists
//         GcdResult gcdResult = extendedGcd(Math.abs(a), Math.abs(b));
//         int gcd = gcdResult.gcd;
        
//         if (c % gcd != 0) {
//             // No integer solutions exist
//             return solutions;
//         }
        
//         // Find particular solution
//         int x0 = gcdResult.x * (c / gcd);
//         int y0 = gcdResult.y * (c / gcd);
        
//         // Adjust for sign of a and b
//         if (a < 0) x0 = -x0;
//         if (b < 0) y0 = -y0;
        
//         // General solution is x = x0 + k*(b/gcd), y = y0 - k*(a/gcd)
//         // We need to find k such that both x and y are non-negative
        
//         // Calculate steps in the solution space
//         int step_x = b / gcd;
//         int step_y = a / gcd;
        
//         // Ensure steps have correct signs
//         if (step_x < 0) step_x = -step_x;
//         if (step_y < 0) step_y = -step_y;
        
//         // Find smallest k where x >= 0
//         int k_min_x = x0 < 0 ? (int)Math.ceil(-1.0 * x0 / step_x) : 0;
        
//         // Find smallest k where y >= 0
//         int k_min_y = y0 < 0 ? (int)Math.ceil(-1.0 * y0 / step_y) : 0;
        
//         // Start from the larger of the two k values
//         int k = Math.max(k_min_x, k_min_y);
        
//         // Generate all solutions within reasonable bounds (button presses <= 100)
//         while (true) {
//             int x = x0 + k * step_x;
//             int y = y0 - k * step_y;
            
//             if (x < 0 || y < 0 || x > 100 || y > 100) {
//                 break;
//             }
            
//             solutions.add(new Press(x, y));
//             k++;
//         }
        
//         return solutions;
//     }
    
//     // Solve for a machine with given buttons and prize
//     private static Press solveMachine(Button buttonA, Button buttonB, Prize prize) {
//         // Solve x-equation: a*A_x + b*B_x = Prize_x
//         List<Press> xSolutions = findNonNegativeSolutions(buttonA.x, buttonB.x, prize.x);
        
//         if (xSolutions.isEmpty()) {
//             return null;
//         }
        
//         // Solve y-equation: a*A_y + b*B_y = Prize_y
//         List<Press> ySolutions = findNonNegativeSolutions(buttonA.y, buttonB.y, prize.y);
        
//         if (ySolutions.isEmpty()) {
//             return null;
//         }
        
//         // Find common solution with minimum cost
//         Press bestSolution = null;
//         int minCost = Integer.MAX_VALUE;
        
//         for (Press xSol : xSolutions) {
//             for (Press ySol : ySolutions) {
//                 // Check if this is a common solution
//                 if (xSol.x == ySol.x && xSol.y == ySol.y) {
//                     int cost = 3 * xSol.x + xSol.y;
//                     if (cost < minCost) {
//                         minCost = cost;
//                         bestSolution = xSol;
//                     }
//                 }
//             }
//         }
        
//         return bestSolution;
//     }
    
//     // Parse the buttons and prize
//     private static Button parseButton(String line) {
//         Pattern pattern = Pattern.compile("Button .: X\\+(\\d+), Y\\+(\\d+)");
//         Matcher matcher = pattern.matcher(line);
//         if (matcher.find()) {
//             return new Button(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
//         }
//         return null;
//     }
    
//     private static Prize parsePrize(String line) {
//         Pattern pattern = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");
//         Matcher matcher = pattern.matcher(line);
//         if (matcher.find()) {
//             return new Prize(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
//         }
//         return null;
//     }

//     public static void main(String[] args) {
//         String[] machines = input.trim().split("\n\n");
//         int totalTokens = 0;
//         int prizesWon = 0;
        
//         for (String machine : machines) {
//             String[] lines = machine.trim().split("\n");
//             Button buttonA = parseButton(lines[0]);
//             Button buttonB = parseButton(lines[1]);
//             Prize prize = parsePrize(lines[2]);
            
//             System.out.println("Machine: " + machine);
//             System.out.println("Button A: " + buttonA);
//             System.out.println("Button B: " + buttonB);
//             System.out.println("Prize: " + prize);
            
//             Press solution = solveMachine(buttonA, buttonB, prize);
            
//             if (solution != null) {
//                 int cost = 3 * solution.x + solution.y;
//                 System.out.println("Solution found: " + solution.x + " A presses, " + solution.y + " B presses");
//                 System.out.println("Cost: " + cost + " tokens");
//                 totalTokens += cost;
//                 prizesWon++;
//             } else {
//                 System.out.println("No solution exists for this machine");
//             }
            
//             System.out.println();
//         }
        
//         System.out.println("Total prizes won: " + prizesWon);
//         System.out.println("Total tokens required: " + totalTokens);
//     }
// }

class Button {
    int x, y;
    
    Button(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "X+" + x + ", Y+" + y;
    }
}

class Prize {
    int x, y;
    
    Prize(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "X=" + x + ", Y=" + y;
    }
}

class Press {
    int x, y;
    
    Press(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    int x, y;
    
    Solution(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

// 34067
