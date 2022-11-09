import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Lab1 {
    static int counter = 0; // exists primarily for counting permutations in recursive function
    static String[] dataImport(String[] arr) {
        try {
            File fileIn = new File("dataset-titanic.csv");
            Scanner reader = new Scanner(fileIn);
            for (int i = 0; reader.hasNextLine(); i++) {
                arr[i] = reader.nextLine();
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File read error.");
            e.printStackTrace();
        }
        return arr;
    }
    static void shellSorting(String[] arr) {
        String buffer;
        int n = arr.length;
        int h = 1;
        while (h < n/3) {
            h = 3*h + 1;
        }
        while (h > 0){
            for (int i = h; i < n; i++) {
                buffer = arr[i];
                int in = i;
                while (in > h - 1 && arr[in-h].compareTo(buffer) > 0) {
                    arr[in] = arr[in - h];
                    in -= h;
                    counter++;
                }
                arr[in] = buffer;
            }
            h = (h-1)/3;
        }
        System.out.println("Shell Sorted Dataset: ");
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("Number of Permutations: " + counter);
        counter = 0;
    }
    static void mergeSorting(String[] arr) {
        String[] emptyBuffer = new String[arr.length];
        String[] result = mergeSortingRecursion(arr, emptyBuffer, 0, arr.length);
        System.out.println("Merge Sorted Dataset: ");
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
        System.out.println("Number of Permutations: " + counter);
        counter = 0;
    }
    static String[] mergeSortingRecursion(String[] buffer1, String[] buffer2, int startPos, int endPos) {
        if (startPos >= endPos-1) {
            return buffer1;
        }
        int mid = startPos + (endPos - startPos) / 2; // determines the middle of the array
        String[] sorted1 = mergeSortingRecursion(buffer1, buffer2, startPos, mid); // first sorted half
        String[] sorted2 = mergeSortingRecursion(buffer1, buffer2, mid, endPos); // second sorted half
        int pos1 = startPos;
        int pos2 = mid;
        int f = startPos;
        String[] result;
        if (sorted1 == buffer1) {
            result = buffer2;
        }
        else {
            result = buffer1;
        }
        while (pos1 < mid && pos2 < endPos) {
            if (sorted1[pos1].compareTo(sorted2[pos2]) < 0) {
                result[f++] = sorted1[pos1++];
            }
            else {
                result[f++] = sorted2[pos2++];
            }
            counter++;
        }
        while (pos1 < mid) {
            result[f++] = sorted1[pos1++];
            counter++;
        }
        while (pos2 < endPos) {
            result[f++] = sorted2[pos2++];
            counter++;
        }
        return result;
    }
    public static void main(String[] args) {
        String[] data = new String[887];
        long startTime = System.nanoTime();
        dataImport(data);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Time taken to read (in milliseconds): " + elapsedTime);
        shellSorting(data);
        mergeSorting(data);
    }
}