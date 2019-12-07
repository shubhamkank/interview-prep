package com.interview.leetcode;

import java.util.*;

public class DecodeString {

    public static String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        int i = 0;

        while(i < s.length()) {
            if(s.charAt(i) != ']') {
                stack.push(s.charAt(i));
            } else {
                StringBuilder sb = new StringBuilder();
                while(!stack.isEmpty() && Character.isLetter(stack.peek())) {
                    sb.insert(0, stack.pop());
                }
                String str = sb.toString();
                stack.pop();

                sb = new StringBuilder();
                while(!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    sb.insert(0, stack.pop());
                }
                int count = Integer.valueOf(sb.toString());

                while(count > 0) {
                    for(char c : str.toCharArray()) {
                        stack.push(c);
                    }
                    count--;
                }
            }
            i++;
        }

        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }
        return result.toString();
    }

    //Time complexity: O(k_max * n)
    public static String decodeString2(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resultStack = new Stack<>();

        String result = "";
        int i = 0;

        while (i < s.length()) {
            char c = s.charAt(i);
            if(Character.isLetter(c)) {
                result += c;
                i++;
            } else if(Character.isDigit(c)) {
                int count = 0;
                while (Character.isDigit(c)) {
                    count = count * 10 + s.charAt(i) - '0';
                    i++;
                    c = s.charAt(i);
                }
                countStack.push(count);
            } else if(c == '[') {
                resultStack.push(result);
                result = "";
                i++;
            } else {
                int count = countStack.pop();
                StringBuilder sb = new StringBuilder();
                while(count > 0) {
                    sb.append(result);
                    count--;
                }
                result = resultStack.pop() + sb.toString();
                i++;
            }
        }
        return result;
    }

    //The reason why we are using global i is because we wanna skip the position that we have already visited in recursion steps
    private int i = 0;

    public String decodeString3(String s) {
        StringBuilder sb = new StringBuilder();
        int num = 0;

        for (; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '[') {
                i++;
                String str = decodeString3(s);
                for (int k = 0; k < num; k++) {
                    sb.append(str);
                }
                num = 0;
            } else if (c == ']') {
                return sb.toString();
            } else if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(decodeString2("3[a]2[bc]"));
        System.out.println(decodeString2("3[a2[c]]"));
        System.out.println(decodeString2("2[abc]3[cd]ef"));
    }
}
