
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Part1 {
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

    public static String createKey(String c1, String c2, String c3) {
        List<String> list = Arrays.asList(c1, c2, c3);
        Collections.sort(list);
        return String.join(",", list);
    }

    public static void locateConnected() {
        for (Map.Entry<String, Set<String>> entry : connections.entrySet()) {
            String computer = entry.getKey();
            Set<String> connects = entry.getValue();

            for (String i: connects) {
                Set<String> otherConnects = connections.get(i);
                // Needs to have at least 2 connections to make three inter-connected computers
                if (otherConnects.size() < 2) {
                    continue;
                }

                for (String j: connects) {
                    if (i.equals(j)) continue;
                    String key = createKey(computer, i, j);
                    if (interconnectedComputers.contains(key)) continue;

                    if (otherConnects.contains(j)) {
                        interconnectedComputers.add(key);
                    }
                }
            }
        }
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
        locateConnected();
        Set<String> filtered = filterConnected();

        // System.err.println("aa" + connections.get("aa"));
        // System.err.println("\nai" + connections.get("ai"));
    
        // List arr = new ArrayList<>(filtered);
        // Collections.sort(arr);
        // System.err.println("\nsorted:" + arr);
        System.err.println("sets of three inter-connected computers that start with a 't':" + filtered.toString());
        System.err.println("\nsize:" + filtered.size());
    }
}

// 2311 - too high
