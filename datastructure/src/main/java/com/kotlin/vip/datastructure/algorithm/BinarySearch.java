package com.kotlin.vip.datastructure.algorithm;

/**
 * Created by likaiyu on 2020/1/19.
 */
public class BinarySearch {

    public static int binarySearch(Comparable[] arr, int n, Comparable target) {
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (target.compareTo(arr[mid]) == 0) {
                return mid;
            }
            if (target.compareTo(arr[mid]) > 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearch2(Comparable[] arr,int n, Comparable target){
        int l = 0,r = n-1;
        return search(arr,l,r,target);
    }

    public static int search(Comparable[] arr,int l,int r,Comparable target) {
        int mid = l + (r - l) / 2;
        if (l > r) {
            return -1;
        }
        if (target.compareTo(arr[mid]) == 0) {
            return mid;
        }
        if (target.compareTo(arr[mid]) > 0) {
            return search(arr, mid + 1, r, target);
        } else {
            return search(arr, l, mid - 1, target);
        }
    }

    public static void main(String[] args) {

        int n = (int)Math.pow(10, 7);
        Integer data[] = Util.generateOrderedArray(n);

        long startTime = System.currentTimeMillis();
        for(int i = 0 ; i < n ; i ++)
            if(i != binarySearch2(data, n, i))
                throw new IllegalStateException("find i failed!");
        long endTime = System.currentTimeMillis();

        System.out.println("Binary Search test complete.");
        System.out.println("Time cost: " + (endTime - startTime) + " ms");
    }


    public static int binaSearch(int[] arr, int target) {
        if(arr == null || arr.length==0){
            throw new IllegalArgumentException("arr can not be null");
        }

        return _binaSearch(arr,0,arr.length-1,target);
    }

    private static int _binaSearch(int[] arr, int l, int r, int target) {
        if (l > r) {
            return -1;
        }
        int mid = l + (r - l) / 2;

        if (target == arr[mid]) {
            return mid;
        } else if (target < arr[mid]) {
            return _binaSearch(arr, l, mid - 1, target);
        } else {
            return _binaSearch(arr, mid + 1, r, target);
        }
    }


}
