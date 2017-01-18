package com.java.jikexueyuan.templatemode.hotdrink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TeaWithHook extends HotDrinkTemplate{

	@Override
	public void brew() {
		// TODO Auto-generated method stub
		System.out.println("Brewing tea");
	}

	@Override
	public void addCondiments() {
		// TODO Auto-generated method stub
		System.out.println("Adding lemon");
	}
	@Override
	public boolean wantCondimentsHook()
	{
		System.out.println("Condiments,yes or no?please input(y/n):");
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String result="";
		try {
			result=in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result.equals("n"))
		{
			return false;
		}
		
		return true;
	}
}
