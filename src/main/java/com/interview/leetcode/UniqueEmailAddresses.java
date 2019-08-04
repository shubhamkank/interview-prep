package com.interview.leetcode;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmailAddresses {

    // https://en.wikipedia.org/wiki/Metacharacter
    // https://docs.oracle.com/javase/tutorial/essential/regex/literals.html
    //Time complexity:  O(NMX) N= Number of emails, M= Length of email, X= number of times string operations are called
    //X < 10, hence O(NMX) = O(MN). Let n = MN, it is O(n)
    public static int numUniqueEmails(String[] emails) {
        Set<String> emailsSet = new HashSet<>();

        for(String email : emails) {
            String[] token = email.split("@");
            String[] split = token[0].split("\\+"); //+ is a metacharacter used in regex and hence use \\+
            String localName = split[0].replace(".", "");
            String domainName = token[1];

            emailsSet.add(localName + "@" + domainName);
        }
        return emailsSet.size();
    }

    //Single iteration without using split, replace, etc. methods
    public static int numUniqueEmails2(String[] emails) {
        if (emails == null) {
            return 0;
        }

        Set<String> addresses = new HashSet<>();

        for (String email : emails) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < email.length(); i++) {
                char ch = email.charAt(i);
                switch (ch) {
                    case '.':
                        break;
                    case '+':
                        while (ch != '@') {
                            ch = email.charAt(++i);
                        }
                        sb.append(email.substring(i));
                        i = email.length();
                        break;
                    case '@':
                        sb.append(email.substring(i));
                        i = email.length();
                        break;
                    default:
                        sb.append(ch);
                        break;
                }
            }
            addresses.add(sb.toString());
        }
        return addresses.size();
    }

    public static void main(String[] args) {
        System.out.println(numUniqueEmails2(new String[] {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"}));
    }
}
