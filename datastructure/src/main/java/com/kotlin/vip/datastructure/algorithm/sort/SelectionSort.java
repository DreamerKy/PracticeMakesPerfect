package com.kotlin.vip.datastructure.algorithm.sort;

/**
 * Created by likaiyu on 2020/2/24.
 */
public class SelectionSort {
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temple = arr[i];
        arr[i] = arr[j];
        arr[j] = temple;
    }


    public static void selectSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr can not be null");
        }
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    min = j;
                }
            }
//            swap(arr, i, min);
        }

    }





















}
