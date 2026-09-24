
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Part2 {
    private static final String INPUT_PATH = "input.txt";

    private static String readInput() {
        try {
            return java.nio.file.Files.readString(java.nio.file.Path.of(INPUT_PATH));
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException("Could not read " + INPUT_PATH + ". Add your puzzle input there.", e);
        }
    }


    static String inputString = readInput();


    static char[] inputCharArray;
    static int[] inputIntArray;

    static long totalSum = 0;
    static int sumOfInputNums = 0;

    static int[] diskMap;

    static Map<Integer, Integer> files = new HashMap<>();
    static Map<Integer, Integer> filesIndexMap = new HashMap<>();
    static Map<Integer, Integer> freeSpace = new HashMap<>();
    static Map<Integer, Integer> freeSpaceIndexMap = new HashMap<>();

    public static void convertStringToIntArray() {
        String cleanedInput = inputString.trim();

        inputCharArray = cleanedInput.toCharArray();
        inputIntArray = new int[inputCharArray.length];

        for (int i = 0; i < inputIntArray.length; i++) {
            int num = Character.getNumericValue(inputCharArray[i]);
            inputIntArray[i] = num;
            sumOfInputNums += num;
        }
    }

    public static void createDiskMap() {
        diskMap = new int[sumOfInputNums];

        int id = 0;

        int diskMapIndex = 0;

        for (int i = 0; i < inputIntArray.length; i++) {
            int curr = inputIntArray[i];
            for (int j = 0; j < curr; j++) {
                if (i % 2 == 0) {
                    diskMap[diskMapIndex++] = id;
                } else {
                    diskMap[diskMapIndex++] = -1;
                }
            }

            if (i % 2 != 0) {
                id++;
            }
        }
    }

    public static int getMaxKey() {
        int max = 0;
        for (int key: files.keySet()) {
            if (key > max) max = key;
        }
        return max;
    }

    public static void updateDiskMap() {
        int id = getMaxKey(); // Start with max and decrease
        
        while (id > 0){
            int fileLength = files.get(id);
            // System.out.println("\nid: " + id + ", fileLength: " + fileLength);

            for (int freeSpaceId = 0; freeSpaceId < id; freeSpaceId++) {
                int availableSpace = freeSpace.get(freeSpaceId);
                // System.out.println("freeSpaceId: " + freeSpaceId +  ", availableSpace: " + availableSpace);


                if (fileLength <= availableSpace) {
                    // Replace free space with file
                    int freeSpaceIndex = freeSpaceIndexMap.get(freeSpaceId);
                    replaceFiles(id, freeSpaceIndex, fileLength);

                    // Replace file with free space
                    int fileIndex = filesIndexMap.get(id);
                    replaceFiles(-1, fileIndex, fileLength);

                    // Adjust freeSpace Map to reflect new availableSpace 
                    freeSpace.put(freeSpaceId, availableSpace - fileLength);
                    freeSpaceIndexMap.put(freeSpaceId, freeSpaceIndex + fileLength);
                    
                    // Break out of loop to prevent id being duplicated in multiple places
                    break;
                }
            }
            id--;
        }
    }

    public static void replaceFiles(int id, int startIndex, int lengthOfFile) {
        for (int index = startIndex; index < startIndex + lengthOfFile; index++) {
            diskMap[index] = id;
        }
    }

    public static void populateDictionaries() {
        int id = 0;
        int count = 0;
        for (int index = 0; index < inputIntArray.length; index++) {
            if (index % 2 == 0) {
                files.put(id, inputIntArray[index]);
                filesIndexMap.put(id, count);
            } else {
                freeSpace.put(id, inputIntArray[index]);
                freeSpaceIndexMap.put(id, count);
                id++;
            }
            count += inputIntArray[index]; // Tracks the starting indexes of every file and freeSpace
        }
    }

    public static void calculateTotal() {
        for (int index = 0; index < diskMap.length; index++) {
            // Skip if space contains freeSpace
            if (diskMap[index] == -1) {
                continue;
            }

            totalSum += index * diskMap[index];
        }
    }

    public static void main(String[] args) {
        convertStringToIntArray();
        populateDictionaries();
        System.out.println("Files: " + files);
        System.out.println("freeSpace: " + freeSpace);

        createDiskMap();

        System.out.println("DiskMap: " + Arrays.toString(diskMap));

        updateDiskMap();
        System.out.println("Updated DiskMap: " + Arrays.toString(diskMap));


        calculateTotal();
        System.out.println("Total: " + totalSum);

        long output = OptimisedPart2.solve(inputString);
        System.out.println("Output: " + output);

    }
}
