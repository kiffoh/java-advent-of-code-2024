
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    static String input = readInput();

    static Map<String, Set<String>> connections = new HashMap<>();

    static Set<String> interconnectedComputers = new HashSet<>();

    public static void addConnection(String curr, String other) {
        connections.computeIfAbsent(curr, k -> new HashSet<String>()).add(other);
    }

    public static void createConnections(String[] parsed) {
        for (String connection: parsed) {
            String[] computers = connection.split("-");
            for (int curr = 0; curr < computers.length; curr++) {
                int other = (curr+1) % computers.length;
                addConnection(computers[curr], computers[other]);
            }
        }
    }

    public static String[] parse(String input) {
        return input.trim().split("\n");
    }

    public static String createKey(String... args) {
        List<String> list = Arrays.asList(args);
        Collections.sort(list);
        return String.join(",", list);
    };

    public static Boolean unionValid(Set<String> union) {
        int initialSize = union.size();
        for (String key: union) {
            Set<String> newConnects = connections.get(key);
            union.retainAll(newConnects);
            if (union.size() != initialSize) return false;
        }

        return true;
    };

    public static String locateConnected() {
        Set<String> visited = new HashSet<>();
        int maxConnections = 0;
        String result = "";
        for (Map.Entry<String, Set<String>> set1 : connections.entrySet()) {
            String computer1 = set1.getKey();
            Set<String> connects1 = set1.getValue();
            connects1.add(computer1);
            for (Map.Entry<String, Set<String>> set2 : connections.entrySet()) {
                String computer2 = set2.getKey();
                String key = createKey(computer1, computer2);
                if (visited.contains(key) || computer1.equals(computer2)) continue;

                // Make a copy as retainAll mutates the set in place
                Set<String> union = new HashSet<>(connects1);
                Set<String> connects2 = set2.getValue();
                connects2.add(computer2);            
                union.retainAll(connects2);

                if (union.size() > maxConnections && unionValid(union)) {
                    maxConnections = union.size();
                    result = createKey(union.stream().toArray(String[]::new));
                }
                visited.add(key);
            }
        }
        return result;
    }

    public static Set<String> filterConnected() {
        return interconnectedComputers.stream().filter(x -> {
            String[] arr = x.split(",");
            for (String comp: arr) {
                if (comp.startsWith("t")) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        String[] parsed = parse(input);
        createConnections(parsed);
        String result = locateConnected();
        System.err.println("result: " + result);
    }
}

