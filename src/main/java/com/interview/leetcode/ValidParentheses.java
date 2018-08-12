package com.interview.leetcode;

import java.util.Stack;

public class ValidParentheses {

    public static boolean isValid(String s) {
        if(s == null || s.isEmpty()) {
            return true;
        }
        if(s.length() % 2 == 1) {
            return false;
        }

        Stack<Character> charStack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            switch (currentChar) {
                case '(':
                case '{':
                case '[':
                    charStack.push(currentChar);
                    break;
                case ')':
                    if (!charStack.isEmpty() && charStack.peek() == '(') {
                        charStack.pop();
                    } else {
                        return false;
                    }
                    break;
                case '}':
                    if (!charStack.isEmpty() && charStack.peek() == '{') {
                        charStack.pop();
                    } else {
                        return false;
                    }
                    break;
                case ']':
                    if (!charStack.isEmpty() && charStack.peek() == '[') {
                        charStack.pop();
                    } else {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return charStack.isEmpty();
    }

    public static boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("{[]}"));

    }
}
