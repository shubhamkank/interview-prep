package com.interview.leetcode;

import java.util.*;

public class LoggerRateLimiter {

    //Time complexity: O(1)
    //Space complexity: O(m) where m is the maximum number of unique message that will be received in a 10 second period.
    class Logger {

        private Queue<Log> queue;
        private Set<String> messages;

        /** Initialize your data structure here. */
        public Logger() {
            //the queue is sorted as we are guaranteed to be called shouldPrintMessage by increasing time value
            //the idea is to toss any log pass the window of 10 when a new log is requested
            //e.g. if the head of queue is pointing to log at 1 sec, and we are requested at 11, the 1 sec log would be tossed
            //as it is no longer relevant
            queue = new LinkedList<>();
            messages = new HashSet<>();
        }

        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
         If this method returns false, the message will not be printed.
         The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {
            while (!queue.isEmpty() && timestamp - queue.peek().timestamp >= 10) {
                //toss away all irrelvant logs
                //also remove the message from seen message
                Log log = queue.remove();
                messages.remove(log.message);
            }

            if(messages.contains(message)) {
                return false;
            }

            queue.add(new Log(timestamp, message));
            messages.add(message);
            return true;
        }
    }

    class Log {
        private int timestamp;
        private String message;

        public Log(int timestamp, String message) {
            this.timestamp = timestamp;
            this.message = message;
        }
    }

    //Time complexity: O(1)
    //Space complexity: O(n)
    //The disadvantage to this solution is that the memory usage never stops growing
    class Logger2 {

        private Map<String, Integer> map;

        /** Initialize your data structure here. */
        public Logger2() {
            map = new HashMap<>();
        }

        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
         If this method returns false, the message will not be printed.
         The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {
            if(!map.containsKey(message)) {
                map.put(message, timestamp);
                return true;
            } else {
                int oldTimestamp = map.get(message);
                if(timestamp - oldTimestamp < 10) {
                    return false;
                } else {
                    map.put(message, timestamp);
                    return true;
                }
            }
        }
    }
}
