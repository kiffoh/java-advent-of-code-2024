class Part1 {
    private static final String INPUT_PATH = "input.txt";

    private static String readInput() {
        try {
            return java.nio.file.Files.readString(java.nio.file.Path.of(INPUT_PATH));
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException("Could not read " + INPUT_PATH + ". Add your puzzle input there.", e);
        }
    }

    public static int numberOfSafeReports(int[][] inputArrays) {
        int unsafeReports = 0;
        // int totalReports = 0;

        for (int[] array: inputArrays) {
            if (array.length < 2) continue; // Skip arrays with less than 2 elements

            // totalReports += array.length;

            boolean increasing = array[0] < array[1];

            // Iterate through the array comparing adjacent elements
            for (int i = 0; i < array.length - 1; i++) {

                // Checks that any two adjacent levels differ by at least one and at most three
                int difference = array[i + 1] - array[i];
                if (array[i] == array[i+1] || Math.abs(difference) > 3) {
                    unsafeReports++;
                    break;
                }

                // Checks that the levels are either all increasing or all decreasing
                if ((difference > 0 && !increasing) || (difference < 0 && increasing)) {
                    unsafeReports++;
                    break;
                }
            }
        }

        int totalReports = inputArrays.length;
        System.out.println("unsafeReports: " + unsafeReports);
        return totalReports - unsafeReports;
    }

    public static void main(String[] args) {
        int[][] arr = readInput().lines()
            .map(line -> java.util.Arrays.stream(line.trim().split("\\s+")).mapToInt(Integer::parseInt).toArray())
            .toArray(int[][]::new);

        System.out.println(numberOfSafeReports(arr));
    }
}
