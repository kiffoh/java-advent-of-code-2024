
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Eleven {
    // static String inputString = """
    // 125 17
    // """;
    static String inputString = """
    1750884 193 866395 7 1158 31 35216 0
    """;

    private static void changeStones(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String num = input.get(i);
            if ("0".equals(num)) {
                input.set(i, "1");
            } else if (num.length() % 2 == 0) {
                int halfOfLength = num.length() / 2;
                String firstHalf = "";
                String secondHalf = "";

                for (int j = 0; j < num.length(); j++) {
                    if (j < halfOfLength) {
                        firstHalf += num.charAt(j);
                    } else {
                        secondHalf += num.charAt(j);
                    }
                }

                firstHalf = Long.toString(Long.parseLong(firstHalf));
                secondHalf = Long.toString(Long.parseLong(secondHalf));

                // Replace current element with first half
                input.set(i, firstHalf);

                // Insert second half into the list at the next index
                input.add(i + 1, secondHalf);

                // Skip the next element because we just inserted one
                i++;
            } else {
                input.set(i, Long.toString(Long.parseLong(num) * 2024));
            }
        }
        // System.out.println("input out: " + input.toString() + "\n");

    }

    public static void main(String[] args) {
        List<String> input = new ArrayList<>(Arrays.asList(inputString.trim().split(" ")));
        System.out.println("Initial input: " + input.toString() + "\n");


        int timesBlinking = 75;

        for (int i = 0; i < timesBlinking; i++) {
            System.out.println("Iteration number: " + (i + 1));
            changeStones(input);
        }

        // System.out.println("Final array: " + input.toString());

        // int output = input.stream().mapToInt(Integer::parseInt).sum();
        int output = input.size();
        System.out.println("Final output: " + output);

    }
}
