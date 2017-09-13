package _5_Factory_pizzastore.method;

import _5_Factory_pizzastore.pizza.LDCheesePizza;
import _5_Factory_pizzastore.pizza.LDPepperPizza;
import _5_Factory_pizzastore.pizza.Pizza;

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
