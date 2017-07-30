package J_6_Linked_List_Array.other.Two_Sum;

import org.junit.Test;

/**
 *和为s的连续正整数序列
 */
public class Course03_4_SeriesSum {
    public void printResult(int start,int end){
        for(int k=start;k<=end;k++){
            System.out.print(k+" ");
        }
        System.out.println();
    }

    public void addSum(int s){
        int start=1,end=2;
        int sum=start+end;
        int half=(s+1)/2;
        while(start<half){
            if(sum==s){
                printResult(start, end);
                sum=sum-start;
                start++;
                end++;
                sum+=end;
            }else if(sum<s){
                end++;
                sum+=end;
            }else{
                sum=sum-start;
                start++;
            }
        }
    }

    @Test
    public void test(){
        addSum(21);
        System.out.println();
        addSum(150);
    }
}
