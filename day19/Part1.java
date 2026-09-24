import java.util.*;

class Part1 {
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


    public static class Combination {
        int left;
        int right;
        int longestWord;
        ArrayList<String> towels;

        public Combination(int left, int right, int longestWord, ArrayList<String> towels) {
            this.left = left;
            this.right = right;
            this.longestWord = longestWord;
            this.towels = new ArrayList<>(towels);
        }

        @Override
        public String toString() {
            return "left:"+left+", right:"+right+". totalTowels:"+towels.size();
        }
    }

    public static Map<Integer, Set<String>> availablePatterns = new HashMap<>();
    public static String[] desiredDesigns;

    public static void parseInput() {
        desiredDesigns = desiredDesignsInput.trim().split("\n");
        String[] availablePatternsSplit = availablePatternsInput.trim().split(", ");
        for (String pattern: availablePatternsSplit) {
            addToAvailablePatterns(pattern, pattern.length());
        }
    }

    public static void addToAvailablePatterns(String pattern, int lengthOfPattern) {
        Set<String> existingSet = availablePatterns.get(lengthOfPattern);
            if (existingSet != null) {
                existingSet.add(pattern);
            } else {
                Set<String> newSet = new HashSet<>();
                newSet.add(pattern);
                availablePatterns.put(lengthOfPattern, newSet);
            }
    }

    public static boolean validSubString(int windowSize, String substring) {
        return availablePatterns.get(windowSize).contains(substring);
    }

    public static Map<String, Boolean> memo = new HashMap<>();

    public static boolean canMake(String design, int start) {
        if (start == design.length()) return true;
        
        String key = start + ""; // or use the substring as key
        if (memo.containsKey(key)) return memo.get(key);
        
        // Try all pattern lengths
        for (int length : availablePatterns.keySet()) {
            if (start + length <= design.length()) {
                String substring = design.substring(start, start + length);
                if (availablePatterns.get(length).contains(substring)) {
                    if (canMake(design, start + length)) {
                        memo.put(key, true);
                        return true;
                    }
                }
            }
        }
        
        memo.put(key, false);
        return false;
    }

    public static boolean solve(String desiredDesign) {
        memo.clear();
        return canMake(desiredDesign, 0);
    }

    public static void main(String[] args) {
        parseInput();
        Map<String, Boolean> output = new HashMap<>();

        // Make available patterns a HashMap<Integer, Set<String>>
        // - Integer is the length of string size
        // - Set is all available patterns at that length

        // When iterating through designs I only need to check the patterns at that window size (e.g. right - left + 1)
        // If that window size is greater than any avilable patterns -> continue as that pattern is not accurate

        for (int i = 0; i < desiredDesigns.length; i++) {
            System.err.println("Trying: " + i);
            String design = desiredDesigns[i];
            output.put(design, solve(design));
        }

        for (String design: desiredDesigns) {
            output.put(design, solve(design));
        }

        int totalSolvable = 0;
        for (Boolean solved: output.values()) {
            if (solved) totalSolvable++;
        }

        System.out.println("output: " + output);
        System.out.println("Amount solved: " + totalSolvable);
    }
}
