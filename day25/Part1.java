
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Lock {
    List<Integer> heights;
    public Lock(List<String> schematic) {
        this.heights = generateHeights(schematic);
    }
    private List<Integer> generateHeights(List<String> schematic) {
        int maxX = schematic.getFirst().length();
        List<Integer> h = new ArrayList<>();

        for (int x = 0; x < maxX; x++) {
            int y = 0;
            while (schematic.get(y).charAt(x) == '#') {
                y++;
            }
            h.add(y - 1);
        }
        return h;
    }

    @Override
    public String toString() {
        return heights.toString();
    }
}

class Key {
    List<Integer> heights;
    public Key(List<String> schematic) {
        this.heights = generateHeights(schematic);
    }
    private List<Integer> generateHeights(List<String> schematic) {
        int maxX = schematic.getFirst().length();
        int maxY = schematic.size();
        List<Integer> h = new ArrayList<>();

        for (int x = 0; x < maxX; x++) {
            int y = 0;
            while (schematic.get(y).charAt(x) != '#') {
                y++;
            }
            h.add(maxY - y - 1);
        }
        return h;
    }

        @Override
    public String toString() {
        return heights.toString();
    }
}

public class Part1 {
    static Set<Lock> lockSet = new HashSet<>();
    static Set<Key> keySet = new HashSet<>();
    static int maxHeight;

    public static String getType(List<String> currentBlock) throws Exception {
        if ("#####".equals(currentBlock.getFirst()) && ".....".equals(currentBlock.getLast())) return "lock";
        if (".....".equals(currentBlock.getFirst()) && "#####".equals(currentBlock.getLast())) return "key";
        throw new IllegalArgumentException("Current Block is not a valid lock or key:\n" + currentBlock.toString());
    }

    public static void addBlock(List<String> currentBlock) throws Exception {
        String schematicType = getType(currentBlock);
        if (schematicType.equals("key")) {
            keySet.add(new Key(currentBlock));
        } else { // schematicType.equals("lock")
            lockSet.add(new Lock(currentBlock));
        }
    }

    public static void parseInput(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        List<String> currentBlock = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                addBlock(currentBlock);
                currentBlock.clear();
            } else {
                currentBlock.add(line);
            }
        }
        addBlock(currentBlock);
        maxHeight = currentBlock.size() - 2; // Both lock + key are 0-indexed
    }

    public static int generateCombinations() {
        int combinations = 0;
        for (Lock l: lockSet) {
            outer:
            for (Key k: keySet) {
                for (int i = 0; i < 5; i++) {
                    if (l.heights.get(i) + k.heights.get(i) > maxHeight) continue outer;
                }
                combinations++;
            }
        }
        return combinations;
    }

    public static void main(String[] args) throws Exception {
        // parseInput("small_input.txt");
        parseInput("input.txt");
        int combinations = generateCombinations();
        System.err.println("Total combinations: " + combinations);
    }
}
