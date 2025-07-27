package com.dsa.learn.bitmagic;

public class BitManipulation {

    // Check if k-th bit is set in a number n
    // This is a naive solution with O(k) time complexity.
    public static boolean isKthBitSetNaiveSolution(int n, int k) {
        int x = 1;
        for(int i=0; i<k-1; i++) {
            x = x*2; // x = 2^k
        }
        return (n & x) != 0; // Check if the k-th bit is set

        // Naive Solution 2
//        for(int i=0; i<k-1; i++) {
//            n = n/2;
//        }
//        return (n & 1) != 0; // Check if the least significant bit is set
    }

    // Check if k-th bit is set in a number n using bitwise operations
    public static boolean isKthBitSet(int n, int k) {
        return (n & (1 << (k - 1))) != 0; // Check if the k-th bit is set using bitwise AND
        // OR
//        return ((n >> (k - 1)) & 1) != 0; // Right shift n by (k-1) and check if the least significant bit is set
    }

    // Count Set Bits in a Number
    // Time Complexity: O(d), where d is the number of bits from last bit to MSB in the number
    public static int countSetBitsNaiveSolution(int n) {
        // Traverse from Last Bit to MSB and increment count if bit is set
        int count = 0;
        while (n > 0) {
            count += (n & 1); // Check if the least significant bit is set [or count += n % 2]
            n >>= 1; // Right shift n to check the next bit [or n = n / 2]
        }
        return count;
    }

