#include <stdio.h>
#include <string.h>
	int lengthOfLastWord(char* s) {
	       if(s==NULL||strlen(s)==0){
	    	   return 0;
	       }else{
	    	   int count=0;
	    	   int i=strlen(s)-1;
	    	   for(;i>=0;i--){
	    		   if(s[i]==' '){

	    		   }else{
	    			   break;
	    		   }
	    	   }
	    	   for(;i>=0;i--){
	    		   if(s[i]==' '){
	    			   break;
	    		   }else{
	    			   count++;
	    		   }
	    	   }
	    	   return count;
	       }
	    }
	int main(int argc, char **argv) {
		char* str="   Thank you very much    ";
		printf("%d\n",lengthOfLastWord(str));
		return 0;
	}
