package ss;

import org.junit.Test;

/**
 * 最后一个单词的长度
 *
 */
public class Course05_5_058LengthOfLastWord {
	  public int lengthOfLastWord(String s) {
	       if(s==null||s.length()==0){
	    	   return 0;
	       }else{
	    	   int count=0;
	    	   int i=s.length()-1;
	    	   for(;i>=0;i--){
	    		   if(s.charAt(i)==' '){
	    			   
	    		   }else{
	    			   break;
	    		   }
	    	   }
	    	   for(;i>=0;i--){
	    		   if(s.charAt(i)==' '){
	    			   break;
	    		   }else{
	    			   count++;
	    		   }
	    	   }
	    	   return count;
	       }
	    }
	  @Test
	  public void test(){
		  String str="   Thank you very much    ";
		  System.out.println(lengthOfLastWord(str));
	  }
}
