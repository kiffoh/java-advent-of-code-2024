
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Eight {
    static String inputString = """
    ............
    ........0...
    .....0......
    .......0....
    ....0.......
    ......A.....
    ............
    ............
    ........A...
    .........A..
    ............
    ............
    """;

    // static String inputString = """
    // ..........M..........j.............y.....O........
    // ...B...............q......m........lGO............
    // ....................q......2.l.GQ...O.............
    // .....X.......................................4....
    // .....................q............................
    // ....M......P...............xl.K.............2.....
    // ....F.........L.......C.K..............m..........
    // ..........FM......P....jy......m..........o...r...
    // ..X.......P.....RL..............G..x..........4...
    // ............L..........NC.....q...................
    // .....C.X...............K....y..........4..........
    // ........S...R.............j.x.....V...4...........
    // .....................R..x.....V..i......m.........
    // ...........................R.V......N.......X.....
    // .....F.........M......N......E....................
    // ................v................T.......F......O.
    // .............................N...V.......Q........
    // ...v.....................C.....i..................
    // ......c.....W..n.w........................E.......
    // 3...................c.....................Q..6....
    // ...........h......................j...............
    // .......n.0......h.................E..............2
    // .v.............7.......120.....c..................
    // ......n.0............w...........D.t.........E...r
    // ....8..3......0.w.hP....z...D..T...............r..
    // .................f........T........G......eQ......
    // ......f.n.....7..p................................
    // .....Y..7.......f......I......D......K............
    // ............Uf....T..W.....D..r...i...............
    // ......I...............................Z...........
    // ....5....B.......b..............s..............Z..
    // ..........d...W..Uwh.............c..........i.....
    // ..I.3..Y......................e...................
    // .....p.b..........k......7........................
    // p...........k....I..b..........s..................
    // .....k.......o...........W........................
    // .A..Y..........U.................a........6.......
    // ..A...Y.p...................................6.....
    // B......k..........................Z............u..
    // ...3.....................s..............a.........
    // ......A.........................g.....a...........
    // .......A....8...b.U......H....sS..................
    // .........................S1.............t.........
    // .....................9z..e.....5..1.g.u...........
    // .......................z....d....g....H.J....o.6..
    // ........B................d.....u....9.J.H.........
    // .8........S.................u9.............J.....H
    // .....................Z5.............t1...........a
    // .....................e..v...................o..t..
    // .....8...............L.....z.............J........
    // """;

    static char[][] inputArray;
    static char[][] antinodeArray;

    static int maxRows;
    static int maxCols;

    static Map<Character, List<Coordinate>> frequencyCoordinates = new HashMap<>();

    private static void convertInputStringToCharArray() {
        String[] splitLines = inputString.strip().split("\n");
        inputArray = new char[splitLines.length][];

        for (int i = 0; i < splitLines.length; i++) {
            inputArray[i] = splitLines[i].toCharArray();
        }
    }

    public static void readInputArray() {
        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {
                if (inputArray[row][col] != '.') {
                    char key = inputArray[row][col];

                    frequencyCoordinates
                            .computeIfAbsent(key, k -> new ArrayList<>())
                            .add(new Coordinate(row, col));
                }
            }
        }
    }

    public static void populateAntinodeArray() {
        Set<Coordinate> antinodeCoordinates = new HashSet<>();
        
        for (char key: frequencyCoordinates.keySet()) {
            List<Coordinate> coordinates = frequencyCoordinates.get(key);

            for (int i = 0; i < coordinates.size(); i++) {
                Coordinate a1 = coordinates.get(i);

                for (int j = i + 1; j < coordinates.size(); j++) {
                    Coordinate a2 = coordinates.get(j);
                    
                    Coordinate antinode1 = a1.antinodeTowards(a2);
                    if (antinode1.row < maxRows && antinode1.col < maxCols && Math.min(antinode1.row, antinode1.col) >= 0) antinodeCoordinates.add(antinode1);
                    
                    Coordinate antinode2 = a2.antinodeTowards(a1);
                    if (antinode2.row < maxRows && antinode2.col < maxCols && Math.min(antinode2.row, antinode2.col) >= 0) antinodeCoordinates.add(antinode2);
                }
            }
        }

        for (Coordinate antenna: antinodeCoordinates) {
            antinodeArray[antenna.row][antenna.col] = '#';
        }
    }

    public static void main(String[] args) {
        convertInputStringToCharArray();

        int totalAntinodes = 0;

        maxRows = inputArray.length;
        maxCols = inputArray[0].length;

        antinodeArray = new char[maxRows][maxCols];

        readInputArray();

        System.out.println("frequencyCoordinates: " + frequencyCoordinates);

        populateAntinodeArray();

        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {
                if (antinodeArray[row][col] == '#') totalAntinodes++;
            }
        }

        System.out.println("Total antinodes: " + totalAntinodes);
    }
}

    
class Coordinate {
    int row, col;

    Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    Coordinate antinodeTowards(Coordinate other) {
        int rowDiff = this.row - other.row;
        int colDiff = this.col - other.col;
        return new Coordinate(this.row + rowDiff, this.col + colDiff);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate)) return false;
        Coordinate c = (Coordinate) o;
        return this.row == c.row && this.col == c.col;
    }

    @Override
    public int hashCode() {
        return 31 * row + col;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
