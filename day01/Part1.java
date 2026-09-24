
import java.util.Arrays;

class Part1 {
    private static final String INPUT_PATH = "input.txt";

    private static String readInput() {
        try {
            return java.nio.file.Files.readString(java.nio.file.Path.of(INPUT_PATH));
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException("Could not read " + INPUT_PATH + ". Add your puzzle input there.", e);
        }
    }

    public static void main(String[] args) {
        java.util.List<String> lines = readInput().lines().toList();
        int[] arr1 = lines.stream().mapToInt(line -> Integer.parseInt(line.trim().split("\\s+")[0])).toArray();
        int[] arr2 = lines.stream().mapToInt(line -> Integer.parseInt(line.trim().split("\\s+")[1])).toArray();

        int res = 0;

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        for (int i = 0; i < arr1.length; i++) {
            res += Math.abs(arr1[i] - arr2[i]);
        }

        System.out.println("Result " + res);
    }
}
