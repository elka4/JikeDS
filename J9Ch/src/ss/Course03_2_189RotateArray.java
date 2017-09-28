package ss;

import org.junit.Test;

public class Course03_2_189RotateArray {

	public void swap(int[] array,int a,int b){
		int t=array[a];
		array[a]=array[b];
		array[b]=t;
	}

	public void printArray(int[] array){
		for(int i:array){
			System.out.print(i+" ");
		}
		System.out.println();
	}

	/**
	 * 逆置数组的start到end部分
	 */
	public void reverse(int[] array,int start,int end){
		if(array==null||array.length<=1){
			return;
		}
		for(int i=start,j=end;i<j;i++,j--){
			swap(array, i, j);
		}
	}

    /**
     * 旋转数组
     */
    public void rotate(int[] nums, int k) {
            if(k==0){
                return ;
            }
            int n=nums.length;
            if(k>n){
                k=k%n;
            }
            reverse(nums, 0, n-1-k);
            reverse(nums, n-k, n-1);
            reverse(nums, 0, n-1);
    }


    @Test
    public void test(){
        int[] nums={1,2,3,4,5,6,7,8,9,10};
        printArray(nums);
        rotate(nums, 4);
        printArray(nums);
    }
}
