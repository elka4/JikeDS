package com._12atguigu.java.jikexueyuan.pizzastore;

import com._12atguigu.java.jikexueyuan.pizzastore.pizza.PepperPizza;
import com._12atguigu.java.jikexueyuan.pizzastore.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com._12atguigu.java.jikexueyuan.pizzastore.pizza.CheesePizza;
import com._12atguigu.java.jikexueyuan.pizzastore.pizza.ChinesePizza;
import com._12atguigu.java.jikexueyuan.pizzastore.pizza.GreekPizza;

public class OrderPizza {

	public OrderPizza() {
		Pizza pizza = null;
		String ordertype;

		do {
			ordertype = gettype();

			if (ordertype.equals("cheese")) {
				pizza = new CheesePizza();
			} else if (ordertype.equals("greek")) {
				pizza = new GreekPizza();
			} else if (ordertype.equals("pepper")) {
				pizza = new PepperPizza();
			} else if (ordertype.equals("chinese")) {
				pizza = new ChinesePizza();
			} else {
				break;
			}
			pizza.prepare();
			pizza.bake();
			pizza.cut();
			pizza.box();
		} while (true);
	}

	private String gettype() {
		try {
			BufferedReader strin = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.println("input pizza type:");
			String str = strin.readLine();

			return str;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

}