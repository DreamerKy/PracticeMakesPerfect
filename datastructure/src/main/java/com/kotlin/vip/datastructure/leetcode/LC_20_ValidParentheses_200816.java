package com.kotlin.vip.datastructure.leetcode;

import java.util.Stack;

/**
 * Practiced by likaiyu on 2020/8/16
 * Created by likaiyu on 2020/8/6.
 *给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *左括号必须用相同类型的右括号闭合。
 *左括号必须以正确的顺序闭合。
 *
 */
public class LC_20_ValidParentheses_200816 {

    public static void main(String[] args) {
        System.out.println(validParentheses2("(}"));
    }

    private static boolean validParentheses2(String s){
        char[] chars =  s.toCharArray();
        Stack<Character> characterStack = new Stack<>();
        for(char c : chars){
            if(!characterStack.isEmpty()){
                if(c == ')'){
                    if(characterStack.peek() == '('){
                        characterStack.pop();
                        continue;
                    }else{
                        return false;
                    }
                }

                if(c == ']'){
                    if(characterStack.peek() == '['){
                        characterStack.pop();
                        continue;
                    }else{
                        return false;
                    }
                }

                if(c == '}'){
                    if(characterStack.peek() == '{'){
                        characterStack.pop();
                        continue;
                    }else{
                        return false;
                    }
                }
            }
            characterStack.push(c);
        }
        return characterStack.isEmpty();
    }


    public static boolean validParentheses(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(char c : chars) {
            if (!stack.isEmpty()) {
                if (c == ')') {
                    if (stack.peek() == '(') {
                        stack.pop();
                        continue;
                    } else {
                        return false;
                    }
                } else if (c == ']') {
                    if (stack.peek() == '[') {
                        stack.pop();
                        continue;
                    } else {
                        return false;
                    }
                } else if (c == '}') {
                    if (stack.peek() == '{') {
                        stack.pop();
                        continue;
                    } else {
                        return false;
                    }
                }
            }
            stack.push(c);
        }
        return stack.isEmpty();
    }
}
