package com.kotlin.vip.datastructure.algorithm.sort;

/**
 * Created by likaiyu on 2020/2/28.
 */
public class BubbleSort {
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n-i; j++) {
                if (arr[j-1] > arr[j ]) {
                    swap(arr, j-1, j );
                }
            }
        }
    }

    public static void sort2(int[] arr) {
        int n = arr.length;
        boolean swapped = false;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (arr[i - 1] > arr[i]) {
                    swap(arr, i - 1, i);
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
    }


    public static void bubbleSort(int[] arr){
        if(arr == null || arr.length==0){
            throw new IllegalArgumentException("arr can not be null");
        }
        int n = arr.length;
        boolean hasSwaped;
        for(int i = 0;i<n;i++) {
            hasSwaped = false;
            for (int j = 1; j < i; j++) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                    hasSwaped = true;
                }
            }
            if (!hasSwaped) {
                break;
            }
        }

    }














    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
