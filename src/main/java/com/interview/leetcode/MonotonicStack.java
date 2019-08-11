package com.interview.leetcode;

import java.util.Arrays;
import java.util.Stack;

public class MonotonicStack {

    public static int[] previousLessElement(int[] a) {
        int[] previousLess = new int[a.length];
        Arrays.fill(previousLess, -1);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < a.length; i++) {
            while (!stack.isEmpty() && a[stack.peek()] > a[i]) {
                stack.pop();
            }
            previousLess[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return previousLess;
    }

    public static int[] nextLessElement(int[] a) {
        int[] nextLess = new int[a.length];
        Arrays.fill(nextLess, -1);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < a.length; i++) {
            while (!stack.isEmpty() && a[stack.peek()] > a[i]) {
                int x = stack.pop();
                nextLess[x] = i;
            }
            stack.push(i);
        }
        return nextLess;
    }

    public static void main(String[] args) {
        Arrays.stream(previousLessElement(new int[] {3, 7, 8, 4})).forEach(x -> System.out.print(x + " "));
        System.out.println();
        Arrays.stream(nextLessElement(new int[] {3, 7, 8, 4})).forEach(x -> System.out.print(x + " "));
    }
}
