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
        return endTime - startTime;
    }
    public static void main(String[] args) {
        int numTests = 10;
        String[] algorithms = {"heapsort","insertionsort","mergesort","quicksort","selectionsort"};
        long[][] timesRandom = new long[algorithms.length][numTests];
        long[][] timesSorted = new long[algorithms.length][numTests];
        long[][] timesReversed = new long[algorithms.length][numTests];
        for (int test = 0; test < numTests; test++){
            System.out.println("Test: " + (test +1) + ":");
            int[] randArr = generateRandomArray(100);
            int[] sortArr = IntStream.rangeClosed(1,100).toArray();
            int[] revSortArr = IntStream.rangeClosed(1,100).map(i->101-i).toArray();
            System.out.println("Random array:");
            for (int i = 0; i < algorithms.length; i++) {
                int[] copyRandom = Arrays.copyOf(randArr, randArr.length);
                timesRandom[i][test] = testSortingAlgorithm(copyRandom, algorithms[i]);
                System.out.println(algorithms[i] + ": " + timesRandom[i][test] + " nanoseconds");
            }
            System.out.println("\nSorted array:");
            for (int i = 0; i < algorithms.length; i++) {
                int[] copySorted = Arrays.copyOf(sortArr, sortArr.length);
                timesSorted[i][test] = testSortingAlgorithm(copySorted, algorithms[i]);
                System.out.println(algorithms[i] + ": " + timesSorted[i][test] + " nanoseconds");
            }
            System.out.println("\nReversed array:");
            for (int i = 0; i < algorithms.length; i++) {
                int[] copyReversed = Arrays.copyOf(revSortArr, revSortArr.length);
                timesReversed[i][test] = testSortingAlgorithm(copyReversed, algorithms[i]);
                System.out.println(algorithms[i] + ": " + timesReversed[i][test] + " nanoseconds");
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
            System.out.println(algorithms[i] + " - Random array: " + (sumRandom / numTests) + " nanoseconds");
            System.out.println(algorithms[i] + " - Sorted array: " + (sumSorted / numTests) + " nanoseconds");
            System.out.println(algorithms[i] + " - Reversed array: " + (sumReversed / numTests) + " nanoseconds");
        }
    }

}