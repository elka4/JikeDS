package com._12atguigu.java.jikexueyuan.pizzastore.absfactory;

import com._12atguigu.java.jikexueyuan.pizzastore.pizza.Pizza;

public interface AbsFactory {
	public Pizza CreatePizza(String ordertype) ;
}
