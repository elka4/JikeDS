package com._12atguigu.java.jikexueyuan.pizzastore.absfactory;

import com._12atguigu.java.jikexueyuan.pizzastore.pizza.NYCheesePizza;
import com._12atguigu.java.jikexueyuan.pizzastore.pizza.NYPepperPizza;
import com._12atguigu.java.jikexueyuan.pizzastore.pizza.Pizza;

public class NYFactory implements AbsFactory {

	
	@Override
	public Pizza CreatePizza(String ordertype) {
		Pizza pizza = null;

		if (ordertype.equals("cheese")) {
			pizza = new NYCheesePizza();
		} else if (ordertype.equals("pepper")) {
			pizza = new NYPepperPizza();
		}
		return pizza;

	}

}
