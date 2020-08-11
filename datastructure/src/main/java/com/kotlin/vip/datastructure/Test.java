package com.kotlin.vip.datastructure;

import com.kotlin.vip.datastructure.tree.BST;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by likaiyu on 2019/11/9.
 */
public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Array<Integer> array = new Array<>(10);
        for(int i =0;i<10;i++ ){
            array.add(i,i);
        }
        String decode = Base64.getEncoder().encodeToString("15210381836".getBytes());
        String encode = new String(Base64.getDecoder().decode(decode));

        BST<Integer> bst = new BST<>();
        bst.add(5);
        bst.add(10);
        bst.add(4);
        bst.add(2);
        bst.add(46);
        bst.add(77);
        bst.add(6);
        bst.add(9);
        bst.inOrder();
        System.out.println("----------------");
        bst.preOrderNR();

        Integer minimum = bst.minimum();
        System.out.println("最小值---"+minimum);

        Integer maximum = bst.maximum();
        System.out.println("最大值---"+maximum);

        bst.removeMin();
//        System.out.println(array.toString());
//        System.out.println("--------");
//        array.addLast(11);
//        System.out.println(array.toString());
//        System.out.println("--------");
//        System.out.println(decode);
//        System.out.println("--------");
//        System.out.println(encode);


    }
}
