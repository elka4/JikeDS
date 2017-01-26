package DesignPattern.pizzastore.absfactory;

import DesignPattern.pizzastore.pizza.Pizza;

public interface AbsFactory {
	public Pizza CreatePizza(String ordertype) ;
}