    // Brian Kernighan's Algorithm to Count Set Bits
    // Time Complexity: O(k), where k is the number of set bits in the number
    public static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            // When we substract 1 from a number, it flips all the bits after the last set bit (including the last set bit).
            n &= (n - 1); // Clear the least significant bit set
            count++;
        }
        return count;
    }

    // Lookup Table for Counting Set Bits
    // Time Complexity: O(1) for each lookup, O(n) for precomputation
    // We will assume that the maximum number of bits in an integer is 32.
    public int countSetBitsUsingLookupTable(int n) {
        int lookupTable[] = new int[256]; // 256 = 2^8, for 8 bits
        // Precompute the number of set bits for each byte (0-255)
        lookupTable[0] = 0;
        for (int i = 1; i < 256; i++) {
            lookupTable[i] = lookupTable[i & (i-1)] + 1; // Count set bits in each byte
        }
        // 0xFF = 11111111 in binary (which is 255), so we can use bitwise AND to get the last byte
        return lookupTable[n & 0xFF] + // Count set bits in the least significant byte
               lookupTable[(n >> 8) & 0xFF] + // Count set bits in the second byte
               lookupTable[(n >> 16) & 0xFF] + // Count set bits in the third byte
               lookupTable[(n >> 24) & 0xFF]; // Count set bits in the most significant byte
        // Instead of using 0xFF, we can also use 255
    }

    // Check if a number is a power of 2
    // A number is a power of 2 if it has only one bit set in its binary representation.
    // Time Complexity: O(log n) for naive solution
    public static boolean isPowerOfTwoNaiveSolution(int n) {
        if(n == 0) {
            return false; // 0 is not a power of 2
        }
        while (n % 2 == 0) {
            n /= 2; // Divide by 2 until n is no longer divisible by 2
        }
        return n == 1; // If n becomes 1, it is a power of 2
    }

    // Check if a number is a power of 2 using bitwise operations
    // Binary Representations of Powers of 2, have only one bit set
    // Time Complexity: O(1)
    public static boolean isPowerOfTwo(int n) {
        // Check if n is greater than 0 and if n AND (n - 1) is equal to 0
        // A number is a power of 2 if it has only one bit set in its binary representation.
        // This can be checked using the property: n > 0 && (n & (n - 1)) == 0
        return n > 0 && (n & (n - 1)) == 0;
    }

    // Find the only odd appearing number in an array where every other number occurs even times
    // E.g., {1, 2, 3, 2, 1, 3, 4} -> 4
    // E.g., {8, 7, 7, 8, 8} -> 8
    // Properties of XOR:
    // 1. x ^ x = 0 (XOR of a number with itself is 0)
    // 2. x ^ 0 = x (XOR of a number with 0 is the number itself)
    // 3. XOR is commutative and associative (order of operations does not matter)
    // TC = O(n), where n is the number of elements in the array
    public static int findOddOccurrence(int[] arr) {
        int result = 0;
        for (int num : arr) {
            result ^= num; // XOR all numbers in the array
        }
        return result; // The result will be the only number that occurs an odd number of times
    }

    // Find two odd appearing numbers in an array where every other number occurs even times
    // E.g., {1, 2, 3, 2, 1, 3, 4, 5, 4, 4} -> 4 and 5
    // E.g., {8, 7, 7, 8, 8, 9} -> 8 and 9
    // To find two odd appearing numbers, we can use the following approach:
    // 1. XOR all numbers in the array to get the XOR of the two odd appearing numbers.
    // 2. Find a set bit in the XOR result (this bit will be set in one of the odd appearing numbers).
    // 3. Partition the array into two groups based on the set bit and XOR each group separately to get the two odd appearing numbers.
    public static int[] findTwoOddOccurrences(int[] arr) {
        int xor = 0;
        for (int num : arr) {
            xor ^= num; // XOR all numbers in the array
        }

        // Find a set bit in the XOR result (this bit will be set in one of the odd appearing numbers)
        int setBit = xor & -xor; // Get the rightmost set bit
        // Or, xor & ~(xor - 1); // Get the rightmost set bit

        // Partition the array into two groups based on the set bit
        // and XOR each group separately to get the two odd appearing numbers
        // One group will have the set bit, and the other will not.
        // This will give us the two odd appearing numbers.
        int num1 = 0, num2 = 0;
        for (int num : arr) {
            if ((num & setBit) != 0) {
                num1 ^= num; // XOR numbers with the set bit
            } else {
                num2 ^= num; // XOR numbers without the set bit
            }
        }
        return new int[]{num1, num2}; // Return the two odd appearing numbers
    }

    // Power Set using Bit Manipulation
    // The power set of a set is the set of all subsets, including the empty set and the set itself.
    // For a set of size n, the power set has 2^n subsets.
    // E.g., str = "abc" -> Power Set = {"", "a", "b", "c", "ab", "ac", "bc", "abc"}
    // Time Complexity: O(2^n * n), where n is the length of the string
    public static void powerSet(String str) {
        int n = str.length();
        int powerSetSize = 1 << n; // 2^n
        for (int i = 0; i < powerSetSize; i++) {
            StringBuilder subset = new StringBuilder();
            for (int j = 0; j < n; j++) {
                // Check if j-th bit is set in i
                if ((i & (1 << j)) != 0) {
                    subset.append(str.charAt(j)); // Include the j-th character in the subset
                }
            }
            System.out.println("'" + subset.toString() + "'"); // Print the subset
        }
    }

    public static void main(String[] args) {
        // Example of Bit Manipulation in Java
        int a = 5; // 0101 in binary
        int b = 3; // 0011 in binary
        System.out.println("a & b = " + (a & b)); // Bitwise AND
        System.out.println("a | b = " + (a | b)); // Bitwise OR
        System.out.println("a ^ b = " + (a ^ b)); // Bitwise XOR
        // Bitwise NOT Example
        // Note: ~ operator inverts all bits, so for positive numbers it gives negative result
        // because of two's complement representation in Java. [-x = 2^32 - x]
        // For example, ~5 = -6 because 5 in binary is 0000 0101 and ~5 is 1111 1010
        int x = 5;
        System.out.println("~x = " + (~x)); // Bitwise NOT
        // Left Shift Example
        // Left shift operator (<<) shifts bits to the left, filling with 0s.
        // It effectively multiplies the number by 2 for each shift. [x * 2^y]
        x = 3;
        System.out.println("x << 1 = " + (x << 1)); // Left shift by 1 (multiplies by 2)
        System.out.println("x << 2 = " + (x << 2)); // Left shift by 2 (multiplies by 4)
        System.out.println("x << 3 = " + (x << 3)); // Left shift by 3 (multiplies by 8)
        x = -1;
        System.out.println("x << 1 = " + (x << 1)); // 2's Complement, shifts bits to the left
        System.out.println("x << 2 = " + (x << 2)); // 2's Complement, shifts bits to the left
        // Signed Right Shift Example
        // Right shift operator (>>) shifts bits to the right, filling with the sign bit (0 for positive, 1 for negative).
        // It effectively divides the number by 2 for each shift. [x / 2^y]
        x = -2;
        System.out.println("x >> 1 = " + (x >> 1)); // Right shift by 1 (divides by 2)
        System.out.println("x >> 2 = " + (x >> 2)); // Right shift by 2 (divides by 4)
        System.out.println("x >> 4 = " + (x >> 4)); // Right shift by 4 (divides by 16)
        // Unsigned Right Shift Example
        // Unsigned right shift operator (>>>) shifts bits to the right, filling with 0s.
        // It does not preserve the sign bit, so it is used for non-negative numbers.
        x = -2;
        System.out.println("x >>> 1 = " + (x >>> 1)); // Unsigned right shift by 1, (2^31 - 1) in binary
        System.out.println("x >>> 4 = " + (x >>> 4)); // Unsigned right shift by 4, (2^28 - 1) in binary
        // Binary Representation of Negative Numbers
        // Negative numbers are represented using two's complement.
        // Range of Numbers: [-2^n-1, 2^n-1 - 1]
        // For example, in 4-bit signed integers: n = 4
        // -2^3 = -8, 2^3 - 1 = 7
        System.out.println("Binary representation of -1: " + Integer.toBinaryString(-1));
        // Why 2's Complement Form?
        // 1. Unique Representation: Each negative number has a unique representation.
        // 2. Simplifies Arithmetic: Addition and subtraction can be performed without special cases for negative numbers.
        // 3. Leading Bit as Sign Bit: The leading bit indicates the sign of the number (0 for positive, 1 for negative).
        // We don't use Sign-Magnitude or One's Complement because:
        // 1. Sign-Magnitude: It has two representations for zero (+0 and -0).
        // 2. One's Complement: It has two representations for zero and requires an additional step for addition.
        // Number Representation Range in Java: 2^31 to 2^31 - 1

        BitManipulation bitManipulation = new BitManipulation();
        System.out.println("isKthBitSetNaiveSolution(5, 1) = " + bitManipulation.isKthBitSetNaiveSolution(5, 1));
        System.out.println("isKthBitSet(5, 1) = " + bitManipulation.isKthBitSet(5, 1));
        System.out.println("countSetBitsNaiveSolution(5) = " + bitManipulation.countSetBitsNaiveSolution(5));
        System.out.println("countSetBits(5) = " + bitManipulation.countSetBits(5));
        System.out.println("countSetBitsUsingLookupTable(5) = " + bitManipulation.countSetBitsUsingLookupTable(5));
        System.out.println("isPowerOfTwoNaiveSolution(8) = " + bitManipulation.isPowerOfTwoNaiveSolution(8));
        System.out.println("isPowerOfTwo(6) = " + bitManipulation.isPowerOfTwo(6));
        System.out.println("findOddOccurrence(new int[]{1, 2, 3, 2, 1, 3, 4}) = " + bitManipulation.findOddOccurrence(new int[]{1, 2, 3, 2, 1, 3, 4}));
        int[] oddOccurrences = bitManipulation.findTwoOddOccurrences(new int[]{1, 2, 3, 2, 1, 3, 4, 5, 4, 4});
        System.out.println("findTwoOddOccurrences(new int[]{1, 2, 3, 2, 1, 3, 4, 5, 4, 4}) = " + oddOccurrences[0] + " and " + oddOccurrences[1]);
        System.out.println("Power Set of 'abc':");
        bitManipulation.powerSet("abc");

    }
}
