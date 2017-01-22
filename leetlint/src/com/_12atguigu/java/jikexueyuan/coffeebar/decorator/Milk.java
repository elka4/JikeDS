package com._12atguigu.java.jikexueyuan.coffeebar.decorator;

import com._12atguigu.java.jikexueyuan.coffeebar.Drink;

public class Milk extends Decorator {

	public Milk(Drink Obj) {		
		super(Obj);
		// TODO Auto-generated constructor stub
		super.setDescription("Milk");
		super.setPrice(2.0f);
	}

}

