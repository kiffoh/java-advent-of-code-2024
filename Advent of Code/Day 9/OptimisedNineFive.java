
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import org.w3c.dom.html.HTMLIsIndexElement;

public class OptimisedNineFive {
    public static long solve(String input) {
        List<File> files = new ArrayList<>();
        List<FreeSpace> freeSpaces = new ArrayList<>();
        buildInitalState(input, files, freeSpaces);

        moveFiles(files, freeSpaces);

        for (File file: files) {
            System.out.println(file.toString());
        }
        

        return calculateCheckSum(files);
    }

    private static void buildInitalState(String input, List<File> files, List<FreeSpace> freeSpaces) {
        int[] values = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            values[i] = Character.getNumericValue(input.charAt(i));
        }

        int id = 0;
        int position = 0;

        for (int i = 0; i < values.length; i++) {
            int size = values[i];
            if (i % 2 == 0) {
                files.add(new File(id, size, position));
            } else {
                freeSpaces.add(new FreeSpace(position, size));
                id++;
            }
            position += size;
        }
    }

    private static void moveFiles(List<File> files, List<FreeSpace> freeSpaces) {
        Collections.sort(files, (a, b) -> Integer.compare(b.id, a.id)); // Sorted in decended order

        TreeSet<FreeSpace> freeSpacesSet = new TreeSet<>();

        for (FreeSpace space: freeSpaces) {
            freeSpacesSet.add(space);
        }

        for (File file: files) {
            FreeSpace filePositionMarker = new FreeSpace(file.position, 0);

            SortedSet<FreeSpace> spacesToLeft = freeSpacesSet.headSet(filePositionMarker);

            FreeSpace bestSpace = null;

            for (FreeSpace space: spacesToLeft) {
                if (file.size <= space.size) {
                    bestSpace = space;
                    break;
                }
            }

            if (bestSpace != null) {
                freeSpacesSet.remove(bestSpace);

                if (bestSpace.size > file.size) {
                    freeSpacesSet.add(new FreeSpace(bestSpace.position + file.size, bestSpace.size - file.size));
                } 

                freeSpacesSet.add(new FreeSpace(file.position, file.size));

                file.position = bestSpace.position;
            }
        }

    }

    private static int calculateCheckSum(List<File> files) {
        int totalSum = 0;
        for (File file: files) {
            for (int position = file.position; position < file.position + file.size; position++) {
                totalSum += file.id * position;
            }
        }
        return totalSum;
    }
}

class File {
    int id;
    int size;
    int position;
    int originalPosition;

    public File(int id, int size, int position) {
        this.id = id;
        this.size = size;
        this.position = position;
        this.originalPosition = position;
    }

    @Override 
    public String toString() {
        return "File{id=" + id + ", size=" + size + ", position=" + position + "}";
    }
}

class FreeSpace implements Comparable<FreeSpace> {
    int position;
    int size;

    public FreeSpace(int position, int size) {
        this.position = position;
        this.size = size;
    }

    // Compare by position for TreeSet ordering
    @Override
    public int compareTo(FreeSpace other) {
        return Integer.compare(this.position, other.position);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FreeSpace other = (FreeSpace) obj;
        return position == other.position;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
