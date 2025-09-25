
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Seventeen {
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

    private static long registerA;
    private static long registerB;
    private static long registerC;

    private static long getOperand(int comboOperand) {
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


    public static boolean computeOutput(long registerAAttempt) {
        // Memoize
        // Also add condition for when output is too long
        registerA = registerAAttempt;
        registerB = 0L;
        registerC = 0L;
        String input = "2,4,1,7,7,5,0,3,4,4,1,7,5,5,3,0";

        Pointer pointer = new Pointer(0);
        int[] intArray = Stream.of(input.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        int programmeLength = intArray.length;
        ArrayList<Integer> output = new ArrayList<>();

        while (pointer.value < programmeLength - 1) { // Two values are needed for an iteration
            // System.out.println("Pointer: " + pointer.value);
            int opcodeInt = intArray[pointer.value];
            int operandInt = intArray[pointer.value + 1];
            Operand operand = new Operand(operandInt, getOperand(operandInt));
            Integer opcodeOutput = performOpcode(opcodeInt, operand, pointer);
            if (opcodeOutput != null) output.add(opcodeOutput);

        }
        
        // System.out.println("Output: " + output.toString());
        // System.out.println("Register A: " + registerA);
        // System.out.println("Register B: " + registerB);
        // System.out.println("Register C: " + registerC);

        String stringOutput = output.stream().map(String::valueOf).collect(Collectors.joining(","));
        // System.out.println("String output: " + stringOutput);
        return input.equals(stringOutput);
    }

    public static String showOutput(long registerAAttempt) {
        registerA = registerAAttempt;
        registerB = 0L;
        registerC = 0L;
        String input = "2,4,1,7,7,5,0,3,4,4,1,7,5,5,3,0";

        Pointer pointer = new Pointer(0);
        int[] intArray = Stream.of(input.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        int programmeLength = intArray.length;
        ArrayList<Integer> output = new ArrayList<>();

        while (pointer.value < programmeLength - 1) { // Two values are needed for an iteration
            // System.out.println("Pointer: " + pointer.value);
            int opcodeInt = intArray[pointer.value];
            int operandInt = intArray[pointer.value + 1];
            Operand operand = new Operand(operandInt, getOperand(operandInt));
            Integer opcodeOutput = performOpcode(opcodeInt, operand, pointer);
            if (opcodeOutput != null) output.add(opcodeOutput);

        }
        
        // System.out.println("Output: " + output.toString());
        // System.out.println("Register A: " + registerA);
        // System.out.println("Register B: " + registerB);
        // System.out.println("Register C: " + registerC);

        String stringOutput = output.stream().map(String::valueOf).collect(Collectors.joining(","));
        return stringOutput;
    }

    public static void main(String[] args) {
        long i = 0;
        for (; i < 52042868L; i++) {
            System.out.println("Trying: " + i);
            if (computeOutput(i)) {
                System.out.println("Found it! " + i);
                break;
            }
        }
        
        System.out.println("Trying: " + i);
        System.out.println("Output: " + showOutput(i));
        System.out.println("Register A: " + registerA);
        System.out.println("Register B: " + registerB);
        System.out.println("Register C: " + registerC);
    }
}
    
// 210172503 is too low
