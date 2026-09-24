class Part2 {
    private static final String INPUT_PATH = "input.txt";

    private static String readInput() {
        try {
            return java.nio.file.Files.readString(java.nio.file.Path.of(INPUT_PATH));
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException("Could not read " + INPUT_PATH + ". Add your puzzle input there.", e);
        }
    }

    public static int numberOfSafeReports(int[][] inputArrays) {
        int safeReports = 0;

        for (int[] array: inputArrays) {

            if (array.length < 2) continue; // Skip arrays with less than 2 elements

            // Confirm a safe report and skip logic underneath
            if (arrayIsValid(array)) { 
                safeReports++;
                continue;
            }

            // Iterate through each value to check if removing a value from the array makes it valid 
            for (int i = 0; i < array.length; i++) { 
                int[] newArray = removeValueFromArray(array, i);

                if (arrayIsValid(newArray)) {
                    safeReports++;
                    break;
                }
            }
        
        }

        return safeReports;
    }

    public static int[] removeValueFromArray(int[] array, int skipIndex) {
        int[] newArray = new int[array.length - 1];
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (i != skipIndex) {
                newArray[index++] = array[i];
            }
        }
        return newArray;
    }

    public static boolean arrayIsValid(int[] array) {
        if (array.length <= 1) return true;

        boolean increasing = array[0] < array[1]; // Initial pattern assumption
        // Iterate through the array comparing adjacent elements
        for (int i = 0; i < array.length - 1; i++) {

            // Checks that any two adjacent levels differ by at least one and at most three
            int difference = array[i + 1] - array[i];

            if (array[i] == array[i+1] || Math.abs(difference) > 3) {
                return false;
            }

            // Checks that the levels are either all increasing or all decreasing
            if ((difference > 0 && !increasing) || (difference < 0 && increasing)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] arr = readInput().lines()
            .map(line -> java.util.Arrays.stream(line.trim().split("\\s+")).mapToInt(Integer::parseInt).toArray())
            .toArray(int[][]::new);

        System.out.println(numberOfSafeReports(arr));
    }
}
