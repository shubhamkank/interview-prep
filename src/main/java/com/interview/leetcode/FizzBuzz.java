package com.interview.leetcode;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

    public static List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();

        for(int i = 1; i <= n; i++) {
            if(i % 3 == 0 && i % 5 == 0) {
                res.add("FizzBuzz");
            } else if(i % 3 == 0) {
                res.add("Fizz");
            } else if(i % 5 == 0) {
                res.add("Buzz");
            } else {
                res.add(Integer.toString(i));
            }
        }
        return res;
    }

    //Extendable code using Java lambdas
    //https://leetcode.com/problems/fizz-buzz/discuss/89936/Java-Fuzz-Buzz-Follow-up(no-if-else-and-extendable)

    public static void main(String[] args) {
        fizzBuzz(15).forEach(System.out::println);
    }
}
