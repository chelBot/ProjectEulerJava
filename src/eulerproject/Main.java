package eulerproject;

import java.io.*;
import static java.lang.System.currentTimeMillis;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cstauber77
 */
public class Main {

    //prob. 5
    //2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
    //What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
    public static void smallestMultiple() {
        boolean flag = true;
        boolean flag2;
        int num = 1;
        while (flag) {
            flag2 = true;
            for (int i = 1; i < 21; i++) {
                if (num % i != 0) {
                    flag2 = false;
                    break;
                }
            }
            if (flag2) {
                System.out.print(num);
                flag = false;
            }
            num++;
        }
    }

    //prob. 5 attempt 2
    public static boolean checkFactors(long num) {
        for (int i = 11; i < 21; i++) {
            if ((num % i) != 0) {
                return false;
            }
        }
        return true;
    }

    public static void smallestMultiple2() {
        long product = 1;
        for (int i = 1; i < 21; i++) {
            product *= i;
        }
        for (int i = 0; i < 20; i++) {
            int divisor = 20 - i;
            long temp = product / divisor;
            while (checkFactors(product)) {
                product = temp;
                temp = product / divisor;
            }
        }
        System.out.println(product);

    }

    //prob. 15
    //Starting in the top left corner of a 2×2 grid, 
    //and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.
    //How many such paths are there in a 20 x 20 grid.
    public static void latticePaths() {
        final int gridSize = 20;
        long[][] grid = new long[gridSize + 1][gridSize + 1];
        //initializing base cases; boundary conditions (bottom edge has one path, right edge has one path)
        for (int i = 0; i < gridSize; i++) {
            grid[gridSize][i] = 1;
            grid[i][gridSize] = 1;
        }
        for (int i = gridSize - 1; i >= 0; i--) {
            for (int j = gridSize - 1; j >= 0; j--) {
                grid[i][j] = grid[i + 1][j] + grid[i][j + 1];
            }
        }
        System.out.print(grid[0][0]);
    }
    //prob. 18

