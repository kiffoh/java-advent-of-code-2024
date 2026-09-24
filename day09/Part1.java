
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Part1 {
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
    static long[] inputLongArray;
    static int[] inputIntArray;

    static long totalSum = 0;
    static int sumOfInputNums = 0;

    static int[] diskMap;

    static Map<Character, Integer> files = new HashMap<>();
    static Map<Character, Integer> freeSpace = new HashMap<>();

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


    public static void calculateTotal() {
        // Two pointer method
        int pointer1 = 0;
        int pointer2 = diskMap.length - 1;

        int index = 0;

        while (pointer1 <= pointer2) {
            long result = 0;
            if (diskMap[pointer1] == -1) {
                result = diskMap[pointer2] * index;
                System.out.println("Pointer 2 == " + diskMap[pointer2] + ", Index == " + index + "; Result:  "+ result);

                pointer2--;
                while (diskMap[pointer2] == -1) pointer2--;
            } else {
                result = diskMap[pointer1] * index;
                System.out.println("Pointer 1 == " + diskMap[pointer1] + ", Index == " + index + "; Result:  "+ result);
                // pointer1++;
            }
            pointer1++;
            index++;
            totalSum += result;
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

    public static void main(String[] args) {
        convertStringToIntArray();

        createDiskMap();

        System.out.println("DiskMap: " + Arrays.toString(diskMap));

        calculateTotal();

        System.out.println("Total: " + totalSum);
    }
}
