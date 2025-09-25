
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SeventeenFive {
    static class Operand {
        int literal;
        long combo;

        public Operand(int literal, long combo) {
            this.literal = literal;
            this.combo = combo;
        }
    }

    static class Pointer {
        int value;

        public Pointer(int value) {
            this.value = value;
        }
    }

    static class EndResult {
        boolean correctPath;
        ArrayList<Integer> output;

        public EndResult(ArrayList<Integer> output) {
            this.output = output;
            this.correctPath = true;
        }

        public EndResult(boolean correctPath) {
            this.correctPath = correctPath;
        }

        @Override
        public String toString() {
            return String.format("correctPath: %s, output: %s",this.correctPath, this.output);
        }
    }

    private static long registerA;
    private static long registerB;
    private static long registerC;
    private static Map<String, EndResult> memo = new HashMap<>();

    private static long getComboOperand(int comboOperand) {
        if (comboOperand <= 3) {
            return comboOperand;
        }
        if (comboOperand == 4) {
            return registerA;
        }
        if (comboOperand == 5) {
            return registerB;
        }
        if (comboOperand == 6) {
            return registerC;
        }
        if (comboOperand == 7) {
            return -1;
        }

        // Consider handling other cases or throw an exception
        throw new IllegalArgumentException("Invalid comboOperand: " + comboOperand);
    }

    private static Integer performOpcode(int opcode, Operand operand, Pointer pointer) {
        // Pointer value changes
        if (opcode == 3 && registerA != 0) {
            // jnz
            pointer.value = operand.literal;
            // System.err.println("Jumping to: " + pointer.value);
            return null;
        } else {
            pointer.value += 2;
        }

        // Can I assume that I need to move the int's to longs?
        if (opcode == 0) {
            // adv
            registerA = registerA >> operand.combo;
        } else if (opcode == 1) {
            // bxl
            registerB = (long) registerB ^ operand.literal;
        } else if (opcode == 2) {
            // bst
            registerB = (long) operand.combo % 8;
        } else if (opcode == 4) {
            // bxc
            registerB = (long) registerB ^ registerC;
        } else if (opcode == 5) {
            // out
            // If a program outputs multiple values, they are separated by commas
            return (int) operand.combo % 8;
        } else if (opcode == 6) {
            // bdv
            registerB = registerA >> operand.combo;
        } else if (opcode == 7) {
            // cdv
            registerC = registerA >> operand.combo;
        }
        return null;
    }

    private static boolean isSubArray(ArrayList<Integer> wholeList, ArrayList<Integer> subList) {
        return Collections.indexOfSubList(wholeList, subList) != -1; // -1 is when an instance is not found
    }

    private static boolean validEnding(ArrayList<Integer> wholeList, ArrayList<Integer> endOfList) {
        int startingIndex = wholeList.size() - endOfList.size();
        List<Integer> cutList = wholeList.subList(startingIndex, wholeList.size());
        return cutList.equals(endOfList);
    }

    private static void addKeysAndSetCorrectPathFalse(Map<String, Integer> localHistory) {
        for (String key: localHistory.keySet()) {
            memo.put(key, new EndResult(false));
        }
    }

    private static void setOutputCombinations(Map<Integer, ArrayList<Integer>> outputCombinations, ArrayList<Integer> output) {
        int outputSize = output.size();
        for (int i = 0; i < outputSize; i++) {
            ArrayList<Integer> outputFromIndex = new ArrayList<>(output.subList(i, outputSize));
            outputCombinations.put(i, outputFromIndex);
        }
    }

    private static void addKeysWithOutputAtTime(Map<String, Integer> localHistory, ArrayList<Integer> output) {
        Map<Integer, ArrayList<Integer>> outputCombinations = new HashMap<>();
        setOutputCombinations(outputCombinations, output);

        for (Entry<String, Integer> entry: localHistory.entrySet()) {
            String iteration = entry.getKey();
            Integer outputFromIndex = entry.getValue();
            ArrayList<Integer> outputAtIteration = outputCombinations.get(outputFromIndex);
            memo.put(iteration, new EndResult(outputAtIteration));
        }
    }

    public static boolean computeOutput(long registerAAttempt) {
        // Memoize - Need to Memoize the true's somehow
        // Also add condition for when output is too long
        registerA = registerAAttempt;
        registerB = 0L;
        registerC = 0L;
        String input = "2,4,1,7,7,5,0,3,4,4,1,7,5,5,3,0";

        Pointer pointer = new Pointer(0);
        // Convert to ArrayList for comparisons
        int[] intArray = Stream.of(input.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        ArrayList<Integer> intList = Arrays.stream(intArray).boxed().collect(Collectors.toCollection(ArrayList::new));
        int programmeLength = intList.size();
        ArrayList<Integer> output = new ArrayList<>();
        Map<String, Integer> localHistory = new HashMap<>();

        // I want to check if the new substring is valid, I want the key to be the starting substring no?

        while (pointer.value < programmeLength - 1) { // Two values are needed for an iteration
            String iteration = String.format("%d,%d,%d,%d", pointer.value, registerA, registerB, registerC);
            EndResult memoizedValue = memo.get(iteration);
            if (memoizedValue != null) {
                if (!memoizedValue.correctPath) {
                    addKeysAndSetCorrectPathFalse(localHistory);
                    if (registerAAttempt == 117440) {
                        System.out.println("memoizedValue: " + memoizedValue.toString());
                        System.out.println("iteration: " + iteration);
                    }
                    return false;
                }

                // correctPath found -> Check if validEnding
                output.addAll(memoizedValue.output);
                break;
            }


            int opcodeInt = intArray[pointer.value];
            int operandInt = intArray[pointer.value + 1];
            Operand operand = new Operand(operandInt, getComboOperand(operandInt));
            Integer opcodeOutput = performOpcode(opcodeInt, operand, pointer);

            // Mark iteration
            localHistory.put(iteration, output.size());
            if (opcodeOutput != null) {
                output.add(opcodeOutput);

                // Not a valid sub array -> set the path to incorrect.
                if (!isSubArray(intList, output)) {
                    addKeysAndSetCorrectPathFalse(localHistory);
                    if (registerAAttempt == 117440) System.out.println("isSubArray:\nintList: " + intList + ", output: " + output);
                    return false;
                }
            }
        }

        if (!validEnding(intList, output)) {
            // Ending not valid -> set all iterations to be an invalid path
            addKeysAndSetCorrectPathFalse(localHistory);
            if (registerAAttempt == 117440) System.out.println("!validEnding:\nintList: " + intList + ", output: " + output);
            return false;
        } 
        // Ending valid -> Add all iterations with the state output at that time
        addKeysWithOutputAtTime(localHistory, output);

        String stringOutput = output.stream().map(String::valueOf).collect(Collectors.joining(","));
        // System.out.println("String output: " + stringOutput);
        if (registerAAttempt == 117440) System.out.println("input == stringOutput: " + input.equals(stringOutput));
        return input.equals(stringOutput);
    }

    public static String showOutput(long registerAAttempt) {
        registerA = registerAAttempt;
        registerB = 0L;
        registerC = 0L;
        String input = "2,4, 1,7, 7,5, 0,3, 4,4, 1,7, 5,5, 3,0";

        Pointer pointer = new Pointer(0);
        int[] intArray = Stream.of(input.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        int programmeLength = intArray.length;
        ArrayList<Integer> output = new ArrayList<>();

        while (pointer.value < programmeLength - 1) { // Two values are needed for an iteration
            // System.out.println("Pointer: " + pointer.value);
            int opcodeInt = intArray[pointer.value];
            int operandInt = intArray[pointer.value + 1];
            Operand operand = new Operand(operandInt, getComboOperand(operandInt));
            Integer opcodeOutput = performOpcode(opcodeInt, operand, pointer);
            if (opcodeOutput != null) {
                output.add(opcodeOutput);
            }
            // System.out.println("Output: " + output.toString());
            // System.out.println("Pointer: " + pointer.value);
            // System.out.println("Register A: " + registerA);
            // System.out.println("Register B: " + registerB);
            // System.out.println("Register C: " + registerC);
            // System.out.println("");

        }
        

        String stringOutput = output.stream().map(String::valueOf).collect(Collectors.joining(","));
        return stringOutput;
    }

    public static void main(String[] args) {
        long i = 0;
        for (; i < 52042868L; i++) {
            if (i%10000 == 0) System.out.println("Trying: " + i);
            if (computeOutput(i)) {
                System.out.println("Found it! " + i);
                System.out.println("Output: " + showOutput(i));
                System.out.println("Register A: " + registerA);
                System.out.println("Register B: " + registerB);
                System.out.println("Register C: " + registerC);
                System.out.println("");
                break;
            }
            // System.out.println("Output: " + showOutput(i));
            // System.out.println("Register A: " + registerA);
            // System.out.println("Register B: " + registerB);
            // System.out.println("Register C: " + registerC);
            // System.out.println("");
        }
        
        // System.out.println("Trying: " + i);
        // System.out.println("Output: " + computeOutput(117440L));
        // System.out.println("Register A: " + registerA);
        // System.out.println("Register B: " + registerB);
        // System.out.println("Register C: " + registerC);
    }
}
    
// 210172503 is too low
