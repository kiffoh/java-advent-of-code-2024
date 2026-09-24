
import java.util.HashMap;

class Part2 {
    private static final String INPUT_PATH = "input.txt";

    private static String readInput() {
        try {
            return java.nio.file.Files.readString(java.nio.file.Path.of(INPUT_PATH));
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException("Could not read " + INPUT_PATH + ". Add your puzzle input there.", e);
        }
    }

    public static HashMap<Integer, Integer> createDict(int[] inputArray) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : inputArray) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        return map;
    }

    public static void main(String[] args) {
        java.util.List<String> lines = readInput().lines().toList();
        int[] arr1 = lines.stream().mapToInt(line -> Integer.parseInt(line.trim().split("\\s+")[0])).toArray();
        int[] arr2 = lines.stream().mapToInt(line -> Integer.parseInt(line.trim().split("\\s+")[1])).toArray();

        var arr1FrequencyMap = createDict(arr1);
        var arr2FrequencyMap = createDict(arr2);

        int sum = 0;

        for (int num : arr1FrequencyMap.keySet()) {
            if (arr2FrequencyMap.containsKey(num)) {
                sum += (num * arr1FrequencyMap.get(num) * arr2FrequencyMap.get(num));
            }       
        }

        System.out.println(sum);
   }
}
