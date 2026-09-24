import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

class Part2 {
    private static final String INPUT_PATH = "input.txt";

    private static String readInput() {
        try {
            return java.nio.file.Files.readString(java.nio.file.Path.of(INPUT_PATH));
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException("Could not read " + INPUT_PATH + ". Add your puzzle input there.", e);
        }
    }

    static class Sequence {
        BigInteger num;
        BigInteger modVal = BigInteger.valueOf(16777216L);
        BigInteger sixtyFour = BigInteger.valueOf(64L);
        BigInteger thirtyTwo = BigInteger.valueOf(32L);
        BigInteger twoThousandAndFourtyEight = BigInteger.valueOf(2048L);

        public Sequence(BigInteger num) {
            this.num = num;
        }
        public void prune() {
            this.num = this.num.mod(modVal);
        }
        public void mix(BigInteger numToMix) {
            this.num = this.num.xor(numToMix);
        }
        public void pattern1() {
            BigInteger result = this.num.multiply(sixtyFour);
            mix(result);
            prune();
        }
        public void pattern2() {
            BigInteger result = this.num.divide(thirtyTwo);
            mix(result);
            prune();
        }
        public void pattern3() {
            BigInteger result = this.num.multiply(twoThousandAndFourtyEight);
            mix(result);
            prune();
        }
    }

    static class LastNumber {
        String wholeNumber;
        char character;
        int integer;

        public LastNumber(BigInteger wholeNumber) {
            this.wholeNumber = wholeNumber.toString();
            getLastChar();
            getLastInt();
        }

        private void getLastChar() {
            this.character = this.wholeNumber.charAt(this.wholeNumber.length() - 1);
        }

        private void getLastInt() {
            this.integer = character - '0';
        }
    }

    static String input = readInput();


    static Map<String, BigInteger> cache = new HashMap<>();

    public static BigInteger[] parseInput(String input) {
        return Arrays.stream(input.trim().split("\n")).map(BigInteger::new).toArray(BigInteger[]::new);
    }

    public static BigInteger manipulateNumber(BigInteger num) {
        Sequence seq = new Sequence(num);
        seq.pattern1();
        seq.pattern2();
        seq.pattern3();
        return seq.num;
    }

    static List<Map<String, Integer>> prices = new ArrayList<>();
    static Set<String> allKeys = new HashSet<>();

    public static String getKey(LastNumber prev, LastNumber curr, Queue<String> queue) {
        String diff = Integer.toString(curr.integer - prev.integer);
        if (queue.size() < 4) {
            queue.add(diff);
            return null;
        } else {
            queue.poll();
            queue.add(diff);
            return queue.stream().collect(Collectors.joining(","));
        }
    }
    // Create a new cache
    // for the 1996 possible sales record the four last differences and register this as the key to the last number
    public static void capturePrices(LastNumber prev, LastNumber curr, Queue<String> queue, Map<String, Integer> currPrices) {
        // manipulate queue
        String key = getKey(prev, curr, queue);
        // add the value to the key if the key doesn't already exist
        if (key != null && !currPrices.containsKey(key)) {
            currPrices.put(key, curr.integer);
            allKeys.add(key);
        }
    }

    public static void simulate(BigInteger[] input, int iterations) {
        for (BigInteger num: input) {
            // Initialise prev & queue
            LastNumber prev = new LastNumber(num);
            Queue<String> queue = new ArrayDeque<>();
            Map<String, Integer> currPrices = new HashMap<>();

            for (int i = 0; i < iterations; i++) {
                String key = num.toString();
                if (cache.containsKey(key)) {
                    num = cache.get(key);
                } else {
                    num = manipulateNumber(num);
                    cache.put(key, num);
                }
                // Assign curr
                LastNumber curr = new LastNumber(num);
                capturePrices(prev, curr, queue, currPrices);
                prev = curr;
            }
            prices.add(currPrices);
        }
    }

    public static long getOptimumPrices() {
        long max = 0;
        for (String key: allKeys) {
            long total = 0;
            for (Map<String, Integer> buyer: prices) {
                total += buyer.getOrDefault(key, 0);
            }
            // if (total > max) {
            //     System.err.println("\nkey: " + key);
            //     System.err.println("total: " + total);
            // }
            max = Math.max(max, total);
        }
        return max;
    }

    public static void main(String[] args) {
        BigInteger[] parsed = parseInput(input);
        simulate(parsed, 2000);
        long result = getOptimumPrices();
        System.err.println("Result: " + result);
    }
}
