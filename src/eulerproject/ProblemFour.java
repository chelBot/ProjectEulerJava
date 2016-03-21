/*
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
package eulerproject;

import static java.lang.Character.digit;
import java.util.Stack;

/**
 *
 * @author cstauber77
 */
public class ProblemFour {
    public static void palindrome(){
        int num = 0;
        int pal = 0;
        for(int i = 999; i >= 0; i--){
            for(int j = i; j >=0; j--){
                int digit = 0; 
                num = i *j;
                int temp = num;
                while (num > 0) {
                    digit = (10* digit) + (num%10); 
                    num = num / 10;
                }
                //Shouldn't the first time this condition is true be sufficient?
                if((digit-temp) == 0 && digit > pal){
                    pal = digit; 
                    break;
                    
                }
            }
        }
        System.out.print(pal);
    }
    
}
