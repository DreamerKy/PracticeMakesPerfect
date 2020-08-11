package com.kotlin.vip.datastructure.algorithm.sort;

import java.util.Arrays;

/**
 * Created by likaiyu on 2020/2/24.
 */
public class MergeSort {

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    //递归对arr[l...r]的范围进行排序
    private static void sort(Comparable[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = l + (r - l) / 2;
        sort(arr, l, mid);
        sort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    //将arr[l...mid] 和[mid+1...l]两部分进行归并
    private static void merge(Comparable[] arr, int l, int mid, int r) {
        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);
        //初始化，i指向左半部分起始索引位置l,j指向右半部分起始索引位置mid+1
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l].compareTo(aux[j - l]) < 0) {
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }
    }


    public static void mergeSort2(int[] arr) {
        int n = arr.length;
        _mergeSort2(arr, 0, n - 1);
    }

    private static void _mergeSort2(int[] arr, int l, int r) {
        //递归出口
        if (l >= r) {
            return;
        }
        int mid = l + (r - l) / 2;
        //本层递归要做的事【将本层的左右两办分别排序并合并】
        _mergeSort2(arr, l, mid);
        _mergeSort2(arr, mid + 1, r);
        _merge(arr, l, mid, r);
    }

    //将arr[l...mid]和arr[mid+1...r]两部分进行归并
    private static void _merge(int[] arr, int l, int mid, int r) {

        //合并过程需要额外空间来辅助操作
        int[] clone = arr.clone();
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k++) {//?????闭区间
            if (i > mid) {
                arr[k] = clone[j - l];
                j++;
            } else if (j > r) {
                arr[k] = clone[i - l];
                i++;
            } else if (clone[i - l] < clone[j - l]) {
                arr[k] = clone[i - l];
                i++;
            } else {
                arr[k] = clone[j - l];
                j++;
            }
        }


    }


    public static void merSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr can not be null");
        }
        _merSort(arr, 0, arr.length - 1);

    }

    private static void _merSort(int[] arr, int l, int r) {

        if (l >= r) {
            return;
        }
        int mid = l + (r - l) / 2;
        _merSort(arr, l, mid);
        _merSort(arr, mid + 1, r);
        mer(arr, l, mid, r);
    }

    private static void mer(int[] arr, int l, int mid, int r) {
        int[] temp = Arrays.copyOfRange(arr, l, r + 1);

        int length = r + 1 - l;
        int[] temp2 = new int[length];
        for (int i = 0; i <length; i++) {
            temp2[i] = arr[i + l];
        }


        int j = l;
        int k = mid + 1;
        for (int i = l; i <= r; i++) {
            if (j > mid) {
                arr[i] = temp[k - l];
                k++;
            } else if (k > r) {
                arr[i] = temp[j - l];
                j++;
            } else if (temp[j - l] > temp[k - l]) {
                arr[i] = temp[k - l];
                k++;
            } else {
                arr[i] = temp[j - l];
                j++;
            }
        }

    }


}
