package com.dsa.learn.timecomplexity;

public class TimeComplexityAnalysis {

    // Loop Example 1
    // TC: O(n)
    // Loo runs ceil(n/c)
    public void loop1(int n, int c) {
        for (int i = 0; i < n; i += c) {
            // Some operation
        }
    }

    // Loop Example 2
    // TC: O(n)
    // Loo runs ceil(n/c)
    public void loop2(int n, int c) {
        for (int i = n; i > 0; i -= c) {
            // Some operation
        }
    }

    // Loop Example 3
    // GP
    // Loop will Run: C^0, C^1, C^2,......,C^(k-1)
    // k < log_c(n) + 1
    // TC: O(log n)
    public void loop3(int n, int c) {
        for (int i = 1; i < n; i *= c) {
            // Some operation
        }
    }

    // Loop Example 4
    // GP
    // Loop will Run: n/C^0, n/C^1, n/C^2,......, n/C^(k-1)
    // k < log_c(n) + 1
    // TC: O(log n)
    public void loop4(int n, int c) {
        for (int i = n; i > 1; i /= c) {
            // Some operation
        }
    }

    // Loop Example 5
    // Loop will Run: 2^c, 2^(c^2), 2^(c^3),......, 2^(c^(k-1))
    // k < log_c(log_2(n)) + 1
    // TC: O(log log n)
    public void loop5(int n, int c) {
        for (int i = 2; i < n; i = (int) Math.pow(i, c)) {
            // Some operation
        }
    }

    // Recursion Example 1
    // For n > 0, T(n) = 2T(n/2) + O(1), T(0) = O(1)
    // This is a binary tree recursion where each call branches into two more calls.
    // c + 2c + 4c + ... + (2^log n)c = O(n). It is a GP
    // Sum of GP: a * (1 - r^k) / (1 - r) where a = c, r = 2, k = log_2 n
    // TC = O(n)
    public void function1(int n) {
        if (n <= 0) {
            return;
        }
        function1(n/2);
        function1(n/2);
    }

    // Recursion Example 2
    // For n > 0, T(n) = 2T(n/2) + O(n), T(0) = O(1)
    // TC = O(n log n)
    // This is a binary tree recursion where each call branches into two more calls,
    // cn + 2cn/2 + 4cn/4 + ... + (2^log n)cn/(2^log n) = O(n log n)
    public void function2(int n) {
        if (n <= 0) {
            return;
        }
        for(int i = 0; i < n; i++) {
            // Some operation
        }
        function2(n/2);
        function2(n/2);
        // Some operation
    }

    // Recursion Example 3
    // For n > 1, T(n) = T(n-1) + O(1), T(0) = O(1)
    // TC = O(n)
    public void function3(int n) {
        if (n <= 1) {
            return;
        }
        function3(n - 1);
        // Some operation
    }

    // Recursion Example 4
    // For n > 1, T(n) = 2T(n-1) + O(1), T(1) = O(1)
    // This is a linear recursion where each call branches into two more calls.
    // c + 2c + 4c + ... + (2^n)c = O(2^n). It is a GP
    // Sum of GP: a * (1 - r^k) / (1 - r) where a = c, r = 2, k = n
    // TC = O(2^n)
    public void function4(int n) {
        if (n <= 1) {
            return;
        }
        function4(n - 1);
        function4(n - 1);
        // Some operation
    }

    // Recursion Example 5
    // For n > 1, T(n) = T(n/2) + O(1), T(1) = O(1)
    // c + c + c + ... + c {log n times}
    // TC = O(log n)
    public void function5(int n) {
        if (n <= 1) {
            return;
        }
        function5(n / 2);
        // Some operation
    }

    // Recursion Example 6
    // For n > 1, T(n) = T(n/2) + T(n/4) + O(n), T(1) = O(1)
    // This is a divide and conquer recursion where each call branches into two more calls,
    // cn + [c(n/2) + c(n/4)] + ... + c(1)
    // cn + 3cn/4 + 9cn/16 + ...  {log_2 n terms}
    // This is a geometric series with a common ratio of 3/4.
    // The sum of a geometric series is given by a * (1 - r^k) / (1 - r)
    // where a = cn, r = 3/4, k = log_2 n
    // TC = O(n)
    public void function6(int n) {
        if (n <= 1) {
            return;
        }
        function6(n / 2);
        function6(n / 4);
        for (int i = 0; i < n; i++) {
            // Some operation
        }
    }

    // Recursion Example 7
    // For n > 1, T(n) = T(n-1) + T(n-2) + O(1), T(1) = O(1), T(0) = O(1)
    // This is a Fibonacci-like recursion where each call branches into two more calls.
    // This is not a perfect binary tree recursion, but it has a similar structure.
    // Assuming perfect binary tree, the number of nodes at each level is 2^k,
    // c + 2c + 4c + ... + (2^n)c = O(2^n).
    // TC = O(2^n)
    public void function7(int n) {
        if (n <= 1) {
            return;
        }
        function7(n - 1);
        function7(n - 2);
        // Some operation
    }
}
