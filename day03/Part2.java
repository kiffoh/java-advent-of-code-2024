class Part2 {
    private static final String INPUT_PATH = "input.txt";

    private static String readInput() {
        try {
            return java.nio.file.Files.readString(java.nio.file.Path.of(INPUT_PATH));
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException("Could not read " + INPUT_PATH + ". Add your puzzle input there.", e);
        }
    }

    public static int fixComputerMemory(String inputString) {
        // Convert to charArray to iterate through array
        char[] inputCharArray = inputString.toCharArray();
        int totalLengthOfInput = inputCharArray.length;
        int totalSum = 0;
        boolean enabled = true;

        for (int i = 0; i < totalLengthOfInput; i++) {
            // Check for "do()" and "don't()" to enable/disable future mul instructions
            if (inputCharArray[i] == 'd') {
                // 4 is min len of do()
                if (totalLengthOfInput - i >= 4) {
                    // 7 is max len (e.g. don't())
                    int endIndex = Math.min(i + 7, totalLengthOfInput);
                    enabled = checkIfInstructionsEnabled(inputString.substring(i, endIndex), enabled);
                }
            }

            else if (inputCharArray[i] == 'm' && enabled) {             
                // min len that mul could be is 8 [ e.g. mul(1,4)]
                if (totalLengthOfInput - i >= 8) {
                    // max len that mul could be is 12 [ e.g. amul(123,487)]
                    int endIndex = Math.min(i + 12, totalLengthOfInput);

                    totalSum += returnSumOfExpression(inputString.substring(i, endIndex)); 
                }
            }
        }

        return totalSum;
    }

    public static boolean checkIfInstructionsEnabled(String expressionToCheck, boolean enabled) {
        System.err.println(expressionToCheck);
        if (expressionToCheck.startsWith("do()")) {
            return true;
        }

        else if (expressionToCheck.equals("don't()")) {
            return false;
        }

        return enabled;
    }

    public static int returnSumOfExpression(String expressionToCheck) {
        if (!expressionToCheck.startsWith("mul(")) return 0;

        // Convert to charArray so I can iterate through String
        // char[] expressionCharArray = (expressionToCheck.substring(4,expressionToCheck.length())).toCharArray();

        int delimiterIndex = expressionToCheck.indexOf(",");
        int openParenIndex = expressionToCheck.indexOf("(");
        int closedParenIndex = expressionToCheck.indexOf(")"); 

        if (openParenIndex == -1 || closedParenIndex == -1 || delimiterIndex == -1 || 
        openParenIndex >= delimiterIndex || delimiterIndex >= closedParenIndex) {
            return 0;
        }

        try {
            String str1 = expressionToCheck.substring(openParenIndex + 1, delimiterIndex);
            String str2 = expressionToCheck.substring(delimiterIndex + 1, closedParenIndex);


            int num1 = Integer.parseInt(str1);
            int num2 = Integer.parseInt(str2);

            // Cases where num1 or num2 are greater than a 1-3 digit number
            if ((num1 / 1000 > 0) || (num2 / 1000 > 0)) return 0;

            int sumOfValidExpression = num1 * num2;

            return sumOfValidExpression;
        } catch (NumberFormatException  e) {
            return 0;
        }
    }
    

    public static void main(String[] args) {
        String inputString = readInput();

        System.err.println(fixComputerMemory(inputString));
    }
}
