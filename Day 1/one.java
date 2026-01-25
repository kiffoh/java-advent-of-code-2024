
import java.util.Arrays;

public class one {
    public static void splitArray(int[] arr, int[] res) {
        if (arr.length <= 1) {
            res.append(arr);
            return;
        }

        int length = arr.length;
        int[] arr1 = Arrays.copyOfRange(arr, 0, length / 2);
        int[] arr2 = Arrays.copyOfRange(arr, length / 2, length);
        splitArray(arr1, res);
        splitArray(arr2, res);
    }

    public static void reconstructArray(int[] arr1, int[] arr2) {
        int p1 = 0, p2 = 0, i=0;

        int length = arr1.length + arr2.length;

        int[] res = new int[length];

        while (p1 < arr1.length && p2 < arr2.length) {
            if (arr1[p1] < arr2[p2]) {
                res[i++] = arr1[p1++];
            } else {
                res[i++] = arr2[p2++];
            }
        }

        while (p1 < arr1.length) {
            res[i++] = arr2[p1++];
        }

        while (p2 < arr2.length) {
            res[i++] = arr2[p2++];
        }
    }

    public static void main(int[] initialArray) {
        int[] res = new int[initialArray.length];

        splitArray(initialArray, res);

        for (int i = 0; i < initialArray.length; i++) {
            reconstructArray(res[i], res[i+1]);
        }
    }
}


