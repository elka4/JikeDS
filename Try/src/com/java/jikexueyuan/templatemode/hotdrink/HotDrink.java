package com.java.jikexueyuan.templatemode.hotdrink;

public abstract class HotDrink {

	public final void prepareRecipe() {
		boilWater();		
		brew();
		pourInCup();
		addCondiments();
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
