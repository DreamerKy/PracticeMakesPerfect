package com.kotlin.vip.datastructure.leetcode;


import com.kotlin.vip.datastructure.MaxHeap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Created by likaiyu on 2019/12/24.
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 *
 * 输入: nums = [1,1,1,2,2,3], k = 1
 * 输出: [1]
 *
 */
public class LC_347_TopKFrequentElements_前K个高频元素_191224 {

    public static void main(String[] args) {
        int[] nums = {3,0,1,0};
        System.out.println(Arrays.toString(topKFrequentII(nums, 1)));
    }


    public static int[] topKFrequentII(int[] nums, int k) {
        final Map<Integer,Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num , map.getOrDefault(num,0) + 1);
        }
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });

        for(int key : map.keySet()){
            if(queue.size()<k){
                queue.add(key);
            }else{
                if(map.get(key)>map.get(queue.peek())){
                    queue.remove();
                    queue.add(key);
                }
            }
        }
        int[] result = new int[k];
        int index = 0;
        while(!queue.isEmpty()){
            result[index++] = queue.remove();
        }
        return result;
    }



    public List<Integer> topKFF(int[] nums,int k){
        final HashMap<Integer,Integer> counts = new HashMap<>();
        for(int num:nums){
            counts.put(num,counts.getOrDefault(num,0)+1);
        }

        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer i1,Integer i2){
                return counts.get(i1)-counts.get(i2);
            }
        });

        for(int num:counts.keySet()){
            queue.add(num);
            if(queue.size()>k){
                queue.poll();
            }
        }

        List<Integer> result = new LinkedList<>();
        while(!queue.isEmpty()){
            result.add(queue.poll());
        }

        Collections.reverse(result);
        return result;


    }


    public List<Integer> topKF(int[] nums,int k) {
        //build hash map
        final HashMap<Integer, Integer> count = new HashMap();
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }
        //init heap
        //PriorityQueue是用最小堆实现的
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer c1, Integer c2) {
                return count.get(c1) - count.get(c2);
            }
        });
        for (int n : count.keySet()) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        List<Integer> top_k = new LinkedList<>();
        while (!heap.isEmpty()) {
            top_k.add(heap.poll());
        }
        Collections.reverse(top_k);
        return top_k;

    }





    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }


        MaxHeap<Freq> maxHeap = new MaxHeap<>();

        for (int key : map.keySet()) {
            if (maxHeap.size() < k) {
                maxHeap.add(new Freq(key, map.get(key)));
            } else if (map.get(key) > maxHeap.findMax().freq) {
                maxHeap.extractMax();
                maxHeap.add(new Freq(key, map.get(key)));
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while(!maxHeap.isEmpty()){
            res.add(maxHeap.extractMax().e);
        }
        return res;
    }

    private class Freq implements Comparable<Freq> {

        public int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) {
            if (this.freq < another.freq) {
                return 1;
            } else if (this.freq > another.freq) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
