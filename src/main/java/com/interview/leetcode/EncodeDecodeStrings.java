package com.interview.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeDecodeStrings {

    static class Codec {

        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            StringBuilder sb = new StringBuilder();
            for(String str : strs) {
                sb.append(str.length());
                sb.append("/");
                sb.append(str);
            }
            return sb.toString();
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            List<String> strs = new ArrayList<>();
            int i = 0;
            while (i < s.length()) {
                int slash = s.indexOf('/', i);
                int len = Integer.valueOf(s.substring(i, slash));
                i = slash + len + 1;
                strs.add(s.substring(slash + 1, i));
            }
            return strs;
        }
    }


    static class Codec2 {

        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            if(strs.size() == 0) {
                return Character.toString((char)258);
            }

            String d = Character.toString((char)257);
            StringBuilder sb = new StringBuilder();
            for(String str : strs) {
                sb.append(str);
                sb.append(d);
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            if(s.equals(Character.toString((char)258)))  {
                return new ArrayList<>();
            }

            String d = Character.toString((char)257);
            //-1 in order not to discard trailing empty strings
            return Arrays.asList(s.split(d, -1));
        }
    }
}
