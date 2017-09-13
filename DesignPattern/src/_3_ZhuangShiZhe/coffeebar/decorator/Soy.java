package _3_ZhuangShiZhe.coffeebar.decorator;

import _3_ZhuangShiZhe.coffeebar.Drink;

public class Soy extends Decorator {

	public Soy(Drink Obj) {
		super(Obj);
		// TODO Auto-generated constructor stub
		super.setDescription("Soy");
		super.setPrice(1.5f);
	}

}

