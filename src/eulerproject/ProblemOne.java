/*
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * Find the sum of all the multiples of 3 or 5 below 1000.
 */
package eulerproject;

/**
 *
 * @author cstauber77
 */
public class ProblemOne {

    
    
    public static void multiplesOf_3_5() {
        // TODO code application logic here
        int sum = 0;
        for(int i = 1; i < 1000; i++){
            if(i%3 == 0 || i%5 == 0){
                sum += i;
            }
        }
        
       System.out.print("sum: " + sum); 
        
    }
    
}
