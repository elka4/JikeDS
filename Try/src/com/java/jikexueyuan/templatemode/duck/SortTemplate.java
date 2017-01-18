package com.java.jikexueyuan.templatemode.duck;

public  abstract class SortTemplate {
	
	public final void sort(Object[] objects) 
	{
		
		for(int i=0,len=objects.length-1;i<len;i++)
		{
			
			
			if(campare(objects[i+1])>0)
			{
				//swap();
			}else{
				
			}
			
		}
		
		
	}
	
	public abstract int campare(Object object);
	
}
