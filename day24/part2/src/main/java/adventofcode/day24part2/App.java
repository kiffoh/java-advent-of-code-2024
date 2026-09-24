package adventofcode.day24part2;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import static guru.nidi.graphviz.model.Factory.*;
import guru.nidi.graphviz.model.Graph;

public class App {
    private static final String INPUT_PATH = "../input.txt";

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
        String relationship;

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

        public void addParents(List<Wire> parents, String parentCalc, String relationship) {
            this.parents = parents;
            this.parentCalc = parentCalc;
            this.relationship = relationship;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public String printFullWire() {
            String output = this.name;
            if (this.parents != null) {
                output += ": " + parents.toString();
            } else {
                output += ": []";
            }
            return output;
        }
    }

    static String input = readInput();
    static Map<String, Wire> wires = new HashMap<>();

    public static void generateWires(String[] gateRelationships) {
        for (String relationship: gateRelationships) {
            String[] parts = relationship.split(" ");

            // 0, 2, 4 are the indexes corresponding to the wires in the relationships
            Wire parent1 = wires.computeIfAbsent(parts[0].trim(), k -> new Wire(null, parts[0].trim()));
            Wire parent2 = wires.computeIfAbsent(parts[2].trim(), k -> new Wire(null, parts[2].trim()));
            List<Wire> parents = new ArrayList<>(List.of(parent1, parent2));
            Wire child = wires.computeIfAbsent(parts[4].trim(), k -> new Wire(null, parts[4].trim()));
            child.addParents(parents, parts[1].trim(), relationship);
        }
    }

