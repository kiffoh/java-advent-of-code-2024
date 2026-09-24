
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
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

    static class Wire {
        Integer value;
        String name;
        List<Wire> parents = new ArrayList<>();
        String parentCalc;
        Boolean seen = false;

        public Wire(Integer value, String name) {
            this.value = value;
            this.parents = null;
            this.name = name;
        }
        public Wire(Integer value, String name, List<Wire> parents, String parentCalc) {
            this.value = value;
            this.name = name;
            this.parents = parents;
            this.parentCalc = parentCalc;
        }

        public void addParents(List<Wire> parents, String parentCalc) {
            this.parents = parents;
            this.parentCalc = parentCalc;
        }

        @Override
        public String toString() {
            String output = this.name;
            if (this.parents != null) {
                output += ": " + parents.toString();
            } else {
                output += ": []";
            }
            return output;
        }
    }

    static Map<String, Wire> wires = new HashMap<>();

    static String input = readInput();

    public static void generateWires(String[] gateRelationships) {
        for (String relationship: gateRelationships) {
            String[] parts = relationship.split(" ");

            // 0, 2, 4 are the indexes corresponding to the wires in the relationships
            Wire parent1 = wires.computeIfAbsent(parts[0].trim(), k -> new Wire(null, parts[0].trim()));
            Wire parent2 = wires.computeIfAbsent(parts[2].trim(), k -> new Wire(null, parts[2].trim()));
            List<Wire> parents = new ArrayList<>(List.of(parent1, parent2));
            Wire child = wires.computeIfAbsent(parts[4].trim(), k -> new Wire(null, parts[4].trim()));
            child.addParents(parents, parts[1].trim());
        }
    }

    public static void assignInitialValues(String[] initialValues) {
        for (String initialisation: initialValues) {
            String[] parts = initialisation.split(": ");
            Wire wire = wires.get(parts[0].trim());
            wire.value = Integer.valueOf(parts[1].trim());
        }
    }

    public static void initialiseWires(String[] initialValues, String[] gateRelationships) {
        generateWires(gateRelationships);
        assignInitialValues(initialValues);
    }

    public static String[] splitInput(String input) {
        return input.split("\n\n");
    }

    public static String[] parse(String splitInput) {
        return splitInput.split("\n");
    }

    static Map<String, BiFunction<Integer, Integer, Integer>> functions = new HashMap<>();

    public static Integer AND(Integer num1, Integer num2) {
        return (Integer.valueOf(1).equals(num1) && Integer.valueOf(1).equals(num2)) ? 1 : 0;
    }
    public static Integer OR(Integer num1, Integer num2) {
        return (Objects.equals(num1, 1) || Objects.equals(num2, 1)) ? 1 : 0;
    }
    public static Integer XOR(Integer num1, Integer num2) {
        return (!Objects.equals(num1, num2)) ? 1 : 0;
    }

    public static void initialiseFunctions() {
        functions.put("AND", Part1::AND);
        functions.put("OR", Part1::OR);
        functions.put("XOR", Part1::XOR);
    }

    public static void calculateValues(Wire wire) {
        // System.err.println(wire + "\n");
        if (wire.parents == null) return;
        
        // If it has parents, make sure the parents values are calculated
        for (Wire parent: wire.parents) calculateValues(parent);

        Wire parent1 = wire.parents.get(0);
        Wire parent2 = wire.parents.get(1);
        // Operand
        String func = wire.parentCalc;

        // Simulation
        wire.value = functions.get(func).apply(parent1.value, parent2.value);
        wire.seen = true;
        // System.err.println(parent1.name + " " + func + " " + parent2.name + " -> " + wire.name);
        // System.err.println(parent1.value + " " + func + " " + parent2.value + " -> " + wire.value);
    }

    public static void simulateRelationships(String[] gateRelationships) {
        for (String relationship: gateRelationships) {
            String[] parts = relationship.split(" ");
            Wire wire = wires.get(parts[4].trim());
            if (wire.seen) continue;
            calculateValues(wire);
        }
    }

    public static void printZValuesAsList() {
        List<String> binaryList = wires.entrySet().stream()
        .filter(e -> e.getKey().startsWith("z"))
        .sorted(Map.Entry.comparingByKey((a, b) -> b.compareTo(a)))       
        .map(e -> {
            String key = e.getKey();
            Wire value = e.getValue();
            return key + ": " + value.value.toString();
        }).collect(Collectors.toList());
        System.err.println("binary List:");
        for (String element: binaryList) {
            System.err.println(element);
        }
    };

    public static String getZValuesAsBinary() {
        printZValuesAsList();
        String binary =  wires.entrySet().stream()
        .filter(e -> e.getKey().startsWith("z"))
        .sorted(Map.Entry.comparingByKey((a, b) -> b.compareTo(a)))
        .map(e -> e.getValue().value.toString())
        .collect(Collectors.joining());
        System.err.println("Binary: " + binary);
        return binary;
    }

    public static BigInteger getResult() {
        String binary = getZValuesAsBinary();
        return new BigInteger(binary, 2);
    }

    public static void main(String[] args) {
        String[] split = splitInput(input);
        String[] initialValues = parse(split[0]);
        String[] gateRelationships = parse(split[1]);

        initialiseWires(initialValues, gateRelationships);
        initialiseFunctions();
        simulateRelationships(gateRelationships);
        BigInteger result = getResult();
        System.err.println("Rec result: ");
        System.err.println("Result: " + result);
    }
}

// 60191023293584 - too high
