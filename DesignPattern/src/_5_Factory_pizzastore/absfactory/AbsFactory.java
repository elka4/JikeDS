package _5_Factory_pizzastore.absfactory;

import _5_Factory_pizzastore.pizza.Pizza;

public interface AbsFactory {
	public Pizza CreatePizza(String ordertype) ;
}
