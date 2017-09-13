package _5_Factory_pizzastore.simplefactory;

import _5_Factory_pizzastore.pizza.CheesePizza;
import _5_Factory_pizzastore.pizza.GreekPizza;
import _5_Factory_pizzastore.pizza.PepperPizza;
import _5_Factory_pizzastore.pizza.Pizza;

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
