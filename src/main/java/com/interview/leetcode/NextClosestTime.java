package com.interview.leetcode;

import java.util.*;

public class NextClosestTime {

    static int diff = Integer.MAX_VALUE;
    static String result = "";

    public static String nextClosestTime(String time) {
        char[] result = time.toCharArray();
        TreeSet<Character> set = new TreeSet<>(Arrays.asList(result[0], result[1], result[3], result[4]));

        result[4] = findNext(set, result[4], '9');
        if(result[4] > time.charAt(4)) {
            return String.valueOf(result);
        }

        result[3] = findNext(set, result[3], '5');
        if(result[3] > time.charAt(3)) {
            return String.valueOf(result);
        }

        result[1] = findNext(set, result[1], result[0] == '2' ? '3' : '9');
        if(result[1] > time.charAt(1)) {
            return String.valueOf(result);
        }

        result[0] = findNext(set, result[0], '2');
        return String.valueOf(result);
    }

    private static char findNext(TreeSet<Character> set, char c, char limit) {
        Character n = set.higher(c);
        return n == null || n > limit ? set.first() : n;
    }

    //Brute-force O(4 * 4 * 4 * 4) = O(256) = O(1)
    public static String nextClosestTime2(String time) {
        char[] digits = new char[4];
        digits[0] = time.charAt(0);
        digits[1] = time.charAt(1);
        digits[2] = time.charAt(3);
        digits[3] = time.charAt(4);

        Set<String> timeSet = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        String candidate = ""+digits[i]+""+digits[j]+":"+digits[k]+""+digits[l];
                        if (isValidTime(candidate)) timeSet.add(candidate);
                    }
                }
            }
        }

        List<String> timeList = new ArrayList<>();
        timeList.addAll(timeSet);
        Collections.sort(timeList);
        int index = timeList.indexOf(time);
        return index == timeList.size() - 1 ? timeList.get(0) : timeList.get(index+1);
    }

    public static boolean isValidTime(String time) {
        int hour = Integer.parseInt(time.substring(0,2));
        int min = Integer.parseInt(time.substring(3,5));
        return hour >= 0 && hour <= 23 && min >= 0 && min <= 59;
    }

    //Depth-first search
    public static String nextClosestTime3(String time) {
        Set<Integer> set = new HashSet<>();
        set.add(Integer.parseInt(time.substring(0, 1)));
        set.add(Integer.parseInt(time.substring(1, 2)));
        set.add(Integer.parseInt(time.substring(3, 4)));
        set.add(Integer.parseInt(time.substring(4, 5)));

        if (set.size() == 1) return time;

        List<Integer> digits = new ArrayList<>(set);
        int minute = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));

        dfs(digits, "", 0, minute);

        return result;
    }

    private static void dfs(List<Integer> digits, String cur, int pos, int target) {
        if (pos == 4) {
            int m = Integer.parseInt(cur.substring(0, 2)) * 60 + Integer.parseInt(cur.substring(2, 4));
            if (m == target) return;
            int d = m - target > 0 ? m - target : 1440 + m - target;
            if (d < diff) {
                diff = d;
                result = cur.substring(0, 2) + ":" + cur.substring(2, 4);
            }
            return;
        }

        for (int i = 0; i < digits.size(); i++) {
            if (pos == 0 && digits.get(i) > 2) continue;
            if (pos == 1 && Integer.parseInt(cur) * 10 + digits.get(i) > 23) continue;
            if (pos == 2 && digits.get(i) > 5) continue;
            if (pos == 3 && Integer.parseInt(cur.substring(2)) * 10 + digits.get(i) > 59) continue;
            dfs(digits, cur + digits.get(i), pos + 1, target);
        }
    }

    public static void main(String[] args) {
        System.out.println(nextClosestTime("23:59"));
        System.out.println(nextClosestTime2("23:59"));
        System.out.println(nextClosestTime3("23:59"));
    }
}
