import java.util.Arrays;
import java.util.Random;

public class Main {

    static final int ARR_SIZE = 100000;
    static final int MIN_VAL = 1;
    static final int MAX_VAL = 20;
    static Random rand = new Random();

    public static void main(String[] args) {
        int[] originalArr = new int[ARR_SIZE];
        for (int i = 0; i < ARR_SIZE; i++) {
            originalArr[i] = rand.nextInt(MAX_VAL) + MIN_VAL;
        }

        int[] arr = Arrays.copyOf(originalArr, ARR_SIZE);

        //BUBBLE
        long start = System.currentTimeMillis();
        bubbleSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("BUBBLE SORT TOOK " + (end - start) + " ms to compute");
        arr = Arrays.copyOf(originalArr, ARR_SIZE);

        //SELECTION
        start = System.currentTimeMillis();
        selectionSort(arr);
        end = System.currentTimeMillis();
        System.out.println("SELECTION SORT TOOK " + (end - start) + " ms to compute");
        arr = Arrays.copyOf(originalArr, ARR_SIZE);

        //INSERTION
        start = System.currentTimeMillis();
        insertionSort(arr);
        end = System.currentTimeMillis();
        System.out.println("INSERTION SORT TOOK " + (end - start) + " ms to compute");
        arr = Arrays.copyOf(originalArr, ARR_SIZE);

        //QUICK
        start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        end = System.currentTimeMillis();
        System.out.println("QUICK SORT TOOK " + (end - start) + " ms to compute");
        arr = Arrays.copyOf(originalArr, ARR_SIZE);

        //MERGE
        start = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1);
        end = System.currentTimeMillis();
        System.out.println("MERGE SORT TOOK " + (end - start) + " ms to compute");
        arr = Arrays.copyOf(originalArr, ARR_SIZE);

        /**
         * //BOGO
         *         start = System.currentTimeMillis();
         *         bogoSort(arr);
         *         end = System.currentTimeMillis();
         *         System.out.println("BOGO SORT TOOK " + (end - start) + " ms to compute");
         *         arr = Arrays.copyOf(originalArr, ARR_SIZE);
         */
    }

    // SORTS

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < ARR_SIZE; i++) {
            for (int j = 0; j < ARR_SIZE - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        int smallest = MAX_VAL + 1;
        for (int i = 0; i < ARR_SIZE; i++) {
            int smallestIndex = i;
            for (int j = i; j < ARR_SIZE; j++) {
                if (arr[j] < smallest) {
                    smallest = arr[j];
                    smallestIndex = j;
                }
            }
            swap(arr, i, smallestIndex);
            smallest = MAX_VAL + 1;
        }
    }

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        long start = System.currentTimeMillis();
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static void mergeSort(int arr[], int l, int r) {
        if (l < r) {

            int m = l + (r - l) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    public static void bogoSort(int[] a) {
        while (!isSorted(a))
            shuffle(a);
    }


    // FUNNY BITS

    public static void printArray(int[] arr) {
        System.out.print("[");
        int arrLength = arr.length;
        for (int i = 0; i < arrLength; i++) {
            System.out.print(arr[i]);
            if (i < arrLength - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void swap(int[]arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    public static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] < a[i - 1])
                return false;
        return true;
    }

    public static void shuffle(int[] a) {
        for (int i = 1; i < a.length; i++)
            swap(a, i, (int)(Math.random() * i));
    }

    public static void merge(int arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}


