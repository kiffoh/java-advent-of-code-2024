import java.util.*;
import java.math.BigInteger;

class ElevenFive {
    static String inputString = """
    1750884 193 866395 7 1158 31 35216 0
    """;

    // Memoization cache for the count of stones: key = "stone:remainingBlinks", value = count
    private static final Map<String, Long> countCache = new HashMap<>();

    // Count how many stones a single stone will become after n blinks
    private static long countStones(String stone, int blinks) {
        // Create a unique key for this stone and number of blinks
        String cacheKey = stone + ":" + blinks;
        
        // Return cached result if we've seen this before
        if (countCache.containsKey(cacheKey)) {
            return countCache.get(cacheKey);
        }
        
        // Base case: no more blinks
        if (blinks == 0) {
            return 1; // This stone remains one stone
        }
        
        long resultCount = 0;
        
        // Apply transformation rules for one blink and count the resulting stones
        if (stone.equals("0")) {
            // 0 becomes 1
            resultCount = countStones("1", blinks - 1);
        } else if (stone.length() % 2 == 0) {
            // Even digit count splits into two stones
            int halfLength = stone.length() / 2;
            String firstHalf = stone.substring(0, halfLength);
            String secondHalf = stone.substring(halfLength);
            
            // Remove leading zeros
            firstHalf = new BigInteger(firstHalf).toString();
            secondHalf = new BigInteger(secondHalf).toString();
            
            // Count stones from each half
            resultCount = countStones(firstHalf, blinks - 1) + 
                         countStones(secondHalf, blinks - 1);
        } else {
            // Odd digit count gets multiplied by 2024
            BigInteger num = new BigInteger(stone);
            BigInteger result = num.multiply(BigInteger.valueOf(2024));
            
            // Count stones from the multiplied value
            resultCount = countStones(result.toString(), blinks - 1);
        }
        
        // Cache the result before returning
        countCache.put(cacheKey, resultCount);
        return resultCount;
    }
    
    // Count all stones after n blinks
    private static long countTotalStones(List<String> initialStones, int blinks) {
        long totalCount = 0;
        
        // Count stones resulting from each initial stone
        for (String stone : initialStones) {
            totalCount += countStones(stone, blinks);
        }
        
        return totalCount;
    }

    // Simulate up to a smaller number of blinks to verify the approach
    private static int simulateDirectly(List<String> initialStones, int blinks) {
        List<String> stones = new ArrayList<>(initialStones);
        
        for (int i = 0; i < blinks; i++) {
            for (int j = 0; j < stones.size(); j++) {
                String stone = stones.get(j);
                
                if ("0".equals(stone)) {
                    stones.set(j, "1");
                } else if (stone.length() % 2 == 0) {
                    int halfLength = stone.length() / 2;
                    String firstHalf = stone.substring(0, halfLength);
                    String secondHalf = stone.substring(halfLength);
                    
                    firstHalf = new BigInteger(firstHalf).toString();
                    secondHalf = new BigInteger(secondHalf).toString();
                    
                    stones.set(j, firstHalf);
                    stones.add(j + 1, secondHalf);
                    j++; // Skip the newly inserted stone
                } else {
                    BigInteger num = new BigInteger(stone);
                    BigInteger result = num.multiply(BigInteger.valueOf(2024));
                    stones.set(j, result.toString());
                }
            }
        }
        
        return stones.size();
    }

    public static void main(String[] args) {
        List<String> initialStones = new ArrayList<>(Arrays.asList(inputString.trim().split(" ")));
        System.out.println("Initial stones: " + initialStones);
        
        // Verify with a small number of blinks first
        int verificationBlinks = 10;
        int directCount = simulateDirectly(initialStones, verificationBlinks);
        long memoizedCount = countTotalStones(initialStones, verificationBlinks);
        
        System.out.println("After " + verificationBlinks + " blinks:");
        System.out.println("Direct simulation count: " + directCount);
        System.out.println("Memoized count: " + memoizedCount);
        
        // Now get count for target number of blinks
        long startTime = System.currentTimeMillis();
        int targetBlinks = 75;
        long finalCount = countTotalStones(initialStones, targetBlinks);
        long endTime = System.currentTimeMillis();
        
        System.out.println("\nFinal stone count after " + targetBlinks + " blinks: " + finalCount);
        System.out.println("Computation time: " + (endTime - startTime) + " ms");
        System.out.println("Cache entries: " + countCache.size());
    }
}
