package com.interview;

public class CommonUtil {

    //O(n) - gcd(x, 1)
    public static int gcd1(int a, int b) {
        if(a == 0 || b == 0) {
            return 0;
        }

        if(a == b) {
            return a;
        }

        if(a > b) {
            return gcd1(a-b, b);
        } else {
            return gcd1(a, b-a);
        }
    }

    //O(log(a+b)) : Fib sequence
    public static int gcd2(int a, int b) {
        if(a % b == 0) {
            return b;
        } else {
            return gcd2(b, a%b);
        }
    }

    //Brute Force: O(n)
    public static boolean isPrime1(int n) {

        //Corner Case
        if(n <= 1) {
            return false;
        }

        for(int i = 2; i <= n-1; i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    //O(sqrt(n))
    public static boolean isPrime2(int n) {

        if(n <= 1) {
            return false;
        }

        int sqrt = (int)Math.sqrt(n);

        for(int i = 2; i <= sqrt; i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    //O(sqrt(n))
    public static void primeFactors(int n) {
        while(n % 2 == 0) {
            System.out.print("2 ");
            n = n / 2;
        }

        for(int i = 3; i <= Math.sqrt(n); i += 2) {
            while(n % i == 0) {
                System.out.print(i + " ");
                n = n / i;
            }
        }
         if(n > 2) {
            System.out.print(n);
         }
    }

}
