package com.interview.leetcode;

import java.util.*;

public class MovingAvgFromDataStream {

    public static void main(String[] args) {
        MovingAverage m = new MovingAverage(3);
        System.out.println(m.next(1));
        System.out.println(m.next(10));
        System.out.println(m.next(3));
        System.out.println(m.next(5));
    }
}

class MovingAverage {

    private Queue<Integer> currentWindow;
    private int maxSize;
    private double sum;

    public MovingAverage(int size) {
        currentWindow = new LinkedList<>();
        maxSize = size;
    }

    public double next(int val) {
        if(currentWindow.size() == maxSize) {
            sum -= currentWindow.remove();
        }
        sum += val;
        currentWindow.add(val);
        return sum / currentWindow.size();
    }
}
