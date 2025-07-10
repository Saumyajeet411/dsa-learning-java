package com.dsa.learn.maths;

public class Mathematics {
    // Count Digits
    // Note Assumption the value provided is Positive
    // E.g., x = 5487, output = 4
    // E.g., x = 42, output = 2
    // E.g., x = 5, output = 1
    // T.C = O(d), where d is number of digits
    public int countDigits(long x){
        int result = 0;
        while(x > 0){
            x = x/10;
            result++;
        }
        return result;
    }

    // Palindrome Number - Number is same where reversed
    // Note Assumption the value provided is Positive
    // E.g., x = 363, output = true
    // E.g., x = 415, output = false
    // T.C = O(d), where d is number of digits
    public boolean isPalindrome(long x){
        long reverse = 0;
        long temp = x;
        while (temp>0){
            long digit = temp % 10;
            reverse = reverse * 10 + digit;
            temp = temp/10;
        }
        return x == reverse;
    }

    // Factorial of a number, n! = 1*2*3*........*(n-1)*n
    // E.g., n = 4, output = 24
    // Note Assumption the value provided is Positive
        // Iterative Implementation
        // T.C = O(n), where n is the value provided, SC = O(1)
    public long factorial(int n){
        long result = 1;
        for(int i=2; i<=n; i++){
            result = result * i;
        }
        return result;
    }
        // Recursive Implementation
        // T.C = O(n), where n is the value provided, SC = O(n)
    public long factorial(int n, boolean doRecursive){
        if(n == 0){
            return 1;
        }
        return n * factorial(n-1, true);
    }

    // Compute trailing zeros in factorial of n. 5! = 120, output should be 1
    // E.g., n = 5, output = 1
    // E.g., n = 10, output = 2  (10! = 362800)
    // E.g., n = 100, output = 24
    // Idea is to count number of 5s nad 2s. Number of 5s will always be less than number of 2s
    // So we need to cound the number of 5s
    // Note there might be number that can have two 5s, like 25 = 5*5
    // Trailing Zeros count = floor(n/5) + floor(n/25) + floor(n/125)+........
    // TC = O(log n), where n is the value provided
    public int countTrailingZerosInFactorial(int n){
        int result = 0;
        for(int i=5; i<=n; i*=5){
            result = result + n/i;
        }
        return result;
    }

    public static void main(String[] args) {
        Mathematics mathematics = new Mathematics();
        System.out.println(mathematics.countDigits(5487));
        System.out.println(mathematics.isPalindrome(363));
        System.out.println(mathematics.isPalindrome(364));
        System.out.println(mathematics.factorial(4));
        System.out.println(mathematics.factorial(5, true));
        System.out.println(mathematics.countTrailingZerosInFactorial(105));
    }
}