    public static void maxPathSum() {
        int[][] triangle = new int[15][15];
        int colSum;
        int diaSum;
        try {

            File file = new File("/Users/cstauber77/Desktop/triangleNums.txt");
            Scanner sc = new Scanner(file);
            for (int i = 0; i <= 14; i++) {
                for (int j = 0; j <= i; j++) {
                    triangle[i][j] = sc.nextInt();
//                        System.out.print(triangle[i][j] + " ");
                }
//                    System.out.println();
            }

            for (int row = 13; row >= 0; row--) {

                for (int col = 0; col <= row; col++) {
                    colSum = triangle[row][col] + triangle[row + 1][col];
                    diaSum = triangle[row][col] + triangle[row + 1][col + 1];
                    if (colSum > diaSum) {
                        triangle[row][col] = colSum;
                    } else {
                        triangle[row][col] = diaSum;
                    }

                    colSum = 0;
                    diaSum = 0;
                }
            }
            System.out.println("largeSum:" + triangle[0][0]);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //prob. 13
    //Work out the first ten digits of the sum of the following one-hundred 50-digit numbers.
    public static void largeSum() {
        try {
            BigInteger bigNumSum = BigInteger.valueOf(0);
            File file = new File("/Users/cstauber77/Desktop/longassnumber.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                try {
                    BigInteger number = (BigInteger) scanner.nextBigInteger();
                    bigNumSum = bigNumSum.add(number);
                } catch (NumberFormatException e) {
                    continue;
                }
            }
            String numStrg = bigNumSum.toString();
            String subStrg = numStrg.substring(0, 10);
            System.out.print(subStrg);
            scanner.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //prob. 11
    //What is the greatest product of four adjacent numbers 
    //in the same direction (up, down, left, right, or diagonally) in the 20×20 grid?
    public static void largestProduct() {
        int[][] grid = new int[20][20];
        int productRow = 1;
        int productCol = 1;
        int productDia = 1;
        int productDia2 = 1;
        int greatestProduct = 1;

        File file = new File("/Users/cstauber77/Desktop/prob11.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                try {
                    for (int i = 0; i < 20; i++) {
                        for (int j = 0; j < 20; j++) {
                            grid[i][j] = scanner.nextInt();

//                            System.out.print(grid[i][j] + " ");
                        }
//                        System.out.println(""); 
                    }

                    for (int i = 0; i < 17; i++) {
                        for (int j = 0; j < 17; j++) {
                            productRow = grid[i][j] * grid[i][j + 1] * grid[i][j + 2] * grid[i][j + 3];
                            productCol = grid[i][j] * grid[i + 1][j] * grid[i + 2][j] * grid[i + 3][j];
                            productDia = grid[i][j] * grid[i + 1][j + 1] * grid[i + 2][j + 2] * grid[i + 3][j + 3];
                            if (i > 2) {
                                productDia2 = grid[i][j] * grid[i - 1][j + 1] * grid[i - 2][j + 2] * grid[i - 3][j + 3];
                            }
                            if (productDia2 > greatestProduct) {
                                greatestProduct = productDia2;
                            }
                            if (productRow > greatestProduct) {
                                greatestProduct = productRow;
                            }
                            if (productCol > greatestProduct) {
                                greatestProduct = productCol;
                            }
                            if (productDia > greatestProduct) {
                                greatestProduct = productDia;
                            }

                        }
                    }
                    System.out.println(greatestProduct);
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //prob. 25    
    //The Fibonacci sequence is defined by the recurrence relation:
    //
    //Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
    //Hence the first 12 terms will be:
    //
    //F1 = 1
    //F2 = 1
    //F3 = 2
    //F4 = 3
    //F5 = 5
    //F6 = 8
    //F7 = 13
    //F8 = 21
    //F9 = 34
    //F10 = 55
    //F11 = 89
    //F12 = 144
    //The 12th term, F12, is the first term to contain three digits.
    //What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
    public static void bigFibDig() {
        BigInteger bigFib = BigInteger.valueOf(1);
        BigInteger bigFib2 = BigInteger.valueOf(1);
        BigInteger temp2;

        int i = 2; //index will start at 3 (seq. number = 2)
        boolean flag = true;
        while (flag) {
            int count = 0;
            i++;
            BigInteger temp = bigFib2;
            bigFib2 = bigFib.add(bigFib2);
            bigFib = temp;

            String bigFibStrg = bigFib2.toString();
            if (bigFibStrg.length() == 1000) {
                System.out.print("index: " + i);
                flag = false;
            }
        }
    }

    //how is this handling 2%1? should be flagging? 
    public static void primeSum() {
        long sum = 0;
        boolean flag;

        for (int i = 2; i < 2000000; i++) {
            flag = true;
            for (int j = 2; j <= i / j; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                sum += i;
            }
        }
        System.out.print("prime sum " + sum);
    }

    public static void primeFinder() {
        int counter = 0;
        int num = 1;
        boolean flag;

        while (counter <= 10000) {
            num++;
            flag = true;
            for (int i = 2; i <= num / i; i++) {
                if (num % i == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                counter++;
                if (counter == 10001) {
                    System.out.print(num);
                }
            }

        }
    }

    public static boolean isPrime(int num) {

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        if (num <= 1) {
            return false;
        }

        return true;
    }
// prob. 27
//Euler discovered the remarkable quadratic formula:
//
//n² + n + 41
//
//It turns out that the formula will produce 40 primes for the consecutive values n = 0 to 39. 
//However, when n = 40, 402 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and certainly when n = 41, 41² + 41 + 41 
//is clearly divisible by 41.
//The incredible formula  n² − 79n + 1601 was discovered, which produces 80 primes for the consecutive values n = 0 to 79. 
//The product of the coefficients, −79 and 1601, is −126479.
//Considering quadratics of the form:
//
//n² + an + b, where |a| < 1000 and |b| < 1000
//
//where |n| is the modulus/absolute value of n
//e.g. |11| = 11 and |−4| = 4
//Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of primes 
//for consecutive values of n, starting with n = 0.
// TODO: Try the sieve of Atkin    

    public static void quadraticPrimes() {

        int maxN = 0;
        int maxA = 0;
        int maxB = 0;

        for (int a = -999; a < 1000; a++) {
            for (int b = -999; b < 1000; b++) {
                int n = 0;
                if ((a + b) % 2 == 0 && isPrime(b)) {
                    while (isPrime(n * n + a * n + b)) {
                        n++;
                    }
                }
                if (n > maxN) {
                    maxN = n;
                    maxA = a;
                    maxB = b;
                }

            }

        }
        System.out.print(maxA * maxB);
    }

    //prob. 16 
    //where does the long value of Math.pow(2,1000) come from? 
    public static void powerDigitSum() {

        BigInteger two = BigInteger.valueOf(2);
        BigInteger bigNum = two.pow(1000);
        BigInteger ten = BigInteger.valueOf(10);
        System.out.println(bigNum);

        BigInteger sum = BigInteger.valueOf(0);
        BigInteger zero = BigInteger.valueOf(0);
        boolean flag = true;
        while (flag) {
            sum = sum.add(bigNum.mod(ten));
            bigNum = bigNum.divide(ten);
            if (bigNum.equals(zero)) {
                flag = false;
            }
        }
        System.out.println(sum);
    }

    //prob. 17
    //If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
    //If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
    //NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters. 
    //The use of "and" when writing out numbers is in compliance with British usage.
    public static void numLetterCount() {

    }

    //prob. 12
    public static void divisibleTriangularNumber() {
        int counter = 0;
        int num = 0;
        int triNum;
        while (counter < 500) {
            counter = 2;
            triNum = 0;
            num++;
            for (int i = 1; i <= num; i++) {
                triNum += i;
            }
            //think about how this is working! why plus 2?  Doesn't work with square numbers? 
            for (int i = 2; i <= Math.sqrt(triNum); i++) {
                if (triNum % i == 0) {
                    counter += 2;
                    if (counter > 500) {
                        System.out.println(triNum);
                        break;
                    }
                }
            }
        }
    }

    //prob. 14
    //why does seq have to be a long? Can't be an int..
    public static void collatzSequence() {

        int largeChain = 0;
        int chain;
        int number = 0;

        long seq;

        for (int i = 2; i < 1000000; i++) {
            chain = 1;
            seq = i;
            while (seq != 1) {
                if ((seq % 2) == 0) {
                    seq = seq / 2;
                } else {
                    seq = (seq * 3) + 1;
                }
                chain++;
            }
//            System.out.println("num: " + i + "chain: " + chain);
            if (chain > largeChain) {
                largeChain = chain;
                number = i;
            }

        }
        System.out.println("chain: " + largeChain);
        System.out.println("number " + number);
    }

//prob. 19
//    1 Jan 1900 was a Monday.
//    Thirty days has September,
//    April, June and November.
//    All the rest have thirty-one,
//    Saving February alone,
//    Which has twenty-eight, rain or shine.
//    And on leap years, twenty-nine.
//    A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
//    How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
    //think about using enums! 
    public static void countingSundays() {
        int sundays = 0;
        int day = 365 % 7;
        for (int i = 1901; i <= 2000; i++) {
            for (int j = 0; j <= 11; j++) {
                if (j == 0 || j == 2 || j == 4 || j == 6 || j == 7 || j == 9 || j == 11) {
                    if ((day + (31 % 7)) == 6) {
                        sundays++;
                    }
                    day = (day + 31) % 7;
                } else if (j == 3 || j == 5 || j == 8 || j == 10) {
                    if ((day + (30 % 7)) == 6) {
                        sundays++;
                    }
                    day = (day + 30) % 7;
                } else if (j == 1 && i / 4 != 0) {
                    if ((day + (28 % 7)) == 6) {
                        sundays++;
                    }
                    day = (day + 28) % 7;

                } else if (j == 1 && i / 4 == 0) {
                    if (((day + (29) % 7)) == 6) {
                        sundays++;
                    }
                    day = (day + 29) % 7;
                }
            }
        }
        System.out.print("Sundays: " + sundays);

    }

    //prob. 20
    //sunday counting using the java's calendar api
    public static void countingSundays2() {
        //why no constructor? 
        LocalDate startDate = LocalDate.of(1901, Month.JANUARY, 1);
        LocalDate endDate = LocalDate.of(2000, Month.DECEMBER, 31);
        int sundays = 0;

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            //startDate = startDate.plusDays(1); 
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY && date.getDayOfMonth() == 1) {
                sundays++;
            }
        }
        System.out.print(sundays);
    }

    public static void factorialSum() {
        BigInteger product = BigInteger.valueOf(100);
        BigInteger sum = BigInteger.valueOf(0);
        boolean flag = true;
        for (int i = 100; i > 0; i--) {
            BigInteger num = BigInteger.valueOf(i);
            product = product.multiply(num);
        }
        while (flag) {
            BigInteger digit = product.mod(BigInteger.valueOf(10));
            sum = sum.add(digit);
            product = product.divide(BigInteger.valueOf(10));
            if (product.equals(BigInteger.valueOf(0))) {
                flag = false;
            }
        }
        System.out.println(product);
        System.out.println(sum);

    }

    //prob. 21
    //Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
    //If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.
    //For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
    //Evaluate the sum of all the amicable numbers under 10000.
    public static void amicableNumbers() {
        int sum = 0;
        int tempSum;
        int tempSum2;
        Stack<Integer> amicable = new Stack<>();
        for (int i = 1; i < 10000; i++) {
            tempSum = 0;
            tempSum2 = 0;
            for (int j = 1; j <= i / 2; j++) {
                if (i % j == 0) {
                    tempSum += j;
                }
            }
            for (int j = 1; j <= tempSum / 2; j++) {
                if (tempSum % j == 0) {
                    tempSum2 += j;
                }

            }
            if (tempSum2 == i && tempSum != i) {
                amicable.add(i);
//                amicable.add(tempSum); 
            }
        }
        for (int num : amicable) {
            sum += num;
            System.out.println("num " + num);
        }
        System.out.println("sum" + sum);
    }

    //If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
    //If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
    //NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters. 
    //The use of "and" when writing out numbers is in compliance with British usage.
    public static int numBetween20_100(int num) {
        int n = 0;
        int[] ones = {3, 3, 5, 4, 4, 3, 5, 5, 4};
        int[] teens = {3, 6, 6, 8, 8, 7, 7, 9, 8, 8};
        int[] tens = {0, 0, 6, 6, 5, 5, 5, 7, 6, 6};

        final int and = 3;
        final int hundred = 7;
        final int oneThousand = 11;
        int lastDig = num % 10;
        int firstDig = num / 10;
        for (int one = 0; one < ones.length; one++) {
            n += ones[one];
        }
        n *= (firstDig - 1);
        for (int teen = 0; teen < teens.length; teen++) {
            n += teens[teen];
        }
        for (int one = 0; one < lastDig; one++) {
            n += ones[one];
        }
        for (int ten = 0; ten < firstDig; ten++) {
            n += 10 * tens[ten];
        }
        n += (lastDig + 1) * tens[firstDig];

        return n;
    }

    public static void numLettersCount(int num) {
        int n = 0;

        int[] ones = {3, 3, 5, 4, 4, 3, 5, 5, 4};
        int[] teens = {3, 6, 6, 8, 8, 7, 7, 9, 8, 8};
        int[] tens = {0, 0, 6, 6, 5, 5, 5, 7, 6, 6};
        if (num < 10) {
            for (int one = 0; one < num; one++) {
                n += ones[one];
            }

        } else if (num >= 10 && num < 20) {
            for (int one = 0; one < ones.length; one++) {
                n += ones[one];
            }
            for (int teen = 0; teen < num % 10 + 1; teen++) {
                n += teens[teen];
            }
        } else if (num >= 20 && num < 100) {
            n = numBetween20_100(num);

        } else if (num >= 100 && num < 1000) {
            n = numBetween20_100(99);
            for (int one = 0; one < num % 10; one++) {

            }

        }
        System.out.print(n);
    }

//prob. 23
//A perfect number is a number for which the sum of its proper divisors is exactly equal to the number. For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.
//A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.
//As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of two abundant numbers is 24. 
//By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers. However, 
//this upper limit cannot be reduced any further by analysis even though it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.
//Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
    public static void nonAbundantSum() {
        ArrayList<Integer> abunNum = new ArrayList<>();
        HashSet<Integer> abunSum = new HashSet<>();
        abunNum.add(12);
        int majorSum = 0;
        for (int i = 13; i < 28123; i++) {
            int tempSum = 0;
            for (int div = 1; div <= i / 2; div++) {
                if (i % div == 0) {
                    tempSum += div;

                }
            }
            if (tempSum > i) {
                abunNum.add(i);
            }
        }
        for (int i = 0; i < abunNum.size(); i++) {
            int element1 = abunNum.get(i);
            for (int j = i; j < abunNum.size(); j++) {
                int temp = 0;
                int element2 = abunNum.get(j);
                temp = element1 + element2;
                if (temp > 28123) {
                    break;
                }

                abunSum.add(temp);
            }
        }
        for (int i = 1; i <= 28123; i++) {
            if (!abunSum.contains(i)) {
                majorSum += i;
            }
        }
        System.out.print(majorSum);

    }

//prob. 24
//A permutation is an ordered arrangement of objects. 
//For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4. 
//If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. 
//The lexicographic permutations of 0, 1 and 2 are: 012   021   102   120   201   210
//What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
    public static int factorial(int n) {
        int product = n;
        if (n == 0) {
            return 1;
        }
        for (int i = n - 1; i > 0; i--) {

            product *= i;

        }

        return product;
    }

    public static void lexPerm() {
        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
//        list.add(0, 0);
//        list.add(1, 0);
//        list.add(2, 0);
        System.out.print(list);
        ArrayList<Integer> milPerm = new ArrayList<>();
        //why can't this number be 1000000???
        int mil = 999999;

        for (int i = 9; i >= 0; i--) {
            int fact = factorial(i);
            int index = mil / fact;
            int num = list.remove(index);
            milPerm.add(num);
            mil = mil - (fact * index);

        }
        for (int num : milPerm) {
            System.out.print(num);
        }

    }
// prob. 26    
// A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:
// 1/2	= 	0.5
// 1/3	= 	0.(3)
// 1/4	= 	0.25
// 1/5	= 	0.2
// 1/6	= 	0.1(6)
// 1/7	= 	0.(142857)
// 1/8	= 	0.125
// 1/9	= 	0.(1)
// 1/10	= 	0.1
// Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.
//
// Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.

    public static void reciprocalCycles() {

        int count = 1;
        int longestCycle = 0;
        int D = 1;

        for (int d = 1000; d > 0; d--) {
            ArrayList<Integer> remainders = new ArrayList<>();
            int value = 1;
            for (int num = d; num > 0; num--) {
                int remainder = value % d;
                if (remainders.contains(remainder)) {
                    count = remainders.size();
                    if (count > longestCycle) {
                        longestCycle = count;
                        D = d;
                    }
                    break;

                }
                remainders.add(remainder);
                value = remainder * 10;

//            System.out.print(remainder);
//            
//            if(count > longestCycle){
//                longestCycle = count; 
//                D = num;
//            }
            }

        }
        System.out.print(longestCycle);
        System.out.println(" ");
        System.out.print(D);
//    

    }

//prob. 29
//Consider all integer combinations of a^b for 2 ≤ a ≤ 5 and 2 ≤ b ≤ 5:
//
//22=4, 23=8, 24=16, 25=32
//32=9, 33=27, 34=81, 35=243
//42=16, 43=64, 44=256, 45=1024
//52=25, 53=125, 54=625, 55=3125
//If they are then placed in numerical order, with any repeats removed, 
//we get the following sequence of 15 distinct terms:
//
//4, 8, 9, 16, 25, 27, 32, 64, 81, 125, 243, 256, 625, 1024, 3125
//
//How many distinct terms are in the sequence generated by a^b for 2 ≤ a ≤ 100 and 2 ≤ b ≤ 100?
    
    public static double powerCombos(){
        long startTime = System.currentTimeMillis();
        Set<Double> doubleCombos = new HashSet<>();
        for(double a = 2; a<= 100; a++){
            for(double b=2; b<= 100; b++){
                double combo = Math.pow(a, b);
                doubleCombos.add(combo);
                 
            }
        }
        long endTime = System.currentTimeMillis();
        
        System.out.println(endTime - startTime);
                
        
        return doubleCombos.size();
    }
    
//prob. 30
//Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:
//
//1634 = 1^4 + 6^4 + 3^4 + 4^4
//8208 = 8^4 + 2^4 + 0^4 + 8^4
//9474 = 9^4 + 4^4 + 7^4 + 4^4
//As 1 = 1^4 is not a sum it is not included.
//
//The sum of these numbers is 1634 + 8208 + 9474 = 19316.
//
//Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
           
public static boolean fifthPowers(int n){
    int num = n;
    int sum = 0;
    while(num > 0){
        int lastDig = num%10;
//        System.out.println("last: " + lastDig);
        int fifthPower = (int)( Math.pow(lastDig, 5) );
//        System.out.println("Pow: " + fifthPower);
        sum += fifthPower; 
        num = num/10; 
//        System.out.println("num: " + num);
        
    }
    if(sum == n){
        return true;
    }
    
    return false;
}

public static int fifthPowersSum(){
    int sum = 0;
    for(int i = 2; i <= Math.pow(9, 5) *5; i++){
        if(fifthPowers(i)){
            sum += i;
        }
    }
    return sum;
}

//****************************************************************************
    
public static String startOz(String str) {
  if(str.length() >= 1 && str.substring(0,1).equals('o')){
     if(str.length() >=2 && str.substring(1,2).equals('z')){
         return "oz";
     }
     System.out.print("hi");
     return "o";
     
  } 
  System.out.print(str.substring(0,1));
  System.out.print(str.substring(0,1).equals('o'));
  return "";
}

public static ArrayList primeArray(int bound) {
        boolean flag;
        ArrayList<Integer> primes = new ArrayList<>();

        for (int i = 2; i < bound; i++) {
            flag = true;
            //**don't quite get this line**
            for (int j = 2; j <= i / j; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                
                primes.add(i);
            }
        }

        return primes; 
        
    }
 


//prob. 35
//The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, 
//are themselves prime.
//There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
//How many circular primes are there below one million?

public static int circularPrime(){
    ArrayList<Integer> primes = primeArray(1000000);
    int count = 0;
    for(int prime : primes){
        if(isCircularPrime(prime)){
            count++;
        }
        
        
    }
    
    System.out.print(count);
    return 0;
}

public static boolean isCircularPrime(int prime){
    String num = Integer.toString(prime);
    String lastDig = num.substring(num.length() -1); 
    String num1 = num.substring(0, num.length() -1); 
    
    int newNum = Integer.parseInt(lastDig + num1);
    
    boolean flag = true;
    
   
    
    while(newNum != prime){
       if(!isPrime(newNum)){
          flag = false; 
          break;
       }
       else{
           num = Integer.toString(newNum);
           lastDig = num.substring(num.length() -1); 
           num1 = num.substring(0, num.length() -1); 
    
           newNum = Integer.parseInt(lastDig + num1);
         
       }
       
    }
    
   return flag;
    
}
//*****************************************************************************


//Problem 32
//We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; 
//for example, the 5-digit number, 15234, is 1 through 5 pandigital.
//The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.
//Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.
//HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.


public static int panDigitalSum(){
    
    int sum = 0;
    ArrayList<Integer> products = new ArrayList<>(); 
    for(int a = 1; a < 5000; a++){
        for(int b = 1; b < 5000; b++){
            int num = a*b ;
            String numStr = Integer.toString(a) + Integer.toString(b) + Integer.toString(num); 
            if(numStr.length() == 9){
              int num1 = Integer.parseInt(numStr); 
//              System.out.println(num1);
              if(isPanDigital9(num1) && !products.contains(num)){
                  products.add(num);
                  sum += num; 
              }
            }
       }
    }
    for(int i = 0; i < products.size(); i++){
        System.out.println(products.get(i)) ;
    }
        
    
    return sum;
}

public static boolean isPanDigital9(int num){
    int count = 0;
    ArrayList<Integer> digits = new ArrayList<>();
    while(num > 0){
        int digit = num%10;
        if (!digits.contains(digit) && digit != 0){
            digits.add(digit);
        }
        else{
            return false;
        }
        num = num/10;
        count++;
    }
    if(count == 9){
        return true;
    }
    return false;
}

public static boolean isPanDigital(int num){
    int count = 0;
    ArrayList<Integer> digits = new ArrayList<>();
    while(num > 0){
        int digit = num%10;
        if (!digits.contains(digit) && digit != 0){
            digits.add(digit);
        }
        num = num/10;
        count++;
        
        //98765431
    }
    if(count == digits.size() && Collections.max(digits) == digits.size()){
        return true;
    }
    return false;
}

//prob. 39
//If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.
//{20,48,52}, {24,45,51}, {30,40,50}
//For which value of p ≤ 1000, is the number of solutions maximised?

//prob. 34
//145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
//Find the sum of all numbers which are equal to the sum of the factorial of their digits.
//Note: as 1! = 1 and 2! = 2 are not sums they are not included.

public static boolean digitFactorial(int n){
    int sum = 0;
    int num = n;
    while (num>0){
        int lastDigit = num%10;
//        System.out.println("last" + lastDigit);
        sum = sum + factorial(lastDigit);
        
        
              
        num = num/10;
//        System.out.println("num" + num);
        
    }
    if(sum == n){
        return true;
    }
    return false;
}

public static void factorialTable(){
    int[] factorialArray = new int[10]; 
    for(int i =0; i < 10; i++){
        factorialArray[i] = factorial(i);
        
        
    }
    for(int num =0; num < factorialArray.length; num++){
        System.out.println(factorialArray[num]);
    }
    //return factorialArray; 
}

public static int digitFactorialSum(){
    int sum =0;
    for(int i = 3; i <= factorial(9) * 7; i++){
        if(digitFactorial(i) ){
            sum += i;
        }
    }
    
    
    return sum;
}

//prob. 36
//The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.
//Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
//(Please note that the palindromic number, in either base, may not include leading zeros.)

public static int isPalindrome(){
    int sum = 0;
    for(int num = 1; num < 1000000; num++){
        String binary = Integer.toBinaryString(num); 
        String numStr = Integer.toString(num);
        if(numStr.equals(numReverse(numStr)) && binary.equals(numReverse(binary))){
            sum += num;
        }
    }
    return sum;
}
public static String numReverse(String numStr){
    String numRev = "";
    for(int i = numStr.length() - 1; i >= 0; i -- ){
        numRev += numStr.charAt(i);
    }
    return numRev;
}
//public static int doubleBasePalindrome(){
//    
//    for(int i = 1; i < 1000000; i++){
//        if
//    }
//    
//}

//prob. 22
//Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first names, 
//begin by sorting it into alphabetical order. 
//Then working out the alphabetical value for each name, multiply this value by its alphabetical position in the list to obtain a name score.
//For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. 
//So, COLIN would obtain a score of 938 × 53 = 49714.
//What is the total of all the name scores in the file?

public static int nameScores() {
        int total = 0;
        String [] nameList = new String[0];
        try {
            File file = new File("/Users/chelsBot/Desktop/name.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNext()){
                String line = sc.nextLine();
                nameList = line.split(",");
//                for(int i = 0; i <= nameList.length -1; i++){
//                    System.out.print(nameList[i]);
//                }
                 Arrays.sort(nameList); 
                
            }
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(int i = 0; i <= nameList.length -1; i++){
                    //System.out.print(nameList[i]);
                    String name = nameList[i];
                    //System.out.println(name);
                    int value = 0;
                    for(int j = 1; j <= name.length() - 2; j++){
                        int ASCII = (int) (name.charAt(j) - 64);
                        //System.out.println(ASCII);
                        value += ASCII;
                        
                    }
                    //System.out.println(value);
                    total += (value * (i +1));
                    //System.out.println(total);
                    
                }
         
       System.out.println(nameList[5163 - 1]);        
        return total;        
}

//prob. 33
//The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.
//We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
//There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two digits in the numerator and denominator.
//If the product of these four fractions is given in its lowest common terms, find the value of the denominator.

public static double digitCanceling(){
    int productDom = 1;
    int productNum = 1;
    int count = 0;
    
    for(int i = 10; i <= 98; i++){
            //System.out.println("num i: " + i);
            int lastDigNum = i%10;
            //System.out.println(lastDigNum);
            int firstDigNum = i/10;
            //System.out.println(firstDigNum);
            
        for(int j = i + 1; j <= 99; j++){
            
            //System.out.println("num j: " + j);
            int lastDigDom = j%10;
            //System.out.println(lastDigDom);
            int firstDigDom = j/10; 
            //System.out.println(firstDigNum);
            double flo = (double) i/j;
            
            
            if ((lastDigNum == firstDigDom) && !(lastDigNum == 0 && lastDigDom == 0))  {
                //System.out.println(flo);
                double smalFlo = (double) firstDigNum/lastDigDom;
                if(flo == smalFlo ){
                    count++;
                    productDom *= j;
                    productNum *= i;
                }
                
            }
            else if((lastDigNum == lastDigDom) && !(lastDigNum == 0 && lastDigDom == 0)){
                double smalFlo = (double) firstDigNum/firstDigDom;
                if(flo == smalFlo){
                    count++;
                    productDom *= j;
                    productNum *= i;
                }
            }
            else if((firstDigNum == lastDigDom) && !(lastDigNum == 0 && lastDigDom == 0)){
                double smalFlo = (double) lastDigNum/firstDigDom;
                if(flo == smalFlo){
                    count++;
                    productDom *=j;
                    productNum *=i;
                }
            }
            else if((firstDigNum == firstDigDom) && !(lastDigNum == 0 && lastDigDom == 0)){
                double smalFlo = (double) lastDigNum/lastDigDom;
                if(flo == smalFlo){
                    count++;
                    productDom *= j;
                    productNum *=i;
                }
                
            }
            
            
        }
    }
    double ans = (double)productDom/productNum;
    return ans;
}

//The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right, 
//and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.
//Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
//NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.

//prob. 37

public static boolean truncatedPrimes(int num){
    int count = 0;
    
    int count2 = 0;
    
    String numStr = Integer.toString(num); 
    int len = numStr.length();
    
   
       
        while(isPrime(num)){
            num = num/10;
            count++;
        }
        int order = (int) Math.pow(10, len -1); 
//      
        int num2 = Integer.parseInt(numStr); 
        
        while(isPrime(num2)){
            //System.out.println("Order: " + order);
            int frontDig = num2/order;
            num2 = num2 - frontDig*order;
            //System.out.println("num: " + num2);
            order = order/10; 
            count2++;
           
        }
        if(count2 == len && count == len ){
            return true;
        }
      
    return false;
    
}

public static int truncPrimesSum(){
    int sum = 0;
    for(int i = 10; i < 1000000; i++){
        if (truncatedPrimes(i)){
            System.out.println(i);
            sum = sum + i;
        }
        
    }
    return sum;
    
}

//Take the number 192 and multiply it by each of 1, 2, and 3:
//192 × 1 = 192
//192 × 2 = 384
//192 × 3 = 576
//By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated product of 192 and (1,2,3)
//The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918273645, 
//which is the concatenated product of 9 and (1,2,3,4,5).
//What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) 
//where n > 1?

//prob. 38
//TODO: make this more generic. Pass in n. 
public static String pandigitalMultiples(){
    String digCompare = "123456789";
    String biggest = "";
    for(int i = 9000; i < 10000; i++){
       String part1 = Integer.toString(i * 1);
       String part2 = Integer.toString(i*2);
       String tot = part1 + part2;
       char[] chars = tot.toCharArray();
       Arrays.sort(chars);
       String sorted = new String(chars);
       //System.out.println(sorted);
       if(sorted.equals(digCompare)){
           biggest = tot;
       }
    }
    return biggest;
}   

//If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.
//{20,48,52}, {24,45,51}, {30,40,50}
//For which value of p ≤ 1000, is the number of solutions maximised?

//prob. 39
public static int intRightTriangle(){
    
    int maxTriCount = 0;
    int maxP = 0;
    for(int p = 2; p <= 1000; p++){
        int triCount = 0;
        for(int a = 2; a < p/3; a ++){
            if((p*p - 2*p*a) % (2*(p -a)) == 0){
                triCount++;
                System.out.print(a + " ");
                int b = (p*p - 2*p*a) / (2*(p -a));
                System.out.print(b + " ");
                int c = (int) Math.sqrt(a*a + b*b);
                System.out.print(c + " ");
                System.out.print("p: " + p + " " );
                System.out.println();
                
        
                
            }
        }
        if(triCount > maxTriCount){
            maxTriCount = triCount;
            maxP = p;
        }
    }
    
    return maxP; 
}

//We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. 
//For example, 2143 is a 4-digit pandigital and is also prime.
//What is the largest n-digit pandigital prime that exists?

//prob. 41

public static int pandigitalPrime(){  
    
    int maxPanPrime = 0;
    for(int i = 1; i <= 7654321; i+=2){
        if(isPanDigital(i) && isPrime(i)){
            maxPanPrime = i; 
        }
   }
   return maxPanPrime;
}

//The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:
//1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
//By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we form a word value. 
//For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle number then we shall call the word a triangle word.
//Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common English words, how many are 
//triangle words?

//prob. 42

public static int codedTriNums() {
    String [] nameList = new String[0];
    int triWords = 0;
        try {
            File file = new File("/Users/chelsBot/Desktop/prob42_words.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNext()){
                String line = sc.nextLine();
                nameList = line.split(",");
//                for(int i = 0; i <= nameList.length -1; i++){
//                    System.out.print(nameList[i]);
//                }
                 
                
            }
            System.out.println(nameList[2]);
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0; i <= nameList.length -1; i++){
            //System.out.print(nameList[i]);
            String name = nameList[i];
            //System.out.println(name);
            int value = 0;
            for(int j = 1; j <= name.length() - 2; j++){
                int ASCII = (int) (name.charAt(j) - 64);
                //System.out.println(ASCII);
                value += ASCII;

            }
            //System.out.println(value);
            if ((-1 + Math.sqrt(1 + 8*value))/2 == (int) (-1 + Math.sqrt(1 + 8*value))/2){
                triWords++;
            }

        }
        
    
    return triWords;
}

//The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has 
//a rather interesting sub-string divisibility property.
//
//Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:
//
//d2d3d4=406 is divisible by 2
//d3d4d5=063 is divisible by 3
//d4d5d6=635 is divisible by 5
//d5d6d7=357 is divisible by 7
//d6d7d8=572 is divisible by 11
//d7d8d9=728 is divisible by 13
//d8d9d10=289 is divisible by 17
//Find the sum of all 0 to 9 pandigital numbers with this property.

//prob. 43

public static boolean isPanDigitBigInt9(BigInteger num){
    int count = 0;
    ArrayList<BigInteger> digits = new ArrayList<>();
    while(num.compareTo(BigInteger.valueOf(0)) == 1 && count <= 10){
        BigInteger digit = num.mod(BigInteger.valueOf(10));
        System.out.println(digit);
        if (!digits.contains(digit)){
            digits.add(digit);
            System.out.print("hi");
        }
        else{
            System.out.print("fuck");
            return false;
         
        }
        num = num.divide(BigInteger.valueOf(10));
        System.out.println(num);
        count++;
        
    }
    
    System.out.println(count);
    if(count == 10){
        return true;
    }
    return false;
}

//prob. 44 Pentagon Numbers
public static int pentagonNumber(){
    Integer[] pents = new Integer[3000];
    int min = 0;
    for(int i = 0; i <= pents.length - 1    ; i++){
        int P = i*(3*i - 1)/ 2;
        pents[i] = P;    
    }
    outerloop:
    for(int i = 1; i <= pents.length - 2; i++){
        for(int j = i + 1; j <= pents.length - 1 ; j++){
            int sum = pents[j] + pents[i];
            int minus = pents[j] - pents[i];
//            System.out.println(Arrays.asList(pents).indexOf(sum));
            if(Arrays.asList(pents).indexOf(sum) != -1 && Arrays.asList(pents).indexOf(minus) != -1){
//                System.out.println(minus);
                System.out.print(minus);
                break outerloop;
            }
        }
    }
    return 0; 
}

public static int panDigitDivisibility() {
    
    //for(double i = 0123456789; i <= 9876543210; i++){
        
    //}
    
    return 0;
}
    
public static void main(String[] args) {
    //BigInteger num = new BigInteger("0123456789");
    //System.out.print(isPanDigitBigInt9(num));
//    System.out.print(pandigitalPrime());
 
    pentagonNumber();
//    int Asc = 'A';
//    System.out.print(Asc);
//    String name2 = "COLIN";
//    int value = 0;
//    for(int j = 0; j <= name2.length() - 1; j++){
//        int ASC = (int) name2.charAt(j) - 64;
//            value += ASC;
//                        
//        }
//        System.out.print(value * 938);
//              
    }
}
