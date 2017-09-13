package _7_ShiPeiQi_adaptermode;

import _7_ShiPeiQi_adaptermode.adapter.TurkeyAdapter2;
import _7_ShiPeiQi_adaptermode.duck.Duck;
import _7_ShiPeiQi_adaptermode.duck.GreenHeadDuck;
import _7_ShiPeiQi_adaptermode.turkey.WildTurkey;


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
