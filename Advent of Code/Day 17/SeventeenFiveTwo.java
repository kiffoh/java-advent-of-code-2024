import java.util.*;

class SeventeenFiveTwo {
    private static final int[] TARGET = {2,4,1,7,7,5,0,3,4,4,1,7,5,5,3,0};
    
    // Simulate one iteration of your program
    private static int simulateIteration(long a) {
        long b = a % 8;           // 2,4: B = A % 8
        b = b ^ 7;               // 1,7: B = B ^ 7
        long c = a >> b;         // 7,5: C = A >> B
        // A = A >> 3            // 0,3: A = A >> 3 (handled by caller)
        b = b ^ c;               // 4,4: B = B ^ C
        b = b ^ 7;               // 1,7: B = B ^ 7
        return (int)(b % 8);     // 5,5: OUTPUT B % 8
    }
    
    // Find all possible A values that could produce the target output
    public static Set<Long> findPossibleA(int targetOutput, long currentA) {
        Set<Long> possibilities = new HashSet<>();
        
        // Since A gets shifted left by 3 bits each iteration when working backwards,
        // we try all 8 possible values for the new lowest 3 bits
        for (int newBits = 0; newBits < 8; newBits++) {
            long candidateA = (currentA << 3) | newBits;
            
            if (simulateIteration(candidateA) == targetOutput) {
                possibilities.add(candidateA);
            }
        }
        
        return possibilities;
    }
    
    public static long solve() {
        // Work backwards from the last output to the first
        Set<Long> candidates = new HashSet<>();
        candidates.add(0L); // A must be 0 at the end for the program to halt
        
        // Work backwards through each target output
        for (int i = TARGET.length - 1; i >= 0; i--) {
            Set<Long> newCandidates = new HashSet<>();
            
            for (long candidate : candidates) {
                newCandidates.addAll(findPossibleA(TARGET[i], candidate));
            }
            
            candidates = newCandidates;
            System.out.printf("After processing output %d (value %d): %d candidates%n", 
                            i, TARGET[i], candidates.size());
            
            if (candidates.isEmpty()) {
                System.out.println("No valid candidates found!");
                return -1;
            }
        }
        
        // Find the minimum valid candidate
        long minimum = Collections.min(candidates);
        System.out.printf("Found %d possible solutions, minimum is: %,d%n", 
                        candidates.size(), minimum);
        
        return minimum;
    }
    
    // Verify a solution by running the full program
    private static boolean verify(long initialA) {
        long registerA = initialA;
        long registerB = 0L;
        long registerC = 0L;
        
        List<Integer> output = new ArrayList<>();
        int pointer = 0;
        
        while (pointer < TARGET.length - 1) {
            int opcode = TARGET[pointer];
            int operand = TARGET[pointer + 1];
            
            long comboValue;
            if (operand <= 3) comboValue = operand;
            else if (operand == 4) comboValue = registerA;
            else if (operand == 5) comboValue = registerB;
            else if (operand == 6) comboValue = registerC;
            else throw new IllegalArgumentException("Invalid operand: " + operand);
            
            switch (opcode) {
                case 0: registerA = registerA >> comboValue; break;
                case 1: registerB = registerB ^ operand; break;
                case 2: registerB = comboValue % 8; break;
                case 3: 
                    if (registerA != 0) {
                        pointer = operand;
                        continue;
                    }
                    break;
                case 4: registerB = registerB ^ registerC; break;
                case 5: output.add((int)(comboValue % 8)); break;
                case 6: registerB = registerA >> comboValue; break;
                case 7: registerC = registerA >> comboValue; break;
            }
            pointer += 2;
        }
        
        return output.size() == TARGET.length && 
               output.equals(Arrays.asList(Arrays.stream(TARGET).boxed().toArray(Integer[]::new)));
    }
    
    public static void main(String[] args) {
        System.out.println("Target: " + Arrays.toString(TARGET));
        
        long startTime = System.currentTimeMillis();
        long result = solve();
        long endTime = System.currentTimeMillis();
        
        if (result != -1) {
            System.out.printf("Solution: %,d%n", result);
            System.out.printf("Time taken: %.2f seconds%n", (endTime - startTime) / 1000.0);
            
            System.out.println("Verifying...");
            if (verify(result)) {
                System.out.println("✓ Solution verified!");
            } else {
                System.out.println("✗ Solution verification failed!");
            }
        }
    }
}

