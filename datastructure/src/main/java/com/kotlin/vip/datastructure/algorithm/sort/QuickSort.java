package com.kotlin.vip.datastructure.algorithm.sort;

import java.util.Random;

/**
 * Created by likaiyu on 2020/3/14.
 */
public class QuickSort {

    public static void quickSort(int[] arr) {
        if (arr == null) {
            return;
        }
        int n = arr.length;
        if (n == 0) {
            return;
        }
        _quickSort(arr, 0, n - 1);
    }

    private static void _quickSort(int[] arr, int l, int r) {
//        if (l >= r) {
//            return;
//        }
        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        int p = partition(arr, l, r);
        _quickSort(arr, l, p - 1);//不包含p了，动动脑子
        _quickSort(arr, p + 1, r);//不包含p了，动动脑子
    }

    //arr[l+1...j]<v    arr[j+1...i-1]>v    i是当前考察元素
    private static int partition(int[] arr, int l, int r) {
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);

        int v = arr[l];
        int j = l;

        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < v) {
                j++;
                swap(arr, i, j);
            }
        }

        swap(arr, l, j);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void quikSort(int[] arr){
        if(arr == null || arr.length == 0){
            throw new IllegalArgumentException("arr can not be null ");
        }
        _quikSort(arr, 0, arr.length - 1);
    }

    private static void _quikSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int index = partion(arr,l,r);
        _quikSort(arr, l, index - 1);
        _quikSort(arr, index + 1, r);
    }

    private static int partion(int[] arr, int l, int r) {

        swap(arr,l, (int) (Math.random()*(r-l+1)+l));

        int x = arr[l];
        int j = l;
        for (int i = l+1; i <= r; i++) {
            if (arr[i] < x) {
                j++;
                swap(arr,i,j);
            }
        }

        swap(arr,l,j);
        return j;
    }


}
