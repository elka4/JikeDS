package DesignPattern.pizzastore.method;

import DesignPattern.pizzastore.pizza.NYCheesePizza;
import DesignPattern.pizzastore.pizza.NYPepperPizza;
import DesignPattern.pizzastore.pizza.Pizza;

public class NYOrderPizza extends OrderPizza {

	@Override
	Pizza createPizza(String ordertype) {
		Pizza pizza = null;

		if (ordertype.equals("cheese")) {
			pizza = new NYCheesePizza();
		} else if (ordertype.equals("pepper")) {
			pizza = new NYPepperPizza();
		}
		return pizza;

	}

}
