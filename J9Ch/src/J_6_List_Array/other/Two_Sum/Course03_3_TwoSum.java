package J_6_List_Array.other.Two_Sum;

import org.junit.Test;

import java.util.Arrays;

public class Course03_3_TwoSum {
public void printResult(int a,int b){
	System.out.println("["+a+","+b+"]");
}
    /**
     * 平方复杂度的算法
     */
    public void twoSum01(int[] array,int s){
        int n=array.length;
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(array[i]+array[j]==s){
                    printResult(array[i], array[j]);
                    break;
                }
            }
        }
    }

//------------------------------------------------------------------------------

    /**
     * 线性复杂度的算法
     */
    public void twoSum02(int[] array,int s){
        int i=0;
        int j=array.length-1;
        int sum=0;
        while(i<j){
            sum=array[i]+array[j];
            if(sum==s){
                printResult(array[i], array[j]);
                i++;
                j--;
            }else if(sum<s){
                i++;
            }else{
                j--;
            }
        }
    }

//------------------------------------------------------------------------------

    /**
     * NlogN的算法 借助于JDK的二分查找
     */
    public void twoSum03(int[] array,int s){
        int n=array.length;
        for(int i=0;i<n-1;i++){
            int another=s-array[i];
            if(Arrays.binarySearch(array, i+1,n-1,another)>=i+1){
                printResult(array[i], another);
            }
        }
    }

//------------------------------------------------------------------------------

    @Test
    public void test(){
        int[] array={1,3,4,5,8,9,11};
        int s=13;
        twoSum01(array, s);
        System.out.println("----");
        twoSum02(array, s);
        System.out.println("----");
        twoSum03(array, s);
    }
}
