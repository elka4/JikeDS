package adaptermode;

import adaptermode.adapter.TurkeyAdapter2;
import adaptermode.duck.Duck;
import adaptermode.duck.GreenHeadDuck;
import adaptermode.turkey.WildTurkey;


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
