package com.kotlin.vip.datastructure.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by likaiyu on 2020/8/25.
 * <p>
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。
 * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 */
public class LC_146_LruCache_LRU缓存机制_200825 {

    public static void main(String[] args) {
        LRUCache2 cache = new LRUCache2(2);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4
        cache.print();
    }

    static class LRUCache2 {

        class Node{
            int key;
            int value;
            Node prev;
            Node next;

            public Node() {
            }

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "key=" + key +
                        ", value=" + value +
                        '}';
            }
        }

        private Map<Integer,Node> cache ;
        private int capacity;
        private Node dummyHead;
        private Node dummyTail;

        public LRUCache2(int capacity) {
            cache = new HashMap<>();
            this.capacity = capacity;
            dummyHead = new Node(-1,-1);
            dummyTail = new Node(-1,-1);
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }

        public int get(int key) {
            if (cache.containsKey(key)) {
                Node node = cache.get(key);
                int val = node.value;
                moveNode2Head(key);
                return val;
            } else {
                return -1;
            }
        }

        private void moveNode2Head(int key) {
            //先把 node 拿出来
            Node node = cache.get(key);
            Node p = node;
            Node next = p.next;
            Node prev = p.prev;
            //链接 node 前驱后继节点
            prev.next = next;
            next.prev = prev;
            addNode2Head(node);
        }

        private void addNode2Head(Node newNode) {
            //当前头结点
            Node oldHead = dummyHead.next;
            //链接上新节点
            oldHead.prev = newNode;
            newNode.next = oldHead;
            newNode.prev = dummyHead;
            dummyHead.next = newNode;
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                Node node = cache.get(key);
                node.value = value;
                moveNode2Head(key);
            } else {
                if (cache.size() == capacity) {
                    Node oldTail = removeTail();
                    cache.remove(oldTail.key);
                }
                Node newNode = new Node(key, value);
                cache.put(key, newNode);
                addNode2Head(newNode);
            }
        }

        private Node removeTail() {
            Node oldTail = dummyTail.prev;
            oldTail.prev.next = dummyTail;
            dummyTail.prev = oldTail.prev;
            oldTail.next = null;
            oldTail.prev = null;
            return oldTail;
        }

        public void print() {
            for (Integer key : cache.keySet()) {
                System.out.println("key--" + key + "\r\n" + "value--" + cache.get(key).toString());
            }
        }
    }







    static class LRUCache extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key,-1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> entry) {
            return size() > capacity;
        }
    }


}
