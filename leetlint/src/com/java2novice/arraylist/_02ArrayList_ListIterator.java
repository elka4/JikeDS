package com.java2novice.arraylist;

import java.util.ArrayList;
import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;
 /*Here we can see example for reading all elements from ArrayList
by using Iterator. Also you can iterate through the ArrayList based
 on index too. 
 - See more at: http://www.java2novice.com/java-collections-and-util/arraylist/iterator/#sthash.nwnFFcHW.dpuf*/
public class _02ArrayList_ListIterator {
	ArrayList<String> arr;
    public static void main(String a[]){
    	ArrayList<String> arrl = new ArrayList<String>();
        //adding elements to the end
        arrl.add("First");
        arrl.add("Second");
        arrl.add("Third");
        arrl.add("Random");
        ListIterator<String> itr = arrl.listIterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        
        /*while(itr.hasNext()){
	        	if (itr.next() == "First") {
	        		itr.remove();
	        	}
	        	System.out.println(itr.next());
        }*/
        
    }
    
    @Before
    public void before() {
    		arr = new ArrayList<String>();
    		arr.add("First");
        arr.add("Second");
        arr.add("Third");
        arr.add("Random");
    }
    
    @Test
    public void testPre() {
    		ListIterator<String> itr = arr.listIterator();
    		if(itr.hasNext()){
           System.out.println(itr.next());
        }
    		if(itr.hasNext()){
    	       System.out.println(itr.next());
    	    }
    		if(itr.hasNext()){
     	     System.out.println(itr.next());
     	}
    		if(itr.hasPrevious()){
    			System.out.println(itr.previous());
    		}
    		if(itr.hasPrevious()){
    			System.out.println(itr.previous());
    		}
    		if(itr.hasPrevious()){
    			System.out.println(itr.previous());
    		}
    		
    }
}
