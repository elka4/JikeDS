package pizzastore.method;

import pizzastore.pizza.NYCheesePizza;
import pizzastore.pizza.NYPepperPizza;
import pizzastore.pizza.Pizza;

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
