package com.java.jikexueyuan.templatemode;

public abstract class HotDrink {

	public abstract void prepareRecipe();

	public void boilWater() {
		System.out.println("Boiling water");
	}

	public void pourInCup() {
		System.out.println("Pouring into cup");
	}

}
