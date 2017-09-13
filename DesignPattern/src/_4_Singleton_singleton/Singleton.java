package _4_Singleton_singleton;

public class Singleton {
	
	private static Singleton uniqeInstance=null;
	
	private Singleton(){
		
	};
	
	public static Singleton getInstance()
	{
		if(uniqeInstance==null)
		{
			uniqeInstance=new Singleton();
		}
		return uniqeInstance;
		
	}

}
