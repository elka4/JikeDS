package DesignPattern.pizzastore.absfactory;

import DesignPattern.pizzastore.pizza.LDCheesePizza;
import DesignPattern.pizzastore.pizza.LDPepperPizza;
import DesignPattern.pizzastore.pizza.Pizza;

public class LDFactory implements AbsFactory {

	@Override
	public Pizza CreatePizza(String ordertype) {
		Pizza pizza = null;

		if (ordertype.equals("cheese")) {
			pizza = new LDCheesePizza();
		} else if (ordertype.equals("pepper")) {
			pizza = new LDPepperPizza();
		}
		return pizza;

	}

}
