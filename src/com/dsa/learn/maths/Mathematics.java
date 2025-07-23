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

    // GCD of two numbers
    // E.g., a = 12, b = 15, output = 3
    // TC = O(min(a, b)), where a and b are the two numbers
    public int gcdNaiveSolution(int a, int b) {
        int result = Math.min(a, b);
        while(result > 0){
            if(a % result == 0 && b % result == 0){
                return result;
            }
            result--;
        }
        return 1; // GCD of any two numbers is at least 1
    }

    // GCD of two numbers using Euclidean Algorithm
    // E.g., a = 12, b = 15, output = 3
    // TC = O(max(a, b)), where a and b are the two numbers
    // E.g., a = 10^6, b = 1, The loop runs 10^6 times
    public int gcdEuclideanAlgorithmBasic(int a, int b) {
        while(a != b){
            if(a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a; // or b, both will be equal
    }

    // GCD of two numbers using Euclidean Algorithm with Modulus
    // E.g., a = 12, b = 15, output = 3
    // TC = O(log(min(a, b))), where a and b are the two numbers
    public int gcdEuclideanAlgorithmOptimised(int a, int b) {
        if(b == 0) {
            return a;
        }
        return gcdEuclideanAlgorithmOptimised(b, a % b);
    }

    // LCM of two numbers
    // E.g., a = 12, b = 15, output = 60
    // T.C = O(max(a, b)), where a and b are the two numbers
    public int lcmNaiveSolution(int a, int b) {
        int result = Math.max(a, b);
        while (true) {
            if (result % a == 0 && result % b == 0) {
                return result;
            }
            result++;
        }
    }

    // LCM of two numbers using GCD
    // E.g., a = 12, b = 15, output = 60
    // a * b = gcd(a, b) * lcm(a, b)
    // T.C = O(log(min(a, b))), where a and b are the two numbers
    public int lcmUsingGCD(int a, int b) {
        return (a * b) / gcdEuclideanAlgorithmOptimised(a, b);
    }

    // Check if a number is prime
    // E.g., n = 5, output = true
    // E.g., n = 4, output = false
    // T.C = O(n), where n is the number to check
    public boolean isPrime(int n){
        if(n <= 1) {
            return false; // 0 and 1 are not prime numbers
        }
        for(int i = 2; i < n; i++) {
            if(n % i == 0) {
                return false; // Found a divisor, so not prime
            }
        }
        return true; // No divisors found, so it is prime
    }

    // Check if a number is prime using optimized method
    // Idea: Divisors always come in pairs
    // If (x, y) is a pair of divisors of n, then x * y = n
    // And if x <= y, then x * x < n, then x <= sqrt(n)
    // TC = O(sqrt(n)), where n is the number to check
    public boolean isPrimeOptimized(int n) {
        if (n <= 1) {
            return false; // 0 and 1 are not prime numbers
        }
        for (int i = 2; i * i <= n; i++) { // Check up to sqrt(n)
            if (n % i == 0) {
                return false; // Found a divisor, so not prime
            }
        }
        return true; // No divisors found, so it is prime
    }

    // More Efficient Prime Check for large numbers
    // Idea: By Checking divisibility by 2 and 3 first, we can skip even numbers and multiples of 3
    // Then we check for factors of the form 6k ± 1, which covers all primes greater than 3
    // TC = O(sqrt(n)), where n is the number to check
    public boolean isPrimeEfficient(int n) {
        if (n <= 1) {
            return false; // 0 and 1 are not prime numbers
        }
        if (n <= 3) {
            return true; // 2 and 3 are prime numbers
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false; // Exclude multiples of 2 and 3
        }
        for (int i = 5; i * i <= n; i += 6) { // Check for factors of the form 6k ± 1
            if (n % i == 0 || n % (i + 2) == 0) {
                return false; // Found a divisor, so not prime
            }
        }
        return true; // No divisors found, so it is prime
    }

    // Prime Factors
    // E.g., n = 28, output = [2, 2, 7]
    // E.g., n = 60, output = [2, 2, 3, 5]
    // TC = O(n log n), where n is the number to factor
    public void primeFactors(int n){
        for (int i = 2; i <= n; i++) {
            if (isPrimeEfficient(i)){
                int x = i;
                // TC = O(log(n)), where n is the number to factor
                while(n % x == 0) {
                    System.out.print(i + " ");
                    x = x * i; // Multiply by i to get the next power of the prime factor
                }
            }
        }
        System.out.println();
    }

    // Prime Factors Efficient
    // Idea: Divisors always come in pairs
    // If (x, y) is a pair of divisors of n, then x * y = n
    // And if x <= y, then x * x < n, then x <= sqrt(n)
    // TC = O(sqrt(n)), where n is the number to factor
    public void primeFactorsEfficient(int n) {
        if(n <= 1) {
            System.out.println("No prime factors for " + n);
            return;
        }
        for(int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                System.out.print(i + " ");
                n /= i;
            }
        }
        if(n > 1) {
            System.out.print(n + " "); // n is prime if it is greater than 1
        }
        System.out.println();
    }

    // Prime Factors More Efficient
    // Idea: By Checking divisibility by 2 and 3 first, we can skip even numbers and multiples of 3
    // Then we check for factors of the form 6k ± 1, which covers all primes greater than 3
    // TC = O(sqrt(n)), where n is the number to factor
    public void primeFactorsMoreEfficient(int n) {
        if(n <= 1) {
            System.out.println("No prime factors for " + n);
            return;
        }
        while(n % 2 == 0) {
            System.out.print(2 + " ");
            n /= 2;
        }
        while(n % 3 == 0) {
            System.out.print(3 + " ");
            n /= 3;
        }
        for(int i = 5; i * i <= n; i += 6) {
            while(n % i == 0) {
                System.out.print(i + " ");
                n /= i;
            }
            while(n % (i + 2) == 0) {
                System.out.print((i + 2) + " ");
                n /= (i + 2);
            }
        }
        if(n > 3) {
            System.out.print(n + " "); // n is prime if it is greater than 3, since we already checked 2 and 3
        }
        System.out.println();
    }

    // Divisors of a Number
    // E.g., n = 28, output = [1, 2, 4, 7, 14, 28]
    // TC = O(n), where n is the number to find divisors for
    public void divisorsNaiveSolution(int n) {
        if(n <= 0) {
            System.out.println("No divisors for " + n);
            return;
        }
        for(int i = 1; i <= n; i++) {
            if(n % i == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    // Divisors of a Number Efficient
    // Idea: Divisors always come in pairs
    // If (x, y) is a pair of divisors of n, then x * y = n
    // And if x <= y, then x * x < n, then x <= sqrt(n)
    // TC = O(sqrt(n)), where n is the number to find divisors for
    // But Divisors are not in sorted order
    public void divisorsEfficient(int n) {
        if(n <= 0) {
            System.out.println("No divisors for " + n);
            return;
        }
        for(int i = 1; i * i <= n; i++) {
            if(n % i == 0) {
                System.out.print(i + " ");
                if(i != n / i) { // Avoid printing the square root twice
                    System.out.print((n / i) + " ");
                }
            }
        }
        System.out.println();
    }

    // Divisors of a Number Efficient + Sorted
    // TC = O(sqrt(n)), where n is the number to find divisors for
    public void divisorsEfficientSorted(int n) {
        if(n <= 0) {
            System.out.println("No divisors for " + n);
            return;
        }
        int i;
        for(i = 1; i * i <= n; i++) {
            if(n % i == 0) {
                System.out.print(i + " ");
            }
        }
        for(;i>=1;i--){
            if(n % i == 0) {
                System.out.print((n / i) + " ");
            }
        }
        System.out.println();
    }

    // Finding all prime numbers up to n
    // E.g., n = 30, output = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
    // TC = O(n sqrt(n)), where n is the upper limit
    public void findPrimeNumbersNaiveSolution(int n){
        for(int i=2; i<=n; i++){
            if(isPrimeEfficient(i)){
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    // Sieve of Eratosthenes for finding all prime numbers up to n
    // Idea: Create a boolean array to mark non-prime numbers
    // Initially, all numbers are assumed to be prime [n+1]
    // Start from the first prime number (2) and mark all its multiples as non-prime
    // TC = O(n log log n), where n is the upper limit
    public void findPrimeNumbersSieveOfEratosthenesSimpleImplementation(int n) {
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true; // Assume all numbers are prime initially
        }
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * 2; j <= n; j += i) {
                    isPrime[j] = false; // Mark all multiples of p as non-prime
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    // Sieve of Eratosthenes for finding all prime numbers up to n - Improved Implementation
    // TC = O(n log log n), where n is the upper limit
    public void findPrimeNumbersSieveOfEratosthenesImprovedImplementation(int n) {
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true; // Assume all numbers are prime initially
        }
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                System.out.print(i + " ");
                for (int j = i * i; j <= n; j += i) { // Start marking from i*i
                    // Why i*i? Because all smaller multiples of i will have already been marked by smaller primes
                    isPrime[j] = false; // Mark all multiples of p as non-prime
                }
            }
        }
        System.out.println();
    }

    // Computing Power of a Number
    // E.g., base = 2, exponent = 3, output = 8
    // TC = O(n), where n is the exponent
    public long power(int base, int exponent) {
        long result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }

    // Computing Power of a Number using Exponentiation by Squaring
    // E.g., base = 2, exponent = 3, output = 8
    // TC = O(log n), where n is the exponent
    // SC = O(log n) due to recursion stack
    public long powerEfficient(int base, int exponent) {
        if (exponent == 0) {
            return 1; // Any number raised to the power of 0 is 1
        }
        if (exponent < 0) {
            return 1 / powerEfficient(base, -exponent); // Handle negative exponents
        }
        long halfPower = powerEfficient(base, exponent / 2);
        if (exponent % 2 == 0) {
            return halfPower * halfPower; // Even exponent
        } else {
            return base * halfPower * halfPower; // Odd exponent
        }
    }

    // Computing Power of a Number using Iterative Exponentiation by Squaring
    // Idea: Every Number can be expressed as a sum of powers of 2
    // We can traverse through all the bits of a number (from least significant to most significant) in logarithmic time [O(log n)]
    // 10 = 1010 in binary, which means 10 = 2^3 + 2^1
    // 19 = 10011 in binary, which means 19 = 2^4 + 2^1 + 2^0
    // TC = O(log n), where n is the exponent
    // SC = O(1) since it uses a loop
    public long powerIterative(int base, int exponent) {
        if (exponent == 0) {
            return 1; // Any number raised to the power of 0 is 1
        }
        long result = 1;
        long currentBase = base;
        while (exponent > 0) {
            if (exponent % 2 == 1) { // If exponent is odd
                result *= currentBase;
            }
            currentBase *= currentBase; // Square the base
            exponent /= 2; // Divide the exponent by 2
        }
        return result;
    }

    // Compute Power with Modular Arithmetic
    // Avoids overflow and is useful in competitive programming
    public long powerModular(int base, int exponent, int modulus) {
        if (exponent == 0) {
            return 1; // Any number raised to the power of 0 is 1
        }
        long result = 1;
        long currentBase = base % modulus; // Take base modulo modulus
        while (exponent > 0) {
            if (exponent % 2 == 1) { // If exponent is odd
                result = (result * currentBase) % modulus;
            }
            currentBase = (currentBase * currentBase) % modulus; // Square the base and take modulo
            exponent = exponent>>1; // Divide the exponent by 2
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
        System.out.println(mathematics.gcdNaiveSolution(12, 15));
        System.out.println(mathematics.gcdEuclideanAlgorithmBasic(12, 15));
        System.out.println(mathematics.gcdEuclideanAlgorithmOptimised(12, 15));
        System.out.println(mathematics.lcmNaiveSolution(12, 15));
        System.out.println(mathematics.lcmUsingGCD(12, 15));
        System.out.println(mathematics.isPrime(5));
        System.out.println(mathematics.isPrimeOptimized(29));
        System.out.println(mathematics.isPrimeEfficient(29));
        mathematics.primeFactors(28);
        mathematics.primeFactorsEfficient(60);
        mathematics.primeFactorsMoreEfficient(60);
        mathematics.divisorsNaiveSolution(28);
        mathematics.divisorsEfficient(28);
        mathematics.divisorsEfficientSorted(28);
        mathematics.findPrimeNumbersNaiveSolution(30);
        mathematics.findPrimeNumbersSieveOfEratosthenesSimpleImplementation(30);
        mathematics.findPrimeNumbersSieveOfEratosthenesImprovedImplementation(30);
        System.out.println(mathematics.power(2, 3));
        System.out.println(mathematics.powerEfficient(2, 3));
        System.out.println(mathematics.powerIterative(2, 3));
        System.out.println(mathematics.powerModular(2, 10, 1000));
    }
}