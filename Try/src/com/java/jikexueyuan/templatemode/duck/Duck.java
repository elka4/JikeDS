package com.java.jikexueyuan.templatemode.duck;

public class Duck implements Comparable{
	String name="";
	int weight=1;
	public Duck(String name,int weight)
	{
		this.name=name;
		this.weight=weight;
	}
	public String toString()
	{
		return name+" weight:"+weight;
	}
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		Duck buf=(Duck)arg0;
		if(this.weight>buf.weight)
		{
			return 1;
		}else if(this.weight<buf.weight)
		{
			return -1;
		}
		return 0;
	}

}
