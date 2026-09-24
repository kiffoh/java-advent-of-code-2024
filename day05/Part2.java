
import java.util.*;

class Part2{
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

    static HashMap<String, Set<String>> orderingRules = new HashMap<>();
    static int total = 0;

    private static void createRules(String[] rulesInput) {
        // Create a dict where the key is associated with all the values which should be after it
        // When I iterate over the 'page' I can add the value to the set to quickly determine if a value which should be after it has come before it
        for (String rule : rulesInput) {
            String[] ruleParts = rule.split("\\|");
            String before = ruleParts[0].trim();
            String after = ruleParts[1].trim();

            // Regular HashSet is a mutable collection in Java 
            orderingRules.computeIfAbsent(before, k -> new HashSet<>()).add(after);
        }
    }

    private static Map<String, Object> isOrderValid(List<Integer> pages) {
        Map<String, Object> funcStatus = new HashMap<>();

        Set<String> seen = new HashSet<>();

        for (int i = 0; i < pages.size(); i++) {
            String current = String.valueOf(pages.get(i));

            Set<String> valuesToBeAfterKey = orderingRules.getOrDefault(current, new HashSet<>());
            
            for (String value : valuesToBeAfterKey) {
                if (seen.contains(value)) { // Array is invalid
                    // System.out.println("    INVALID: " + value + " appears before " + current);

                    // // Need to rearrange array, can then check if new page is valid
                    // int indexOfInvalidString = Arrays.asList(pages).indexOf(value);
                    // String[] copiedArray = new String[pages.length];

                    // // Copy the StringArray up to the indexOfInvalidPlace
                    // System.arraycopy(pages, 0, copiedArray, 0, indexOfInvalidString);

                    // // Copy the value earlier into the copied array
                    // copiedArray[indexOfInvalidString] = pages[i];

                    // // Copy the StringArray from the indexOfInvalidString up to the current index (e.g. the value which should be before the invalidString)
                    // System.arraycopy(pages, indexOfInvalidString + 1, copiedArray, indexOfInvalidString + 1, i);

                    // copiedArray[i] = pages[indexOfInvalidString];

                    // // Copy the StringArray from the current index (e.g. the value which should be before the invalidString) up to the end of StringArray
                    // System.arraycopy(pages, i + 1, copiedArray, i + 1, pages.length);
                    
                    // isOrderValid(copiedArray);
                    int beforeElementIndex = pages.indexOf(Integer.parseInt(value));
                    funcStatus.put("valid", false);
                    funcStatus.put("beforeElementIndex", beforeElementIndex);
                    funcStatus.put("afterElementIndex", i);
                    return funcStatus;
                }
            }

            // Add the key to the set
            seen.add(current);
            System.out.println("  Updated seen set: " + seen);
        }
        funcStatus.put("valid", true);
        return funcStatus;
    }

    private static List<Integer> reorderPages(List<Integer> listToReorder, int beforeElementIndex, int afterElementIndex) {
        // Swap element at beforeIndex with element at afterIndex
        int temp = listToReorder.get(beforeElementIndex);
        listToReorder.set(beforeElementIndex, listToReorder.get(afterElementIndex));
        listToReorder.set(afterElementIndex, temp);

        // Get the result from isOrderValid
        Map<String, Object> funcStatus = isOrderValid(listToReorder);

        boolean valid = (Boolean) funcStatus.get("valid");
        if (valid) return listToReorder;
        else {
            // Only access beforeElementIndex and afterElementIndex if the order is invalid
            int newBeforeElementIndex = (int) funcStatus.get("beforeElementIndex");
            int newAfterElementIndex = (int) funcStatus.get("afterElementIndex");

            return reorderPages(listToReorder, newBeforeElementIndex, newAfterElementIndex);
        }
    }

    public static void main(String[] args) {
        String rulesInput = readSection(0);

        String[] rulesInputFormatted = rulesInput.split("\n");

        createRules(rulesInputFormatted);

        // for (Map.Entry<String, Set<String>> entry : orderingRules.entrySet()) {
        //     String key = entry.getKey();
        //     Set<String> values = entry.getValue();
        // }

        String inputString = readSection(1);

        String[] inputList = inputString.split("\n");

        for (String line : inputList) {
            // System.err.println(line);
            String[] pages = line.split(",");

            List<Integer> pageNumbers = new ArrayList<>();
            for (String page : pages) {
                pageNumbers.add(Integer.parseInt(page.trim()));
            }

            // String[] formattedLine = new String[splitLine.length];
            // for (int i= 0; i < splitLine.length; i++) {
            //     formattedLine[i] = splitLine[i].trim();
            // }

            Map<String, Object> funcStatus = isOrderValid(pageNumbers);

            boolean valid = (Boolean) funcStatus.get("valid");

            if (!valid) {
                // Only access beforeElementIndex and afterElementIndex if the order is invalid
                int beforeElementIndex = (int) funcStatus.get("beforeElementIndex");
                int afterElementIndex = (int) funcStatus.get("afterElementIndex");

                // Rearrage order and take middle number
                List<Integer> correctOrder = reorderPages(pageNumbers, beforeElementIndex, afterElementIndex);

                // Take middle index
                int middleIndex = correctOrder.size() / 2;
                int middleValue = correctOrder.get(middleIndex);
                total += middleValue;
            }
        }

        System.out.println("total: " + total);
    }
}
