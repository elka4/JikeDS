package _5_Factory_pizzastore.absfactory;

import _5_Factory_pizzastore.pizza.LDCheesePizza;
import _5_Factory_pizzastore.pizza.LDPepperPizza;
import _5_Factory_pizzastore.pizza.Pizza;

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
