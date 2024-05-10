import misc.*; //importing all sorts, since they're in one package

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static int[] generateRandomArray(int size){
        Random rand = new Random();
        int[] arr = new int[size];
        for(int i = 0; i < size; i++){
            arr[i] = rand.nextInt(1000);
        }
        return arr;
    }
    public static long testSortingAlgorithm(int[] arr, String algorithm){
        long startTime = System.nanoTime();
        switch (algorithm){
            case "heapsort":
                HeapSort.sort(arr);
                break;
            case "insertionsort":
                InsertSort.sort(arr);
                break;
            case "mergesort":
                MergeSort.sort(arr, 0, arr.length - 1);
                break;
            case "quicksort":
                QuickSort.sort(arr, 0, arr.length - 1);
                break;
            case "selectionsort":
                SelectSort.sort(arr);
                break;
            default:
                System.out.println("Invalid algorithm");
        }
        long endTime = System.nanoTime();
        long durationNano = endTime - startTime;
        return durationNano / 1000;
    }
    public static void main(String[] args) {
        int numTests = 10;
        String[] algorithms = {"heapsort","insertionsort","mergesort","quicksort","selectionsort"};
        long[][] timesRandom = new long[algorithms.length][numTests];
        long[][] timesSorted = new long[algorithms.length][numTests];
        long[][] timesReversed = new long[algorithms.length][numTests];
        for (int test = 0; test < numTests; test++){
            System.out.println("Test: " + (test +1) + ":");
            int[] randArr = generateRandomArray(1000);
            int[] sortArr = IntStream.rangeClosed(1,1000).toArray();
            int[] revSortArr = IntStream.rangeClosed(1,1000).map(i->1001-i).toArray();
            System.out.println("Random array:");
            for (int i = 0; i < algorithms.length; i++) {
                int[] copyRandom = Arrays.copyOf(randArr, randArr.length);
                timesRandom[i][test] = testSortingAlgorithm(copyRandom, algorithms[i]);
                System.out.println(algorithms[i] + ": " + timesRandom[i][test] + " microseconds");
            }
            System.out.println("\nSorted array:");
            for (int i = 0; i < algorithms.length; i++) {
                int[] copySorted = Arrays.copyOf(sortArr, sortArr.length);
                timesSorted[i][test] = testSortingAlgorithm(copySorted, algorithms[i]);
                System.out.println(algorithms[i] + ": " + timesSorted[i][test] + " microseconds");
            }
            System.out.println("\nReversed array:");
            for (int i = 0; i < algorithms.length; i++) {
                int[] copyReversed = Arrays.copyOf(revSortArr, revSortArr.length);
                timesReversed[i][test] = testSortingAlgorithm(copyReversed, algorithms[i]);
                System.out.println(algorithms[i] + ": " + timesReversed[i][test] + " microseconds");
            }
        }
        System.out.println("\nAverage time for each algorithm for each array:");
        for (int i = 0; i < algorithms.length; i++) {
            long sumRandom = 0, sumSorted = 0, sumReversed = 0;
            for (int test = 0; test < numTests; test++) {
                sumRandom += timesRandom[i][test];
                sumSorted += timesSorted[i][test];
                sumReversed += timesReversed[i][test];
            }
            System.out.println(algorithms[i] + " - Random array: " + (sumRandom / numTests) + " microseconds");
            System.out.println(algorithms[i] + " - Sorted array: " + (sumSorted / numTests) + " microseconds");
            System.out.println(algorithms[i] + " - Reversed array: " + (sumReversed / numTests) + " microseconds");
        }
    }

}