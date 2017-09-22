package _5_Factory_pizzastore.absfactory;

import _5_Factory_pizzastore.pizza.NYCheesePizza;
import _5_Factory_pizzastore.pizza.NYPepperPizza;
import _5_Factory_pizzastore.pizza.Pizza;

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