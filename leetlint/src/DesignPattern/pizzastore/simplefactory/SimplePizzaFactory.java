package DesignPattern.pizzastore.simplefactory;

import DesignPattern.pizzastore.pizza.CheesePizza;
import DesignPattern.pizzastore.pizza.GreekPizza;
import DesignPattern.pizzastore.pizza.PepperPizza;
import DesignPattern.pizzastore.pizza.Pizza;

public class SimplePizzaFactory {

	public Pizza CreatePizza(String ordertype) {
		Pizza pizza = null;

		if (ordertype.equals("cheese")) {
			pizza = new CheesePizza();
		} else if (ordertype.equals("greek")) {
			pizza = new GreekPizza();
		} else if (ordertype.equals("pepper")) {
			pizza = new PepperPizza();
		}
		return pizza;

	}

}
