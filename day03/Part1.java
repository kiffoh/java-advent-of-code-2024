class Part1 {
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

        for (int i = 0; i < totalLengthOfInput; i++) {
            if (inputCharArray[i] == 'm') {             
                // min len that mul could be is 8 [ e.g. mul(1,4)]
                if (totalLengthOfInput - i >= 8) {
                    // Returns 0 if invalid
                    // max len that mul could be is 12 [ e.g. amul(123,487)]
                    totalSum += returnSumOfExpression(inputString.substring(i, Math.min(i + 13, totalLengthOfInput))); 
                }
            }
        }

        return totalSum;
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
