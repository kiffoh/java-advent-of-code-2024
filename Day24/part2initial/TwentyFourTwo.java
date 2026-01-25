
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

class TwentyFourTwo {
    static class Wire {
        Integer value;
        String name;
        List<Wire> parents = new ArrayList<>();
        String parentCalc;

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

    static String input = """
    x00: 0
    x01: 1
    x02: 0
    x03: 1
    x04: 0
    x05: 1
    y00: 0
    y01: 0
    y02: 1
    y03: 1
    y04: 0
    y05: 1

    x00 AND y00 -> z05
    x01 AND y01 -> z02
    x02 AND y02 -> z01
    x03 AND y03 -> z03
    x04 AND y04 -> z04
    x05 AND y05 -> z00
    """;

    static Map<String, Wire> wires = new HashMap<>();

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

    static Set<Wire> knownWires = new HashSet<>();

    public static void getAllKnownWires() {
        for (Wire wire : wires.values()) {
            if (wire.parents == null) knownWires.add(wire);
        }

    }

    public static void initialiseWires(String[] initialValues, String[] gateRelationships) {
        generateWires(gateRelationships);
        assignInitialValues(initialValues);
        getAllKnownWires();
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
        return (Integer.valueOf(1).equals(num1) || Integer.valueOf(1).equals(num2)) ? 1 : 0;
    }
    public static Integer XOR(Integer num1, Integer num2) {
        return (!Objects.equals(num1, num2)) ? 1 : 0;
    }

    public static void initialiseFunctions() {
        functions.put("AND", TwentyFourTwo::AND);
        functions.put("OR", TwentyFourTwo::OR);
        functions.put("XOR", TwentyFourTwo::XOR);
    }

    public static void calculateValue(Wire wire) {
        Wire parent1 = wire.parents.get(0);
        Wire parent2 = wire.parents.get(1);
        // Operand
        String func = wire.parentCalc;

        // Simulation
        
        wire.value = functions.get(func).apply(parent1.value, parent2.value);
    }

    public static Boolean bothParentsKnown(Wire curr) {
        for (Wire parent: curr.parents) {
            if (!knownWires.contains(parent)) return false;
        }
        return true;
    }

    public static void simulateRelationships(String[] gateRelationships) {
        Queue<String[]> queue = new ArrayDeque<>();
        for (String relationship: gateRelationships) {
            queue.add(relationship.split(" "));
        }
        while (!queue.isEmpty()) {
            String[] currentRelationship = queue.poll();
            Wire wire = wires.get(currentRelationship[4].trim());
            if (bothParentsKnown(wire)) {
                // System.err.println("All known:\n" + Arrays.toString(currentRelationship));
                calculateValue(wire);
                knownWires.add(wire);
            } else {
                // add to back of queue if value not known
                // System.err.println("\nNOT known:\n" + Arrays.toString(currentRelationship));
                queue.add(currentRelationship);
            }
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

    public static String[] convertArrayToBinaryString(String[] input, String filterLetter) {
        return Arrays.stream(input)
        .filter(val -> val.startsWith(filterLetter))
        .map(val -> val.split(": "))
        .sorted(Comparator.comparing((String[] arr) -> arr[0]).reversed())
        .map(arr -> arr[1].trim())
        .toArray(String[]::new);
    }

    public static int getDigits(String binaryString) {
        int digits = 1;
        int length = binaryString.length();
        while (length > 10) {
            length /= 10;
            digits += 1;
        }
        return digits < 2 ? 2 : digits;
    }

    public static String getString(int number, int digits) {
        String str = String.valueOf(number);
        while (str.length() < digits) {
            str = "0" + str;
        }
        return "z" + str;
    }

    static Map<String, Integer> binaryOutput = new HashMap<>();

    public static void convertBinaryToMap(String binaryString) {
        int wireNumber = binaryString.length() - 1;
        int digits = getDigits(binaryString);
        for (String num: binaryString.split("")) {
            String key = getString(wireNumber, digits);
            binaryOutput.put(key, Integer.valueOf(num));
            wireNumber--;
        }
        System.err.println("Map: " + binaryOutput);
    }

    public static String getBinaryOutput(String[] initialValues) {
        String[] xValue = convertArrayToBinaryString(initialValues, "x");
        String[] yValue = convertArrayToBinaryString(initialValues, "y");
        String zValue = "";
        for (int i = 0; i < xValue.length; i++) {
            zValue += AND(Integer.parseInt(xValue[i]), Integer.parseInt(yValue[i])).toString();
        }
        convertBinaryToMap(zValue);
        return zValue;
    }

    public static void main(String[] args) {
        String[] split = splitInput(input);
        String[] initialValues = parse(split[0]);
        String[] gateRelationships = parse(split[1]);

        initialiseWires(initialValues, gateRelationships);
        String desired = getBinaryOutput(initialValues);
        System.err.println("Binary: " + desired);
        initialiseFunctions();
        simulateRelationships(gateRelationships);
        BigInteger result = getResult();
        System.err.println("Top result: ");
        System.err.println("Result: " + result);
    }
}

// 60191023293584 - too high
// 60191023293584
