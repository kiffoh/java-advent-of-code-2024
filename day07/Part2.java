
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Part2 {
    private static final String INPUT_PATH = "input.txt";

    private static String readInput() {
        try {
            return java.nio.file.Files.readString(java.nio.file.Path.of(INPUT_PATH));
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException("Could not read " + INPUT_PATH + ". Add your puzzle input there.", e);
        }
    }


    // static Map<Integer, String> operators = Map.of(
    //         0, "+",
    //         1, "-",
    //         2, "*",
    //         3, "/"
    // );
    static Map<Integer, String> operators = Map.of(
            0, "+",
            1, "*",
            2, "||"
    );

    static String inputString = readInput();


    static String[] inputStringArray;

    public static boolean equationValid(String operatorPath, long currentResult, long expectedResult, long[] calibrationNumbers, int currentIndex, Set<String> path) {
        // Check if recursion is complete
        if (currentIndex == calibrationNumbers.length) {
            boolean sum = currentResult == expectedResult;
            if (sum) System.out.println("Expected: " + expectedResult + ", Actual: " + currentResult + ". Achieved with operators: " + String.join(" ", operatorPath.split("")) + " for numbers : " + Arrays.toString(calibrationNumbers));
            return sum;
        }

        for (int op = 0; op < operators.size(); op++) {
            // Alter currentOperatorPath
            String operator = operators.get(op);
            String currentOperatorPath = operatorPath + operator;

            // Combination already tried 
            if (path.contains(currentOperatorPath)) {
                continue;
            }

            // Add current operator combination to path
            path.add(currentOperatorPath);

            long nextNumber = calibrationNumbers[currentIndex];
            String expression = String.format("%d %s %d", currentResult, operator, nextNumber);
            long newResult;

            try {
                newResult = evaluateExpression(expression);
            } catch (ArithmeticException e) {
                // Handle division by zero or other arithmetic errors
                path.remove(currentOperatorPath);
                continue;
            }

            if (equationValid(currentOperatorPath, newResult, expectedResult, calibrationNumbers, currentIndex + 1, path)) {
                return true;
            }

            path.remove(currentOperatorPath);
        }

        return false;
    }

    public static long evaluateExpression(String expressionAsString) {
        String[] parts = expressionAsString.split(" ");
        long left = Long.parseLong(parts[0]);
        String operator = parts[1];
        long right = Long.parseLong(parts[2]);

        return switch (operator) {
            case "+" ->
                left + right;
            case "*" ->
                left * right;
           case "||" -> {
                long[] tempLongArray = new long[] {left, right};
                long[] concatenatedValue = copyAndModify(tempLongArray, 0);
                yield concatenatedValue[0];
           }
            default ->
                throw new IllegalArgumentException("Unknown operator: " + operator);
        };
    }

    public static FormattedInput formatInput(String inputLine) {
        String[] splitInput = inputLine.split(":");
        long expectedResult = Long.parseLong(splitInput[0]);

        String calibrationEquation = splitInput[1].trim();
        long[] calibrationNumbers = Arrays.stream(calibrationEquation.split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        return new FormattedInput(expectedResult, calibrationNumbers);
    }

    public static void populateInputStringArray() {
        inputStringArray = inputString.split("\n");
    }

    public static long[] copyAndModify(long[] original, int startingIndex) {
        String firstNumberToCombine = Long.toString(original[startingIndex]);
        String secondNumberToCombine = Long.toString(original[startingIndex + 1]);
        long concatenatedNumbers = Long.parseLong(firstNumberToCombine + secondNumberToCombine);

        long[] modifiedArray = new long[original.length - 1];

        int originalIndex = 0;
        
        for (int i = 0; i < modifiedArray.length; i++) {
            if (i == startingIndex) {
                modifiedArray[i] = concatenatedNumbers;
                originalIndex += 2;
            } else {
                modifiedArray[i] = original[originalIndex++];
            }
        }

        return modifiedArray;
    }

    public static void main(String[] args) {
        populateInputStringArray();

        long totalSum = 0;

        for (String line : inputStringArray) {
            Set<String> path = new HashSet<>();

            FormattedInput formattedInput = formatInput(line);
            long expectedResult = formattedInput.getExpectedResult();
            long[] calibrationNumbers = formattedInput.getCalibrationNumbers();

            long startingNumber = calibrationNumbers[0];

            if (equationValid("", startingNumber, expectedResult, calibrationNumbers, 1, path)) {
                 System.out.println("Valid equation found!");
                System.out.println("Total sum before: " + totalSum + " + " + expectedResult + "");
                totalSum += expectedResult;
                System.out.println("Total sum after: " + totalSum + "\n");
            } else {
                System.out.println("No valid equation found for: " + Arrays.toString(calibrationNumbers) + "\n");
            }
        }

        System.out.println("Total sum: " + totalSum);
    }
}

class FormattedInput {

    long expectedResult;
    long[] calibrationNumbers;

    public FormattedInput(long value1, long[] value2) {
        this.expectedResult = value1;
        this.calibrationNumbers = value2;
    }

    public long getExpectedResult() {
        return expectedResult;
    }

    public long[] getCalibrationNumbers() {
        return calibrationNumbers;
    }

}
