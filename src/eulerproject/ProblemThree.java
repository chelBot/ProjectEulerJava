/*
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 */
package eulerproject;

import java.util.Stack;

/**
 *
 * @author cstauber77
 */
public class ProblemThree {
    public static void primeFinder(long num){
        long largestPrime = 0;
        Stack<Long> factors = new Stack<Long>();       
        for(long i =1; i <= Math.sqrt(num); i+=2){
            if(num % i == 0){
                factors.push(i); 
            }
        }  
        while(true){
            boolean flag = true;
            long factor = factors.pop();
            for(long i = 2; i < Math.sqrt(factor); i++){
                if(factor%i ==0){
                    flag = false;
                    break;
                }
            }
            if(flag){
                System.out.print(factor); 
                break;
            }
        }
    }
}

        

    

