package com.interview.interviewbit.arrays;

import java.util.*;

public class RepeatedNumber {

    //Time complexity: O(n), Space complexity: O(1)
    //https://cs.stackexchange.com/questions/10360/floyds-cycle-detection-algorithm-determining-the-starting-point-of-cycle
    public int repeatedNumber(final List<Integer> a) {
        int slow = 0, fast = 0;
        do{
            slow = a.get(slow);
            fast = a.get(a.get(fast));
        }while(slow != fast);
        // They meet somewhere in the loop. Reset the slow pointer and move both slow and fast together till they meet
        slow = 0;
        while(slow != fast){
            slow = a.get(slow);
            fast = a.get(fast);
        }
        return slow;
    }
}
