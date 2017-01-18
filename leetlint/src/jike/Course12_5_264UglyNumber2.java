package jike;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class Course12_5_264UglyNumber2{
  public boolean isUgly(int num) {
      int n=num;
      if(n<=0){
    	  return false;
      }
      if(n==1){
    	  return true;
      }
      while(n%2==0){
    	  n/=2;
      }
      while(n%3==0){
    	  n/=3;
      }
      while(n%5==0){
    	  n/=5;
      }
      if(n==1){
    	  return true;
      }else{
    	  return false;
      }
  }
  public int nthUglyNumberBruteForce(int n){
	  int count=1;
	  int i=1;
	  while(true){
		  if(isUgly(i)){
			  if(count==n){
				  return i;
			  }
			  count++;
		  }
		  i++;
	  }
  }
  @Test
  public void testBruteForce(){
	  //System.out.println(nthUglyNumberBruteForce(20));
	  //System.out.println(nthUglyNumberBruteForce(1352));
	  System.out.println(402653184*28);
  }
  public int nthUglyNumberQueue(int n){
	  int count=1;
	  int i=1;
	  Queue<Integer> q2=new LinkedList<Integer>();
	  Queue<Integer> q3=new LinkedList<Integer>();
	  Queue<Integer> q5=new LinkedList<Integer>();
	  while(count<n){
		  q2.offer(i*2);
		  q3.offer(i*3);
		  q5.offer(i*5);
		  int min2=q2.peek();
		  int min3=q3.peek();
		  int min5=q5.peek();
		  int min=Math.min(Math.min(min2, min3), min5);
		  if(min2==min){
			  q2.poll();
		  }
		  if(min3==min){
			  q3.poll();
		  }
		  if(min5==min){
			  q5.poll();
		  }
		  i=min;
		  count++;
	  }
	  return i;
  }
  @Test
  public void testQueue(){
	  System.out.println(nthUglyNumberQueue(20));
	  System.out.println(nthUglyNumberQueue(1352));
  }
}
