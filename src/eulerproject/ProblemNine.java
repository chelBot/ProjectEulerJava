/*
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 * a^2 + b^2 = c^2
 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 */
package eulerproject;

/**
 *
 * @author cstauber77
 */
public class ProblemNine {
    public void tripletFinder(){
        for(int c = 1; c < 1000; c++)
            for(int b =1; b < c; b++)
                for(int a = 1; a < c; a++){
                    if((a*a + b*b) == c*c && (a + b +c) == 1000){
                        System.out.print(a + " " + b + " " +c);
                        System.out.println(a*b*c);
                    }
                }
    }
    
    
}
