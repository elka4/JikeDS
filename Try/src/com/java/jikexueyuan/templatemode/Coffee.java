package com.java.jikexueyuan.templatemode;

public class Coffee {

	public void prepareRecipe() {
		boilWater();		
		brewCoffee();
		pourInCup();
		addSugarMilk();
	}

	public void boilWater() {
		System.out.println("Boiling water");
	}

	public void brewCoffee() {
		System.out.println("Brewing Coffee ");
	}

	public void pourInCup() {
		System.out.println("Pouring into cup");
	}

	public void addSugarMilk() {
		System.out.println("Adding sugar and milk");
	}

}
