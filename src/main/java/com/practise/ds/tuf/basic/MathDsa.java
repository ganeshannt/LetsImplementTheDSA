package com.practise.ds.tuf.basic;

import java.util.ArrayList;
import java.util.Arrays;

class MathDsa {

    static int countDigit(int n) {
        int count = 0;
        while (n > 0) {
            count++;
            System.out.println("/10 " + n / 10);
            System.out.println("%10 " + n % 10);
            n = n / 10;

        }
        return count;
    }

    static int countOddDigit(int n) {
        int count = 0;
        while (n > 0) {
            if ((n % 10) % 2 != 0) {
                count++;
            }
            n = n / 10;
        }
        return count;
    }

    static int reverseNumber(int n) {
        int reverseNumber = 0;
        if (n == 0) return reverseNumber;
        while (n > 0) {
            int lastDigit = n % 10;
            reverseNumber = (reverseNumber * 10) + lastDigit;
            n = n / 10;
        }
        return reverseNumber;
    }


    static boolean isPalindrome(int n) {
        int number = n;
        int reverseNumber = 0;
        if (n == 0) return true;
        while (n > 0) {
            int lastDigit = n % 10;
            reverseNumber = (reverseNumber * 10) + lastDigit;
            n = n / 10;
        }
        return reverseNumber == number;
    }


    static int largestDigit(int n) {
        int largestDigit = 0;

        while (n > 0) {
            int lastDigit = n % 10;
            if (largestDigit < lastDigit) {
                largestDigit = lastDigit;
            }
            n = n / 10;
        }
        return largestDigit;
    }


    static int factorial(int n) {
        if (n == 0) return 1;

        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    static boolean isArmstrong(int n) {
        if (n == 0) return false;
        int initalValue = n;
        double result = 0;
        int numberOfDigit = 0;
        while (n > 0) {
            numberOfDigit++;
            n = n / 10;
        }

        n = initalValue;

        while (n > 0) {
            int lastDigit = n % 10;
            result = result + Math.pow(lastDigit, numberOfDigit);
            n = n / 10;
        }

        return (int) result == initalValue;
    }

    static boolean isPerfect(int n) {
        int result = 0;

        for (int i = 1; i < (n / 2) + 1; i++) {
            if (n % i == 0) {
                result += i;
            }
        }

        return result == n;

    }


    static boolean isPrime(int n) {
        if (n == 1) return true;
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    static int primeUptoN(int n) {
        int primeCount = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) primeCount++;
        }
        return primeCount;
    }


    static int GCD(int n1, int n2) {
        if (n1 == 1 || n2 == 1) return 1;

        int minNumber = Math.min(n1, n2);

        int maxNumber = Math.max(n1, n2);

        int maxDivisor = 1;

        for (int i = 2; i < Math.sqrt(minNumber); i++) {
            if (maxDivisor < i && minNumber % i == 0 && maxNumber % i == 0) {
                maxDivisor = i;
            }
        }

        if (maxNumber % minNumber == 0) {
            maxDivisor = minNumber;
        }

        return maxDivisor;
    }

    static int GCDBestApproach(int n1, int n2) {
        if (n1 < 2 || n2 < 2) return 1;

        while (n1 > 0 && n2 > 0) {
            if (n1 < n2) {
                n2 = n2 % n1;
            } else {
                n1 = n1 % n2;
            }
        }

        return Math.max(n1, n2);
    }

    static int[] divisors(int n) {
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 1; i < (n / 2) + 1; i++) {
            if (n % i == 0) {
                result.add(i);
            }
        }

        result.add(n);

        int[] resultArr = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            resultArr[i] = result.get(i).intValue();
        }

        return resultArr;
    }

    static int[] divisorsOptimal(int n) {
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                result.add(i);

                if (n / i != i) {
                    result.add(n / i);
                }
            }
        }

        result.sort((a, b) -> a - b);

        int[] resultArr = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            resultArr[i] = result.get(i).intValue();
        }

        return resultArr;
    }

    public static void main(String[] args) {
//        System.out.println(countDigit(1456));
//        System.out.println(countOddDigit(1456));
        System.out.println(reverseNumber(1456));
    }

    int findGCD(int[] nums) {

        Arrays.sort(nums);

        int n1 = nums[0];
        int n2 = nums[nums.length - 1];

        if (n1 < 2 || n2 < 2) return 1;

        while (n1 > 0 && n2 > 0) {
            if (n1 < n2) {
                n2 = n2 % n1;
            } else {
                n1 = n1 % n2;
            }
        }

        return Math.max(n1, n2);

    }

    int LCM(int n1, int n2) {
        int max = Math.max(n1, n2);
        int min = Math.min(n1, n2);

        for (int i = 1; i < 1000; i++) {
            int target = max * i;

            int source = 0;
            for (int j = 1; source < target; j++) {
                source = j * min;
            }

            if (source == target) return target;

        }

        return -1;

    }
}
