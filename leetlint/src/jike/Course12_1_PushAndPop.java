package jike;

import java.util.Stack;

import org.junit.Test;

public class Course12_1_PushAndPop {
	public boolean checkValid(Integer[] pushSequence,Integer[] popSequence){
		Stack<Integer> stack=new Stack<Integer>();
		int i=0;
		for(Integer k:popSequence){
			if(!stack.isEmpty()&&stack.peek().equals(k)){
				stack.pop();
			}else{
				while(true){
					if(i>=pushSequence.length){
						return false;
					}
					if(pushSequence[i].equals(k)){
						i++;
						break;
					}else{
						stack.push(pushSequence[i]);
						i++;
					}
				}
			}
		}
		return true;
	}
	@Test
	public void test(){
		Integer[] pushSequence={1,2,3,4,5};
		Integer[] popSequence1={4,5,3,2,1};
		Integer[] popSequence2={4,3,5,1,2};
		Integer[] popSequence3={3,5,4,2,1};
		boolean flag1=checkValid(pushSequence, popSequence1);
		boolean flag2=checkValid(pushSequence, popSequence2);
		boolean flag3=checkValid(pushSequence, popSequence3);
		System.out.println(flag1+" "+flag2+" "+flag3);
	}
}
