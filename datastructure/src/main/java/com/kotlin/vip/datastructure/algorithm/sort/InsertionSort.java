package com.kotlin.vip.datastructure.algorithm.sort;

/**
 * Created by likaiyu on 2020/2/24.
 */
public class InsertionSort {

    public static void sort1(Comparable[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
//            for(int j = i; j>0;j--){
//                if(arr[j].compareTo(arr[j-1])<0){
//                    swap(arr,j,j-1);
//                }else{
//                    break;
//                }
//            }
            for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
                swap(arr, j, j - 1);
            }
        }


    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            Comparable e = arr[i];
            int j = i;
            for (; j > 0 && arr[j - 1].compareTo(e) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    public static void sort(int[] arr,int l,int r) {

        assert l >= 0 && l <= r && r < arr.length;

        for (int i = l+1; i <= r; i++) {
            int e = arr[i];
            int j = i;
            for (; j > 0 && arr[j - 1]>e; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
