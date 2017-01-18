package com.java.jikexueyuan.templatemode.hotdrink;

public abstract class HotDrinkTemplate {


	public final void prepareRecipe() {
		boilWater();		
		brew();
		pourInCup();
		if(wantCondimentsHook())
		{
			addCondiments();
		}else{
			System.out.println("No Condiments");
		}
		
	}
	public boolean wantCondimentsHook()
	{
		return true;
	}
	public final void boilWater() {
		System.out.println("Boiling water");
	}

	public abstract void brew();

	public  final void pourInCup() {
		System.out.println("Pouring into cup");
	}

	public abstract void addCondiments();



}
