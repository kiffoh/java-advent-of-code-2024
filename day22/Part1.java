import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Part1 {
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

    public static BigInteger simulate(BigInteger[] input, int iterations) {
        BigInteger total = BigInteger.ZERO;
        for (BigInteger num: input) {
            System.err.println("starting num: " + num);
            for (int i = 0; i < iterations; i++) {
                String key = num.toString();
                if (cache.containsKey(key)) {
                    num = cache.get(key);
                } else {
                    num = manipulateNumber(num);
                    cache.put(key, num);
                }
            }
            System.err.println("ending num: " + num);
            total = total.add(num);
        }
        return total;
    }

    public static void main(String[] args) {
        BigInteger[] parsed = parseInput(input);
        BigInteger result = simulate(parsed, 2000);
        System.err.println("Result: " + result);
    }
}
