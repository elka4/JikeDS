package DesignPattern.adaptermode;

import DesignPattern.adaptermode.adapter.TurkeyAdapter2;
import DesignPattern.adaptermode.duck.Duck;
import DesignPattern.adaptermode.duck.GreenHeadDuck;
import DesignPattern.adaptermode.turkey.WildTurkey;


public class MainTest {
	public static void main(String[] args) {
		GreenHeadDuck duck=new GreenHeadDuck();
		
		WildTurkey turkey=new WildTurkey();
		
		Duck duck2turkeyAdapter=new TurkeyAdapter2();
		turkey.gobble();
		turkey.fly();
		duck.quack();
		duck.fly();
		duck2turkeyAdapter.quack();
		duck2turkeyAdapter.fly();
		
	
	}
}
