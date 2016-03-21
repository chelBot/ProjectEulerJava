/*
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
package eulerproject;

/**
 *
 * @author cstauber77
 */
public class ProblemFive {
    
    long bigNum = 1; 
    public void createNumber(){
        for(int i = 1; i <= 20; i++){
            bigNum = bigNum * i; 
        }
        
    }
    public void findSmallest(){
        createNumber(); 
        boolean flag = true;
        long smallNum = bigNum;
        int k = 20;
       
        for(long i = bigNum; i> 0; i = i/k){
            for(int j = 19; j >10; j--){
                if(i%j != 0){
                    flag = false;
                    break; 
                }
            }
            if(flag ==true && i < smallNum){
                smallNum = i;
                //bigNum = i;
               k--;
            }
        }
        
        
        System.out.println(smallNum);
        System.out.println(bigNum);
    }
    
}
    

