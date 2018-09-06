package com.interview.leetcode;

import java.util.Stack;

public class MinStack2 {

    long min;
    Stack<Long> stack;

    public MinStack2() {
        stack = new Stack<>();
    }

    public void push(int x) {
        if(stack.isEmpty()) {
            /* If stack is empty, push 0 with current min as the pushed element */
            stack.push(0L);
            min = x;
        } else {
            /* If stack is not empty, store element as subtraction of the actual value from the current min.
               If the actual value is less than the previous min, update the min with the actual elements value. */
            stack.push(x - min);
            if(x < min) {
                min = x;
            }
        }
    }

    public void pop() {
        long pop = stack.pop();
        /* Negative pop value implies that the min got changed when the element to be now popped was pushed.
           And, the element pushed became the new min. Therefore,
           x(cur_min) - prev_min = cur_pop_value */
        if(pop < 0) {
            min = min - pop;
        }

        /* Positive pop value implies that the element (to be now popped) did not result into the change in min.
           And, therefore can be popped without updating the current minimum value. */
    }

    public int top() {
        long top = stack.peek();
        if(top > 0) {
            /* Positive top value implies that min did not change when the current top element was pushed.
               But, when the current top value was pushed it was pushed with current min being subtracted from it.
               Therefore, actual_value - current_min = stored_top_value => actual_value = current_min + stored_top_value */
            return (int) (top + min);
        } else {
            /* Negative top value implies that the actual value which got pushed latest was smaller than the previous
               min and therefore, the min is the actual value. */
            return (int) min;
        }
    }

    public int getMin() {
        return (int) min;
    }

    public static void main(String[] args) {
        MinStack2 minStack = new MinStack2();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-1);
        System.out.println(minStack.getMin());
        System.out.println(minStack.top());
        minStack.pop();
        System.out.println(minStack.getMin());
    }
}
