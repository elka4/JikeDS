package s_4_TwoPointer;

public class TwoSum_Conclusion {
	int[] A = null;
	int i = 0; 
	int j = A.length;
	int sum;
	void twoSum(){
		if (A[i]  + A[j] > sum) {
			j--;
			//do something
		} else if (A[i] + A[j] < sum) {
			i++;
			//do something
		} else {
			//do something
			//i++ or j--
		}
	}
	
	//灌水
	void Container() {
		if (A[i] > A[j]) {
			j--;
		} else if (A[i] < A[j]) {
			i++;
		} else {
			//i++; or j--;
		}
	}
	
	//这一类通过对撞型指针优化算法，根本上其实要证明就是不用扫描多余状态
	/*
	 * if(考虑A［i］和A［j］满足某个条件)
	 * 	j--;//不用考虑［i+1, j-1］和j组成的pair
	 * do something
	 * else if (考虑A［i］和A［j］不满足某个条件)
		i++;//不用考虑i和［i+1, j-1］组成的pair
	 	else
	 		do somehting
	 		i++ or j--
	  */
}