    public static void assignInitialValues(String[] initialValues) {
        for (String initialisation: initialValues) {
            String[] parts = initialisation.split(": ");
            Wire wire = wires.get(parts[0].trim());
            initialSet.add(wire);
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
        functions.put("AND", App::AND);
        functions.put("OR", App::OR);
        functions.put("XOR", App::XOR);
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
    // public static void calculateValues(Wire wire, AtomicBoolean solutionIncorrect) {
    public static void calculateValues(Wire wire) {
        // System.err.println(wire + "\n");
        // if (wire.parents == null || solutionIncorrect.get() == true) return;
        if (wire.parents == null) return;
        
        // If it has parents, make sure the parents values are calculated
        for (Wire parent: wire.parents) calculateValues(parent);

        Wire parent1 = wire.parents.get(0);
        Wire parent2 = wire.parents.get(1);
        // Operand
        String func = wire.parentCalc;

        // Simulation
        wire.value = functions.get(func).apply(parent1.value, parent2.value);
        // if (wire.name.startsWith("z")) {
        //     solutionIncorrect.set(binaryOutput.get(wire.name).equals(wire.value));
        //     if (solutionIncorrect.get() == true) return;
        // }
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

    public static String[] swapGates(String[] gatesToSwap) {
        String[] LHS = new String[8];
        String[] RHS = new String[8];
        for (int i = 0; i < 8; i++) {
            String[] parts = gatesToSwap[i].split(" -> ");
            LHS[i] = parts[0].trim();
            RHS[(i + 1)%4] = parts[1].trim();
        }
        String[] swappedGates = new String[4];
        for (int i = 0; i < 8; i++) {
            swappedGates[i] = LHS[i] + " -> " + RHS[i];
        }
        return swappedGates;
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

    public static String getZValuesAsBinary(Boolean print) {
        if (print) {
            printZValuesAsList();
        }
        String binary =  wires.entrySet().stream()
        .filter(e -> e.getKey().startsWith("z"))
        .sorted(Map.Entry.comparingByKey((a, b) -> b.compareTo(a)))
        .map(e -> e.getValue().value.toString())
        .collect(Collectors.joining());
        if (print) System.err.println("Binary: " + binary);
        return binary;
    }

    public static BigInteger getResult(Boolean print) {
        String binary = getZValuesAsBinary(print);
        return new BigInteger(binary, 2);
    }

    public static String convertArrayToBinaryString(String[] input, String filterLetter) {
        return Arrays.stream(input)
        .filter(val -> val.startsWith(filterLetter))
        .map(val -> val.split(": "))
        .sorted(Comparator.comparing((String[] arr) -> arr[0]).reversed())
        .map(arr -> arr[1].trim())
        .collect(Collectors.joining());
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

    static Map<String, Integer> expectedMap = new HashMap<>();

    public static void convertBinaryToMap(String binaryString, Boolean print) {
        int wireNumber = binaryString.length() - 1;
        int digits = getDigits(binaryString);
        for (String num: binaryString.split("")) {
            String key = getString(wireNumber, digits);
            expectedMap.put(key, Integer.valueOf(num));
            wireNumber--;
        }
        if (print) System.err.println("Map: " + expectedMap);
    }

    public static String getBinaryOutput(String[] initialValues, Boolean print) {
        String xValue = convertArrayToBinaryString(initialValues, "x");
        String yValue = convertArrayToBinaryString(initialValues, "y");
        BigInteger x = new BigInteger(xValue, 2);
        BigInteger y = new BigInteger(yValue, 2);
        BigInteger sum = x.add(y);
        String binaryOutput = sum.toString(2);
        convertBinaryToMap(binaryOutput, print);
        return binaryOutput;
    }

    public static String[] reverse(String input) {
        String[] rev = new String[input.length()];
        int index = input.length() - 1;
        for (String num: input.split("")) {
            rev[index] = num;
            index--;
        }
        return rev;
    }

    public static Set<String> incorrectZValues = new HashSet<>();

    public static void printDifferences() {
        for (int i = 0; i < 46; i++) {
            String key = getString(i, 2);
            Integer actual = actualMap.get(key);
            Integer expected = expectedMap.get(key);

            if (!actual.equals(expected)) {
                incorrectZValues.add(key);
                System.err.println(key + " is " + actual + " and should be " + expected);
            }
        }
    }
    public static void getIncorrectZValues() {
        for (int i = 0; i < 46; i++) {
            String key = getString(i, 2);
            Integer actual = actualMap.get(key);
            Integer expected = expectedMap.get(key);

            if (!actual.equals(expected)) {
                incorrectZValues.add(key);
            }
        }
    }

    static Map<String, Integer> actualMap = new HashMap<>();

    public static void populateActualMap(String binaryOutput) {
        int index = 45;
        for (String digit: binaryOutput.split("")) {
            String key = getString(index, 2);
            actualMap.put(key, Integer.parseInt(digit));
            index--;
        }
    }


    public static Set<String> getRelationships(Wire wire, int depth, int maxDepth) {
        if (depth == maxDepth) return new HashSet<>();

        Set<String> allRelationships = new HashSet<>();
        String currRelationship = wire.parents.get(0).name + " " + wire.parentCalc + " " + wire.parents.get(1).name + " -> " + wire.name;
        allRelationships.add(currRelationship);

        for (Wire parent: wire.parents) {
            if (parent.parents != null) {
                allRelationships.addAll(getRelationships(parent, depth + 1, maxDepth));
            }
        }

        return allRelationships;
    }
    public static Set<Wire> identifyZRelationshipsToSwap() {
        Set<Wire> nonXORZRelationships = new HashSet<>();

        for (String key: incorrectZValues) {
            Wire incorrectWire = wires.get(key);
            if (!incorrectWire.parentCalc.equals("XOR")) {
                nonXORZRelationships.add(incorrectWire);
            }
        }
        return nonXORZRelationships;
    }

    public static Set<Wire> getParentRelationship(Wire wire) {
        Set<Wire> allRelationships = new HashSet<>();
        for (Wire parent: wire.parents) {
            if (parent.parents != null) {
                allRelationships.add(parent);
            }
        }

        return allRelationships;
    }

    public static Set<String> combinationsTried = new HashSet<>();

    public static void addParentWires(Set<Wire> parentWires, Map<String, Set<Wire>> relationshipsToSwap) {
        Set<Wire> XOROutputs = parentWires.stream()
            .filter(parent -> (parent.parentCalc != null && parent.parentCalc.equals("XOR")))
            .collect(Collectors.toCollection(HashSet::new));
        if (XOROutputs.isEmpty()) relationshipsToSwap.computeIfAbsent("remaining", k -> new HashSet<>()).addAll(parentWires);
        else {
            Set<Wire> otherOutputs = parentWires.stream()
                .filter(parent -> (parent.parentCalc != null && !parent.parentCalc.equals("XOR")))
                .collect(Collectors.toCollection(HashSet::new));
            relationshipsToSwap.computeIfAbsent("remaining", k -> new HashSet<>()).addAll(otherOutputs);
            relationshipsToSwap.computeIfAbsent("XOR", k -> new HashSet<>()).addAll(XOROutputs);
        }
    }

    public static List<List<Wire>> identifySwapPartners(Set<Wire> nonXORZRelationships, String[] gateRelationships) {
        List<List<Wire>> zWiresToSwap = new ArrayList<>();
        Map<String, Wire> idealRelationshipLHS = new HashMap<>();
        for (Wire w: nonXORZRelationships) {
            String ideal = w.parents.get(0) + " XOR " + w.parents.get(1);
            idealRelationshipLHS.put(ideal, w);
        }
        for (String rel: gateRelationships) {
            String[] parts = rel.split(" -> ");
            for (Entry<String, Wire> entry: idealRelationshipLHS.entrySet()) {
                String key = entry.getKey();
                if (key.contains(parts[0])) {
                    Wire initialOutput = wires.get(parts[1]);
                    zWiresToSwap.add(Arrays.asList(initialOutput, entry.getValue()));
                }
            }
        }
        return zWiresToSwap;
        // System.err.println(zWiresToSwap);
    }

    public static void getRelationshipsToSwap(String[] gateRelationships, Boolean print) {
        Map<String, Set<Wire>> relationshipsToSwap = new HashMap<>();
        relationshipsToSwap.put("zWires", identifyZRelationshipsToSwap());
        Set<Wire> nonXORZRelationships = identifyZRelationshipsToSwap();

        identifySwapPartners(nonXORZRelationships, gateRelationships);
        
        for (String key: incorrectZValues) {
            Wire incorrectWire = wires.get(key);
            Set<Wire> parentWires = getParentRelationship(incorrectWire);
            addParentWires(parentWires, relationshipsToSwap);
        }

        if (print) {
            System.out.println("Total relationships: " + gateRelationships.length);
            int totalRelationships = relationshipsToSwap.values().stream().mapToInt(Set::size).sum();
            System.out.println("Relationships to swap: " + totalRelationships);
            System.out.println((totalRelationships * 100 / gateRelationships.length) + "%");

            for (Map.Entry<String, Set<Wire>> entry : relationshipsToSwap.entrySet()) {
                String category = entry.getKey();
                Set<Wire> values = entry.getValue();
                System.err.println(category);
                for (Wire w: values) {
                    System.err.println("\t" + w.parents.get(0).name + " " + w.parentCalc + " " + w.parents.get(1).name + " -> " + w.name);
                }
            }

            System.err.println("all XOR relationships: " + wires.values().stream().filter(wire -> wire.parentCalc != null && wire.parentCalc.equals("XOR")).collect(Collectors.counting()));
        }
    }

    public static String getActualOutputAsString(Boolean print) {
        BigInteger actual = getResult(print);
        String actualString = actual.toString(2);
        populateActualMap(actualString);
        getIncorrectZValues();
        return actualString;
    }

    public static void printOutput(String actualString) {
        long expectedOnes = Arrays.stream(expectedBinaryOutput.split("")).filter("1"::equals).count();
        long actualOnes = Arrays.stream(actualString.split("")).filter("1"::equals).count();
        long expectedZeros = Arrays.stream(expectedBinaryOutput.split("")).filter("0"::equals).count();
        long actualZeros = Arrays.stream(actualString.split("")).filter("0"::equals).count();
        System.err.println("Desired and result match frequency of characters = " + (expectedOnes == actualOnes && expectedZeros == actualZeros));
        System.err.println("expected: " + expectedBinaryOutput + ". 1: " + expectedOnes + ", 0: " + expectedZeros);
        System.err.println("actual:   " + actualString+ ". 1: " + actualOnes + ", 0: " + actualZeros);
        printDifferences();
    }

    static String expectedBinaryOutput;

    public static void createGraphViz(String name) throws Exception {
        Graph g = graph("example").directed();

        for (Wire w: wires.values()) {
            List<Wire> parents = w.parents;
            if (parents != null) {
                for (Wire parent: parents) {
                    g = g.with(
                        node(parent.name)
                            .link(to(node(w.name))
                                // .with(Label.of(w.parentCalc))));
                                .with(attr("xlabel",w.parentCalc))));
                }
            }
        }   
        
        Graphviz.fromGraph(g).width(1200).render(Format.SVG).toFile(new File(name));
        System.out.println("Graph image generated!");

    }

    static Set<Wire> initialSet = new HashSet<>();

    public static List<String> resetWires() {
        List<String> newRelationships = new ArrayList<>();
        for (Wire w: wires.values()) {
            if (initialSet.contains(w)) continue;
            w.value = null;
            w.seen = false;
            newRelationships.add(w.parents.get(0) + " " + w.parentCalc + " " + w.parents.get(1) + " -> " + w.name);
        }
        return newRelationships;
    }

    public static List<String> swapRelationships(String[] gateRelationships) {
        // Identify relationships to swap
        // List<List<Wire>> zWiresToSwap = identifySwapPartners(identifyZRelationshipsToSwap(), gateRelationships);
        List<List<Wire>> zWiresToSwap = new ArrayList<>();
        Wire z15 = wires.get("z15");
        Wire fph = wires.get("fph");

        Wire z21 = wires.get("z21");
        Wire gds = wires.get("gds");

        Wire wrk = wires.get("wrk");
        Wire jrs = wires.get("jrs");

        Wire z34 = wires.get("z34");
        Wire cqk = wires.get("cqk");

        zWiresToSwap.add(Arrays.asList(fph, z15));
        zWiresToSwap.add(Arrays.asList(gds, z21));
        zWiresToSwap.add(Arrays.asList(wrk, jrs));
        zWiresToSwap.add(Arrays.asList(cqk, z34));

        String output = zWiresToSwap.stream()
        .flatMap(List::stream)
        .map(w -> w.name)
        .sorted()
        .collect(Collectors.joining(",")); 
        System.err.println("Swapping the following relationships:" + output);

        // Swap them
        for (List<Wire> pair : zWiresToSwap) {
            Wire w1 = pair.get(0);
            Wire w2 = pair.get(1);

            // Save originals
            List<Wire> tempParents = w1.parents;
            String tempCalc = w1.parentCalc;

            // Swap fields
            w1.parents = w2.parents;
            w1.parentCalc = w2.parentCalc;

            w2.parents = tempParents;
            w2.parentCalc = tempCalc;
        }

        // Re-simulate
        List<String> newRelationships = resetWires();
        return newRelationships;
    }

    public static void main(String[] args) throws Exception {
        System.err.println("COMPILED");
        String[] split = splitInput(input);
        String[] initialValues = parse(split[0]);
        String[] gateRelationships = parse(split[1]);
        Boolean printBinaryInformation = true;
        Boolean printRelationshipsToSwap = true;

        initialiseWires(initialValues, gateRelationships);
        expectedBinaryOutput = getBinaryOutput(initialValues, printBinaryInformation);
        initialiseFunctions();
        simulateRelationships(gateRelationships);
        String actual = getActualOutputAsString(printBinaryInformation);
        if (printBinaryInformation) printOutput(actual);

        getRelationshipsToSwap(gateRelationships, printRelationshipsToSwap);
        createGraphViz("initialGraph.svg");
        List<String> newRelationships = swapRelationships(gateRelationships);
        simulateRelationships(newRelationships.toArray(new String[0]));
        String newActual = getActualOutputAsString(true);
        printOutput(newActual);
        createGraphViz("finalGraph.svg");
        System.err.println("z15 parents: " + wires.get("z15").parents);
        System.err.println("z34 parents: " + wires.get("z34").parents);
        System.err.println("z21 parents: " + wires.get("z21").parents);
        System.err.println("dwm val: " + wires.get("dwm").value);
    }
}
