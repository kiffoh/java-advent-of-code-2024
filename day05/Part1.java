
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    static HashMap<String, String[]> orderingRules = new HashMap<>();
    static int total = 0;

    private static void createRules(String[] rulesInput) {
        for (int i = 0; i < rulesInput.length; i++) {
            String[] ruleParts =  rulesInput[i].split("\\|");

        // Create a dict where the key is associated with all the values which should be after it
        // When I iterate over the 'page' I can add the value to the set to quickly determine if a value which should be after it has come before it
            String key = ruleParts[0].trim();
            String valueToBeAfterKey = ruleParts[1].trim();

            String[] currentRules = orderingRules.getOrDefault(key, new String[0]);
            
            String[] updatedRules = new String[currentRules.length + 1];

            System.arraycopy(currentRules, 0, updatedRules, 0, currentRules.length);
            updatedRules[currentRules.length] = valueToBeAfterKey;

            // Update the HashMap
            orderingRules.put(key, updatedRules);
        }
    }

    private static void checkIfPageValid(String line) {
        String[] inputLine = line.split(",");
        Set<String> seen = new HashSet<>();

        // System.out.println("Processing line: " + line);
        
        for (String nString : inputLine) {
            String trimmedString = nString.trim();
            System.out.println("Outer loop: processing " + trimmedString);
            String[] valuesToBeAfterKey = orderingRules.getOrDefault(trimmedString, new String[0]);
            
            System.out.println("  Values that should come after " + trimmedString + ": " + 
                            Arrays.toString(valuesToBeAfterKey));
            
            for (String value : valuesToBeAfterKey) {
                System.out.println("    Inner loop: checking if " + value + " is in seen set: " + seen);
                
                if (seen.contains(value)) {
                    System.out.println("    INVALID: " + value + " appears before " + trimmedString);
                    return;
                }
            }

            // Add the key to the set
            seen.add(trimmedString);
            System.out.println("  Updated seen set: " + seen);
        }
        System.out.println("Line is VALID");
        int middleInt = inputLine.length / 2;
        int middleNum = Integer.parseInt(inputLine[middleInt]);
        total += middleNum;
        return;
    }

    public static void main(String[] args) {
        String rulesInput = readSection(0);

        String[] rulesInputFormatted = rulesInput.split("\n");

        createRules(rulesInputFormatted);

        for (Map.Entry<String, String[]> entry : orderingRules.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();

            System.out.println(key + ": " + Arrays.toString(values));
        }

        String inputString = readSection(1);
        String[] inputList = inputString.split("\n");

        int totalValidPages = 0;

        for (String line : inputList) {
            System.err.println(line);
            checkIfPageValid(line);
        }

        System.out.println("total: " + total);
    }
}
