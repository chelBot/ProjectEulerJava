/*
 * The sum of the squares of the first ten natural numbers is,
 * 1^2 + 2^2 + ... + 10^2 = 385
 * The square of the sum of the first ten natural numbers is,
 * (1 + 2 + ... + 10)^2 = 552 = 3025
 * Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.
 *  Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 */
package eulerproject;

/**
 *
 * @author cstauber77
 */
public class ProblemSix {
    public int sumOfSquares(){
        int sumOfSquares =0;
        for(int i = 1; i <= 100; i++){
            sumOfSquares += i*i; 
        }
        return sumOfSquares;
    }
    public int squareOfSum(){
        int squareOfSum = 0;
        for(int i = 1; i <= 100; i++){
            squareOfSum += i;
        }
        return squareOfSum*squareOfSum;
    }
    public void difference(){
        int sumOfSquares = sumOfSquares();
        int squareOfSum = squareOfSum();
        System.out.print(squareOfSum - sumOfSquares);
    }
    
}
