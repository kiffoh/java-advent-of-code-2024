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

    static String input = """
    x00: 1
    x01: 1
    x02: 0
    x03: 0
    x04: 0
    x05: 1
    x06: 0
    x07: 1
    x08: 1
    x09: 0
    x10: 1
    x11: 0
    x12: 0
    x13: 0
    x14: 1
    x15: 1
    x16: 1
    x17: 1
    x18: 1
    x19: 0
    x20: 0
    x21: 0
    x22: 0
    x23: 0
    x24: 0
    x25: 1
    x26: 0
    x27: 0
    x28: 0
    x29: 0
    x30: 0
    x31: 1
    x32: 0
    x33: 0
    x34: 1
    x35: 0
    x36: 0
    x37: 1
    x38: 0
    x39: 1
    x40: 1
    x41: 0
    x42: 0
    x43: 0
    x44: 1
    y00: 1
    y01: 0
    y02: 1
    y03: 1
    y04: 0
    y05: 0
    y06: 1
    y07: 1
    y08: 0
    y09: 1
    y10: 1
    y11: 1
    y12: 1
    y13: 0
    y14: 1
    y15: 0
    y16: 0
    y17: 0
    y18: 0
    y19: 1
    y20: 0
    y21: 1
    y22: 1
    y23: 0
    y24: 1
    y25: 0
    y26: 1
    y27: 1
    y28: 0
    y29: 1
    y30: 1
    y31: 0
    y32: 1
    y33: 0
    y34: 0
    y35: 1
    y36: 1
    y37: 0
    y38: 0
    y39: 1
    y40: 0
    y41: 0
    y42: 0
    y43: 1
    y44: 1

    y24 XOR x24 -> jhj
    y00 AND x00 -> ksw
    rmj OR khc -> cqp
    y22 AND x22 -> pdf
    fsb AND thf -> brr
    y02 AND x02 -> jmb
    y07 AND x07 -> fpb
    x35 AND y35 -> mkv
    hgm XOR jvd -> z12
    hkv OR kmk -> gcm
    x13 AND y13 -> vnf
    tkg AND sjq -> pjh
    x38 XOR y38 -> bjr
    y23 XOR x23 -> wdg
    bct OR ggp -> chn
    hhw AND ccp -> snp
    fpp AND jdw -> cfb
    x21 AND y21 -> z21
    y36 AND x36 -> whb
    hpt XOR jkc -> z31
    fbs AND bjj -> fwm
    tgt OR nrg -> rkk
    x42 AND y42 -> kgq
    x25 AND y25 -> hkw
    kpt OR fgv -> bkq
    y04 AND x04 -> fnh
    x19 XOR y19 -> hwf
    y30 AND x30 -> wrk
    rcb OR prp -> bbr
    gds OR vqn -> fsh
    fpk XOR bkq -> z13
    y03 XOR x03 -> kpp
    wkh OR pmj -> tqk
    grk AND gpr -> bwv
    x33 AND y33 -> qwt
    gcm AND pfd -> rgn
    y12 XOR x12 -> hgm
    x06 XOR y06 -> fsb
    x40 XOR y40 -> gfd
    y28 XOR x28 -> vnn
    hdt XOR wcc -> z43
    gnp OR qgm -> ccp
    y36 XOR x36 -> pjs
    y14 XOR x14 -> shm
    y05 AND x05 -> vjt
    gwv XOR rkk -> z11
    cqp XOR psh -> z25
    y18 XOR x18 -> sgs
    hjm OR jrs -> hpt
    vjt OR swv -> thf
    fwm OR mkv -> nmb
    sgm XOR tsj -> z08
    tmr XOR kpp -> z03
    ccp XOR hhw -> fph
    x10 AND y10 -> tgt
    x26 AND y26 -> nkc
    cbg AND fph -> bsb
    chn XOR gfd -> z40
    hmn AND jkv -> ght
    rsj AND bbr -> ncn
    vmf AND vwd -> nrg
    brr OR wcw -> twm
    gsg XOR twm -> z07
    y09 XOR x09 -> sjq
    x24 AND y24 -> rmj
    pdf OR nns -> nsj
    y44 AND x44 -> wcr
    wrk XOR dwm -> z30
    gpv AND wjj -> swv
    y08 AND x08 -> jbk
    y17 AND x17 -> gmg
    x26 XOR y26 -> sjk
    vpj XOR dcm -> z42
    x29 AND y29 -> tqc
    x15 XOR y15 -> hhw
    x12 AND y12 -> fgv
    x10 XOR y10 -> vmf
    rwc OR nkc -> fpp
    vwd XOR vmf -> z10
    y32 AND x32 -> hbq
    x20 XOR y20 -> sbs
    x43 XOR y43 -> wcc
    y11 AND x11 -> gnb
    wqv XOR jvv -> z37
    cqk OR dfq -> bjj
    nsp XOR tqh -> gds
    fbs XOR bjj -> z35
    ksw AND kgn -> kmk
    x01 AND y01 -> hkv
    shm AND kds -> gnp
    x31 XOR y31 -> jkc
    gmm OR qbr -> ptm
    jkv XOR hmn -> z04
    x21 XOR y21 -> tqh
    sjk AND btt -> rwc
    ncn OR bck -> vpj
    x29 XOR y29 -> dcn
    y09 AND x09 -> drj
    ght OR fnh -> wjj
    tsj AND sgm -> vsq
    vpj AND dcm -> ttq
    vnf OR wmm -> kds
    y08 XOR x08 -> tsj
    rfw AND fsh -> nns
    x03 AND y03 -> npd
    bsb OR dcw -> msb
    gwv AND rkk -> srh
    hjq XOR pjj -> z39
    kgn XOR ksw -> z01
    gjd XOR dcn -> z29
    pjs XOR nmb -> z36
    fvv OR hkw -> btt
    x35 XOR y35 -> fbs
    khs AND sms -> qkf
    y00 XOR x00 -> z00
    ptm XOR smj -> z44
    y11 XOR x11 -> gwv
    y13 XOR x13 -> fpk
    nrd OR nss -> bbt
    jhj XOR tqk -> z24
    x42 XOR y42 -> dcm
    x33 XOR y33 -> grk
    x34 AND y34 -> dfq
    y43 AND x43 -> gmm
    x19 AND y19 -> fcw
    sgs AND qcf -> vsk
    y14 AND x14 -> qgm
    y44 XOR x44 -> smj
    gnb OR srh -> jvd
    hjq AND pjj -> ggp
    y16 AND x16 -> dcw
    wcr OR swb -> z45
    x39 XOR y39 -> hjq
    thf XOR fsb -> z06
    rgn OR jmb -> tmr
    y30 XOR x30 -> jrs
    pfd XOR gcm -> z02
    x05 XOR y05 -> gpv
    y31 AND x31 -> hqj
    smj AND ptm -> swb
    shm XOR kds -> z14
    wdg XOR nsj -> z23
    x32 XOR y32 -> sms
    y34 XOR x34 -> fcv
    mkc AND vnn -> jjs
    jkc AND hpt -> pct
    pjh OR drj -> vwd
    x06 AND y06 -> wcw
    wqv AND jvv -> nrd
    jbk OR vsq -> tkg
    sgs XOR qcf -> z18
    vsk OR hpw -> mch
    snp OR mnh -> z15
    ksm AND fcv -> z34
    gsg AND twm -> nfb
    x04 XOR y04 -> jkv
    y27 XOR x27 -> jdw
    mkc XOR vnn -> z28
    y25 XOR x25 -> psh
    rjb OR tqc -> dwm
    sjk XOR btt -> z26
    fcw OR nsq -> fjh
    y37 XOR x37 -> wqv
    y41 AND x41 -> bck
    y02 XOR x02 -> pfd
    dcn AND gjd -> rjb
    y40 AND x40 -> rcb
    jhj AND tqk -> khc
    fsh XOR rfw -> z22
    y22 XOR x22 -> rfw
    fng OR gmg -> qcf
    hqj OR pct -> khs
    fjh XOR sbs -> z20
    y17 XOR x17 -> ckk
    y07 XOR x07 -> gsg
    fpk AND bkq -> wmm
    mch AND hwf -> nsq
    x20 AND y20 -> frw
    whb OR jbc -> jvv
    mch XOR hwf -> z19
    bbr XOR rsj -> z41
    kgq OR ttq -> hdt
    jjs OR nbd -> gjd
    x38 AND y38 -> cjr
    y39 AND x39 -> bct
    chn AND gfd -> prp
    wrk AND dwm -> hjm
    ckk AND msb -> fng
    sms XOR khs -> z32
    y37 AND x37 -> nss
    bwv OR qwt -> ksm
    fjh AND sbs -> pvq
    ksm XOR fcv -> cqk
    x18 AND y18 -> hpw
    cqp AND psh -> fvv
    pvq OR frw -> nsp
    fcc OR npd -> hmn
    y27 AND x27 -> pks
    kpp AND tmr -> fcc
    fpb OR nfb -> sgm
    x41 XOR y41 -> rsj
    wdg AND nsj -> wkh
    y28 AND x28 -> nbd
    pks OR cfb -> mkc
    y23 AND x23 -> pmj
    msb XOR ckk -> z17
    tqh AND nsp -> vqn
    chp OR cjr -> pjj
    hgm AND jvd -> kpt
    x15 AND y15 -> mnh
    nmb AND pjs -> jbc
    bbt AND bjr -> chp
    jdw XOR fpp -> z27
    x01 XOR y01 -> kgn
    x16 XOR y16 -> cbg
    wjj XOR gpv -> z05
    bjr XOR bbt -> z38
    cbg XOR fph -> z16
    hbq OR qkf -> gpr
    grk XOR gpr -> z33
    sjq XOR tkg -> z09
    hdt AND wcc -> qbr
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
