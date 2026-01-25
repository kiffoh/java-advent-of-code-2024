import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Gate {
    String input1, input2, output, type;
    public Gate(String input1, String input2, String output, String type) {
        this.input1 = input1;
        this.input2 = input2;
        this.output = output;
        this.type = type;
    }
    public Gate copy() {
        return new Gate(input1, input2, output, type);
    }
}

public class CrossedWiresPart2 {

    static void simulateGates(Map<String, Boolean> wires, List<Gate> gates) {
        Set<Gate> unresolved = new HashSet<>(gates);
        while (!unresolved.isEmpty()) {
            Iterator<Gate> it = unresolved.iterator();
            while (it.hasNext()) {
                Gate g = it.next();
                if (wires.containsKey(g.input1) && wires.containsKey(g.input2)) {
                    boolean a = wires.get(g.input1);
                    boolean b = wires.get(g.input2);
                    boolean res = false;
                    switch (g.type) {
                        case "AND": res = a && b; break;
                        case "OR":  res = a || b; break;
                        case "XOR": res = a ^ b; break;
                    }
                    wires.put(g.output, res);
                    it.remove();
                }
            }
        }
    }

    static BigInteger wiresToNumber(Map<String, Boolean> wires, String prefix) {
        List<String> bitWires = new ArrayList<>();
        for (String w : wires.keySet())
            if (w.startsWith(prefix)) bitWires.add(w);
        bitWires.sort(Comparator.comparingInt(s -> Integer.parseInt(s.replaceAll("\\D",""))));
        StringBuilder sb = new StringBuilder();
        for (int i = bitWires.size()-1; i>=0; i--)
            sb.append(wires.get(bitWires.get(i)) ? '1' : '0');
        return new BigInteger(sb.toString(), 2);
    }

    static boolean checkSum(Map<String, Boolean> wires, BigInteger sum) {
        List<String> zWires = new ArrayList<>();
        for (String w : wires.keySet()) if (w.startsWith("z")) zWires.add(w);
        zWires.sort(Comparator.comparingInt(s -> Integer.parseInt(s.replaceAll("\\D",""))));
        String sumBin = sum.toString(2);
        while (sumBin.length() < zWires.size()) sumBin = "0" + sumBin;
        for (int i=0;i<zWires.size();i++){
            boolean actual = wires.get(zWires.get(i));
            boolean expected = sumBin.charAt(zWires.size()-1-i)=='1';
            if (actual != expected) return false;
        }
        return true;
    }

    // Find gates that influence any z wire
    static Set<Gate> findRelevantGates(List<Gate> gates) {
        Map<String, List<Gate>> inputToGates = new HashMap<>();
        for (Gate g : gates) {
            inputToGates.computeIfAbsent(g.input1, k->new ArrayList<>()).add(g);
            inputToGates.computeIfAbsent(g.input2, k->new ArrayList<>()).add(g);
        }
        Set<String> relevantWires = new HashSet<>();
        for (Gate g : gates)
            if (g.output.startsWith("z")) relevantWires.add(g.output);

        Set<Gate> relevantGates = new HashSet<>();
        Queue<String> queue = new LinkedList<>(relevantWires);
        while (!queue.isEmpty()) {
            String w = queue.poll();
            for (Gate g : gates) {
                if (!relevantGates.contains(g) && (g.output.equals(w))) {
                    relevantGates.add(g);
                    if (!relevantWires.contains(g.input1)) { relevantWires.add(g.input1); queue.add(g.input1);}
                    if (!relevantWires.contains(g.input2)) { relevantWires.add(g.input2); queue.add(g.input2);}
                }
            }
        }
        return relevantGates;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        Map<String, Boolean> wires = new HashMap<>();
        List<Gate> gates = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            if (line.contains(":")) {
                String[] parts = line.split(":");
                wires.put(parts[0].trim(), parts[1].trim().equals("1"));
            } else if (line.contains("->")) {
                String[] parts = line.split(" -> ");
                String[] inParts = parts[0].trim().split(" ");
                gates.add(new Gate(inParts[0], inParts[2], parts[1].trim(), inParts[1]));
            }
        }

        BigInteger x = wiresToNumber(wires, "x");
        BigInteger y = wiresToNumber(wires, "y");
        BigInteger sum = x.add(y);

        List<Gate> relevantGates = new ArrayList<>(findRelevantGates(gates));
        List<String> swappedWires = new ArrayList<>();

        // Only try swaps among relevant gates
        for (int i = 0; i < relevantGates.size(); i++) {
            for (int j = i + 1; j < relevantGates.size(); j++) {
                List<Gate> gatesCopy = new ArrayList<>();
                for (Gate g : gates) gatesCopy.add(g.copy());

                // Swap outputs of relevant gates i and j
                Gate gi = gatesCopy.get(gates.indexOf(relevantGates.get(i)));
                Gate gj = gatesCopy.get(gates.indexOf(relevantGates.get(j)));
                String temp = gi.output;
                gi.output = gj.output;
                gj.output = temp;

                Map<String, Boolean> wiresCopy = new HashMap<>(wires);
                simulateGates(wiresCopy, gatesCopy);
                if (checkSum(wiresCopy, sum)) {
                    swappedWires.add(relevantGates.get(i).output);
                    swappedWires.add(relevantGates.get(j).output);
                    if (swappedWires.size() == 8) break;
                }
            }
            if (swappedWires.size() == 8) break;
        }

        Collections.sort(swappedWires);
        System.out.println(String.join(",", swappedWires));
    }
}
