package com._12atguigu.java.jikexueyuan.pizzastore.method;

import com._12atguigu.java.jikexueyuan.pizzastore.pizza.LDCheesePizza;
import com._12atguigu.java.jikexueyuan.pizzastore.pizza.LDPepperPizza;
import com._12atguigu.java.jikexueyuan.pizzastore.pizza.Pizza;

public class LDOrderPizza extends OrderPizza {

	@Override
	Pizza createPizza(String ordertype) {
		Pizza pizza = null;

		if (ordertype.equals("cheese")) {
			pizza = new LDCheesePizza();
		} else if (ordertype.equals("pepper")) {
			pizza = new LDPepperPizza();
		}
		return pizza;

	}

}
