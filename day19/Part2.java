import java.util.*;

class Part2 {
    private static final String INPUT_PATH = "input.txt";

    private static String readInput() {
        try {
            return java.nio.file.Files.readString(java.nio.file.Path.of(INPUT_PATH));
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException("Could not read " + INPUT_PATH + ". Add your puzzle input there.", e);
        }
    }

    private static String readSection(int index) {
        String section = readInput().split("\n\n")[index];
        return section.endsWith("\n") ? section : section + "\n";
    }

    public static String availablePatternsInput = readSection(0);
    public static String desiredDesignsInput = readSection(1);


    // public static Map<String, Integer> availablePatterns = new HashMap<>();
    public static Set<String> availablePatterns = new HashSet<>();
    public static String[] desiredDesigns;
    public static int min;
    public static int max;


    public static void parseInput() {
        desiredDesigns = desiredDesignsInput.trim().split("\n");
        String[] availablePatternsSplit = availablePatternsInput.trim().split(", ");
        min = availablePatternsSplit[0].length();
        max = availablePatternsSplit[0].length();
        for (String pattern: availablePatternsSplit) {
            availablePatterns.add(pattern);

            int patternLength = pattern.length();
            if (patternLength < min) min = patternLength;
            else if (patternLength > max) max = patternLength;
        }
    }

    public static long dfs(int i, String design, Long[] cache) {
        if (i == design.length()) {
            return 1;
        }
        if (cache[i] != null) {
            return cache[i];
        }

        long result = 0;
        for (int j = i + min; j <= Math.min(i + max + 1, design.length()); j++) {
            String substring = design.substring(i, j);
            if (availablePatterns.contains(substring)) {
                result +=  dfs(j, design, cache);
            }
        }
        cache[i] = result;
        return result;
    }
    public static void main(String[] args) {
        parseInput();
        Map<String, Long> result = new HashMap<>();
        
        for (String design: desiredDesigns) {
            Long[] cache = new Long[design.length()];
            result.put(design, dfs(0, design, cache));
        }

        long totalCorrect = result.values().stream().reduce(0L, (x, y) -> x + y);

        System.out.println("Result: " + result);
        System.out.println("Total correct answers: " + totalCorrect);
    }
}
